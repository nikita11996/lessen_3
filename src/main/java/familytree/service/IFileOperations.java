package familytree.service;

import familytree.model.FamilyTreeOperations;
import java.io.IOException;

public interface IFileOperations {
    void saveToFile(String fileName, FamilyTreeOperations<?> familyTree) throws IOException;
    FamilyTreeOperations<?> loadFromFile(String fileName) throws IOException, ClassNotFoundException;
}