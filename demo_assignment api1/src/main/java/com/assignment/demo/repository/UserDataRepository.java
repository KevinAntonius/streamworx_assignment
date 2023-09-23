package com.assignment.demo.repository;

import com.assignment.demo.service.dto.UserDataPayloadDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDataRepository extends CrudRepository<UserDataPayloadDTO, String> {
    public Optional<UserDataPayloadDTO> findOneById(String Id);
}
