package via.sdj3.ass2.service;

import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.Product;

import java.util.List;

public interface ProductService {
    public Product makeProductHalfAnimal(String animalType);
    public Product makeProductSamePartType(String animalType, String partType);
    public List<Product> getAllProductsWithAnimalRegNum(int regNum);

    List<Animal> getAllAnimalsInAProduct(int productId);
}
