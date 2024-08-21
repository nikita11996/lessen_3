package familytree.presenter;

import familytree.model.FamilyTreeOperations;
import familytree.model.Person;
import familytree.view.FamilyTreeView;
import familytree.service.FamilyTreeService;
import familytree.service.FamilyTreeFileManager;

import java.util.List;

public class FamilyTreePresenter {
    private FamilyTreeService service;
    private FamilyTreeView view;
    private FamilyTreeFileManager fileManager;

    public FamilyTreePresenter(FamilyTreeService service, FamilyTreeView view, FamilyTreeFileManager fileManager) {
        this.service = service;
        this.view = view;
        this.fileManager = fileManager;
    }

    public void run() {
        while (true) {
            view.displayExtendedMenu();
            int choice = view.getMenuChoice();

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    findPerson();
                    break;
                case 3:
                    showAllMembers();
                    break;
                case 4:
                    service.sortByName();
                    view.displayMessage("Отсортировано по имени");
                    break;
                case 5:
                    service.sortByBirthDate();
                    view.displayMessage("Отсортировано по дате рождения");
                    break;
                case 6:
                    saveToFile();
                    break;
                case 7:
                    loadFromFile();
                    break;
                case 8:
                    addParentChild();
                    break;
                case 9:
                    showChildren();
                    break;
                case 10:
                    showParents();
                    break;
                case 11:
                    showSiblings();
                    break;
                case 12:
                    showAncestors();
                    break;
                case 13:
                    showDescendants();
                    break;
                case 14:
                    showGenerationCount();
                    break;
                case 0:
                    return;
                default:
                    view.displayMessage("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void addPerson() {
        String name = view.getInput("Введите имя: ");
        String gender = view.getInput("Введите пол (М/Ж): ");
        String birthDateInput = view.getInput("Введите дату рождения (ГГГГ-ММ-ДД): ");
        
        service.addPerson(name, gender, birthDateInput);
        view.displayMessage("Человек добавлен в семейное дерево.");
    }

    private void findPerson() {
        String name = view.getInput("Введите имя для поиска: ");
        Person person = service.findPerson(name);
        if (person != null) {
            view.displayMessage("Найдено:");
            view.displayPerson(person);
        } else {
            view.displayMessage("Человек не найден.");
        }
    }

    private void showAllMembers() {
        view.displayMessage("Все члены семьи:");
        view.displayPersonList(service.getAllMembers());
    }

    private void saveToFile() {
        String fileName = view.getInput("Введите имя файла для сохранения: ");
        try {
            fileManager.saveToFile(fileName, service.getFamilyTree());
            view.displayMessage("Семейное дерево сохранено в файл.");
        } catch (Exception e) {
            view.displayMessage("Ошибка при сохранении: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        String fileName = view.getInput("Введите имя файла для загрузки: ");
        try {
            FamilyTreeOperations<Person> loadedTree = (FamilyTreeOperations<Person>) fileManager.loadFromFile(fileName);
            service.setFamilyTree(loadedTree);
            view.displayMessage("Семейное дерево загружено из файла.");
        } catch (Exception e) {
            view.displayMessage("Ошибка при загрузке: " + e.getMessage());
        }
    }

    private void addParentChild() {
        String parentName = view.getInput("Введите имя родителя: ");
        String childName = view.getInput("Введите имя ребенка: ");
        service.addParentChild(parentName, childName);
        view.displayMessage("Отношение родитель-ребенок добавлено.");
    }

    private void showChildren() {
        String name = view.getInput("Введите имя человека: ");
        List<Person> children = service.getChildren(name);
        if (children != null && !children.isEmpty()) {
            view.displayMessage("Дети:");
            view.displayPersonList(children);
        } else {
            view.displayMessage("У этого человека нет детей или человек не найден.");
        }
    }

    private void showParents() {
        String name = view.getInput("Введите имя человека: ");
        Person[] parents = service.getParents(name);
        if (parents != null) {
            view.displayMessage("Родители:");
            if (parents[0] != null) view.displayPerson(parents[0]);
            if (parents[1] != null) view.displayPerson(parents[1]);
        } else {
            view.displayMessage("У этого человека нет родителей или человек не найден.");
        }
    }

    private void showSiblings() {
        String name = view.getInput("Введите имя человека: ");
        List<Person> siblings = service.getSiblings(name);
        if (siblings != null && !siblings.isEmpty()) {
            view.displayMessage("Братья/сестры:");
            view.displayPersonList(siblings);
        } else {
            view.displayMessage("У этого человека нет братьев/сестер или человек не найден.");
        }
    }

    private void showAncestors() {
        String name = view.getInput("Введите имя человека: ");
        List<Person> ancestors = service.getAncestors(name);
        if (!ancestors.isEmpty()) {
            view.displayMessage("Предки:");
            view.displayPersonList(ancestors);
        } else {
            view.displayMessage("У этого человека нет предков или человек не найден.");
        }
    }

    private void showDescendants() {
        String name = view.getInput("Введите имя человека: ");
        List<Person> descendants = service.getDescendants(name);
        if (!descendants.isEmpty()) {
            view.displayMessage("Потомки:");
            view.displayPersonList(descendants);
        } else {
            view.displayMessage("У этого человека нет потомков или человек не найден.");
        }
    }

    private void showGenerationCount() {
        int count = service.getGenerationCount();
        view.displayMessage("Количество поколений в семейном дереве: " + count);
    }
}