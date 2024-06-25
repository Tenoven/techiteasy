package nl.techiteasy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class TelevisionController {

    @GetMapping("/television")
    public ResponseEntity<Object> getTelevion() {
        return ResponseEntity.ok("television");
    }

    @GetMapping("/television/{id}")
    public ResponseEntity<Object> getTelevision() {
        return ResponseEntity.ok("television");
    }

    @PostMapping("television/{id}")
    public ResponseEntity<Object> addTelevision(@RequestBody String televisionBrand) {
//        ...(hier wordt iets opgeslagen in de database)...
        String name = "television"
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}")
                        .buildAndExpand(name).toUri();
        return ResponseEntity.created(null).body("television");
    }

    @PutMapping("television/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody String televisionBrand) {
//    …(hier wordt een resource aangepast)
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/television/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
//        ...(hier wordt een object verwijderd)...
        return ResponseEntity.noContent().build();
    }
}
