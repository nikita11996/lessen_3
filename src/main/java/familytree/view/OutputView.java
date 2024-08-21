package familytree.view;

import familytree.model.Person;
import java.util.List;

public interface OutputView {
    void displayMenu();
    void displayMessage(String message);
    void displayPerson(Person person);
    void displayPersonList(List<Person> people);
}