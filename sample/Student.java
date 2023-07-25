package sample;

import java.util.Objects;

public class Student extends Human {
    private int id;
    private String groupName;

    public Student(String name, String lastName, Gender gender, int age, int id, String groupName) {
        super(name, lastName, gender, age);
        this.id = id;
        this.groupName = groupName;
    }

    public Student() {

    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, groupName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        Student student = (Student) obj;
        return Objects.equals(groupName, student.groupName);
        // return id == student.id && Objects.equals(groupName, student.groupName);
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", groupName=" + groupName + ", " + super.toString() + "]";
    }
}