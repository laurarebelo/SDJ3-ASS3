package via.sdj3.ass2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.Part;
import via.sdj3.ass2.model.Product;
import via.sdj3.ass2.model.Tray;
import via.sdj3.ass2.repository.AnimalRepository;
import via.sdj3.ass2.repository.ProductRepository;
import via.sdj3.ass2.repository.TrayRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public TrayRepository trayRepository;
    @Autowired
    public AnimalRepository animalRepository;

    @Override
    public Product makeProductHalfAnimal(String animalType) {
        Product newProd = new Product(Product.PRODUCT_TYPE_ANIMAL_HALF);
        List<Tray> traysToTakeFrom = trayRepository.findTraysByAnimalType(animalType);
        putFourPartsFromTraysIntoProduct(newProd, traysToTakeFrom);
        return productRepository.save(newProd);

    }

    @Override
    public Product makeProductSamePartType(String animalType, String partType) {
        Product newProd = new Product(Product.PRODUCT_TYPE_SAME_TYPE_PARTS);
        List<Tray> traysToTakeFrom = trayRepository.findTraysByAnimalTypeAndPartType(animalType, partType);
        putFourPartsFromTraysIntoProduct(newProd, traysToTakeFrom);
        return productRepository.save(newProd);
    }

    @Override
    public List<Product> getAllProductsWithAnimalRegNum(int regNum) {
        return productRepository.findProductsByProductIdIn(productRepository.findProductsIdsByAnimalRegIdInvolved(regNum));
    }

    @Override
    public List<Animal> getAllAnimalsInAProduct(int productId) {
        return animalRepository.findAnimalsByRegNumIn(productRepository.findAnimalRegIdsInvolvedInProductByProductId(productId));
    }

    private void putFourPartsFromTraysIntoProduct(Product newProd, Iterable<Tray> traysToTakeFrom) {
        Iterator<Tray> traysItr = traysToTakeFrom.iterator();
        int counter = 0;
        while (traysItr.hasNext() && counter < 4) {
            Tray currentTray = traysItr.next();
            Set<Part> currentTrayParts = currentTray.getParts();
            ArrayList<Part> partsToRemove = new ArrayList<>();
            for (Part part : currentTrayParts
            ) {
                newProd.addPart(part, currentTray);
                partsToRemove.add(part);
                counter++;
                System.out.println(counter);
                if (counter >= 4) {
                    break;
                }
            }
            for (Part part: partsToRemove
                 ) {
                currentTray.getParts().remove(part);
            }
        }
        //save that the parts were removed from the trays
        trayRepository.saveAll(traysToTakeFrom);
    }
}
