package familytree.service;

import familytree.model.FamilyTreeOperations;
import java.io.IOException;

public class FamilyTreeFileManager {
    private IFileOperations fileOps;

    public FamilyTreeFileManager(IFileOperations fileOps) {
        this.fileOps = fileOps;
    }

    public void saveToFile(String fileName, FamilyTreeOperations<?> familyTree) throws IOException {
        fileOps.saveToFile(fileName, familyTree);
    }

    public FamilyTreeOperations<?> loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        return fileOps.loadFromFile(fileName);
    }
}