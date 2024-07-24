package nl.techiteasy.Services;

import jakarta.persistence.EntityNotFoundException;
import nl.techiteasy.dtos.WallBracketDto;
import nl.techiteasy.models.WallBracket;
import nl.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WallBracketService {

    private WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public List<WallBracket> getWallBrackets() {return wallBracketRepository.findAll();}

    public WallBracket getWallBracket(long id) {
        WallBracket wallBracket= wallBracketRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Wallbracket" + id + "not found"));
        return wallBracket;
    }

    public WallBracketDto addWallbracket(WallBracket newWallBracket) {
    WallBracket savedWallbracket = wallBracketRepository.save(newWallBracket);
    return toWallBracketDto(savedWallbracket);
    }

    public WallBracketDto updateWallBracket(long id, WallBracket updatedWallBracket) {
        WallBracket wallBracket= wallBracketRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Wallbracket" + id + "not found"));

        wallBracket.setSize(updatedWallBracket.getSize());
        wallBracket.setAdjustable(updatedWallBracket.getAdjustable());
        wallBracket.setName(updatedWallBracket.getName());
        wallBracket.setPrize(updatedWallBracket.getPrize());

        WallBracket savedWallBracket= wallBracketRepository.save(wallBracket);
        return toWallBracketDto(savedWallBracket);
    }

    public void deleteWallBracket(long id) {
        if (!wallBracketRepository.existsById(id)) {
            throw new EntityNotFoundException("Wallbracket" + id + "not found");
        }
        wallBracketRepository.deleteById(id);
    }

    public static WallBracketDto toWallBracketDto(WallBracket wallBracket) {
        WallBracketDto dto = new WallBracketDto();
        dto.setId(wallBracket.getId());
        dto.setName(wallBracket.getName());
        dto.setAdjustable(wallBracket.getAdjustable());
        dto.setPrize(wallBracket.getPrize());
        dto.setSize(wallBracket.getSize());
        return dto;
    }

    public WallBracket toWallBracket(WallBracketDto wallBracketDto) {
        WallBracket wallBracket= new WallBracket();
        wallBracket.setPrize(wallBracketDto.getPrize());
        wallBracket.setName(wallBracketDto.getName());
        wallBracket.setAdjustable(wallBracketDto.getAdjustable());
        wallBracket.setSize(wallBracketDto.getSize());
        return wallBracket;
    }



}
