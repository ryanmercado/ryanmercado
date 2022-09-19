import java.util.Scanner;

/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data.
 * Ryan Mercado
 * rdm3649
 */
public class Driver {
    public static void main(String[] args) throws InterruptedException {
        SecretCodeGenerator secret = SecretCodeGenerator.getInstance();
        boolean gameState = true;
        String input;
        long delay = 1;
        Scanner scan = new Scanner(System.in);
        Game guesses[] = new Game[GameConfiguration.guessNumber];


        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nWelcome to Mastermind.  Here are the rules. \n");
        Thread.sleep(delay);
        System.out.println("This is a text version of the classic board game Mastermind.\n");
        Thread.sleep(delay);
        System.out.println("The  computer  will  think  of  a  secret  code.  The  code  consists  of  4 \n" +
                "colored  pegs.  The  pegs  MUST  be  one  of  six  colors:  blue,  green, \n" +
                "orange, purple, red, or yellow. A color may appear more than once in \n" +
                "the  code.  You  try  to  guess  what  colored  pegs  are  in  the  code  and \n" +
                "what  order  they  are  in.  After  you  make  a  valid  guess  the  result \n" +
                "(feedback) will be displayed.\n");
        Thread.sleep(delay * 5);
        System.out.println("The  result  consists  of  a  black  peg  for  each  peg  you  have  guessed \n" +
                "exactly correct (color and position) in your guess.  For each peg in \n" +
                "the guess that is the correct color, but is out of position, you get \n" +
                "a  white  peg.  For  each  peg,  which  is  fully  incorrect,  you  get  no \n" +
                "feedback.  \n");
        Thread.sleep(delay * 5);
        System.out.println("Only the first letter of the color is displayed. B for Blue, R for \n" +
                "Red, and so forth. When entering guesses you only need to enter the \n" +
                "first character of each color as a capital letter. \n");
        Thread.sleep(delay * 5);
        System.out.print("You  have " + GameConfiguration.guessNumber + "  guesses  to  figure  out  the  secret  code  or  you  lose  the \n" +
                "game.  Are you ready to play? (Y/N):");
        gameLoop:
        while (gameState) {
            input = scan.nextLine();
            while (!(input.equals("y") || input.equals("Y"))) {
                if (input.equals("n") || input.equals("N"))
                    return;
                input = scan.nextLine();
            }


            String code = secret.getNewSecretCode();
            char[] secretCode = Game.stringToArray(code);
            System.out.print("\n\nGenerating secret code ...(for this example the secret code is " + code + ")\n");

            boolean validGuess;
            int[] feedback;
            int count;
            bigLoop:
            for (int i = 0; i < GameConfiguration.guessNumber; i++) {
                validGuess = false;
                System.out.println("You have " + (GameConfiguration.guessNumber - i) + " guesses left. \n" +
                        "What is your next guess? \n" +
                        "Type in the characters for your guess and press enter. \n" +
                        "Enter guess:");

                do {
                    input = scan.nextLine();
                    if (input.length() != GameConfiguration.pegNumber) {
                        System.out.println("Invalid Syntax, re-enter your guess and press enter.");
                        continue;
                    }
                    validGuess = true;

                    for (int j = 0; j < input.length(); j++) {
                        count = 0;
                        for (int k = 0; k < GameConfiguration.colors.length; k++) {
                            if (!(Character.toString(Character.toUpperCase(input.charAt(j))).equals(GameConfiguration.colors[k]))) {
                                count++;
                            }
                        }
                        if (count == GameConfiguration.colors.length) {
                            validGuess = false;
                            break;
                        }
                    }
                    if (!validGuess)
                        System.out.println("Invalid Syntax, re-enter your guess and press enter.");
                } while (!validGuess);
                guesses[i] = new Game(input);
                feedback = guesses[i].checkCorrect(secretCode);//feedback[0] is black peg, [1] is white peg, [2] is empty peg
                if (feedback[0] == GameConfiguration.pegNumber) {
                    System.out.println("\n" + code + "Result: " + feedback[0] + "B_" + feedback[1] + "W - You Win!\n\n");
                    Thread.sleep(delay*4);
                    System.out.print("Are you ready for another game (Y/N):");
                    continue gameLoop;
                }
                System.out.println("\n" + "Result: " + feedback[0] + "B_" + feedback[1] + "W\n\n");

            }
            System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.\n");
            System.out.print("Are you ready for another game (Y/N):");
        }
    }
}
