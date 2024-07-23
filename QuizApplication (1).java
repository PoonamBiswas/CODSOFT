import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Quiz(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

public class QuizApplication {
    private static Quiz[] quizzes;
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static Timer timer = new Timer();
    private static Scanner sc = new Scanner(System.in);
    private static String playerName;

    public static void main(String[] args) {
        takePlayerDetails();
        setupQuiz();
        System.out.println("The quiz will start now. Answer each question by typing 1, 2, 3, or 4.");
        startQuiz();
    }

    private static void takePlayerDetails() {
        System.out.println("Enter your name: ");
        playerName = sc.nextLine();
    }

    private static void setupQuiz() {
        quizzes = new Quiz[] {
            new Quiz("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3),
            new Quiz("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 2),
            new Quiz("What is the largest ocean on Earth?", new String[]{"1. Atlantic Ocean", "2. Indian Ocean", "3. Arctic Ocean", "4. Pacific Ocean"}, 4),
            new Quiz("Who wrote 'Romeo and Juliet'?", new String[]{"1. William Shakespeare", "2. Charles Dickens", "3. J.K. Rowling", "4. Mark Twain"}, 1),
            new Quiz("What is the chemical symbol for water?", new String[]{"1. H2O", "2. CO2", "3. O2", "4. N2"}, 1)
        };
    }

    private static void startQuiz() {
        if (currentQuestionIndex < quizzes.length) {
            displayQuestion(quizzes[currentQuestionIndex]);
        } else {
            displayResult();
        }
    }

    private static void displayQuestion(Quiz quiz) {
        System.out.println(quiz.getQuestion());
        for (String option : quiz.getOptions()) {
            System.out.println(option);
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                nextQuestion();
            }
        };

        TimerTask fiveSecondsLeftTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("5 seconds left!");
            }
        };

        timer.schedule(task, 30000);  // 30 seconds for each question
        timer.schedule(fiveSecondsLeftTask, 25000); // Notify 5 seconds before time is up

        int answer = -1;
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 30000) {
            if (sc.hasNextInt()) {
                answer = sc.nextInt();
                break;
            }
        }

        timer.cancel(); // Cancel the timer if an answer is given within the time limit
        timer = new Timer(); // Re-initialize the timer for the next question

        if (answer == quiz.getCorrectAnswer()) {
            score++;
        }

        nextQuestion();
    }

    private static void nextQuestion() {
        currentQuestionIndex++;
        startQuiz();
    }

    private static void displayResult() {
        System.out.println("Quiz Over!");
        System.out.println("Player: " + playerName);
        System.out.println("Your score: " + score + "/" + quizzes.length);

        for (int i = 0; i < quizzes.length; i++) {
            System.out.println("Question: " + quizzes[i].getQuestion());
            System.out.println("Correct answer: " + quizzes[i].getOptions()[quizzes[i].getCorrectAnswer() - 1]);
        }
    }
}
