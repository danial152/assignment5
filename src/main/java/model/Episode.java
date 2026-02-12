package model;

public class Episode extends MediaContent implements Validatable<Episode> {
    private int episode_id;
    private int episodeNumber;
    private int duration; // minutes
    // constructor
    public Episode(int media_id, int episode_id, int releaseYear, int episodeNumber, String title, int duration) {
        super(media_id, title, releaseYear);
        this.episode_id = episode_id;
        this.episodeNumber = episodeNumber;
        this.duration = duration;
    }
    // getters and setters
    public int getEpisodeID() {
        return episode_id;
    }
    public void setEpisodeID(int episode_id) {
        this.episode_id = episode_id;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }
    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    // overriding an abstract method
    @Override
    public boolean isValid(Episode m) {
        return m.duration > 0 && m.title != null && !m.title.isEmpty();
    }

    @Override
    public void displayInfo() {
        System.out.println("Episode: " + title + ", Year: " + releaseYear + ", Episode Number: " + episodeNumber + ", Duration: " + duration + " minutes");
    }
}
