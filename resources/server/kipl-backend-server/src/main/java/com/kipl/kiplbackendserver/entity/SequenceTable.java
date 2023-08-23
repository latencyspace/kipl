package com.kipl.kiplbackendserver.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "sequence_table")
public class SequenceTable {
    @Id
    @Column(name = "sequence_name")
    private String sequenceName;

    @Column(name = "next_value")
    private Long nextValue = 1L;
}
