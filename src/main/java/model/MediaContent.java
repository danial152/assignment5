package model;
import java.time.Year;

public abstract class MediaContent implements Displayable {
    protected int media_id;
    protected String title;
    protected int releaseYear;
    //constructor
    public MediaContent(int media_id, String title, int releaseYear) {
        this.media_id = media_id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    // getters setters
    public int getMediaID() {
        return media_id;
    }
    public void setMediaID(int media_id) {
        this.media_id = media_id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    //method
    public void releasedAgo() {
        int year = Year.now().getValue();
        System.out.println(year - this.releaseYear);
    }


    // abstract methods
    public abstract void displayInfo();


}
