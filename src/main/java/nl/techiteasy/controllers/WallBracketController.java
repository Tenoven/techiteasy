package nl.techiteasy.controllers;

import nl.techiteasy.Services.WallBracketService;
import nl.techiteasy.dtos.WallBracketDto;
import nl.techiteasy.models.WallBracket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class WallBracketController {


    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @GetMapping()
    public ResponseEntity<Object> getWallBrackets() {
        List<WallBracket> Wallbrackets = wallBracketService.getWallBrackets();
        return ResponseEntity.ok(Wallbrackets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getWallBracket(@PathVariable long id) {
        WallBracket wallBracket = wallBracketService.getWallBracket( id);
        return ResponseEntity.ok(wallBracket);
    }

    @PostMapping()
    public ResponseEntity<WallBracketDto> addWallBracket(@RequestBody WallBracket wallBracket) {
        WallBracketDto WallBracket1 = wallBracketService.addWallBracket(wallBracket);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(nl.techiteasy.models.WallBracket1.getId()).toUri();
        return ResponseEntity.created(location).body(WallBracket1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@PathVariable long id, @RequestBody WallBracket updatedWallBracket) {
        wallBracketService.updateWallBracket(id, updatedWallBracket);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallBracket(@PathVariable long id) {
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }
}
