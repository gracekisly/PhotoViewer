/**
 * Homework 3 
 * Grace Kisly, gck4mwf
 * 
 * Sources : Class notes, In Class activities, Big Java book, Piazza
 */

import java.util.Comparator; 

public class CompareByCaption implements Comparator<Photograph>{
    
    /**
     * compares two photographs by caption alphabetically in photograph
     * 
     * if caption the same, compares by rating in descending order
     */
    public int compare(Photograph a, Photograph b) {
        int returnVal = a.getCaption().compareTo(b.getCaption());
        if (returnVal != 0) {
            return returnVal;
        }
        returnVal = b.getRating() - a.getRating();
        return returnVal; 
    }

}
