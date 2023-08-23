package com.kipl.kiplbackendserver.config;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class FoodCustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(
            SharedSessionContractImplementor session,
            Object object
    ) {
        String sequenceName = "food_evaluation_sequence"; // 시퀀스 이름
        Long nextValue = (Long) session.createNativeQuery("SELECT next_value FROM kipl_database.sequence_table WHERE sequence_name = :name")
                .setParameter("name", sequenceName)
                .getSingleResult();

        session.createNativeQuery("UPDATE kipl_database.sequence_table SET next_value = next_value + 1 WHERE sequence_name = :name")
                .setParameter("name", sequenceName)
                .executeUpdate();

        return "food_evaluation_" + nextValue;
    }
}

