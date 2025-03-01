package exercise1;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private List<Student> students;

    public Session() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (students.size() < 20) {
            students.add(student);
        }
    }

    public void printAverageQuizScores() {
        for (Student student : students) {
            System.out.println(student.getName() + " - Average Quiz Score: " + student.getAverageQuizScore());
        }
    }

    public void printSortedQuizScores() {
        for (Student student : students) {
            System.out.println(student.getName() + "'s Quiz Scores: " + student.getSortedQuizScores());
        }
    }

    public void printPartTimeStudents() {
        for (Student student : students) {
            if (student instanceof PartTimeStudent) {
                System.out.println(student.getName());
            }
        }
    }

    public void printFullTimeStudentsExamScores() {
        for (Student student : students) {
            if (student instanceof FullTimeStudent) {
                ((FullTimeStudent) student).printExamScores();
            }
        }
    }
}
