package com.assignment.demo.repository;

import com.assignment.demo.domain.PostgresqlData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostgresqlDataRepository extends JpaRepository<PostgresqlData, Long> {
    List<PostgresqlData> findAllByUserId(Long userId);
}
