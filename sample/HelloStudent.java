package sample;

import java.util.Scanner;

import sample.exceptions.GroupOverflowException;
import sample.exceptions.SameIdException;

public class HelloStudent {

    Scanner scanner = new Scanner(System.in);

    public HelloStudent() {

    }

    public void writeStudent(Group group) throws GroupOverflowException, SameIdException {

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Gender (Write MALE or FEMALE): ");
        Gender gender = Gender.valueOf(scanner.nextLine());
        System.out.print("Age: ");
        int age = scanner.nextInt();
        System.out.print("ID: ");
        int id = scanner.nextInt();

        group.addStudent(new Student(name, lastName, gender, age, id, group.getGroupName()));

    }

}
