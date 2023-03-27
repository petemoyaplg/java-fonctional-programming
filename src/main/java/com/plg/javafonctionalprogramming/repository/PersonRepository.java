package com.plg.javafonctionalprogramming.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.plg.javafonctionalprogramming.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

  final String FINDYONGESTS = "SELECT * FROM public.person WHERE age < 18";
  final String FINDOLDESTS = "SELECT * FROM public.person WHERE age >= 18";
  final String FIND_By_YEAR_OF_BIRTH = "SELECT * FROM public.person WHERE EXTRACT('Year' FROM date_of_birth) = :year";

  @Query(value = FINDYONGESTS, nativeQuery = true)
  Page<Person> findYoungests(PageRequest of);

  @Query(value = FINDOLDESTS, nativeQuery = true)
  Page<Person> findOldests(PageRequest of);

  @Query(value = FIND_By_YEAR_OF_BIRTH, nativeQuery = true)
  Page<Person> findByYearOfBirth(int year, PageRequest pr);

}
