import java.util.Random;
import java.util.Scanner;

public class RandomNumber {
    private Scanner sc = new Scanner(System.in);
    private int score = 0;
    private int roundsWon = 0;
    private int round = 0;

    public void guessNumber(int min, int max, int rn) {
        int lives = 5;
        boolean guessedCorrectly = false;
        System.out.println("YOU ONLY HAVE 5 LIVES");

        while (lives > 0 && !guessedCorrectly) {
            System.out.println("Guess the number: ");
            int gn = sc.nextInt();

            if (gn == rn) {
                System.out.println("You guessed right!");
                System.out.println("YOU WON WITH " + lives + " LIVES REMAINING");
                score += lives; // Increase score based on remaining lives
                roundsWon++;
                guessedCorrectly = true;
            } else if (gn < rn) {
                System.out.println("Guess the number between " + gn + " and " + max + ".");
                min = gn;
            } else {
                System.out.println("Guess the number between " + min + " and " + gn + ".");
                max = gn;
            }
            lives--;

            if (lives == 0 && !guessedCorrectly) {
                System.out.println("You are out of lives. The correct number was " + rn + ".");
            }
        }
    }

    public void playGame() {
        boolean playAgain = true;

        while (playAgain) {
            round = round+1;
            System.out.println("Round "+round+ " Begins!");
            System.out.println("Enter the lower bound: ");
            int min = sc.nextInt();
            System.out.println("Enter the upper bound: ");
            int max = sc.nextInt();

            // Generate random number within the specified range
            Random random = new Random();
            int randomNumber = random.nextInt(max - min + 1) + min;

            // Start the guessing game
            guessNumber(min, max, randomNumber);

            System.out.println("Do you want to play another round? (yes/no)");
            String response = sc.next();

            if (!response.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        // Display final score
        System.out.println("You played " + roundsWon + " rounds and your final score is: " + score);
    }

    public static void main(String[] args) {
        RandomNumber randomNumberGame = new RandomNumber();
        randomNumberGame.playGame();
    }
}
