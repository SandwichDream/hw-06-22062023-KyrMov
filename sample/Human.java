package sample;

import java.util.Objects;

public class Human {
    private String name;
    private String lastName;
    private Gender gender;
    private int age;

    public Human(String name, String lastName, Gender gender, int age) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    public Human() {

    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, gender, age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Human human = (Human) obj;
        return Objects.equals(name, human.name) && Objects.equals(lastName, human.lastName) && gender == human.gender
                && age == human.age;
    }

    @Override
    public String toString() {
        return "Human [name=" + name + ", lastName=" + lastName + ", gender=" + gender + "]";
    }
}