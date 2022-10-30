package via.sdj3.ass2.service;

import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.Part;

import java.util.List;

public interface PartService {
    List<Part> findAll();
    List<Part> cutAnimal(Animal animal);
}
