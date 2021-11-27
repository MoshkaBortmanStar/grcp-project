import com.bortman.grpc.HelloRequest;
import com.bortman.grpc.HelloResponse;
import com.bortman.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrpcClient {

    private static final Logger logger =Logger.getLogger(GrpcClient.class.getName());

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
            .usePlaintext()
            .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
            = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
            .setFirstName("Baeldung")
            .setLastName("gRPC")
            .build());

        logger.log(Level.INFO, helloResponse.toString());

        helloResponse = stub.hello(HelloRequest.newBuilder()
            .setFirstName("Moshka")
            .setLastName("Bortman")
            .build());

        logger.log(Level.INFO, helloResponse.toString());

        channel.shutdown();
    }

}
