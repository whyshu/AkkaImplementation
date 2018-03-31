package controllers;

import akka.NotUsed;
import views.html.*;
import actors.TimeActor;
import actors.UserActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.actor.*;
import play.libs.streams.ActorFlow;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.stream.javadsl.Flow;
import akka.util.Timeout;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import play.libs.F.Either;
import play.mvc.*;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static akka.pattern.PatternsCS.ask;

import org.webjars.play.WebJarsUtil;

/**
 * The main web controller that handles returning the index page, setting up a WebSocket, and watching a stock.
 */
public class HomeController extends Controller {
	
	@Inject private ActorSystem actorSystem;
    @Inject private Materializer materializer; 
    
    @Inject 
    public HomeController(ActorSystem system) {
    	system.actorOf(TimeActor.getProps(), "timeActor");
    }
    
    public Result index() {
        return ok(index.render(request()));
    }
   
    public WebSocket ws() {
        return WebSocket.Json.accept(request -> ActorFlow.actorRef(UserActor::props, actorSystem, materializer));
    }
}
