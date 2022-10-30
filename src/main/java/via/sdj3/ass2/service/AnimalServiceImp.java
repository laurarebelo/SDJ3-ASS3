package via.sdj3.ass2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.AnimalDTO;
import via.sdj3.ass2.model.Part;
import via.sdj3.ass2.repository.AnimalRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImp implements AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    public Animal create(AnimalDTO dto) {
        Animal animalObject = new Animal(0, dto.getAnimalType(), dto.getFarm(), dto.getWeight());
        return animalRepository.save(animalObject);
    }

    public List<Animal> findAll() {
        List<Animal> animals = new ArrayList<>();
        animalRepository.findAll().forEach(animals::add);
        return animals;
    }

    public Optional<Animal> findById(int id) {
        return animalRepository.findById(id);
    }

    public List<Animal> findByDate(int day, int month, int year) {
        LocalDate date = LocalDate.of(year, month, day);
        return animalRepository.findAnimalsByDate(date);
    }

    public List<Animal> findByFarm(String farm) {
        return animalRepository.findAnimalsByFarm(farm);
    }




}
