package via.sdj3.ass2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.AnimalDTO;
import via.sdj3.ass2.model.Part;
import via.sdj3.ass2.service.AnimalService;
import via.sdj3.ass2.service.PartService;
import via.sdj3.ass2.service.TrayService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parts")
public class PartApiController {

    private PartService partService;
    private AnimalService animalService;

    public PartApiController(AnimalService animalService, PartService partService) {
        this.animalService = animalService;
        this.partService = partService;
    }

    @PostMapping()  // C endpoint
    public ResponseEntity<Object> cutAnimal(@RequestBody Integer animalRegId) throws Exception {
        Optional<Animal> foundAnimal = animalService.findById(animalRegId);
        if (foundAnimal.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find an animal with this id.");
        }
        Animal animal = foundAnimal.get();
        List<Part> partsCut = partService.cutAnimal(animal);
        return new ResponseEntity<Object>(partsCut, HttpStatus.CREATED);

    }
}
