package com.assignment.demo.service;

import com.assignment.demo.domain.PostgresqlData;
import com.assignment.demo.repository.PostgresqlDataRepository;
import com.assignment.demo.service.dto.PostgresqlDataPayloadDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostgresqlDataService {

    private final PostgresqlDataRepository dataRepository;

    public PostgresqlDataService(PostgresqlDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<PostgresqlDataPayloadDTO> getPostgresqlData(Long userId){
        List<PostgresqlDataPayloadDTO> resultDTO = new ArrayList<PostgresqlDataPayloadDTO>() {
        };
        List<PostgresqlData> postgresqlDatas = dataRepository.findAllByUserId(userId);
        if (!postgresqlDatas.isEmpty()){
            for (PostgresqlData tempData : postgresqlDatas){
                PostgresqlDataPayloadDTO tempDTO = new PostgresqlDataPayloadDTO();
                tempDTO.id = tempData.getId();
                tempDTO.title = tempData.getTitle();
                tempDTO.body = tempData.getBody();
                resultDTO.add(tempDTO);
            }
            return resultDTO;
        }
        return null;
    }
}
