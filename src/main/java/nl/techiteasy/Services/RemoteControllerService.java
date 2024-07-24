package nl.techiteasy.Services;

import jakarta.persistence.EntityNotFoundException;

import nl.techiteasy.dtos.RemoteControllerDto;
import nl.techiteasy.repositories.RemoteControllerRepository;
import nl.techiteasy.models.RemoteController;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteControllerService {

    private RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = RemoteControllerService.this.remoteControllerRepository;
    }

    public List<RemoteController> getRemoteControllers() {
        return remoteControllerRepository.findAll();
    }

    public RemoteController getRemoteController(long id) {
        RemoteController remoteController= remoteControllerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("RemoteController" + id + "not found"));
        return remoteController;
    }

    public RemoteControllerDto addRemoteController(RemoteController newRemoteController) {
        RemoteController savedRemoteController = remoteControllerRepository.save(newRemoteController);
        return toRemoteControllerDto(savedRemoteController);
    }

    public RemoteControllerDto updateRemoteController(long id, RemoteController updatedRemoteController) {
        RemoteController remoteController = remoteControllerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Remote controller" + id + "not found"));

        remoteController.setCompatibleWith(updatedRemoteController.getCompatibleWith());
        remoteController.setBatteryType(updatedRemoteController.getBatteryType());
        remoteController.setName(updatedRemoteController.getName());
        remoteController.setPrice(updatedRemoteController.getPrice());
        remoteController.setBrand(updatedRemoteController.getBrand());
        remoteController.setOriginalStock(updatedRemoteController.getOriginalStock());

        RemoteController savedRemoteController= remoteControllerRepository.save(remoteController);
        return toRemoteControllerDto(savedRemoteController);
    }

    public void deleteRemoteController(long id) {
        if (!remoteControllerRepository.existsById(id)) {
            throw new EntityNotFoundException("RemoteController " + id + " not found");
        }
        remoteControllerRepository.deleteById(id);
    }

    public static RemoteControllerDto toRemoteControllerDto(RemoteController remoteController) {
        RemoteControllerDto dto = new RemoteControllerDto();
        dto.setId(remoteController.getId());
        dto.setCompatibleWith(remoteController.getCompatibleWith());
        dto.setBatteryType(remoteController.getBatteryType());
        dto.setName(remoteController.getName());
        dto.setPrice(remoteController.getPrice());
        dto.setBrand(remoteController.getBrand());
        dto.setOriginalStock(remoteController.getOriginalStock());
        return dto;
    }


    public RemoteController toRemoteController(RemoteControllerDto remoteControllerDto) {
        RemoteController remoteController = new RemoteController();
        remoteController.setCompatibleWith(remoteControllerDto.getCompatibleWith());
        remoteController.setBatteryType(remoteControllerDto.getBatteryType());
        remoteController.setName(remoteControllerDto.getName());
        remoteController.setPrice(remoteControllerDto.getPrice());
        remoteController.setBrand(remoteControllerDto.getBrand());
        remoteController.setOriginalStock(remoteControllerDto.getOriginalStock());
        return remoteController;
    }
}
