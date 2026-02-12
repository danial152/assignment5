package model;

public class Series extends MediaContent {
    private String genre;
    private int seasons;
    // constructor
    public Series(int media_id, String title, int releaseYear, String genre, int seasons) {
        super(media_id, title, releaseYear);
        this.genre = genre;
        this.seasons = seasons;
    }

    // getters setters
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getSeasons() {
        return seasons;
    }
    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    // overriding an abstract method
    @Override
    public void displayInfo() {
        System.out.println("Series: " + title + ", Year: " + releaseYear + ", Genre: " + genre + ", Seasons: " + seasons);
    }
}
