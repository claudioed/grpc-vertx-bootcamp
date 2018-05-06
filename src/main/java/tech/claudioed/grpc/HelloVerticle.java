package tech.claudioed.grpc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import java.io.IOException;
import tech.claudioed.helloworld.GreeterGrpc;
import tech.claudioed.helloworld.HelloReply;
import tech.claudioed.helloworld.HelloRequest;

/**
 * @author claudioed on 06/05/18.
 * Project grpc-vertx-bootcamp
 */
public class HelloVerticle extends AbstractVerticle {

  @Override
  public void start() throws IOException {

    GreeterGrpc.GreeterVertxImplBase service = new GreeterGrpc.GreeterVertxImplBase() {
      @Override
      public void sayHello(HelloRequest request, Future<HelloReply> future) {
        future.complete(HelloReply.newBuilder().setMessage(request.getName()).build());
      }
    };
    VertxServer rpcServer = VertxServerBuilder
        .forAddress(vertx, "localhost", 8080)
        .addService(service)
        .build();

    rpcServer.start();

  }
}
