package service;

import org.springframework.stereotype.Service;
import database.*;
import model.*;
import repository.*;
import java.sql.SQLException;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repo;

    public MovieService(MovieRepository repo) {
        this.repo = repo;
    }

    public void create(Movie m) throws SQLException {
        repo.create(m);
    }

    public Movie getById(int id) {
        return repo.getById(id);
    }

    public List<Movie> getAll() {
        return repo.getAll();
    }

    public void update(int id, Movie m) {
        repo.update(id, m);
    }

    public void delete(int id) throws SQLException {
        repo.delete(id);
    }

    public double findAverage() throws SQLException {
        return repo.findAverage();
    }
}