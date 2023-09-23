package com.assignment.demo.service;

import com.assignment.demo.repository.UserDataRepository;
import com.assignment.demo.service.dto.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class DataService {
    RestTemplate rest = new RestTemplate();
    private final UserDataRepository userDataRepository;
    private final RedisTemplate redisTemplate;

    public DataService(UserDataRepository userDataRepository, RedisTemplate redisTemplate) {
        this.userDataRepository = userDataRepository;
        this.redisTemplate = redisTemplate;
    }

    public List<PostDataPayloadDTO> getPostDataByUserId(Long userId){
        ResponseEntity<List<PostDataPayloadDTO>> response =
                rest.exchange("http://172.24.32.1:8080/api/data/get/"+userId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<PostDataPayloadDTO>>() {
                        });
        return response.getBody();
    }

    public List<TodosDataPayloadDTO> getTodosDataByUserId(Long userId){
        ResponseEntity<List<TodosDataPayloadDTO>> response =
                rest.exchange("https://jsonplaceholder.typicode.com/user/"+userId+"/todos",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<TodosDataPayloadDTO>>() {
                        });
        return response.getBody();
    }

    public UserDataPayloadDTO getUserDataById(Long userId) throws IOException {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        UserDataPayloadDTO result = new UserDataPayloadDTO();
        String jsonMainData = hashOps.get(Long.toString(userId), "main_data");
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String,Object> mainData = mapper.readValue(new ByteArrayInputStream(jsonMainData.getBytes("UTF-8")), typeRef);
        result.setId(Long.toString(userId));
        result.setName(mainData.get("name").toString());
        result.setName(mainData.get("username").toString());
        result.setName(mainData.get("email").toString());
        result.setName(mainData.get("phone").toString());
        result.setName(mainData.get("website").toString());

        String jsonCompany = hashOps.get(Long.toString(userId), "company");
        HashMap<String,Object> company = mapper.readValue(new ByteArrayInputStream(jsonCompany.getBytes("UTF-8")), typeRef);
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.name = company.get("name").toString();
        companyDTO.catchPhrase = company.get("catchPhrase").toString();
        companyDTO.bs = company.get("bs").toString();
        result.setCompany(companyDTO);

        String jsonAddress = hashOps.get(Long.toString(userId), "address");
        HashMap<String,Object> address = mapper.readValue(new ByteArrayInputStream(jsonAddress.getBytes("UTF-8")), typeRef);
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.street = address.get("street").toString();
        addressDTO.suite = address.get("suite").toString();
        addressDTO.city = address.get("city").toString();
        addressDTO.zipcode = address.get("zipcode").toString();

        GeoDTO geoDTO = new GeoDTO();
        String geoString = address.get("geo").toString();
        HashMap<String,Object> geo = mapper.readValue(new ByteArrayInputStream(jsonAddress.getBytes("UTF-8")), typeRef);
        geoDTO.lan = ((Map)address.get("geo")).get("lat").toString();
        geoDTO.lng = ((Map)address.get("geo")).get("lng").toString();
        addressDTO.geo = geoDTO;

        result.setAddress(addressDTO);

        result.setTodos(getTodosDataByUserId(userId));
        result.setPosts(getPostDataByUserId(userId));

        return result;
    }
}
