
/*By Connor Ludwig and Kendell Prather
CSCI 451 HW2
*/
package globalalignment;

import java.io.*;
import java.util.Scanner;

public class Driver {
    static String seq1;
    static String seq2;
    public int alignmentScore;
    public Element[][] matrix;
    static int match,misMatch,indel;
    
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Using the Neddleman-Wunsch algorithm to find maxium alignment scores by Connor Ludwig and Kendell Prather");
        String string1 = null;
        String string2 = null;
        int score[] = new int[3];
        //reads in scores from command line
        score[0] = 0;
        score[1] = 1;
        score[2] = 1;
        System.out.println("Theta = "+ score[0]);
        System.out.println("Alpha = "+ score[1]);
        System.out.println("Beta = "+ score[2]);
        String file = "test.FASTA"; //reads in file name from command line
        boolean first = true;
        int count = 0;
        try (Scanner sc = new Scanner(new File(file))) { //reads in FASTA file
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.charAt(0) == '>') { //recognizes delimiter used FASTA file
                    if (first)
                        first = false;
                    else
                        System.out.println();
                }
                else {
                    //sets values for string 1 and string 2, only set up to take in two strings
                    if(count==0){
                        string1 = line;
                        System.out.println("Sequence 1: " + string1);
                        count++;
                    }
                    else{
                        string2 = line;
                        System.out.println("Sequence 2: " + string2);
                    } 
                }
            }
        }
        
        //method calls
        GlobalAlignment gb = new GlobalAlignment(string1,string2,score);
        gb.setAlignment();
        System.out.println("Alignment Score: " + gb.alignmentScore);
    }
    
}

