package com.example.demo.repository;

import com.example.demo.model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


@Transactional
public interface TokenRepository extends JpaRepository<TokenModel, Integer> {

}
