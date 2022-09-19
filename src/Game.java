import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* EE422C Assignment #2 submission by
 * Replace <...> with your actual data.
 * Ryan Mercado
 * rdm3649
 */
public class Game {
    private char pegs[]=new char[GameConfiguration.pegNumber];

    public Game(){
        for(int i=0;i<GameConfiguration.pegNumber;i++)
            pegs[i]=' ';
    }
    public Game(String guess){
        for(int i=0; i<GameConfiguration.pegNumber;i++)
            pegs[i]=Character.toUpperCase(guess.charAt(i));
    }

    public char[] getPegs() {
        return pegs;
    }

    public int[] checkCorrect(char[] secretGuess){
        int feedback[] = new int[3]; //feedback[0] is black peg, [1] is white peg, [2] is empty peg
        List<Character> dynamicGuess =new ArrayList<Character>();
        List<Character> dynamicSecret = new ArrayList<>();
        for(char c: secretGuess)
            dynamicSecret.add(c);
        for (char c: pegs)
            dynamicGuess.add(c);

    for(int i=0;i<dynamicGuess.size();i++)
    {
        if(dynamicSecret.indexOf(dynamicGuess.get(i))==-1)
        {
            feedback[2]++;
        }
        else if(dynamicSecret.indexOf(dynamicGuess.get(i))==i){//Only looks at first instance inside guess, needs a method that checks for any
            feedback[0]++;
            dynamicSecret.set(dynamicSecret.indexOf(dynamicGuess.get(i)), ' ');
        }
    }
    for(int i=0;i<dynamicGuess.size();i++)
    {
        if(!(dynamicSecret.indexOf(dynamicGuess.get(i))==-1)&&!(dynamicSecret.get(i).equals(' '))) {
            feedback[1]++;
            dynamicSecret.set(dynamicSecret.indexOf(dynamicGuess.get(i)), ' ');
        }
    }



//        for(int i=0;i<secretGuess.length;i++)
//        {
//            if(secretGuess[i]==pegs[i])
//            {
//                feedback[0]++;
//                dynamicSecret.remove(i);
//            }
//            else
//            {
//                nonBlacks.add(secretGuess[i]);
//            }
//        }
//        for(int c=0;c<nonBlacks.size();c++)
//            for(int d=0;d<dynamicSecret.size();d++){
//                if(nonBlacks.get(c).equals(dynamicSecret.get(d))) {
//                    feedback[1]++;
//                    dynamicSecret.remove(d);
//                    nonBlacks.remove(c);
//                }
//            }
        feedback[2]=GameConfiguration.pegNumber-(feedback[0]+feedback[1]);
        if(feedback[2]<0)
            System.out.println("Uge problem with checkCorreckt");
        return feedback;
    }

    public static char[] stringToArray(String guess){
        if(guess.length()!=GameConfiguration.pegNumber)
            System.out.println("Incorrect Guess Length");
        char guessArr[] = new char[GameConfiguration.pegNumber];
        for(int i=0;i<GameConfiguration.pegNumber;i++)
            guessArr[i]=Character.toUpperCase(guess.charAt(i));
        return guessArr;
    }

    public void printString() {
        for(int i=0;i<pegs.length;i++){
            System.out.print(pegs[i]);
        }
    }
}
