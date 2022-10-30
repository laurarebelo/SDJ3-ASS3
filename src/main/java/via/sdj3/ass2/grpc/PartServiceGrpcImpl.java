package via.sdj3.ass2.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import via.sdj3.ass2.grpc.generated.*;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.Part;
import via.sdj3.ass2.service.AnimalService;
import via.sdj3.ass2.service.PartService;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static via.sdj3.ass2.grpc.AnimalServiceGrpcImpl.getAnimalResponse;

@Configurable
public class PartServiceGrpcImpl extends PartServiceGrpc.PartServiceImplBase {

    @Autowired
    private PartService partService;
    @Autowired
    private AnimalService animalService;

    public PartServiceGrpcImpl() {
        partService = SpringContext.getBean(PartService.class);
        animalService = SpringContext.getBean(AnimalService.class);
    }
    @Override
    public void cutAnimal(AnimalIdRequest request, StreamObserver<PartListResponse> responseObserver) {
        Optional<Animal> animal = animalService.findById(request.getAnimalId());
        if (animal.isEmpty()) {
            return;
        }
        List<Part> parts = partService.cutAnimal(animal.get());
        PartListResponse response = transformPartListToPartListResponse(parts);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public AnimalResponse transformAnimalIntoAnimalResponse(Animal animal) {
        return getAnimalResponse(animal);
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

    public PartListResponse transformPartListToPartListResponse(List<Part> parts) {
        List<PartResponse> partsParsed = new ArrayList<>();
        for (Part part: parts
             ) {
            partsParsed.add(transformPartIntoPartResponse(part));
        }
        return PartListResponse.newBuilder()
                .addAllParts(partsParsed).build();
    }
}
