import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class hangman {
    static int lives = 6;
    static String wordToGuess;
    static Scanner sc = new Scanner(System.in);
    static boolean gameOver = false;
    static char[] correctChars;
    static int correctCharsCount = 0;
    static char[] wrongGuessedChars = new char[6];
    static int wrongGuessedCharsCount = 0;
    static Pattern whitelist = Pattern.compile("[a-zA-Z]");

    // Main method
    public static void main(String[] args) {
        while (!gameOver) {
            System.out.println("Welcome to Hangman!");
            generateWord();
            gameLogic();
            System.out.println("Do you want to play again? (y/n)");
            char answer = Character.toLowerCase(sc.next().charAt(0));
            if (answer == 'n') {
                gameOver = true;
                System.out.println("Thanks for playing!");
            } else {
                System.out.print("\033\143");
                gameOver = false;
                lives = 6;
                correctCharsCount = 0;
                wrongGuessedCharsCount = 0;
                wrongGuessedChars = new char[wrongGuessedChars.length];
            }
        }
    }

    // Main game logic
    static void gameLogic() {
        printHangman(lives);
        characterToShow();
        char guess;
        while (true && lives > 0 && !gameOver) {
            // input goes here
            guess = input();
            // end of input
            System.out.print("\033\143");
            if (wordToGuess.toLowerCase().contains(guess + "")) {
                System.out.println("Correct!");
                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.toLowerCase().charAt(i) == guess && correctChars[i] != guess) {
                        correctChars[i] = guess;
                        correctCharsCount++;
                    }
                }
            } else {
                System.out.println("Incorrect!");
                wrongGuessedChars[wrongGuessedCharsCount] = guess;
                wrongGuessedCharsCount++;
                lives--;
            }
            printHangman(lives);
            characterToShow();
            if (correctCharsCount == wordToGuess.length()) {
                System.out.print("\033\143");
                System.out.println("/////////");
                System.out.println("|  YOU  |");
                System.out.println("|  WON  |");
                System.out.println("/////////");
                gameOver = true;
            } else if (lives == 0) {
                System.out.print("\033\143");
                System.out.println("/////////");
                System.out.println("|  YOU  |");
                System.out.println("|  LOSE |");
                System.out.println("/////////");
                System.out.println("The word is: " + wordToGuess);
                gameOver = true;
            }
        }

    }
    /* Takes input from user 
     * checks if the caharacter is valid and if it has already been guessed.
     * otherwise it returns the character.
     */
    static char input() {
        System.out.println("Guess a letter: ");
        char guess = Character.toLowerCase(sc.next().charAt(0));
        if (whitelist.matcher(guess + "").matches()) {
            if ((new String(wrongGuessedChars)).contains(guess + "") || (new String(correctChars)).contains(guess + "")) {
                System.out.println("You already guessed that letter!");
                return input();
            }
            else{
                return guess;
            }
        }
        System.out.println("Invalid input!");
        return input();
    }

    // Shows the characters that are correct and the ones that are unknown
    static void characterToShow() {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.toLowerCase().charAt(i) == correctChars[i]) {
                System.out.print(correctChars[i]);
            } else {
                System.out.print("_");
            }
        }
        System.out.println("\n");
        System.out.println("Wrong guesses: " + Arrays.toString(wrongGuessedChars));
    }

    // generate a random word
    static void generateWord() {
        String[] wordlist = { "Test", "Word", "Hello", "World", "Java", "Coding", "Programming", "Computer", "Program",
                "Hangman", "Tiger", "Cat", "Orange", "Pink", "Green" };
        wordToGuess = wordlist[(int) (Math.random() * wordlist.length)];
        correctChars = new char[wordToGuess.length()];
    }

    // prints the hangman
    static void printHangman(int state) {
        switch (state) {
            case 6:
                System.out.println(" _________");
                System.out.println("|         |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|_________");
                break;
            case 5:
                System.out.println(" _________");
                System.out.println("|         |");
                System.out.println("|         0");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|_________");
                break;
            case 4:
                System.out.println(" _________");
                System.out.println("|         |");
                System.out.println("|         0");
                System.out.println("|         |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|_________");
                break;
            case 3:
                System.out.println(" _________");
                System.out.println("|         |");
                System.out.println("|         0");
                System.out.println("|        /|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|_________");
                break;
            case 2:
                System.out.println(" _________");
                System.out.println("|         |");
                System.out.println("|         0");
                System.out.println("|        /|\\");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|_________");
                break;
            case 1:
                System.out.println(" _________");
                System.out.println("|         |");
                System.out.println("|         0");
                System.out.println("|        /|\\");
                System.out.println("|        /");
                System.out.println("|");
                System.out.println("|_________");
                break;
            case 0:
                System.out.println(" _________");
                System.out.println("|         |");
                System.out.println("|         0");
                System.out.println("|        /|\\");
                System.out.println("|        / \\");
                System.out.println("|");
                System.out.println("|_________");
                break;
        }
        System.out.println("");
    }
}
