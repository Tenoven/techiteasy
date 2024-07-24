package nl.techiteasy.controllers;

import nl.techiteasy.Services.CIModuleService;
import nl.techiteasy.dtos.CIModuleDto;
import nl.techiteasy.models.CIModule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ci-Module")
public class CIModuleController {

    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping()
    public ResponseEntity<Object> getCIModules() {
        List<CIModule> ciModule = ciModuleService.getCIModules();
        return ResponseEntity.ok(ciModule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCIModule(@PathVariable long id) {
        CIModule CIModule = ciModuleService.getCIModule( id);
        return ResponseEntity.ok(CIModule);
    }

    @PostMapping()
    public ResponseEntity<CIModuleDto> addCIModule(@RequestBody CIModule CIModule) {
        CIModuleDto ciModule1 = ciModuleService.addCIModule(CIModule);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(ciModule1.getId()).toUri();
        return ResponseEntity.created(location).body(ciModule1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable long id, @RequestBody CIModule updatedCIModule) {
        ciModuleService.updateCIModule(id, updatedCIModule);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCIModule(@PathVariable long id) {
        ciModuleService.deleteCIModule(id);
        return ResponseEntity.noContent().build();
    }

}
