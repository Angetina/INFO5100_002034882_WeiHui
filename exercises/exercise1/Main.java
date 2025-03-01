package exercise1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Session session = new Session();
        Random random = new Random();

        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                FullTimeStudent fullTimeStudent = new FullTimeStudent("Student" + i);
                fullTimeStudent.setExamScores(random.nextInt(101), random.nextInt(101));
                for (int j = 0; j < 15; j++) {
                    fullTimeStudent.addQuizScore(random.nextInt(101));
                }
                session.addStudent(fullTimeStudent);
            } else {
                PartTimeStudent partTimeStudent = new PartTimeStudent("Student" + i);
                for (int j = 0; j < 15; j++) {
                    partTimeStudent.addQuizScore(random.nextInt(101));
                }
                session.addStudent(partTimeStudent);
            }
        }

        session.printAverageQuizScores();
        session.printSortedQuizScores();
        session.printPartTimeStudents();
        session.printFullTimeStudentsExamScores();
    }
}
