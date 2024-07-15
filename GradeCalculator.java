import java.util.Scanner;

public class GradeCalculator {
    private Scanner sc = new Scanner(System.in);
    private String studentName;
    private int numSubjects;
    private int[] marks;
    private String[] subjects;

    public void inputStudentDetails() {
        System.out.println("Enter student name: ");
        studentName = sc.nextLine();
        System.out.println("Enter number of subjects: ");
        numSubjects = sc.nextInt();
        marks = new int[numSubjects];
        subjects = new String[numSubjects];

        sc.nextLine();  

        for (int i = 0; i < numSubjects; i++) {
            System.out.println("Enter subject " + (i + 1) + " name: ");
            subjects[i] = sc.nextLine();
            System.out.println("Enter marks obtained in " + subjects[i] + ": ");
            marks[i] = sc.nextInt();
            sc.nextLine();  
        }
    }

    public void calculateGrades() {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        double averagePercentage = (double) totalMarks / numSubjects;
        String grade;

        if (averagePercentage >= 80 && averagePercentage <= 100) {
            grade = "O";
        } else if (averagePercentage >= 70) {
            grade = "A+";
        } else if (averagePercentage >= 60) {
            grade = "A";
        } else if (averagePercentage >= 55) {
            grade = "B+";
        } else if (averagePercentage >= 50) {
            grade = "B";
        } else if (averagePercentage >= 45) {
            grade = "C";
        } else {
            grade = "F";
        }

        displayResults(totalMarks, averagePercentage, grade);
    }

    private void displayResults(int totalMarks, double averagePercentage, String grade) {
        System.out.println("\nStudent Details:");
        System.out.println("Name: " + studentName);
        System.out.println("Subjects and Marks:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.println(subjects[i] + ": " + marks[i]);
        }
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage);
        System.out.println("Grade: " + grade);
    }

    public static void main(String[] args) {
        GradeCalculator calculator = new GradeCalculator();
        calculator.inputStudentDetails();
        calculator.calculateGrades();
    }
}
