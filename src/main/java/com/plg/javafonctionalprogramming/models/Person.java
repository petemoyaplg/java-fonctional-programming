package com.plg.javafonctionalprogramming.models;

import com.plg.javafonctionalprogramming.enumeration.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
  private String name;
  private int age;
  private Gender gender;

  @Override
  public String toString() {
    return "Persone{name = " + name + ", age = " + age + ", gender = " + gender + "}";
  }
}
