package nl.techiteasy.repositories;

import nl.techiteasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionRepository extends JpaRepository<Television, Long> {
}
