
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;


public class HW3Test {

    PhotoLibrary PL;
    PhotoLibrary PL_2;
    PhotoLibrary PL_4; 

    Photograph TEST_1;
    Photograph TEST_2;
    Photograph TEST_3;
    Photograph TEST_4;
    Photograph TEST_5;
    Photograph TEST_6;
    Photograph TEST_7;
    Photograph TEST_8;
    Photograph TEST_9;
    Photograph TEST_10;
    Photograph TEST_11;

    @Before
    public void setUp() {
        // photolibraries
        PL = new PhotoLibrary("grace's library", 1);
        PL_2 = new PhotoLibrary("charlotte's library", 2);
        PL_4 = new PhotoLibrary("kc's library", 4);

        // photographs
        TEST_1 = new Photograph("dog", "firstPhoto");
        TEST_2 = new Photograph("dog", "firstPhoto");
        TEST_3 = new Photograph("good vibes", "thirdPhoto", "2001-03-13", 4);
        TEST_4 = new Photograph("smile more", "fourthPhoto", "2010-45-09", 3);
        TEST_5 = new Photograph("happy days", "fifthPhoto", "1901-11-09", 1);
        TEST_6 = new Photograph("fish", "sixthPhoto", "2010-05-09", 6);
        TEST_7 = new Photograph("you're doing great", "seventhPhoto", "1980-12-19", 2);
        TEST_8 = new Photograph("you're doing great", "seventhPhoto", "1980-12-19", 2);
        TEST_9 = new Photograph("cat", "ninthPhoto", "2003-02-31", 5);
        TEST_10 = new Photograph("salmon", "tenthPhoto", "2030-01-11", 7);

        // adding Photos to PhotoLibraries
        PL.addPhoto(TEST_1);
        PL.addPhoto(TEST_2); // shouldn't add
        PL.addPhoto(TEST_3);
        PL.addPhoto(TEST_4);
        PL.addPhoto(TEST_5);
        PL.addPhoto(TEST_6);
        PL.addPhoto(TEST_7);
        PL.addPhoto(TEST_8);
        PL.addPhoto(TEST_9);
        PL.addPhoto(TEST_10);
        PL.addPhoto(TEST_11);

        PL_2.addPhoto(TEST_3);
        PL_2.addPhoto(TEST_4);
        PL_2.addPhoto(TEST_5);
        
        PL_4.addPhoto(TEST_1);
        PL_4.addPhoto(TEST_6);
        PL_4.addPhoto(TEST_9);

        // adding Albums to PhotoLibraries
        PL.createAlbum("pets");
        PL.addPhotoToAlbum(TEST_1, "pets");
        PL.addPhotoToAlbum(TEST_2, "pets"); // shouldn't add
        PL.addPhotoToAlbum(TEST_6, "pets");
        PL.addPhotoToAlbum(TEST_9, "pets");

        PL.createAlbum("quotes");
        PL.addPhotoToAlbum(TEST_3, "quotes");
        PL.addPhotoToAlbum(TEST_4, "quotes");
        PL.addPhotoToAlbum(TEST_5, "quotes");
        PL.addPhotoToAlbum(TEST_7, "quotes");
        PL.addPhotoToAlbum(TEST_8, "quotes");

        PL_2.createAlbum("odd number photos");
        PL_2.addPhotoToAlbum(TEST_1, "odd number photos"); // shouldn't add
        PL_2.addPhotoToAlbum(TEST_3, "odd number photos");
        PL_2.addPhotoToAlbum(TEST_5, "odd number photos");
        PL_2.addPhotoToAlbum(TEST_7, "odd number photos"); // shouldn't add
        PL_2.addPhotoToAlbum(TEST_9, "odd number photos"); // shouldn't add
    }

    @Test(timeout = 100)
    public void getPhotosTest1() {
        // input
        int rating = 4;

        // expected
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(TEST_3);
        expected.add(TEST_9);

        // actual
        ArrayList<Photograph> actual = PL.getPhotos(rating);
        assertEquals("failed to get photos equal to or higher than rating", expected, actual);

    }

    @Test(timeout = 100)
    public void getPhotosTest2() {
        // input
        int rating = 5;

        // expected
        ArrayList<Photograph> expected = new ArrayList<Photograph>();

        // actual
        ArrayList<Photograph> actual = PL_2.getPhotos(rating);
        assertEquals("failed to get photos equal to or higher than rating", expected, actual);

    }

    @Test(timeout = 100)
    public void getPhotosTest3() {
        // input
        int rating = 6;

        // expected
        ArrayList<Photograph> expected = null;

        // actual
        ArrayList<Photograph> actual = PL.getPhotos(rating);
        assertEquals("failed to get photos in a month", expected, actual);
    }

    @Test
    public void getPhotosInMonthTest1() {
        // input
        int month = 5;
        int year = 2010;

        // expected
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(TEST_6);

        // actual
        ArrayList<Photograph> actual = PL.getPhotosInMonth(month, year);
        assertEquals("failed to get photos in a month", expected, actual);
    }

    @Test
    public void getPhotosInMonthTest2() {
        // input
        int month = 1;
        int year = 1901;

        // expected
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(TEST_1);
        expected.add(TEST_4);
        expected.add(TEST_9);

        // actual
        ArrayList<Photograph> actual = PL.getPhotosInMonth(month, year);
        assertEquals("failed to get photos in a month", expected, actual);
    }

    @Test
    public void getPhotosInBetweenTest() {
        // input
        String beginDate = "1975-03-05";
        String endDate = "2020-10-25";

        // expected
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(TEST_3);
        expected.add(TEST_6);
        expected.add(TEST_7);

        // actual
        ArrayList<Photograph> actual = PL.getPhotosBetween(beginDate, endDate);
        assertEquals("failed to get photos in between dates", expected, actual);
    }

    @Test
    public void getPhotosInBetweenTest2() {
        // input
        String beginDate = "2010-05-09";
        String endDate = "2020-10-25";

        // expected
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(TEST_6);

        // actual
        ArrayList<Photograph> actual = PL.getPhotosBetween(beginDate, endDate);
        assertEquals("failed to get photos in between dates", expected, actual);
    }

    @Test
    public void getPhotosInBetweenTest3() {
        // input
        String beginDate = "2020-11-09";
        String endDate = "2020-10-25";

        // expected
        ArrayList<Photograph> expected = null;

        // actual
        ArrayList<Photograph> actual = PL.getPhotosBetween(beginDate, endDate);
        assertEquals("failed to get photos in between dates", expected, actual);
    }

    @Test
    public void erasePhotoTest() {
        // input
        Photograph p = new Photograph("dog", "firstPhoto");

        // expected
        boolean expected = true;

        // actual
        boolean actual = PL.removePhoto(p);
        assertEquals("failed to erase photos", expected, actual);
    }

    @Test
    public void erasePhotoTest2() {
        // input
        Photograph p = new Photograph("salmon", "tenthPhoto", "2030-01-11", 7);

        // expected
        boolean expected = true;

        // actual
        boolean actual = PL.removePhoto(p);
        assertEquals("failed to erase photos", expected, actual);
    }

    @Test
    public void similarityTest1() {
        // input
        PhotoLibrary PL_3 = new PhotoLibrary("delia's library", 3);
        PL_3.addPhoto(TEST_10);
        PL_3.addPhoto(TEST_4);
        PL_3.addPhoto(TEST_9);

        // expected
        double expected = .333;

        // actual
        double actual = PhotoLibrary.similarity(PL_2, PL_3);
        assertEquals("failed to get similarity", expected, actual, .001);
    }

    @Test
    public void similarityTest2() {

        // expected
        double expected = 1;

        // actual
        double actual = PhotoLibrary.similarity(PL, PL_2);
        assertEquals("failed to get similarity", expected, actual, .001);
    }

    @Test
    public void addPhoto() {
        // input
        Album newOne = new Album("joe");
      

        // expected
        boolean expected = true;

        // actual
        boolean actual = newOne.addPhoto(TEST_10);
       

        assertEquals("fails to pass add photo", expected, actual);

    }
    
    @Test
    /**
     * attempts to erase photo p, same as TEST_4
     * 
     * removes photo from Photolibrary PL and album quotes
     * 
     * returns true
     */
    public void removePhotoTest() {
        //input 
        Photograph p = new Photograph("smile more", "fourthPhoto", "2010-45-09", 3);
        
        //expected
        boolean expected = true; 
        
     // actual
        boolean actual = PL.removePhoto(p);
        assertEquals("failed to remove photo", expected, actual);
        
    }
    
    @Test
    /**
     * attempts to erase photo p from PhotoLibrary PL_2
     * 
     * photo not found in PL_2
     * 
     * returns false
     */
    public void removePhotoTest2() {
        //input 
        Photograph p = new Photograph("fish", "sixthPhoto", "2010-05-09", 6);
        
        //expected
        boolean expected = false; 
        
     // actual
        boolean actual = PL_2.removePhoto(p);
        assertEquals("failed to remove photo", expected, actual);
}
    
    @Test
    /**
     * sorts photos in ALbum Pets 
     * 
     * expected order TEST_9, TEST_1, TEST_6
     * 
     * creates ArrayList of Photographs in expected sorting order
     * 
     * compares two ArrayLists of Photographs
     * 
     * uses Collections.sort to sort
     */
    public void compareTest() {
        
        //expected
            ArrayList<Photograph> expected = new ArrayList<Photograph>();
            expected.add(TEST_9);
            expected.add(TEST_1);
            expected.add(TEST_6);
       //actual
            Collections.sort(PL_4.getPhotos());           
           assertEquals("Failed", expected, PL_4.getPhotos());
        }
    
    @Test
    /**
     * sorts photos in PL_2 
     * 
     * expected order TEST_4, TEST_5, TEST_3
     * 
     * creates ArrayList of Photographs in expected sorting order
     * 
     * compares two ArrayLists of Photographs
     * 
     * uses Collections.sort to sort
     */
    public void compareTest2() {
        
        //expected
            ArrayList<Photograph> expected = new ArrayList<Photograph>();
            expected.add(TEST_4);
            expected.add(TEST_5);
            expected.add(TEST_3);
       //actual
            Collections.sort(PL_2.getPhotos());           
           assertEquals("Failed", expected, PL_2.getPhotos());
            
    }

    @Test 
    /** 
     * sorts photos in PL by caption
     * 
     * expected order 9, 1, 6, 3, 5, 10, 4, 7
     */
    
    public void compareByCaption() {
       
       //expected
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(TEST_9);
        expected.add(TEST_1);
        expected.add(TEST_6);
        expected.add(TEST_3);
        expected.add(TEST_5);
        expected.add(TEST_10);
        expected.add(TEST_4);
        expected.add(TEST_7);
    
    //actual
       PL.removePhoto(TEST_11);
        Collections.sort(PL.getPhotos(), new CompareByCaption()); 
        assertEquals("Failed", expected, PL.getPhotos());
    }
    
    @Test 
    /** 
     * sorts photos 9, 1, 6, 12 by caption
     * 
     * 12 and 1 same caption, 12 higher rating
     * 
     * expected order 9, 12, 1, 6
     */
    
    public void compareByCaption2() {

        // expected
        Photograph TEST_12 = new Photograph("dog", "twelvephoto", "1920-10-12", 3);
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(TEST_9);
        expected.add(TEST_12);
        expected.add(TEST_1);
        expected.add(TEST_6);

        // actual
        PL_4.addPhoto(TEST_12);
        Collections.sort(PL_4.getPhotos(), new CompareByCaption());
        assertEquals("Failed", expected, PL_4.getPhotos());
    }

    @Test
    /**
     * Sorts photos in PL_2 by rating 
     * expected order 3, 4, 5
     */

    public void compareByRating() {
        // expected
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(TEST_3);
        expected.add(TEST_4);
        expected.add(TEST_5);

        // actual

        Collections.sort(PL_2.getPhotos(), new CompareByRating());
        assertEquals("Failed", expected, PL_2.getPhotos());
    }
    
    @Test
    /**
     * Sorts photos in PL_4 by rating 
     * 
     * 1 and 6 have same rating, compare by caption
     * 
     * expected order 9, 1, 6
     *
     */

    public void compareByRating2() {
        // expected
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(TEST_9);
        expected.add(TEST_1);
        expected.add(TEST_6);

        // actual

        Collections.sort(PL_4.getPhotos(), new CompareByRating());
        assertEquals("Failed", expected, PL_4.getPhotos());
    }
}
