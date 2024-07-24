package nl.techiteasy.Services;

import jakarta.persistence.EntityNotFoundException;
import nl.techiteasy.dtos.TelevisionDto;
import nl.techiteasy.models.Television;
import nl.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelevisionService {

    private TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<Television> getTelevisions() {
        return televisionRepository.findAll();
    }

    public Television getTelevision(long id) {
        Television television= televisionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Television" + id + "not found"));
        return television;
    }

    public TelevisionDto addTelevision(Television newTelevision) {
        Television savedTelevision = televisionRepository.save(newTelevision);
        return toTelevisionDto(savedTelevision);
    }

    public TelevisionDto updateTelevision(long id, Television updatedTelevision) {
        Television television = televisionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Television" + id + "not found"));

        television.setType(updatedTelevision.getType());
        television.setBrand(updatedTelevision.getBrand());
        television.setName(updatedTelevision.getName());
        television.setPrice(updatedTelevision.getPrice());
        television.setAvailableSize(updatedTelevision.getAvailableSize());
        television.setRefreshRate(updatedTelevision.getRefreshRate());
        television.setScreenType(updatedTelevision.getScreenType());
        television.setScreenQuality(updatedTelevision.getScreenQuality());
        television.setSmartTv(updatedTelevision.getSmartTv());
        television.setWifi(updatedTelevision.getWifi());
        television.setVoiceControl(updatedTelevision.getVoiceControl());
        television.setHdr(updatedTelevision.getHdr());
        television.setBluetooth(updatedTelevision.getBluetooth());
        television.setAmbiLight(updatedTelevision.getAmbiLight());
        television.setOriginalStock(updatedTelevision.getOriginalStock());
        television.setSold(updatedTelevision.getSold());

        Television savedTelevision= televisionRepository.save(television);
        return toTelevisionDto(savedTelevision);
    }

    public void deleteTelevision(long id) {
        if (!televisionRepository.existsById(id)) {
            throw new EntityNotFoundException("Television " + id + " not found");
        }
        televisionRepository.deleteById(id);
    }

    public static TelevisionDto toTelevisionDto(Television television) {
        TelevisionDto dto = new TelevisionDto();
        dto.setId(television.getId());
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getSmartTv());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());
        return dto;
    }


    public Television toTelevision(TelevisionDto televisionDto) {
        Television television = new Television();
        television.setId(televisionDto.getId());
        television.setType(televisionDto.getType());
        television.setBrand(televisionDto.getBrand());
        television.setName(televisionDto.getName());
        television.setPrice(televisionDto.getPrice());
        television.setAvailableSize(televisionDto.getAvailableSize());
        television.setRefreshRate(televisionDto.getRefreshRate());
        television.setScreenType(televisionDto.getScreenType());
        television.setScreenQuality(televisionDto.getScreenQuality());
        television.setSmartTv(televisionDto.getSmartTv());
        television.setWifi(televisionDto.getWifi());
        television.setVoiceControl(televisionDto.getVoiceControl());
        television.setHdr(televisionDto.getHdr());
        television.setBluetooth(televisionDto.getBluetooth());
        television.setAmbiLight(televisionDto.getAmbiLight());
        television.setOriginalStock(televisionDto.getOriginalStock());
        television.setSold(televisionDto.getSold());
        return television;
    }
}