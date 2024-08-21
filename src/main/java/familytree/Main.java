package familytree;

import familytree.model.FamilyTree;
import familytree.model.FamilyTreeOperations;
import familytree.model.Person;
import familytree.view.ConsoleFamilyTreeView;
import familytree.view.FamilyTreeView;
import familytree.presenter.FamilyTreePresenter;
import familytree.service.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("console.encoding", "UTF-8");

        FamilyTreeOperations<Person> familyTree = new FamilyTree<>();
        PersonFactory personFactory = new PersonFactory();
        FamilyTreeService service = new FamilyTreeService(familyTree, personFactory);
        FamilyTreeView view = new ConsoleFamilyTreeView();
        FileOperations fileOps = new FileOperations();
        FamilyTreeFileManager fileManager = new FamilyTreeFileManager(fileOps);

        FamilyTreePresenter presenter = new FamilyTreePresenter(service, view, fileManager);

        presenter.run();
    }
}