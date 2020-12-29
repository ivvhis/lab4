package bmstu;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class AkkaMainApplication extends AllDirectives {

    public static final int TIMEOUT_MILLIS = 5000;
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        final ActorSystem system = ActorSystem.create("test");
        ActorRef router = system.actorOf(Props.create(bmstu.ActorRouter.class));
        final Http http = Http.get(system);
        final AkkaMainApplication app = new AkkaMainApplication();
        final Materializer materializer = ActorMaterializer.create(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                app.createRoute(system , router).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", PORT),
                materializer
        );
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }
    private Route createRoute(ActorSystem system , ActorRef router){
        return get(()->
                parameter("packageId" , key -> {
                    Future<Object> result = Patterns.ask(router, key, TIMEOUT_MILLIS);
                    return completeOKWithFuture(result, Jackson.marshaller());
                }
        )).orElse(
            post(()->entity(Jackson.unmarshaller(bmstu.JavaScriptFunctionStore.class), msg -> {
                router.tell(msg, ActorRef.noSender());
                return complete("Test started!");
            })));
    }
}
