package familytree.view;

import familytree.model.Person;
import java.util.List;
import java.util.Scanner;

public class ConsoleFamilyTreeView implements FamilyTreeView {
    private Scanner scanner;

    public ConsoleFamilyTreeView() {
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    @Override
    public void displayMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1. Добавить человека");
        System.out.println("2. Найти человека");
        System.out.println("3. Показать всех членов семьи");
        System.out.println("4. Сортировать по имени");
        System.out.println("5. Сортировать по дате рождения");
        System.out.println("6. Сохранить дерево в файл");
        System.out.println("7. Загрузить дерево из файла");
        System.out.println("0. Выйти");
    }

    @Override
    public void displayExtendedMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1. Добавить человека");
        System.out.println("2. Найти человека");
        System.out.println("3. Показать всех членов семьи");
        System.out.println("4. Сортировать по имени");
        System.out.println("5. Сортировать по дате рождения");
        System.out.println("6. Сохранить дерево в файл");
        System.out.println("7. Загрузить дерево из файла");
        System.out.println("8. Добавить отношение родитель-ребенок");
        System.out.println("9. Показать детей");
        System.out.println("10. Показать родителей");
        System.out.println("11. Показать братьев/сестер");
        System.out.println("12. Показать предков");
        System.out.println("13. Показать потомков");
        System.out.println("14. Показать количество поколений");
        System.out.println("0. Выйти");
    }

    @Override
    public int getMenuChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayPerson(Person person) {
        System.out.println(person);
    }

    @Override
    public void displayPersonList(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }
}