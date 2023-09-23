package com.assignment.demo.web.rest;

import com.assignment.demo.service.PostgresqlDataService;
import com.assignment.demo.service.dto.PostgresqlDataPayloadDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/data", produces = "application/xml")
public class PostgresqlDataResource {

    private final PostgresqlDataService dataService;

    public PostgresqlDataResource(PostgresqlDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<PostgresqlDataPayloadDTO>> getDatas(@PathVariable Long userId) throws URISyntaxException {
        List<PostgresqlDataPayloadDTO> datas = dataService.getPostgresqlData(userId);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<List<PostgresqlDataPayloadDTO>> response
                = new ResponseEntity<>(datas, headers,
                HttpStatus.FOUND);
        return response;
    }
}
