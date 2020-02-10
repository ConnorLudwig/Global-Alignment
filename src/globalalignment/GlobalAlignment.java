
package globalalignment;

public class GlobalAlignment {
    static String seq1; //store the two sequences
    static String seq2;
    public int alignmentScore; //will hold final alignment score from last element in matrix
    public Element[][] matrix; //matrix to hold elements
    static int match,misMatch,indel; //score values
    
public GlobalAlignment(String string1, String string2, int[] score){
        
        //sets score values from input array using a switch case, inside for loop
        for(int i = 0; i<score.length; i++){
            switch(i){
                case 0: match = score[i];
                break;
                
                case 1: misMatch = score[i];
                break;
                
                case 2: indel = score[i];
                break;
            }
        }
        seq1 = string1; //sets squences from input
        seq2 = string2;
        
        //constructs matrix, filling in 1st x row and y column
        matrix = new Element[seq1.length()][seq2.length()];
        for (int i = 0; i < seq1.length(); i++){
            matrix[i][0] = new Element(i*indel, i, 0); //all gaps so i*indel provides score
         }
        for (int i = 0; i < seq2.length(); i++){
            matrix[0][i] = new Element(i*indel, 0, i); //all gaps so i*indel provides score
        }
      
        
    }
    
    /*if you choose to use diagonal selection, you must check if it is
    a match/mismatch so you know what value to add, this method returns that*/
    public int returnScoreType(int i, int j){
        if (seq1.charAt(i) == seq2.charAt(j)){
            return match; //if i and j contain the same char value, we know we have a match
        }
        
        else{
            return misMatch;
        }
    }
    
    public Element pathSelection(int i, int j){
        Element path1,path2,path3; //creates temporary elements that hold scores for all possible direction choices
        
        path1 = new Element(matrix[i-1][j-1].score + returnScoreType(i, j), i, j);//diagonal choice
        path2 = new Element(matrix[i-1][j].score + indel, i, j); //upper choice
        path3 = new Element(matrix[i][j-1].score + indel, i, j); //left choice
        
        //math.max used to seleect the highest score out of all three paths choices
        Element choice = new Element(Math.max(path1.score, Math.max(path2.score, path3.score)), i, j);

        return choice;
        
    }
    
    /*making use of the pathSelection method, this method parses through the whole matrix
    and fills the next element with the best possile alingment score*/
    public Element setAlignment(){
        for (int i = 1; i < seq1.length(); i++) {
            for (int j = 1; j < seq2.length(); j++){
                matrix[i][j] = pathSelection(i, j);
            }
        }
        
        alignmentScore = matrix[seq1.length()-1][seq2.length()-1].score; //very last element of the array. We know this is the best possible alignment score.
        return matrix[seq1.length()-1][seq2.length()-1]; //return value for print statment in driver
    }
    

}
