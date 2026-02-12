package controller;

import database.*;
import model.*;
import org.springframework.web.bind.annotation.*;
import repository.*;
import service.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/media/episodes")
public class EpisodeController {

    private final EpisodeService service;

    public EpisodeController(EpisodeService service) {
        this.service = service;
    }

    @PostMapping
    public void create(@RequestBody Episode m) throws SQLException {
        service.create(m);
    }

    @GetMapping("/{id}")
    public Episode getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Episode> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id,
                       @RequestBody Episode m) {
        service.update(id, m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws SQLException {
        service.delete(id);
    }

}