package via.sdj3.ass2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.AnimalDTO;
import via.sdj3.ass2.service.AnimalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalApiController {

    private AnimalService service;

    public AnimalApiController(AnimalService service) {
        this.service = service;
    }
    @PostMapping()  // C endpoint
    public ResponseEntity<Object> createAnimal(@RequestBody AnimalDTO animal) {
        System.out.println(animal);
        Animal createdAnimal = service.create(animal);
        return new ResponseEntity<Object>(createdAnimal, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<Object> getAnimals() {
        List<Animal> allAnimals = new ArrayList<>(service.findAll());
        return new ResponseEntity<Object>(allAnimals, HttpStatus.OK);

    }

    @GetMapping("/{id}")  // R endpoint
    public ResponseEntity<Object> getAnimalById(@PathVariable int id) {
        Animal animal = service.findById(id).get();
        System.out.println("\n [Backend - Server] Read operation is reached");
        System.out.println(animal);
        return new ResponseEntity<Object>(animal, HttpStatus.OK);
    }

    @GetMapping(params={"day","month","year"})  // R endpoint
    public ResponseEntity<Object> getAnimalsByDate(@RequestParam int day, @RequestParam int month, @RequestParam int year) {
        List<Animal> animalsFromThisDate = service.findByDate(day, month, year);
        return new ResponseEntity<Object>(animalsFromThisDate, HttpStatus.OK);
    }

    @GetMapping(params = "farm")  // R endpoint
    public ResponseEntity<Object> getAnimalByFarm(@RequestParam String farm) {
        List<Animal> animalsFromThisFarm = service.findByFarm(farm);
        return new ResponseEntity<Object>(animalsFromThisFarm, HttpStatus.OK);
    }


}
