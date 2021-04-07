import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public abstract class PhotographContainer {

    /**
     * holds the name of the PhotographContainer as a String
     */
    protected String name;
    
  
    /**
     * holds the Photographs of the PhotographContainer as an ArrayList
     */
    protected ArrayList <Photograph> photos = new ArrayList<Photograph>();

    
    /**
     * constructs a PhotographContainer that takes a name
     */
    public PhotographContainer(String name) {
        this.name = name;
    }
    
    /**
     * accessor method that returns reference to name field
     */
    public String getName() {
        return this.name; 
    }
    
    /**
     * accessor method that returns reference to photo field
     */
    public ArrayList<Photograph> getPhotos() {
        return this.photos;
    }
    
    /**
     * method to add Photograph to photos, if it is not already in set
     * 
     * returns true if Photograph was added, false if it was not added
     * 
     * @ param p the Photograph attempting to be added
     */
    public boolean addPhoto(Photograph p) {
        if (p == null) {
            return false;
        }
        if (this.photos.contains(p)) {
            return false;
        }
        else {
        this.photos.add(p);
        return true;
        }
    }
    
    /**
     * method to check if Photograph is in the set of Photos
     * 
     * returns true if Photograph is in PhotographContainer, false if not
     * 
     * @ param p the Photograph that is being checked
     */
    public boolean hasPhoto(Photograph p) {

        return this.photos.contains(p);

    }

    /**
     * method to remove Photograph from PhotographContainer if Photograph is in it
     * 
     * returns true if Photograph was removed, false if not
     * 
     * @ param p the Photograph being erased
     */
    public boolean removePhoto(Photograph p) {
        if (this.photos.contains(p)) {
        this.photos.remove(p);
        return true; 
        }
        else {
        return false;
        }
    }
        
    /**
     * method to return number of Photographs in photos
     */
    public int numPhotographs() {
      
        return this.photos.size();
    }

    /**
     * checks if object is not null and instance of PhotographContainer then compares if names are equal and returns
     * true if the same
     */
    public boolean equals(Object o) {
        if (o instanceof PhotographContainer) {
            PhotographContainer other = (PhotographContainer) o;
            if (this.name == other.getName()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * a means to print a PhotographContainer object
     */
    public String toString() {
        ArrayList<String> fileNamesList = new ArrayList<String>();
        for (Photograph item : this.photos) {
            fileNamesList.add(item.getFilename());
        }

        return "Name: '" + this.name + ", Photos: " + fileNamesList;
    }
    
    /**
     * returns unique integer for an PhotographContainer of the name field
     */
    public int hashCode() {
        return (int) this.name.hashCode();
    }
    
    /**
     * returns ArrayList of photos with ratings greater than or equal to input rating
     * 
     * if rating syntactically incorrect, return null
     * 
     * if no photos of that rating, return empty ArrayList
     * 
     * @ param rating is rating photos are being compared to
     */
    public ArrayList<Photograph> getPhotos(int rating) {
        if (rating < 0 || rating > 5) {
            return null; }
        else {
            ArrayList<Photograph> highRatings = new ArrayList<Photograph>();
            for (Photograph items : this.photos) {
                if (items.getRating() >= rating) {
                    highRatings.add(items);
                }

            }
            return highRatings;
        }

    }
   

    /**
     * returns ArrayList of photos that were taken in the year provided if incorrectly formatted year, return null if no
     * photos taken that year, return an empty ArrayList @ param year is integer value of year being compared
     */
    public ArrayList<Photograph> getPhotosInYear(int year) {
        String yearString = String.valueOf(year);
        if (year < 0 || yearString.length() != 4) {
            return null;}
        else {
            ArrayList<Photograph> sameYear = new ArrayList<Photograph>();
            for (Photograph items : this.photos) {
                System.out.println(items.getYear());
                if (items.getYear().equals(yearString)) {
                    sameYear.add(items);
                    
                }
            }
            System.out.println(yearString);
            return sameYear;
        }
    }
    

    /**
     * returns an Arraylist of Photos that were taken in the year and month provided 
     * 
     *  if month and date incorrectly formatted, return null
     *  
     *   if no photos taken that month, return empty ArrayList
     *   
     *   @ param month is integer value of month being compared
     *   
     *   @ param year is integer value of year being compared
     */
  public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
      String yearString = String.valueOf(year);
      if (year < 0 || yearString.length() != 4) {
          return null;}
      else if (month < 1 || month > 12) {
          return null;
      } else {
          ArrayList<Photograph> sameMonth = new ArrayList<Photograph>();
          String monthString = String.valueOf(month);
          // check if month is from 1-9, need 0 to compare strings
          if (month <= 9) {
              monthString = "0" + monthString;
          }

          for (Photograph items : this.photos) {
              if (items.getYear().equals(yearString) && items.getMonth().equals(monthString)) {
                      sameMonth.add(items);
                  }
              
          }
          return sameMonth;
      }

  }
  
  /**
   * returns an ArrayList of Photos that were taken in between two dates
   *
   *  if dates incorrectly formatted or end date before begin date, return null
   *  
   *   if no photos taken in between, return empty ArrayList
   *   
   *   @ param beginDate is String value of start date being compared
   *   
   *   @ param endDate is String value of end date being compared
   */
  public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate){
      
      try {
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
          format.setLenient(false);
          Date startDate = format.parse(beginDate);
          Date lastDate = format.parse(endDate);

          if (startDate.after(lastDate)) {
              return null;
          }
          ArrayList<Photograph> inBetween = new ArrayList<Photograph>();
          for (Photograph items : this.photos) {
              Date newDate = format.parse(items.getDateTaken());
              if ((newDate.after(startDate) || newDate.equals(startDate)) && ( newDate.before(lastDate) || newDate.equals(lastDate))) {
                      inBetween.add(items);
                  }
              

          }
          return inBetween;
      }

      catch (ParseException e) {
          return null;
      }
  }
  
}
