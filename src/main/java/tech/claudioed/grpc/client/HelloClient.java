package tech.claudioed.grpc.client;

import io.grpc.ManagedChannel;
import io.vertx.core.Vertx;
import io.vertx.grpc.VertxChannelBuilder;
import tech.claudioed.helloworld.GreeterGrpc;
import tech.claudioed.helloworld.HelloRequest;

/**
 * @author claudioed on 06/05/18.
 * Project grpc-vertx-bootcamp
 */
public class HelloClient {

  public static void main(String[]args){

    final Vertx vertx = Vertx.vertx();
    ManagedChannel channel = VertxChannelBuilder
        .forAddress(vertx, "localhost", 8080)
        .usePlaintext(true)
        .build();

    GreeterGrpc.GreeterVertxStub stub = GreeterGrpc.newVertxStub(channel);

    final HelloRequest request = HelloRequest.newBuilder().setName("claudio").build();

    stub.sayHello(request, ar -> {
      if (ar.succeeded()) {
        System.out.println("Got the server response: " + ar.result().getMessage());
      } else {
        System.out.println("Coult not reach server " + ar.cause().getMessage());
      }
    });
  }

}
