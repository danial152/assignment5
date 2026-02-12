package model;

public class Movie extends MediaContent implements Validatable<Movie> {
    private int duration;
    private String genre;
    // constructor
    public Movie(int media_id, String title, int releaseYear, int duration, String genre) {
        super(media_id, title, releaseYear);
        this.duration = duration;
        this.genre = genre;
    }
    // getters setters
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(int duration) {
        this.genre = genre;
    }

    // overriding abstract method
    @Override
    public boolean isValid(Movie m) {
        return m.duration > 0 && m.title != null && !m.title.isEmpty();
    }

    @Override
    public void displayInfo() {
        System.out.println("Movie: " + title + ", Year: " + releaseYear + ", Duration: " + duration + " minutes");
    }
}
