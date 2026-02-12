package model;

public class MediaFactory {

    private MediaFactory() {}

    public static MediaContent createMovie(
            int media_id,
            String title,
            int releaseYear,
            int duration,
            String genre
    ) {
        return new Movie(media_id, title, releaseYear, duration, genre);
    }

    public static MediaContent createSeries(
            int media_id,
            String title,
            int releaseYear,
            String genre,
            int seasons
    ) {
        return new Series(media_id, title, releaseYear, genre, seasons);
    }

    public static MediaContent createEpisode(
            int media_id,
            int episode_id,
            int releaseYear,
            int episodeNumber,
            String title,
            int duration
    ) {
        return new Episode(media_id, episode_id, releaseYear, episodeNumber, title, duration);
    }
}