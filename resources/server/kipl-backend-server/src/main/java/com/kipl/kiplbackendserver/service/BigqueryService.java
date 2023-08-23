package com.kipl.kiplbackendserver.service;

import com.google.cloud.bigquery.*;
import com.kipl.kiplbackendserver.DTO.FoodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class BigqueryService {
    private final BigQuery bigQuery;

    @Autowired
    public BigqueryService(
            BigQuery bigQuery
    ) {
        this.bigQuery = bigQuery;
    }

    private TableResult bigQueryExecute(String query) throws Exception {
        QueryJobConfiguration queryJobConfiguration =
                QueryJobConfiguration.newBuilder(query).
                        setUseLegacySql(false)
                        .build();

        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job job = bigQuery.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());

        job = job.waitFor();

        if (job == null) {
            throw new RuntimeException("query does not exist");
        } else {
            if (job.getStatus().getError() != null) {
                String errorMessage =
                        job.getStatus().getError().getMessage() + "\n"
                                + job.getStatus().getError().getReason() + "\n"
                                + job.getStatus().getError().toString();
                throw new RuntimeException(errorMessage);
            } else {
                TableResult tableResult = job.getQueryResults();
                return tableResult;
            }
        }
    }

    public List<FoodDTO> getAllFood() throws Exception {
        String query = "SELECT * FROM `kipl_bigquery_database.food`";
        TableResult result = bigQueryExecute(query);
        return FoodDTO.fromTableResult(result);
    }

    public List<String> getFoodNames() throws Exception{
        TableResult result = bigQueryExecute("SELECT food_name FROM `kipl_bigquery_database.food`");
        List<String> foodNames = new LinkedList<>();

        for (FieldValueList row : result.iterateAll()) {
            foodNames.add(row.get("food_name").toString());
        }

        return foodNames;
    }
}
