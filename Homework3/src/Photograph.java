
/**
 * Homework 1 
 * Grace Kisly, gck4mwf
 * 
 * Sources : Class notes, In Class activities, Big Java book, Piazza, Java API Text SimpleDateFormat, Text ParseException, Util Date, setLenient method
 */

import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException; 

public class Photograph implements Comparable<Photograph>{

    /**
     * holds the caption of the Photograph
     */
    private String caption;

    /**
     * holds the filename of the Photograph
     */
    private String filename;

    /**
     * holds the date the photograph was taken
     */
    private String dateTaken; 
    
    /**
     * holds the rating of the photograph from 0-5
     */
    private int rating; 
    
    /**
     * contains the image's data
     */
    protected BufferedImage imageData; 
    
    /**
     * constructs Photograph that takes a caption and filename
     */
    public Photograph(String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
        this.dateTaken ="1901-01-01";
        this.rating = 0; 
    }
    
    /**
     * constructs Photograph that takes a caption filename, valid date, and valid rating
     */
    public Photograph(String caption, String filename, String dateTaken, int rating) {
        this.caption = caption;
        this.filename = filename;
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        } else {
            this.rating = 0;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false);
            format.parse(dateTaken);
            this.dateTaken = dateTaken;
        } catch (ParseException e) {
            this.dateTaken = "1901-01-01";
        }

    }

    /**
     * accessor method that returns reference to caption field
     */
    public String getCaption() {
        return this.caption;
    }

    /**
     * accessor method that returns reference to filename field
     */
    public String getFilename() {
        return this.filename;
    }

    /**
     * accessor method that returns reference to dateTaken field
     */
    public String getDateTaken() {
        return this.dateTaken;
    }
    
    /**
     * accessor method that returns substring of dateTaken to get year
     */
    public String getYear() {
        return this.dateTaken.substring(0, 4);
    }
    
    /**
     * accessor method that returns substring of dateTaken to get month
     */
    public String getMonth() {
        return this.dateTaken.substring(5,7);
    }
    
    /**
     * accessor method that returns rating value
     */
    public int getRating() {
        return this.rating;
    }
    
    /**
     * accessor method that returns imageData 
     */
    public BufferedImage getImageData() {
        return this.imageData;
    }
    
    /** 
     * mutator method that sets the rating of the Photograph if within 0-5
     * 
     * @ param newRating the updated rating of the Photograph
     */
    public void setRating(int newRating) {
        if (newRating >= 0 && newRating <= 5) {
            this.rating = newRating;
        } else {
            this.rating = 0;
        }
    }
    
    /** 
     * mutator method that sets the caption of the Photograph
     * 
     * @ param newCaption the updated caption of the Photograph
     */
    public void setCaption(String newCaption) {
        this.caption = newCaption;
    } 
    
    /** 
     * mutator method that sets the imageData of the Photograph
     * 
     * @ param newImageData the updated imageData of the Photograph
     */
    public void setImageData(BufferedImage newImageData) {
        this.imageData = newImageData;
    } 
    
    /**
     * checks if object is not null and instance of Photograph then compares if filename and caption are equal and returns
     * true if the same
     */
    public boolean equals(Object o) {
        if  (o instanceof Photograph) {
            Photograph otherPhotograph = (Photograph) o;
            if (this.caption.equals(otherPhotograph.getCaption()) && this.filename == otherPhotograph.getFilename()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * means to print out a Photograph object
     */
    public String toString() {
        return "Caption: '" + this.caption + "', Filename: '" + this.filename + "'";
    }
    /**
     * returns unique integer for Photograph of the filename field
     */
    public int hashCode() {
        return (int)this.filename.hashCode(); 
    }
    
    /**
     * Compares the dateTaken of the current Photograph object 
     * If current objects date taken is before p, return a negative number
     * if p is earlier, return positive number
     * if equal, return comparison of this object's caption with p's caption
     * @ param p the Photograph being compared to current Photograph
     */
    
    public int compareTo(Photograph p) {
        int value = this.dateTaken.compareTo(p.getDateTaken());
        if (value != 0) {
            return value; 
        }
        return this.caption.compareTo(p.getCaption());
    }
    
    /**
     * takes the filename and loads the Image data from the file to store in imageData
     * returns true on success false if failure
     * 
     * @ param the name of the file being uploads
     */
    public boolean loadImageData(String filename) {
        try {
            BufferedImage img = ImageIO.read(new File(filename));
            this.imageData = img;
            return true; 
        }
        catch(IOException e) {
            return false; 
        }
    }

}
