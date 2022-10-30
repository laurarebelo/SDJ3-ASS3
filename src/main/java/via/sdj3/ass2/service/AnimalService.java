package via.sdj3.ass2.service;

import org.springframework.stereotype.Service;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.AnimalDTO;
import via.sdj3.ass2.model.Part;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnimalService {
    Animal create(AnimalDTO animal);
    List<Animal> findAll();
    Optional<Animal> findById(int id);
    List<Animal> findByDate(int day, int month, int year);
    List<Animal> findByFarm(String farm);
}
