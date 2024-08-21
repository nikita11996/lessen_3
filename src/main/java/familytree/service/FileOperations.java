package familytree.service;

import familytree.model.FamilyTreeOperations;
import java.io.*;

public class FileOperations implements IFileOperations {
    @Override
    public void saveToFile(String fileName, FamilyTreeOperations<?> familyTree) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(fileName)))) {
            oos.writeObject(familyTree);
        }
    }

    @Override
    public FamilyTreeOperations<?> loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                    new FileInputStream(fileName)))) {
            return (FamilyTreeOperations<?>) ois.readObject();
        }
    }
}