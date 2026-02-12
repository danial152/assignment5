package service;

import org.springframework.stereotype.Service;
import database.*;
import model.*;
import repository.*;
import java.sql.SQLException;
import java.util.List;

@Service
public class SeriesService {

    private final SeriesRepository repo;

    public SeriesService(SeriesRepository repo) {
        this.repo = repo;
    }

    public void create(Series m) throws SQLException {
        repo.create(m);
    }

    public Series getById(int id) {
        return repo.getById(id);
    }

    public List<Series> getAll() {
        return repo.getAll();
    }

    public void update(int id, Series m) {
        repo.update(id, m);
    }

    public void delete(int id) throws SQLException {
        repo.delete(id);
    }

}