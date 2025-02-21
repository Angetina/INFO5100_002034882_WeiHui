import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Student {
    protected String name;
    protected List<Integer> quizzes;

    public Student(String name) {
        this.name = name;
        this.quizzes = new ArrayList<>();
    }

    public void addQuizScore(int score) {
        if (quizzes.size() < 15) {
            quizzes.add(score);
        }
    }

    public double getAverageQuizScore() {
        return quizzes.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public List<Integer> getSortedQuizScores() {
        List<Integer> sortedScores = new ArrayList<>(quizzes);
        Collections.sort(sortedScores);
        return sortedScores;
    }

    public String getName() {
        return name;
    }

    public abstract void printDetails();
}
