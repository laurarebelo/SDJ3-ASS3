package via.sdj3.ass2.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.ass2.model.Tray;

import java.util.List;
import java.util.Optional;

public interface TrayRepository extends CrudRepository<Tray, Integer> {
    Optional<Tray> findTrayByAnimalTypeAndPartType(String animalType, String partType);
    List<Tray> findTraysByAnimalTypeAndPartType(String animalType, String partType);
    List<Tray> findTraysByAnimalType(String animalType);
}
