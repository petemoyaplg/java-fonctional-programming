package com.plg.javafonctionalprogramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.plg.javafonctionalprogramming.enumeration.Gender;
import com.plg.javafonctionalprogramming.models.Person;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class JavaFonctionalProgrammingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavaFonctionalProgrammingApplication.class, args);
	}

	private List<Person> getPeople() {
		return List.of(
				new Person("James Bond", 20, Gender.MALE),
				new Person("Alina Smith", 33, Gender.FEMALE),
				new Person("Helene White", 57, Gender.FEMALE),
				new Person("Alex Boze", 14, Gender.MALE),
				new Person("Jamie Goa", 99, Gender.MALE),
				new Person("Anna Cook", 7, Gender.FEMALE),
				new Person("Zelda Brown", 120, Gender.FEMALE));
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Serveur run");
		List<Person> poeple = getPeople();
		// Imperative approach
		List<Person> females = new ArrayList<>();
		for (Person person : poeple) {
			if (person.getGender().equals(Gender.FEMALE)) {
				females.add(person);
			}
		}
		System.out.println("===========================================================");
		System.out.println("Imperative approach");
		females.forEach(System.out::println);
		System.out.println("===========================================================");
		// Declarative approach

		// Filter
		females = poeple.stream()
				.filter(person -> person.getGender().equals(Gender.FEMALE))
				.collect(Collectors.toList());
		System.out.println("Declarative approach/Filter");
		females.forEach(System.out::println);
		System.out.println("===========================================================");
		// Sort
		females = poeple.stream()
				.sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender))
				.collect(Collectors.toList());
		System.out.println("Declarative approach/Sort");
		females.forEach(System.out::println);
		System.out.println("===========================================================");
		// Sort reverse
		females = poeple.stream()
				.sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
				.collect(Collectors.toList());
		System.out.println("Declarative approach/Sort reverse");
		females.forEach(System.out::println);
		System.out.println("===========================================================");
		// All match
		boolean allMatch = poeple.stream().allMatch(person -> person.getAge() > 8);
		System.out.println("Declarative approach/All match");
		System.out.println("allMatch = " + allMatch);
		System.out.println("===========================================================");
		// Any match
		boolean anyMatch = poeple.stream().anyMatch(person -> person.getAge() > 8);
		System.out.println("Declarative approach/Any match");
		System.out.println("anyMatch = " + anyMatch);
		System.out.println("===========================================================");
		// None match
		boolean noneMatch = poeple.stream().noneMatch(person -> person.getName().equals("Antonio"));
		System.out.println("Declarative approach/None match");
		System.out.println("noneMatch = " + noneMatch);
		System.out.println("===========================================================");
		// Max
		Optional<Person> max = poeple.stream()
				.max(Comparator.comparing(Person::getAge));
		// .ifPresent(System.out::printn);
		System.out.println("Declarative approach/Max match");
		System.out.println("max = " + max);
		System.out.println("===========================================================");
		// Min
		Optional<Person> min = poeple.stream()
				.min(Comparator.comparing(Person::getAge));
		// .ifPresent(System.out::printn);
		System.out.println("Declarative approach/Max match");
		System.out.println("min = " + min);
		System.out.println("===========================================================");
		// Group
		Map<Gender, List<Person>> groupByGender = poeple.stream().collect(Collectors.groupingBy(Person::getGender));
		groupByGender.forEach((gender, persons) -> {
			System.out.println(gender);
			persons.forEach(System.out::println);
			System.out.println("");
		});

		System.out.println("===========================================================");
		Optional<String> oldestFemaleAge = poeple.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
				.max(Comparator.comparing(Person::getAge))
				.map(Person::getName);
		oldestFemaleAge.ifPresent(System.out::println);
	}

}
