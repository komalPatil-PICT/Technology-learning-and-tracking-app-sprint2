package com.capgemini.tlta.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.capgemini.tlta.model.RegisterUser;


@Repository
public interface LoginRepository extends JpaRepository<RegisterUser, Integer> {
}