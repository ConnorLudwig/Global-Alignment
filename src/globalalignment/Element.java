
package globalalignment;

public class Element {
    int i;
    int j;
    Element previous; //was going to be used for traversing back through the matrix.
    int score; //holds score for each element
    
    //constructor, requires a score and an x and y value for the matrix.
    public Element(int inScore, int x, int y){
        i = x;
        j = y;
        score = inScore;
        previous = null;
        
    }
    
}
