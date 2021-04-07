/**
 * Homework 1 
 * Grace Kisly, gck4mwf
 * 
 * Sources : Class notes, In Class activities, Big Java book, Piazza, Java API Text SimpleDateFormat, Text ParseException, Util Date, setLenient method
 */

import java.util.ArrayList;
import java.util.HashSet; 

public class PhotoLibrary extends PhotographContainer {

   
    /**
     * holds the id of the PhotoLibrary as an integer
     */
    private int id;

   
    /**
     * holds a HashSet of Albums 
     */
    private HashSet<Album> albums = new HashSet<Album>();
    
    /**
     * constructs a PhotoLibrary that takes a name and id
     */
    public PhotoLibrary(String name, int id) {
        super(name);
        this.id = id;
    }
    
    /**
     * accessor method that returns id number
     */
    public int getId() {
        return this.id;
    }
    
    
    /**
     * accessor method that returns reference to albums field
     */
    public HashSet<Album> getAlbums() {
        return this.albums;
    }
   
    
    /**
     * method to remove Photograph from PhotoLibrary if Photograph is in it
     * 
     * returns true if Photograph was removed, false if not
     * 
     * @ param p the Photograph being erased
     */
    public boolean removePhoto(Photograph p) {
        boolean removed = false;

        for (Album pic : this.albums) {
            if (pic.hasPhoto(p)) {
                pic.removePhoto(p);
                removed = true;

            }
            for (int i = 0; i < photos.size(); i++) {
                if (photos.get(i).equals(p)) {
                    photos.remove(i);
                    if (!removed) {
                        removed = true;}
                }
            }
        }
        return removed;   
    }
    
    
    /**
     * checks if object is not null and instance of PhotoLibrary then compares if ids are equal and returns
     * true if the same
     */
    public boolean equals(Object o) {
        if (o instanceof PhotoLibrary) {
            PhotoLibrary otherPhotoLibrary = (PhotoLibrary) o;
            if (this.id == otherPhotoLibrary.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * a means to print a PhotoLibrary object
     */
    public String toString() {
        return "Name: '" + this.name + "', Id: " + this.id + ", Photos: " + this.photos + ", Albums: " + this.albums;
    }
    
    /**
     * method to create ArrayList of photos in between two PhotoLibraries 
     * 
     * @ param a is first PhotoLibrary
     * 
     * @ param b is second PhotoLibrary being compared to PhotoLibrary a
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
        ArrayList<Photograph> commonLibrary = new ArrayList<Photograph>();
        for (int i = 0; i < a.getPhotos().size(); i++) {
            for (int j = 0; j < b.getPhotos().size(); j++) {
                if (a.getPhotos().get(i).equals(b.getPhotos().get(j))) {
                    commonLibrary.add((Photograph) a.getPhotos().get(i));
                }
            }
        }
        return commonLibrary;
    }
    
   /** 
    * return a measure of how similar two PhotoLibraries are from 0 to 1
    */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        if (a.numPhotographs() == 0 || b.numPhotographs() == 0) {
            return 0;
        }
        double numCommon = commonPhotos(a,b).size();
        
        double totalPhotos = Math.min(a.numPhotographs(), b.numPhotographs());
        return numCommon / totalPhotos;
    }
    
    
    /**
     * method to create Album with name albumName to albums, if it is not already in set
     * 
     * returns true if Album was added, false if it was not added
     * 
     * @ param albumName the name of Album attempting to be added
     */
    public boolean createAlbum(String albumName) {
        Album newAlbum = new Album(albumName);
       if (this.albums.contains(newAlbum)) {
           return false;
       }
       else {
       this.albums.add(newAlbum);
       return true; 
       } 
    }
    
    /**
     * returns the Album with that name from set of albums
     * 
     * returns null if album with that name is not found
     * 
     * @ param albumName is name of Album being returned
     */
    private Album getAlbumByName(String albumName) {
        Album newAlbum = new Album(albumName);
        for(Album item: this.albums) {
            if(item.equals(newAlbum)) {
                return item; 
            }
        }
        return null; 
    }

    /**
     * method to remove Album from albums if Album is in it
     * 
     * returns true if Album was removed, false if not
     * 
     * @ param albumName the name being erased
     */
    public boolean removeAlbum(String albumName) {
        if (this.albums.contains(this.getAlbumByName(albumName))) {
            this.albums.remove(this.getAlbumByName(albumName));
            return true;
        }
        else {
            return false; 
        }
    }
    
    /**
     * adds Photograph p to Album in the set of albums that has name albumName
     * 
     * added if it is in photoLibrary's photos and was not already in the album
     * 
     * return true if added, false if not
     * 
     * @ param p the Photograph trying to be added
     * 
     * @ param albumName the album it is being added to
     */
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        if (this.hasPhoto(p) && this.albums.contains(this.getAlbumByName(albumName))) {
            this.getAlbumByName(albumName).addPhoto(p);
            return true; 
        }
        else {
        return false;
        }
    }
    
    /**
     * removes Photograph p from Album in the set of albums that has name albumName
     * 
     * return true if removed, false if not
     * 
     * @ param p the Photograph trying to be removed
     * 
     * @ param albumName the album it is being removed from
     */
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {
        if (p == null) {
            return false; 
        }
        if (this.albums.contains(this.getAlbumByName(albumName))) {
        if (this.getAlbumByName(albumName).hasPhoto(p)) {
            this.getAlbumByName(albumName).removePhoto(p);
            return true; 
        }
        return false; 
        }
        else {
        return false;
        }
    }
    
    /**
     * tester class, tests two of each methods
     
    public static void main(String[] args) {
        Photograph TEST_1 = new Photograph("hi", "first");
        System.out.println("Photo 1: " + TEST_1.toString());
        Photograph TEST_2 = new Photograph("what's up", "second");
        System.out.println("Photo 2: " + TEST_2.toString());
        Photograph TEST_3 = new Photograph("what's up", "second");
        System.out.println("Photo 3: " + TEST_3.toString());
        System.out.println("Are Photo 1 and 2 equal: " + TEST_1.equals(TEST_2));
        System.out.println("Are Photo 2 and 3 equal: " + TEST_2.equals(TEST_3));
        PhotoLibrary SET_1 = new PhotoLibrary("first set", 1);
        System.out.println("PhotoLibrary 1: " + SET_1.toString());
        System.out.println("Add Photo 3 to PhotoLibrary 1: " + SET_1.addPhoto(TEST_1));
        System.out.println("PhotoLibrary 1: " + SET_1.toString());
        System.out.println("Add Photo 3 to PhotoLibrary 1: " + SET_1.addPhoto(TEST_2));
        System.out.println("PhotoLibrary 1: " + SET_1.toString());
        System.out.println("Add Photo 3 to PhotoLibrary 1: " + SET_1.addPhoto(TEST_3));
        System.out.println("PhotoLibrary 1: " + SET_1.toString());
        PhotoLibrary SET_2 = new PhotoLibrary("second set", 2);
        SET_2.addPhoto(TEST_1);
        System.out.println("PhotoLibrary 1: " + SET_2.toString());
        System.out.println("Does PhotoLibrary 1 have Photo 3: " + SET_1.hasPhoto(TEST_3));
        System.out.println("Does PhotoLibrary 2 have Photo 3: " + SET_2.hasPhoto(TEST_3));
        System.out.println("Are PhotoLibrary 1 and 2 equal: " + SET_1.equals(SET_2));
        System.out.println("Remove Photo 3 from PhotoLibrary 1: " + SET_1.erasePhoto(TEST_3));
        System.out.println("PhotoLibrary 1: " + SET_1.toString());
        System.out.println("PhotoLibrary 2: " + SET_2.toString());
        System.out.println("Common photos between PhotoLibrary 1 and 2: " + commonPhotos(SET_1, SET_2));
        PhotoLibrary SET_3 = new PhotoLibrary("third set", 1);
        SET_3.addPhoto(TEST_2);
        SET_3.addPhoto(TEST_1);
        Photograph TEST_4 = new Photograph("hello", "fourth");
        System.out.println("Photo 4: " + TEST_1.toString());
        System.out.println("Add Photo 4 to PhotoLibrary 1: " + SET_1.addPhoto(TEST_4));
        System.out.println("PhotoLibrary 1: " + SET_1.toString());
        System.out.println("PhotoLibrary 3: " + SET_3.toString());
        System.out.println("Are PhotoLibrary 1 and 3 equal: " + SET_1.equals(SET_3));
        System.out.println("Number of Photos in PhotoLibrary 1: " + SET_1.numPhotographs());
        System.out.println("Number of Photos in PhotoLibrary 3: " + SET_3.numPhotographs());
        System.out.println("Common photos between PhotoLibrary 1 and 3: " + commonPhotos(SET_1, SET_3));
        System.out.println("The similarity between PhotoLibrary 1 and 3: " + similarity(SET_1, SET_3));
        SET_3.addPhoto(TEST_4);
        System.out.println("PhotoLibrary 2: " + SET_2.toString());
        System.out.println("PhotoLibrary 3: " + SET_3.toString());
        System.out.println("The similarity between PhotoLibrary 2 and 3: " + similarity(SET_2, SET_3));
        System.out.println("Remove Photo 1 from PhotoLibrary 2 : " + SET_2.erasePhoto(TEST_1));
        System.out.println("PhotoLibrary 2: " + SET_2.toString());
        System.out.println("PhotoLibrary 3: " + SET_3.toString());
        System.out.println("The similarity between PhotoLibrary 2 and 3: " + similarity(SET_2, SET_3));
    }
    **/
}
