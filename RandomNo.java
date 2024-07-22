import java.util.Random;
import java.util.Scanner;

public class RandomNo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 10;
        int totalRounds = 3;
        int score = 0;

        for (int round = 1; round <= totalRounds; round++) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessed = false;
            System.out.println("Round " + round + " of " + totalRounds + ": Guess the number between 1 and 100");

            while (attempts < maxAttempts && !guessed) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Higher!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Lower!");
                } else {
                    System.out.println("Correct! You've guessed the number.");
                    guessed = true;
                    score += (maxAttempts - attempts + 1) * 10; // Points based on attempts
                }
            }

            if (!guessed) {
                System.out.println("You've used all attempts. The number was: " + numberToGuess);
            }

            System.out.println("Your score after round " + round + ": " + score);
        }

        System.out.println("Game Over! Your final score is: " + score);
        scanner.close();
    }
}

