package com.example.travelboard.repository;

import com.example.travelboard.entity.Accept;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptRepository extends CrudRepository<Accept, Long> {
}
