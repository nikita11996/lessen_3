package familytree.service;

import familytree.model.FamilyTree;
import familytree.model.Person;
import familytree.model.FamilyTreeOperations;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FamilyTreeService {
    private FamilyTreeOperations<Person> familyTree;
    private PersonFactory personFactory;

    public FamilyTreeService(FamilyTreeOperations<Person> familyTree, PersonFactory personFactory) {
        this.familyTree = familyTree;
        this.personFactory = personFactory;
    }

    public void addPerson(String name, String gender, String birthDateInput) {
        Person person = personFactory.createPerson(name, gender, birthDateInput);
        familyTree.addMember(person);
    }

    public Person findPerson(String name) {
        return familyTree.findPerson(name);
    }

    public List<Person> getAllMembers() {
        return familyTree.getMembers();
    }

    public void sortByName() {
        familyTree.sortByName();
    }

    public void sortByBirthDate() {
        familyTree.sortByBirthDate();
    }

    public void addParentChild(String parentName, String childName) {
        Person parent = familyTree.findPerson(parentName);
        Person child = familyTree.findPerson(childName);
        if (parent != null && child != null) {
            parent.addChild(child);
            if (parent.getGender().equals("Мужской")) {
                child.setFather(parent);
            } else {
                child.setMother(parent);
            }
        }
    }

    public List<Person> getChildren(String name) {
        Person person = familyTree.findPerson(name);
        return person != null ? person.getChildren() : null;
    }

    public Person[] getParents(String name) {
        Person person = familyTree.findPerson(name);
        if (person != null) {
            return new Person[]{person.getFather(), person.getMother()};
        }
        return null;
    }

    public List<Person> getSiblings(String name) {
        Person person = familyTree.findPerson(name);
        if (person != null) {
            Person father = person.getFather();
            Person mother = person.getMother();
            if (father != null) {
                return father.getChildren().stream()
                        .filter(child -> !child.equals(person))
                        .collect(Collectors.toList());
            } else if (mother != null) {
                return mother.getChildren().stream()
                        .filter(child -> !child.equals(person))
                        .collect(Collectors.toList());
            }
        }
        return null;
    }

    public List<Person> getAncestors(String name) {
        Person person = familyTree.findPerson(name);
        List<Person> ancestors = new ArrayList<>();
        if (person != null) {
            addAncestors(person, ancestors);
        }
        return ancestors;
    }

    private void addAncestors(Person person, List<Person> ancestors) {
        if (person.getFather() != null) {
            ancestors.add(person.getFather());
            addAncestors(person.getFather(), ancestors);
        }
        if (person.getMother() != null) {
            ancestors.add(person.getMother());
            addAncestors(person.getMother(), ancestors);
        }
    }

    public List<Person> getDescendants(String name) {
        Person person = familyTree.findPerson(name);
        List<Person> descendants = new ArrayList<>();
        if (person != null) {
            addDescendants(person, descendants);
        }
        return descendants;
    }

    private void addDescendants(Person person, List<Person> descendants) {
        for (Person child : person.getChildren()) {
            descendants.add(child);
            addDescendants(child, descendants);
        }
    }

    public int getGenerationCount() {
        int maxGeneration = 0;
        for (Person person : familyTree.getMembers()) {
            int generation = getPersonGeneration(person);
            if (generation > maxGeneration) {
                maxGeneration = generation;
            }
        }
        return maxGeneration;
    }

    private int getPersonGeneration(Person person) {
        int generation = 0;
        Person current = person;
        while (current.getFather() != null || current.getMother() != null) {
            generation++;
            current = current.getFather() != null ? current.getFather() : current.getMother();
        }
        return generation;
    }

    public FamilyTreeOperations<Person> getFamilyTree() {
        return familyTree;
    }

    public void setFamilyTree(FamilyTreeOperations<Person> familyTree) {
        this.familyTree = familyTree;
    }
}