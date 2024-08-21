package familytree.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class Person implements FamilyMember<Person>, Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Person father;
    private Person mother;
    private List<Person> children;
    private String gender;
    private LocalDate dateOfBirth;

    public Person(String name, String gender, LocalDate dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.children = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Person getFather() {
        return father;
    }

    @Override
    public void setFather(Person father) {
        this.father = father;
    }

    @Override
    public Person getMother() {
        return mother;
    }

    @Override
    public void setMother(Person mother) {
        this.mother = mother;
    }

    @Override
    public List<Person> getChildren() {
        return children;
    }

    @Override
    public void addChild(Person child) {
        this.children.add(child);
    }

    public String getGender() {
        return gender;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return new String(String.format("%s (Пол: %s, Дата рождения: %s)", name, gender, dateOfBirth).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }
}