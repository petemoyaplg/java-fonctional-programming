package com.plg.javafonctionalprogramming;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		for (int i = 0; i < 4009; i++) {
			// Faker faker = new Faker(new Locale("us-US"));
			Faker faker = new Faker(Locale.forLanguageTag("us-US"));
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String address = faker.address().streetAddress();
			String country = faker.address().country();
			String phoneNumber = faker.phoneNumber().cellPhone();
			double height = ThreadLocalRandom.current().nextDouble(1.45, 2.5);
			double weight = ThreadLocalRandom.current().nextDouble(45, 250);
			int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
			String[] extentions = { "@gmail.com", "@rawsur.com", "@vodacom.com", "@airtel.com", "@rawbank.com" };
			StringBuilder email = new StringBuilder(firstName.toLowerCase()).append(faker.bothify("##"))
					.append(extentions[randomNum]);
			long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
			long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
			long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
			LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

			randomNum = ThreadLocalRandom.current().nextInt(1, 3);
			Gender gender = Gender.values()[randomNum - 1];

			Person person = new Person(firstName, lastName, gender, randomDate, email.toString(), phoneNumber, address,
					country, height, weight);
			poeple.add(person);
		}
		return poeple;
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// List<Person> people = this.getPeople();
		// log.info("Get Poeple");
		// this.personService.saveAll(people);
		log.info("Serveur run");
	}

	// private void test() {
	// List<Person> poeple = getPeople();

	// // All match
	// boolean allMatch = poeple.stream().allMatch(person -> person.getAge() > 8);
	// System.out.println("Declarative approach/All match");
	// System.out.println("allMatch = " + allMatch);
	// System.out.println("===========================================================");
	// // Any match
	// boolean anyMatch = poeple.stream().anyMatch(person -> person.getAge() > 8);
	// System.out.println("Declarative approach/Any match");
	// System.out.println("anyMatch = " + anyMatch);
	// System.out.println("===========================================================");
	// // None match
	// boolean noneMatch = poeple.stream().noneMatch(person ->
	// person.getFname().equals("Antonio"));
	// System.out.println("Declarative approach/None match");
	// System.out.println("noneMatch = " + noneMatch);
	// System.out.println("===========================================================");
	// // Group
	// Map<Gender, List<Person>> groupByGender =
	// poeple.stream().collect(Collectors.groupingBy(Person::getGender));
	// groupByGender.forEach((gender, persons) -> {
	// System.out.println(gender);
	// persons.forEach(System.out::println);
	// System.out.println("");
	// });

	// System.out.println("===========================================================");
	// Optional<String> oldestFemaleAge = poeple.stream().filter(person ->
	// person.getGender().equals(Gender.FEMALE))
	// .max(Comparator.comparing(Person::getAge))
	// .map(Person::getFname);
	// oldestFemaleAge.ifPresent(System.out::println);
	// }

}
