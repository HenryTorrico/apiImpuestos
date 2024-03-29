package com.example.demo.repository;

import com.example.demo.model.CuisModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CuisRepository extends JpaRepository<CuisModel, Integer> {
}
