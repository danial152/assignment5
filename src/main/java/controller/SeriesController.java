package controller;

import database.*;
import model.*;
import org.springframework.web.bind.annotation.*;
import repository.*;
import service.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/media/series")
public class SeriesController {

    private final SeriesService service;

    public SeriesController(SeriesService service) {
        this.service = service;
    }

    @PostMapping
    public void create(@RequestBody Series m) throws SQLException {
        service.create(m);
    }

    @GetMapping("/{id}")
    public Series getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Series> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id,
                       @RequestBody Series m) {
        service.update(id, m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws SQLException {
        service.delete(id);
    }

}