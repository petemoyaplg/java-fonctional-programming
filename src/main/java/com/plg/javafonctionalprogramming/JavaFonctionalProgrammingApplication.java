package com.plg.javafonctionalprogramming;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.plg.javafonctionalprogramming.enumeration.Gender;
import com.plg.javafonctionalprogramming.models.Person;
import com.plg.javafonctionalprogramming.services.PersonService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class JavaFonctionalProgrammingApplication implements CommandLineRunner {

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(JavaFonctionalProgrammingApplication.class, args);
	}

	private List<Person> getPeople() {
		List<Person> poeple = new ArrayList<>();
		for (int i = 0; i < 500; i++) {
			Faker faker = new Faker(new Locale("us-US"));
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String address = faker.address().streetAddress();
			String country = faker.address().country();
			String phoneNumber = faker.phoneNumber().cellPhone();
			String email = firstName.toLowerCase() + faker.bothify("##@gmail.com");
			long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
			long maxDay = LocalDate.of(2005, 12, 31).toEpochDay();
			long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
			LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

			int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
			Gender gender = Gender.values()[randomNum - 1];

			Person person = new Person(firstName, lastName, gender, randomDate, email, phoneNumber, address, country);
			poeple.add(person);
		}
		return poeple;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Serveur run");
		// List<Person> people = this.getPeople();
		// this.personService.saveAll(people);
	}

	private void test() {
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
		boolean noneMatch = poeple.stream().noneMatch(person -> person.getFname().equals("Antonio"));
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
				.map(Person::getFname);
		oldestFemaleAge.ifPresent(System.out::println);
	}

}
