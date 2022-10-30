package via.sdj3.ass2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.Part;
import via.sdj3.ass2.repository.PartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartServiceImp implements PartService {

    @Autowired
    PartRepository repository;
    @Autowired
    TrayService trayService;

    @Override
    public List<Part> findAll() {
        List<Part> parts = new ArrayList<>();
        repository.findAll().forEach(parts::add);
        return parts;
    }

    public List<Part> cutAnimal(Animal animal) {
        double animalWeight = animal.getWeight();
        Part head = new Part(animal, "head", animalWeight * 0.4);
        Part leg1 = new Part(animal, "leg", animalWeight * 0.15);
        Part leg2 = new Part(animal, "leg", animalWeight * 0.15);
        Part breast = new Part(animal, "breast", animalWeight * 0.3);
        List<Part> partList = new ArrayList<>();
        partList.add(head);
        partList.add(leg1);
        partList.add(leg2);
        partList.add(breast);
        repository.saveAll(partList);
        trayService.savePartsInTrays(partList);
        return partList;
    }
}
