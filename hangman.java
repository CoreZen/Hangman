import java.util.Scanner;

public class hangman 
{
    public static int lives = 6;
    public static String wordToGuess;
    public static Scanner sc = new Scanner(System.in);
    public static boolean gameOver = false;
    public static char [] correctChars;
    public static int correctCharsCount=0;
    
    //Main method
    public static void main(String[] args) 
    {
        while(!gameOver)
        {
            System.out.println("Welcome to Hangman!");
            generateWord();
            gameLogic();
            System.out.println("Do you want to play again? (y/n)");
            char answer = sc.next().charAt(0);
            if(answer == 'n')
            {
                gameOver = true;
                System.out.println("Thanks for playing!");
            }else{
                System.out.print("\033\143");
                gameOver = false;
                lives = 6;
                correctCharsCount = 0;
            }
        }
    }
    //Main game logic
    public static void gameLogic()
    {
        printHangman(lives);
        characterToShow();
        char guess;
        while(true && lives > 0 && !gameOver)
        {
            System.out.print("Guess a letter: ");
            guess=sc.next().charAt(0);
            System.out.print("\033\143");
            if(wordToGuess.toLowerCase().contains(guess+""))
            {
                    System.out.println("Correct!");
                    for(int i=0;i<wordToGuess.length();i++)
                    {
                        if(wordToGuess.toLowerCase().charAt(i)==guess && correctChars[i]!=guess)
                        {
                            correctChars[i]=guess;
                            correctCharsCount++;
                        }
                    }
            }
            else
            {
                System.out.println("Incorrect!");
                lives--;
            }
            printHangman(lives);
            characterToShow();
        }

    }
    // Shows the characters that are correct and the ones that are unknown
    public static void characterToShow()
    {
        for(int i=0;i<wordToGuess.length();i++)
        {
            if(wordToGuess.toLowerCase().charAt(i)==correctChars[i])
            {
                System.out.print(correctChars[i]);
            }
            else
            {
                System.out.print("_");
            }
        }
        System.out.println("\n");
     
        if(correctCharsCount==wordToGuess.length())
        {
            System.out.println("You won!");
            gameOver=true;
        }
        else if(lives==0)
        {
            System.out.println("You lost! The word is: " + wordToGuess);
            gameOver=true;
        }
    }

    // generate a random word
    public static void generateWord()
    {
     String [] wordlist = {"Test","Word","Hello","World","Java","Coding","Programming","Computer","Program","Hangman", "Tiger", "Cat", "Orange", "Pink", "Green"};
     wordToGuess=wordlist[(int) (Math.random() * wordlist.length)];
     correctChars= new char[wordToGuess.length()];
    }
   // prints the hangman 
    public static void printHangman(int state)
    {
        switch(state){
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
