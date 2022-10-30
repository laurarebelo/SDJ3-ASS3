package via.sdj3.ass2.grpc;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import via.sdj3.ass2.grpc.generated.*;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.Part;
import via.sdj3.ass2.model.Product;
import via.sdj3.ass2.model.Tray;
import via.sdj3.ass2.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static via.sdj3.ass2.grpc.AnimalServiceGrpcImpl.getAnimalResponse;

@Configurable
public class ProductServiceGrpcImpl extends ProductServiceGrpc.ProductServiceImplBase {

    @Autowired
    ProductService service;

    public ProductServiceGrpcImpl() {
        service = SpringContext.getBean(ProductService.class);
    }

    @Override
    public void makeHalfAnimalProduct(HalfAnimalProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product newProduct = service.makeProductHalfAnimal(request.getAnimalType());
        ProductResponse response = transformProductIntoProductResponse(newProduct);
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void makeSamePartTypeProduct(SamePartProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product newProduct = service.makeProductSamePartType(request.getAnimalType(), request.getPartType());
        ProductResponse response = transformProductIntoProductResponse(newProduct);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductsIncludingAnimalRegId(AnimalIdRequest request, StreamObserver<ProductListResponse> responseObserver) {
        List<Product> products = service.getAllProductsWithAnimalRegNum(request.getAnimalId());
        ProductListResponse response = transformProductListIntoProductListResponse(products);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllAnimalsInAProduct(ProductIdRequest request, StreamObserver<AnimalListResponse> responseObserver) {
        List<Animal> animals = service.getAllAnimalsInAProduct(request.getProductId());
        AnimalListResponse response = transformAnimalListIntoAnimalListResponse(animals);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public ProductListResponse transformProductListIntoProductListResponse(List<Product> products) {
        List<ProductResponse> productsParsed = new ArrayList<>();
        for (Product product : products
        ) {
            productsParsed.add(transformProductIntoProductResponse(product));
        }
        ProductListResponse response = ProductListResponse.newBuilder()
                .addAllProducts(productsParsed).build();
        return response;
    }

    public ProductResponse transformProductIntoProductResponse(Product product) {
        return ProductResponse.newBuilder()
                .setProductId(product.getProductId())
                .addAllParts(transformPartListToPartResponseList(new ArrayList<>(product.getParts())))
                .addAllTrays(transformTrayListToTrayResponseList(new ArrayList<>(product.getTrays())))
                .setProductType(product.getProductType())
                .build();
    }

    public PartResponse transformPartIntoPartResponse(Part part) {
        return PartResponse
                .newBuilder()
                .setPartId(part.getPartId())
                .setOgAnimal(transformAnimalIntoAnimalResponse(part.getOgAnimal()))
                .setPartType(part.getPartType())
                .setWeight(part.getWeight())
                .build();
    }

    public List<PartResponse> transformPartListToPartResponseList(List<Part> parts) {
        List<PartResponse> partsParsed = new ArrayList<>();
        for (Part part : parts
        ) {
            partsParsed.add(transformPartIntoPartResponse(part));
        }
        return partsParsed;
    }

    public List<TrayResponse> transformTrayListToTrayResponseList(List<Tray> trays) {
        List<TrayResponse> traysParsed = new ArrayList<>();
        for (Tray tray : trays
        ) {
            traysParsed.add(transformTrayIntoTrayResponse(tray));
        }
        return traysParsed;
    }

    private TrayResponse transformTrayIntoTrayResponse(Tray tray) {
        return TrayResponse.newBuilder()
                .setTrayId(tray.getTrayId())
                .setMaxWeight(tray.getMaxWeight())
                .setAnimalType(tray.getAnimalType())
                .setPartType(tray.getPartType())
                .addAllParts(transformPartListToPartResponseList(new ArrayList<>(tray.getParts())))
                .build();
    }

    public AnimalResponse transformAnimalIntoAnimalResponse(Animal animal) {
        return getAnimalResponse(animal);
    }

    private AnimalListResponse transformAnimalListIntoAnimalListResponse(List<Animal> animals) {
        List<AnimalResponse> allAnimalsParsed = new ArrayList<>();
        for (Animal animal : animals
        ) {
            allAnimalsParsed.add(transformAnimalIntoAnimalResponse(animal));
        }
        return AnimalListResponse
                .newBuilder()
                .addAllAnimals(allAnimalsParsed).build();
    }
}
