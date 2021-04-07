
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import PhotoViewer.NextListener;
import PhotoViewer.PreviousListener;
import PhotoViewer.Rating1Listener;
import PhotoViewer.Rating2Listener;
import PhotoViewer.Rating4Listener;
import PhotoViewer.SortByCaptionListener;
import PhotoViewer.SortByDateListener;

/** Homework 4
 * 
 * Grace Kisly, gck4mwf
 * 
 * sources: In Class Activity examples, Lab 7 example, Piazza posts, Icon API, JLabel API, MousListener API, BoxLayout API, BorderLayout API, FlowLayout API, ButtonGroup API, LineSeparator, JLabel and MouseListeners- using .getSource() 
 * 
 */
public class PhotoViewer extends JFrame { // superclass is JFrame

    private PhotographContainer imageLibrary;
    JFrame frame;
    JButton previous;
    JButton next;
    JRadioButton sortByDate;
    JRadioButton sortByCaption;
    JRadioButton sortByRating;
    JLabel ratingLabel;
    JRadioButton rating1;
    JRadioButton rating2;
    JRadioButton rating3;
    JRadioButton rating4;
    JRadioButton rating5;
    JLabel mainImage;
    JPanel thumbnails;
    JLabel thumbnail1;
    JLabel thumbnail2;
    JLabel thumbnail3;
    JLabel thumbnail4;
    JLabel thumbnail5;
    int mainImageIndex;
    ArrayList<JLabel> thumblist = new ArrayList<JLabel>();
    BorderLayout borderlayout = new BorderLayout();

    public void createAndShowGUI() {

        frame = new JFrame();

        frame.setLayout(borderlayout);

        // top buttons

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        frame.add(topPanel, BorderLayout.PAGE_START);

        // previous button
        previous = new JButton("Previous");
        previous.addActionListener(new PreviousListener());
        topPanel.add(previous);

        // next button
        next = new JButton("Next");
        next.addActionListener(new NextListener());
        topPanel.add(next);

        // sorting buttons
        ButtonGroup sortingbuttongroup = new ButtonGroup();

        // sort by date button
        sortByDate = new JRadioButton("Sort by Date");
        sortingbuttongroup.add(sortByDate);
        sortByDate.addActionListener(new SortByDateListener());
        topPanel.add(sortByDate);

        // sort by caption button
        sortByCaption = new JRadioButton("Sort by Caption");
        sortingbuttongroup.add(sortByCaption);
        sortByCaption.addActionListener(new SortByCaptionListener());
        topPanel.add(sortByCaption);

        // sort by rating button
        sortByRating = new JRadioButton("Sort by Rating");
        sortingbuttongroup.add(sortByRating);
        sortByRating.addActionListener(new SortByRatingListener());
        topPanel.add(sortByRating);

        // bottom buttons

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        frame.add(bottomPanel, BorderLayout.PAGE_END);

        // rating label
        ratingLabel = new JLabel("Rating");
        bottomPanel.add(ratingLabel);
        ButtonGroup ratingbuttongroup = new ButtonGroup();

        // rating 1 button
        rating1 = new JRadioButton("1");
        ratingbuttongroup.add(rating1);
        rating1.addActionListener(new Rating1Listener());
        bottomPanel.add(rating1);

        // rating 2 button
        rating2 = new JRadioButton("2");
        ratingbuttongroup.add(rating2);
        rating2.addActionListener(new Rating2Listener());
        bottomPanel.add(rating2);

        // rating 3 button
        rating3 = new JRadioButton("3");
        ratingbuttongroup.add(rating3);
        rating3.addActionListener(new Rating3Listener());
        bottomPanel.add(rating3);

        // rating 4 button
        rating4 = new JRadioButton("4");
        ratingbuttongroup.add(rating4);
        rating4.addActionListener(new Rating4Listener());
        bottomPanel.add(rating4);

        // rating 5 button
        rating5 = new JRadioButton("5");
        ratingbuttongroup.add(rating5);
        rating5.addActionListener(new Rating5Listener());
        bottomPanel.add(rating5);

        // create thumbnail panel in box layout

        thumbnails = new JPanel();
        thumbnails.setLayout(new BoxLayout(thumbnails, BoxLayout.PAGE_AXIS));
        frame.add(thumbnails, BorderLayout.LINE_START);

        // create 5 separate thumbnails for each icon
        thumbnail1 = new JLabel();
        thumbnail2 = new JLabel();
        thumbnail3 = new JLabel();
        thumbnail4 = new JLabel();
        thumbnail5 = new JLabel();

        // center panel
        JPanel mainPanel = new JPanel();
        frame.add(mainPanel, BorderLayout.CENTER);
        mainImage = new JLabel();
        mainImage.setSize(500, 400);

        // add thumbnails to list of thumbnails for easy looping

        thumblist.add(thumbnail1);
        thumblist.add(thumbnail2);
        thumblist.add(thumbnail3);
        thumblist.add(thumbnail4);
        thumblist.add(thumbnail5);

        // method to set photographs from imageLibrary into icons on thumbnail labels
        setThumbnailImages();

        // starting index is the first image
        mainImageIndex = 0;

        // method to set the image of the main photo based on its index
        setMainImage(mainImageIndex);

        // add thumbnails to list of thumbnails
        thumbnails.add(thumbnail1);
        thumbnails.add(thumbnail2);
        thumbnails.add(thumbnail3);
        thumbnails.add(thumbnail3);
        thumbnails.add(thumbnail4);
        thumbnails.add(thumbnail5);

        // add main image to main panel
        mainPanel.add(mainImage);

        // create MouseListener for selecting images from thumbnails
        MouseEventListener myMouseListener = new MouseEventListener();

        // add to each label so that clicks correspond to the right icon and display the right image
        thumbnail1.addMouseListener(myMouseListener);
        thumbnail2.addMouseListener(myMouseListener);
        thumbnail3.addMouseListener(myMouseListener);
        thumbnail4.addMouseListener(myMouseListener);
        thumbnail5.addMouseListener(myMouseListener);

        // set title to Photo Viewer
        frame.setTitle("Photo Viewer");

        // close on hitting the X on screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the window.
        frame.pack();
        frame.setSize(1000, 700); // dimensions
        frame.setVisible(true); // to see the GUI
    }

    /**
     * method that loops through imageLibrary photographs and sets each one to its corresponding thumbnail thumbnail is
     * retrieved through list of thumbnails the text of the thumbnail label is set to the caption, date, and rating of the
     * photo
     */
    private void setThumbnailImages() {

        for (int i = 0; i < 5; i++) {
            Photograph p = imageLibrary.getPhotos().get(i);
            p.loadImageData(p.getFilename());
            BufferedImage image = p.getImageData();
            Image newImage = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
            thumblist.get(i).setIcon(new ImageIcon(newImage));
            thumblist.get(i).setText("\"" + p.getCaption() + "\"" + System.lineSeparator() + p.getDateTaken()
            + System.lineSeparator() + "Rating: " + p.getRating());
        }

    }

    /**
     * method to take the photograph based on the mainImage index and set it to the mainImage Label @ param i index of
     * Photograph in imageLibrary
     */
    private void setMainImage(int i) {
        Photograph mainP = imageLibrary.getPhotos().get(i);
        mainP.loadImageData(mainP.getFilename());
        BufferedImage image = mainP.getImageData();
        Image newMainImage = image.getScaledInstance(500, 400, java.awt.Image.SCALE_SMOOTH);
        mainImage.setIcon(new ImageIcon(newMainImage));

    }

    // private inner classes

    /**
     * goes to previous image when first image is selected, clicking the previous button should display the last image
     */
    private class PreviousListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (mainImageIndex > 0) {
                mainImageIndex--;
                setMainImage(mainImageIndex);

            } else {
                mainImageIndex = 4;
                setMainImage(mainImageIndex);
            }

        }
    }

    /**
     * goes to next image when end of container is reached, click the next button displays the first image
     */
    private class NextListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (mainImageIndex < 4) {
                mainImageIndex++;
                setMainImage(mainImageIndex);
            } else {
                mainImageIndex = 0;
                setMainImage(mainImageIndex);
            }

        }
    }

    /**
     * uses comparable (compareTo) method to sort items in container by date
     */
    private class SortByDateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Collections.sort(imageLibrary.getPhotos());
            setThumbnailImages();
            mainImageIndex = 0;
            setMainImage(mainImageIndex);

        }
    }

    /**
     * uses comparator sortByCaption to sort
     */
    private class SortByCaptionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Collections.sort(imageLibrary.getPhotos(), new CompareByCaption());
            setThumbnailImages();
            mainImageIndex = 0;
            setMainImage(mainImageIndex);
        }
    }

    /**
     * uses comparator sortByRating to sort
     */
    private class SortByRatingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Collections.sort(imageLibrary.getPhotos(), new CompareByRating());
            setThumbnailImages();
            mainImageIndex = 0;
            setMainImage(mainImageIndex);
        }
    }

    /**
     * changes rating of image to 1, re-sorts images
     */
    private class Rating1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            imageLibrary.getPhotos().get(mainImageIndex).setRating(1);
            setThumbnailImages();
        }
    }

    /**
     * changes rating of image to 1, re-sorts images
     */
    private class Rating2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            imageLibrary.getPhotos().get(mainImageIndex).setRating(2);
            setThumbnailImages();
        }
    }

    /**
     * changes rating of image to 1, re-sorts images
     */
    private class Rating3Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            imageLibrary.getPhotos().get(mainImageIndex).setRating(3);
            setThumbnailImages();
        }
    }

    /**
     * changes rating of image to 1, re-sorts images
     */
    private class Rating4Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            imageLibrary.getPhotos().get(mainImageIndex).setRating(4);
            // Collections.sort(imageLibrary.getPhotos(), new CompareByRating());
            setThumbnailImages();
        }
    }

    /**
     * changes rating of image to 1, re-sorts images
     */
    private class Rating5Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            imageLibrary.getPhotos().get(mainImageIndex).setRating(5);
            // Collections.sort(imageLibrary.getPhotos(), new CompareByRating());
            setThumbnailImages();
        }
    }

    /**
     * Listens to see if mouse is clicked on certain JLabel sets mainImage to the clicked thumbnail, adjusts index
     */
    private class MouseEventListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            JLabel l = (JLabel) e.getSource();
            mainImageIndex = thumblist.indexOf(l);
            setMainImage(mainImageIndex);

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }
    }

    /**
     * accessor method that returns imageData
     */
    public PhotographContainer getImageLibrary() {
        return this.imageLibrary;
    }

    /**
     * mutator method that sets the imageLibrary of the Photoviewer @ param newImageLibrary the updated imageLibrary of the
     * Photoviewer
     */
    public void setImageLibrary(PhotographContainer newImageLibrary) {
        this.imageLibrary = newImageLibrary;
    }

    public static void main(String[] args) {

        PhotoViewer myViewer = new PhotoViewer();

        // the relative image directory
        String imageDirectory = "images/";

        Photograph p1 = new Photograph("ireland trip", "first photo", "2018-06-28", 2);
        p1.loadImageData(imageDirectory + "IMG_4111.jpg");
        Photograph p2 = new Photograph("pool time", "second photo", "2019-08-17", 4);
        p2.loadImageData(imageDirectory + "IMG_6092.JPG");
        Photograph p3 = new Photograph("selfie", "third photo", "2018-11-08", 5);
        p3.loadImageData(imageDirectory + "IMG_7055.jpg");
        Photograph p4 = new Photograph("football", "fourth photo", "2018-12-12", 1);
        p4.loadImageData(imageDirectory + "IMG_7072.jpg");
        Photograph p5 = new Photograph("toy car", "fifth photo", "2019-10-28", 3);
        p5.loadImageData(imageDirectory + "IMG_7088.jpg");

        myViewer.setImageLibrary(new PhotoLibrary("Test Library", 1));

        myViewer.getImageLibrary().addPhoto(p1);
        myViewer.getImageLibrary().addPhoto(p2);
        myViewer.getImageLibrary().addPhoto(p3);
        myViewer.getImageLibrary().addPhoto(p4);
        myViewer.getImageLibrary().addPhoto(p5);

        // use Collections.sort() to sort the photos based on the
        // written compareTo() method
        Collections.sort(myViewer.getImageLibrary().getPhotos());

        myViewer.createAndShowGUI(); // start by running this method (does all the work)

    }
}