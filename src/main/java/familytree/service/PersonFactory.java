package familytree.service;

import familytree.model.Person;
import java.time.LocalDate;

public class PersonFactory {
    public Person createPerson(String name, String gender, String birthDateInput) {
        String normalizedGender = gender.trim().equalsIgnoreCase("М") ? "Мужской" : "Женский";
        LocalDate birthDate = LocalDate.parse(birthDateInput);
        return new Person(name, normalizedGender, birthDate);
    }
}