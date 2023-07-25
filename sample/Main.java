package sample;

import java.io.File;
import java.io.IOException;

import sample.exceptions.GroupOverflowException;
import sample.exceptions.StudentNotFoundException;
import sample.exceptions.SameIdException;

public class Main {

    public static void main(String[] args) {

        File folderIn = new File("sample/FolderA");

        File folderOut = new File("sample/FolderB");
        folderOut.mkdirs();

        try {
            System.out.println(FileService.copyFolder(folderIn, folderOut, 1_000_000L, ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Group groupA = new Group("GroupA");

            for (int i = 0; i < 5; i++) {
                groupA.addStudent(new Student("Nick#" + i, "Last#" + i, Gender.MALE, i + 15, i + 1,
                        groupA.getGroupName()));
            }

            groupA.addStudent(new Student("Luna", "Crime", Gender.FEMALE, 17, 6, groupA.getGroupName()));
            groupA.addStudent(new Student("Jane", "Smith", Gender.FEMALE, 18, 7, groupA.getGroupName()));
            groupA.addStudent(new Student("Steve", "Adams", Gender.MALE, 18, 8, groupA.getGroupName()));
            groupA.addStudent(new Student("July", "Rich", Gender.FEMALE, 17, 9, groupA.getGroupName()));
            groupA.addStudent(new Student("Steve", "Adams", Gender.MALE, 18, 10, groupA.getGroupName()));
            // group.addStudent(new Student("John", "Doe", Gender.MALE, 11,
            // group.getGroupName()));

            System.out.println(groupA);

            System.out.println();

            System.out.println("Found Student: " + groupA.searchStudentByLastName("Smith"));

            System.out.println();

            if (groupA.removeStudentByID(5)) {
                System.out.println("Student has been removed");
            }

            System.out.println();

            System.out.println(groupA);

            System.out.println(System.lineSeparator() + "After sorting by last name:");
            groupA.sortStudentsByLastName();

            System.out.println(groupA);

            // System.out.println();

            // System.out.println("Add student");
            // HelloStudent ws = new HelloStudent();
            // ws.writeStudent(group);

            System.out.println();

            groupA.sortStudentsByLastName();
            System.out.println(groupA);

            System.out.println();

            GroupFileStorage gfs = new GroupFileStorage();
            gfs.saveGroupToCSV(groupA);

            File file = new File("sample/workFolder/GroupA.csv");

            Group groupB = gfs.loadGroupFromCSV(file);
            groupB.setGroupName("GroupB");

            System.out.println(groupB);

            System.out.println();

            File folder = new File("sample/workFolder");
            Group groupE = gfs.loadGroupFromCSV(gfs.findFileByGroupName("GroupE", folder));
            System.out.println(groupE);

            System.out.println();

            System.out.println(groupA.getStudents()[0].equals(groupA.getStudents()[1]));
            System.out.println(groupA.getStudents()[0].hashCode());
            System.out.println(groupA.getStudents()[1].hashCode());

            System.out.println();

            System.out.println(groupA.getStudents()[8]);
            System.out.println(groupB.getStudents()[8]);

            System.out.println(groupA.equals(groupB)); // !!!!!!!!! Проблема !!!!!!!!!!

            System.out.println();

            System.out.println(groupA.hasSameStudents());

        } catch (GroupOverflowException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SameIdException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
