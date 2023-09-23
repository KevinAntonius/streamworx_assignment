package com.assignment.demo.service.dto;

import lombok.ToString;

@ToString
public class TodosDataPayloadDTO {
    public Long id;
    public String title;
    public Boolean completed;
}
