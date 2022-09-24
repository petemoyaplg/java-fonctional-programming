package com.plg.javafonctionalprogramming.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.plg.javafonctionalprogramming.models.Person;
import com.plg.javafonctionalprogramming.services.PersonService;

@Controller
@RequestMapping("person")
public class PersonController {
  @Autowired
  private PersonService personService;

  /**
   * @return
   */
  @GetMapping("v1/list")
  public ResponseEntity<List<Person>> getAll() {
    List<Person> poeple = this.personService.findByAll();
    return ResponseEntity.ok().body(poeple);
  }

  /**
   * @return
   */
  @GetMapping("v1/list-by-gender")
  public ResponseEntity<Map<String, List<Person>>> getAllByGender() {
    Map<String, List<Person>> mapPerson = this.personService.findAllGender();
    return ResponseEntity.ok().body(mapPerson);
  }

  /**
   * @return
   */
  @GetMapping("v1/list-sort-by-gender")
  public ResponseEntity<Map<String, List<Person>>> sortByAge() {
    Map<String, List<Person>> mapPerson = this.personService.sortByAge();
    return ResponseEntity.ok().body(mapPerson);
  }

  // the oldest and the youngest
  /**
   * @return
   */
  @GetMapping("v1/oldest-youngest")
  public ResponseEntity<Map<String, Person>> oldestAndYoungest() {
    Map<String, Person> mapPerson = this.personService.oldestAndYoungest();
    return ResponseEntity.ok().body(mapPerson);
  }

  /**
   * @return
   */
  @GetMapping("v1/oldests-youngests")
  public ResponseEntity<Map<String, List<Person>>> oldestAndYoungests() {
    Map<String, List<Person>> mapPerson = this.personService.oldestAndYoungests();
    return ResponseEntity.ok().body(mapPerson);
  }
}
