package nl.techiteasy.repositories;

import nl.techiteasy.models.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CIModuleRepository  extends JpaRepository<CIModule, Long> {
}
