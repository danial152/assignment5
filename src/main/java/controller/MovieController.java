package controller;

import database.*;
import model.*;
import org.springframework.web.bind.annotation.*;
import repository.*;
import service.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/media/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping
    public void create(@RequestBody Movie m) throws SQLException {
        service.create(m);
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Movie> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id,
                       @RequestBody Movie m) {
        service.update(id, m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws SQLException {
        service.delete(id);
    }

    @GetMapping("/average")
    public double avgDuration() throws SQLException {
        return service.findAverage();
    }
}