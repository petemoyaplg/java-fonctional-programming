package com.plg.javafonctionalprogramming.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plg.javafonctionalprogramming.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

}
