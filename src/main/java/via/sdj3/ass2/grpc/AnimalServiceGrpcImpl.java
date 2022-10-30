package via.sdj3.ass2.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import via.sdj3.ass2.grpc.generated.*;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.AnimalDTO;
import via.sdj3.ass2.service.AnimalService;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configurable
public class AnimalServiceGrpcImpl extends AnimalServiceGrpc.AnimalServiceImplBase {

    @Autowired
    private AnimalService service;

    public AnimalServiceGrpcImpl() {
        service = SpringContext.getBean(AnimalService.class);
    }



    @Override
    public void createAnimal(RegisterAnimalRequest request, StreamObserver<AnimalResponse> responseObserver) {
        AnimalDTO dto = new AnimalDTO(request.getAnimalType(), request.getFarm(), request.getWeight());
        Animal created = service.create(dto);
        AnimalResponse response = transformAnimalIntoAnimalResponse(created);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllAnimals(Nothing request, StreamObserver<AnimalListResponse> responseObserver) {
        List<Animal> allAnimals = service.findAll();
        AnimalListResponse response = fromAnimalListToAnimalListResponse(allAnimals);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAnimalById(AnimalIdRequest request, StreamObserver<AnimalResponse> responseObserver) {
        Optional<Animal> animal = service.findById(request.getAnimalId());
        if (animal.isEmpty()) {
            return;
        }
        AnimalResponse response = transformAnimalIntoAnimalResponse(animal.get());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAnimalsByDate(DateRequest request, StreamObserver<AnimalListResponse> responseObserver) {
        List<Animal> animals = service.findByDate(request.getDay(), request.getMonth(), request.getYear());
        AnimalListResponse response = fromAnimalListToAnimalListResponse(animals);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



    @Override
    public void getAnimalsByFarm(FarmStringRequest request, StreamObserver<AnimalListResponse> responseObserver) {
        List<Animal> animals = service.findByFarm(request.getFarm());
        AnimalListResponse response = fromAnimalListToAnimalListResponse(animals);
        responseObserver.onNext(response);
        responseObserver.onCompleted();    }

    public AnimalResponse transformAnimalIntoAnimalResponse(Animal animal) {
        return getAnimalResponse(animal);
    }

    static AnimalResponse getAnimalResponse(Animal animal) {
        Instant instant = animal.getDateTime().toInstant(ZoneOffset.UTC);
        Timestamp createdTimestamp = Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
        return AnimalResponse
                .newBuilder()
                .setRegNum(animal.getRegNum())
                .setAnimalType(animal.getAnimalType())
                .setDateTime(createdTimestamp)
                .setFarm(animal.getFarm())
                .setWeight(animal.getWeight())
                .build();
    }

    private AnimalListResponse fromAnimalListToAnimalListResponse(List<Animal> animals) {
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
