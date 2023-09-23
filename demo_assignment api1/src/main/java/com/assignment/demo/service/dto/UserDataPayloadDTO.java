package com.assignment.demo.service.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@Data
@RedisHash("User")
public class UserDataPayloadDTO implements Serializable {

    @Id
    @Indexed
    public String id;
    public String name;
    public String username;
    public String email;
    public AddressDTO address;
    public String phone;
    public String website;
    public CompanyDTO company;
    public List<TodosDataPayloadDTO> todos;
    public List<PostDataPayloadDTO> posts;
}
