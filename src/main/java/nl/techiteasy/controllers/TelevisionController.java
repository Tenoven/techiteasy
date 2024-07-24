package nl.techiteasy.controllers;

import nl.techiteasy.Services.TelevisionService;
import nl.techiteasy.dtos.TelevisionDto;
import nl.techiteasy.models.Television;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/television")
public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping()
    public ResponseEntity<Object> getTelevisions() {
        List<Television> televisions = televisionService.getTelevisions();
        return ResponseEntity.ok(televisions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable long id) {
        Television television = televisionService.getTelevision( id);
        return ResponseEntity.ok(television);
    }

    @PostMapping()
    public ResponseEntity<TelevisionDto> addTelevision(@RequestBody Television television) {
        TelevisionDto television1 = televisionService.addTelevision(television);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(television1.getId()).toUri();
        return ResponseEntity.created(location).body(television1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable long id, @RequestBody Television updatedTelevision) {
        televisionService.updateTelevision(id, updatedTelevision);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }
}
