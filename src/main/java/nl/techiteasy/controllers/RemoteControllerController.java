package nl.techiteasy.controllers;

import nl.techiteasy.Services.RemoteControllerService;
import nl.techiteasy.dtos.RemoteControllerDto;
import nl.techiteasy.models.RemoteController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/remote-controller")
public class RemoteControllerController {
        private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping()
        public ResponseEntity<Object> getRemotes() {
            List<RemoteController> remoteControllers = remoteControllerService.getRemoteControllers();
            return ResponseEntity.ok(remoteControllers);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> getRemote(@PathVariable long id) {
            RemoteController remoteController = remoteControllerService.getRemoteController(id);
            return ResponseEntity.ok(remoteController);
        }

        @PostMapping
        public ResponseEntity<RemoteControllerDto> addRemote(@RequestBody RemoteController remoteController) {
            RemoteControllerDto remoteController1 = remoteControllerService.addRemoteController(remoteController);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(remoteController1.getId()).toUri();
            return ResponseEntity.created(location).body(remoteController1);
        }

        @PutMapping("/{id}")
        public ResponseEntity<RemoteControllerDto> updateRemote(@PathVariable long id, @RequestBody RemoteController updatedRemoteController) {
            remoteControllerService.updateRemoteController(id, updatedRemoteController);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteRemote(@PathVariable long id) {
            remoteControllerService.deleteRemoteController(id);
            return ResponseEntity.noContent().build();
        }
}
