package com.plg.javafonctionalprogramming.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plg.javafonctionalprogramming.enumeration.Gender;
import com.plg.javafonctionalprogramming.models.Person;
import com.plg.javafonctionalprogramming.repository.PersonRepository;

@Service
public class PersonService {
  @Autowired
  private PersonRepository personRepository;

  @Transactional
  public List<Person> saveAll(List<Person> poeple) {
    return this.personRepository.saveAll(poeple);
  }

  @Transactional
  public Page<Person> findByAll(int page, int size) {
    Sort fnameSort = Sort.by(Direction.DESC, "fname");
    Sort lnameSort = Sort.by(Direction.DESC, "lname");

    Sort groupBySort = fnameSort.and(lnameSort);

    return this.personRepository.findAll(PageRequest.of(page, size, groupBySort));
  }

  @Transactional
  public Map<String, List<Person>> findAllGender() {
    Map<String, List<Person>> mapPerson = new HashMap<>();
    List<Person> poeple = this.personRepository.findAll();
    List<Person> females = poeple.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
        .collect(Collectors.toList());
    List<Person> males = poeple.stream().filter(person -> person.getGender().equals(Gender.MALE))
        .collect(Collectors.toList());
    mapPerson.put("females", females);
    mapPerson.put("males", males);
    return mapPerson;
  }

  @Transactional
  public Map<String, List<Person>> sortByAge() {
    Map<String, List<Person>> mapPerson = new HashMap<>();
    List<Person> poeple = this.personRepository.findAll();
    List<Person> sortByAge = poeple.stream()
        .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender))
        .collect(Collectors.toList());
    List<Person> sortByAgereverse = poeple.stream()
        .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
        .collect(Collectors.toList());
    mapPerson.put("sortByAge", sortByAge);
    mapPerson.put("sortByAgereverse", sortByAgereverse);
    return mapPerson;
  }

  @Transactional
  public Map<String, Person> oldestAndYoungest() {
    Map<String, Person> mapPerson = new HashMap<>();
    List<Person> poeple = this.personRepository.findAll();
    Optional<Person> oldest = poeple.stream()
        .max(Comparator.comparing(Person::getAge));
    Optional<Person> youngest = poeple.stream()
        .min(Comparator.comparing(Person::getAge));
    mapPerson.put("oldest", oldest.get());
    mapPerson.put("youngest", youngest.get());
    return mapPerson;
  }

  @Transactional
  public Map<String, List<Person>> oldestAndYoungests() {
    Map<String, List<Person>> mapPerson = new HashMap<>();
    List<Person> poeple = this.personRepository.findAll();
    Map<String, Person> oldestAndYoungest = this.oldestAndYoungest();
    Person oldest = oldestAndYoungest.get("oldest");
    List<Person> oldests = poeple.stream().filter(person -> person.getAge() == oldest.getAge())
        .collect(Collectors.toList());
    Person youngest = oldestAndYoungest.get("youngest");
    List<Person> youngests = poeple.stream().filter(person -> person.getAge() == youngest.getAge())
        .collect(Collectors.toList());
    mapPerson.put("oldests", oldests);
    mapPerson.put("youngests", youngests);
    return mapPerson;
  }

  public Map<String, Page<Person>> oldestAndYoungests(int page, int size) {
    Map<String, Page<Person>> mapPerson = new HashMap<>();
    Sort fnameSort = Sort.by(Direction.DESC, "fname");
    Sort lnameSort = Sort.by(Direction.DESC, "lname");

    Sort groupBySort = fnameSort.and(lnameSort);
    PageRequest pr = PageRequest.of(page, size, groupBySort);
    Page<Person> youngests = this.personRepository.findYoungests(pr);
    Page<Person> oldests = this.personRepository.findOldests(pr);
    mapPerson.put("oldests", oldests);
    mapPerson.put("youngests", youngests);
    return mapPerson;
  }

  public Page<Person> getByYearOfBirth(int year, int page, int size) {
    Sort fnameSort = Sort.by(Direction.DESC, "fname");
    Sort lnameSort = Sort.by(Direction.DESC, "lname");

    Sort groupBySort = fnameSort.and(lnameSort);
    PageRequest pr = PageRequest.of(page, size, groupBySort);
    return this.personRepository.findByYearOfBirth(year, pr);
  }
}
