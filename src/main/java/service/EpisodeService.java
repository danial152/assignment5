package service;

import org.springframework.stereotype.Service;
import database.*;
import model.*;
import repository.*;
import java.sql.SQLException;
import java.util.List;

@Service
public class EpisodeService {

    private final EpisodeRepository repo;

    public EpisodeService(EpisodeRepository repo) {
        this.repo = repo;
    }

    public void create(Episode m) throws SQLException {
        repo.create(m);
    }

    public Episode getById(int id) {
        return repo.getById(id);
    }

    public List<Episode> getAll() {
        return repo.getAll();
    }

    public void update(int id, Episode m) {
        repo.update(id, m);
    }

    public void delete(int id) throws SQLException {
        repo.delete(id);
    }

}