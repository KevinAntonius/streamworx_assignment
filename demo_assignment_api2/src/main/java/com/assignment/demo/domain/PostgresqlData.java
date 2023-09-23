package com.assignment.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "PostgresqlData")
@Table(name = PostgresqlData.TABLE_NAME)
public class PostgresqlData{
    public static final String TABLE_NAME = "assignmentpost";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;
}
