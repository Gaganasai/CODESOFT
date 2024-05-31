import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber 
{
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to Guess the Number game!");

        while (playAgain) {
            int targetNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nNew round started! Guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");

            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
                int guess = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score += (MAX_ATTEMPTS - attempts) * 10;
                    guessedCorrectly = true;
                } else if (guess < targetNumber) {
                    System.out.println("Too low. Try again!");
                } else {
                    System.out.println("Too high. Try again!");
                }

                attempts++;
            }

            if (!guessedCorrectly) {
                System.out.println("Oops! You ran out of attempts. The number was " + targetNumber);
            }

            System.out.println("Your score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.nextLine();
            playAgain = playAgainResponse.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing Guess the Number!");
        scanner.close();
    }
}
