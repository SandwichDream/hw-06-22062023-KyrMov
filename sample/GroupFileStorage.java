package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import sample.exceptions.GroupOverflowException;
import sample.exceptions.SameIdException;

public class GroupFileStorage {

    public GroupFileStorage() {

    }

    public void saveGroupToCSV(Group gr) {
        String students = gr.getGroupName() + System.lineSeparator()
                + "Name,Last name,Gender,Age,ID";
        for (int i = 0; i < gr.getStudents().length; i++) {
            if (gr.getStudents()[i] != null) {
                students += System.lineSeparator() + gr.getStudents()[i].getName() + ","
                        + gr.getStudents()[i].getLastName() + ","
                        + gr.getStudents()[i].getGender() + ","
                        + gr.getStudents()[i].getAge() + ","
                        + gr.getStudents()[i].getId();
            }
        }

        try (PrintWriter pw = new PrintWriter("sample/workFolder/" + gr.getGroupName() + ".csv")) {
            pw.write(students);
        } catch (IOException e) {
            System.out.println("Error saving group to CSV file: " + e.getMessage());
        }
    }

    public Group loadGroupFromCSV(File file) throws GroupOverflowException, SameIdException {
        Group group = new Group();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length == 1) {
                    group.setGroupName(data[0]);
                    continue;
                } else if (data[2].equals("Gender")) {
                    continue;
                }

                String name = data[0];
                String lastName = data[1];
                Gender gender = Gender.valueOf(data[2]);
                int age = Integer.parseInt(data[3]);
                int id = Integer.parseInt(data[4]);

                Student student = new Student(name, lastName, gender, age, id, null);

                group.addStudent(student);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getPath());
        } catch (IOException e) {
            System.out.println("Error reading data from file: " + e.getMessage());
        }

        group.setGroupName(group.getGroupName());

        return group;
    }

    public File findFileByGroupName(String groupName, File workFolder) {
        File[] files = workFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().equals(groupName + ".csv")) {
                    return file;
                }
            }
        }
        return null;
    }
}