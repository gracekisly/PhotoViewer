/**
 * Homework 3 
 * Grace Kisly, gck4mwf
 * 
 * Sources : Class notes, In Class activities, Big Java book, Piazza
 */

import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph> {

    /**
     * compares two photographs by rating in descending order
     * if rating the same, compares by caption in alphabetical order
     */
   public int compare(Photograph a, Photograph b) {
    int returnVal = b.getRating() - a.getRating();
    if (returnVal != 0) {
        return returnVal;
    }
    returnVal = a.getCaption().compareTo(b.getCaption());
    return returnVal; 
}

}
