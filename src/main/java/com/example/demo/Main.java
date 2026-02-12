import database.*;
import model.*;
import repository.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        IDB db = new DatabaseConnection();

        MovieRepository movieRepo = new MovieRepository(db);
        SeriesRepository seriesRepo = new SeriesRepository(db);
        EpisodeRepository episodeRepo = new EpisodeRepository(db);

        System.out.println("create\n");

        Movie movie1 = new Movie(1, "Inception", 2010, 148, "Sci-Fi");
        Movie movie2 = new Movie(2, "Interstellar", 2014, 169, "Sci-Fi");

        Series series1 = new Series(3, "Breaking Bad", 2008, "Drama", 5);

        Episode ep1 = new Episode(4, 101, 2008, 1, "Pilot", 58);
        Episode ep2 = new Episode(5, 102, 2008, 2, "Cat's in the Bag", 48);

        movieRepo.create(movie1);
        movieRepo.create(movie2);
        seriesRepo.create(series1);
        episodeRepo.create(ep1);
        episodeRepo.create(ep2);

        System.out.println("entities created\n");

        // update
        System.out.println("update \n");

        Movie updatedMovie = new Movie(1, "Inception (Updated)", 2010, 150, "Sci-Fi");
        movieRepo.update(1, updatedMovie);

        System.out.println("update finishes\n");

        // delete
        System.out.println("delete\n");

        Movie movieToDelete = movieRepo.getById(2);
        if (movieToDelete != null) {
            movieRepo.delete(2);
            System.out.println("movie deleted \n");
        } else {
            System.out.println("cant delete the movie\n");
        }

        // displays
        System.out.println("lists\n");

        List<Displayable> mediaList = new ArrayList<>();
        mediaList.addAll(movieRepo.getAll());
        mediaList.addAll(seriesRepo.getAll());
        mediaList.addAll(episodeRepo.getAll());

        for (Displayable item : mediaList) {
            item.displayInfo();
        }



        // test

    }
}
