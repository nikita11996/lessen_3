package familytree.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FamilyTree<T extends FamilyMember<T>> implements FamilyTreeOperations<T>, Serializable, Iterable<T> {
    private static final long serialVersionUID = 1L;
    private List<T> members;
    
    public FamilyTree() {
        this.members = new ArrayList<>();
    }

    @Override
    public void addMember(T member) {
        members.add(member);
    }

    @Override
    public List<T> getMembers() {
        return members;
    }

    @Override
    public T findPerson(String name) {
        for (T member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public void sortByName() {
        members.sort(Comparator.comparing(FamilyMember::getName));
    }

    @Override
    public void sortByBirthDate() {
        members.sort(Comparator.comparing(FamilyMember::getDateOfBirth));
    }

    @Override
    public Iterator<T> iterator() {
        return members.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T member : members) {
            sb.append(member).append("\n");
        }
        return sb.toString();
    }
}