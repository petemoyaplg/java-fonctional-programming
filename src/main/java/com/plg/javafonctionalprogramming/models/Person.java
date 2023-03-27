package com.plg.javafonctionalprogramming.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import javax.persistence.Column;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String fname;
  @Column(nullable = false)
  private String lname;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String phoneNumber;
  @Column(nullable = false)
  private String address;
  @Column(nullable = false)
  private String country;
  @Column(nullable = false)
  private int age;
  @Column(nullable = false)
  private Gender gender;
  @Column(nullable = false)
  private double height;
  @Column(nullable = false)
  private double weight;
  @Column(nullable = false)
  private LocalDate dateOfBirth;

  public Person(String fname, String lname, Gender gender, LocalDate dateOfBirth, String email, String phoneNumber,
      String address, String country, double height, double weight) {
    this.fname = fname;
    this.lname = lname;
    this.gender = gender;
    this.email = email;
    this.address = address;
    this.country = country;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = dateOfBirth;
    this.age = (int) ChronoUnit.DAYS.between(dateOfBirth, LocalDate.now()) / 365;
    this.height = height;
    this.weight = weight;
  }

  // @Override
  // public String toString() {
  // return "Persone{name = " + this.fname + " " + this.lname + ", age = " +
  // this.age + ", gender = " + this.gender
  // + ", dateOfBirth = "
  // + this.dateOfBirth + "}";
  // }
}
