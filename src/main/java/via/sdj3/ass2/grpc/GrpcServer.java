package via.sdj3.ass2.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new AnimalServiceGrpcImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
