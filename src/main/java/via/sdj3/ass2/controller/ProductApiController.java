package via.sdj3.ass2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.Part;
import via.sdj3.ass2.model.Product;
import via.sdj3.ass2.model.SpecificTrayDTO;
import via.sdj3.ass2.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    private ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/halfAnimal")  // C endpoint
    public ResponseEntity<Object> makeHalfAnimalProduct(@RequestBody String animalType) throws Exception {
        Product newProd = productService.makeProductHalfAnimal(animalType);
        return new ResponseEntity<Object>(newProd, HttpStatus.CREATED);

    }

    @PostMapping("/samePartType")  // C endpoint
    public ResponseEntity<Object> makeSamePartProduct(@RequestBody SpecificTrayDTO dto) throws Exception {
        System.out.println(dto.getAnimalType());
        System.out.println(dto.getPartType());
        Product newProd = productService.makeProductSamePartType(dto.getAnimalType(), dto.getPartType());
        return new ResponseEntity<Object>(newProd, HttpStatus.CREATED);

    }

    @GetMapping("/byAnimal/{animalRegId}")
    public ResponseEntity<Object> getProductsIncludingAnimalRegId(@PathVariable int animalRegId) {
        List<Product> products = productService.getAllProductsWithAnimalRegNum(animalRegId);
        System.out.println(products);
        return new ResponseEntity<Object>(products, HttpStatus.OK);
    }


    @GetMapping("/{productId}/animals")
    public ResponseEntity<Object> getAllAnimalsInAProduct(@PathVariable int productId) {
        List<Animal> animals = productService.getAllAnimalsInAProduct(productId);
        System.out.println(animals);
        return new ResponseEntity<Object>(animals, HttpStatus.OK);
    }
}
