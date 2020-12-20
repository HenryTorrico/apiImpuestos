package com.example.demo.repository;

import com.example.demo.model.CufdModel;
import com.example.demo.model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CufdRepository extends JpaRepository<CufdModel, Integer> {
}
