public class FullTimeStudent extends Student {
    private int exam1;
    private int exam2;

    public FullTimeStudent(String name) {
        super(name);
    }

    public void setExamScores(int exam1, int exam2) {
        this.exam1 = exam1;
        this.exam2 = exam2;
    }

    public void printExamScores() {
        System.out.println(name + "'s Exam Scores: " + exam1 + ", " + exam2);
    }

    @Override
    public void printDetails() {
        System.out.println("Full-Time Student: " + name);
    }
}
