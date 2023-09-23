package com.assignment.demo.service.dto;


import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

@ToString
public class PostgresqlDataPayloadDTO {
    @NotNull
    public Long id;

    public String title;

    public String body;
}
