package familytree.model;

import java.time.LocalDate;
import java.util.List;

public interface FamilyMember<T> {
    String getName();
    LocalDate getDateOfBirth();
    T getFather();
    T getMother();
    void setFather(T father);
    void setMother(T mother);
    List<T> getChildren();
    void addChild(T child);
}