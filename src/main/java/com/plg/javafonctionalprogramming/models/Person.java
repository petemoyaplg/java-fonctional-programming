package com.plg.javafonctionalprogramming.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.plg.javafonctionalprogramming.enumeration.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "person")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID id;
  private String fname;
  private String lname;
  private String email;
  private String phoneNumber;
  private String address;
  private String country;
  private int age;
  private Gender gender;
  private LocalDate dateOfBirth;

  public Person(String fname, String lname, Gender gender, LocalDate dateOfBirth, String email, String phoneNumber,
      String address, String country) {
    this.fname = fname;
    this.lname = lname;
    this.gender = gender;
    this.email = email;
    this.address = address;
    this.country = country;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = dateOfBirth;
    this.age = (int) ChronoUnit.DAYS.between(dateOfBirth, LocalDate.now()) / 365;
  }

  // @Override
  // public String toString() {
  // return "Persone{name = " + this.fname + " " + this.lname + ", age = " +
  // this.age + ", gender = " + this.gender
  // + ", dateOfBirth = "
  // + this.dateOfBirth + "}";
  // }
}
