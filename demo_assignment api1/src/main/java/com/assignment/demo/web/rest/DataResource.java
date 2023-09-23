package com.assignment.demo.web.rest;

import com.assignment.demo.service.DataService;
import com.assignment.demo.service.dto.PostDataPayloadDTO;
import com.assignment.demo.service.dto.TodosDataPayloadDTO;
import com.assignment.demo.service.dto.UserDataPayloadDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataResource {

    private final DataService dataService;

    public DataResource(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDataPayloadDTO> getDatas(@PathVariable Long userId) throws URISyntaxException, IOException {

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<UserDataPayloadDTO> entityModel
                = new ResponseEntity<>(dataService.getUserDataById(userId), headers,
                HttpStatus.CREATED);

        return entityModel;
    }
//    public ResponseEntity<List<TodosDataPayloadDTO>> getDatas(@PathVariable Long userId) throws URISyntaxException {
//
//        HttpHeaders headers = new HttpHeaders();
//        ResponseEntity<List<TodosDataPayloadDTO>> entityModel
//                = new ResponseEntity<>(dataService.getTodosDataByUserId(userId), headers,
//                HttpStatus.CREATED);
//
//        return entityModel;
//    }
}
