package via.sdj3.ass2;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import via.sdj3.ass2.grpc.AnimalServiceGrpcImpl;
import via.sdj3.ass2.grpc.PartServiceGrpcImpl;
import via.sdj3.ass2.grpc.ProductServiceGrpcImpl;

import java.io.IOException;

@SpringBootApplication
public class Ass3Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(Ass3Application.class, args);
        Server server = ServerBuilder
                .forPort(9090)
                .addService(new AnimalServiceGrpcImpl())
                .addService(new PartServiceGrpcImpl())
                .addService(new ProductServiceGrpcImpl())
                .build();

        server.start();
        server.awaitTermination();
    }

}
