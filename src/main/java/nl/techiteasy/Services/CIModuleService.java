package nl.techiteasy.Services;

import jakarta.persistence.EntityNotFoundException;
import nl.techiteasy.dtos.CIModuleDto;
import nl.techiteasy.models.CIModule;
import nl.techiteasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CIModuleService {

    private CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<CIModule> getCIModules(){return ciModuleRepository.findAll();}

    public CIModule getCIModule(long id) {
        CIModule ciModule= ciModuleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("CIModule"+ id +"not found"));
        return ciModule;
    }

    public CIModuleDto addCIModule(CIModule newCIModule) {
        CIModule savedCIModule = ciModuleRepository.save(newCIModule);
        return toCIModuleDto(savedCIModule);
    }

    public CIModuleDto updateCIModule(long id, CIModule updatedCIModule) {
        CIModule ciModule = ciModuleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CIModule" + id + "not found"));

        ciModule.setName(updatedCIModule.getName());
        ciModule.setPrice(updatedCIModule.getPrice());
        ciModule.setType(ciModule.getType());

        CIModule savedCIModule= ciModuleRepository.save(ciModule);
        return toCIModuleDto(savedCIModule);
    }

    public void deleteCIModule(long id) {
        if(!ciModuleRepository.existsById(id)) {
            throw new EntityNotFoundException("CIModule" + id + " not found");
        }
        ciModuleRepository.deleteById(id);
    }

    public static CIModuleDto toCIModuleDto(CIModule ciModule) {
        CIModuleDto dto = new CIModuleDto();
        dto.setId(ciModule.getId());
        dto.setName(ciModule.getName());
        dto.setPrice(ciModule.getPrice());
        dto.setType(ciModule.getType());
        return  dto;
    }

    public CIModule toCIModule(CIModuleDto ciModuleDto) {
        CIModule ciModule = new CIModule();
        ciModule.setId(ciModuleDto.getId());
        ciModule.setType(ciModuleDto.getType());
        ciModule.setName(ciModuleDto.getName());
        ciModule.setPrice(ciModuleDto.getPrice());
        return ciModule;
    }
}
