package actors;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.Duration;
import akka.actor.AbstractActorWithTimers;
import akka.actor.*;
import java.util.*;
import javax.inject.Inject;
import java.time.LocalDateTime;

public class TimeActor extends AbstractActorWithTimers {
    // actor keeps a set of ActorRefs (registered UserActors)

	private static ArrayList<ActorRef> userActors=new ArrayList<>();
    private final class Tick {
    	public Tick(){
    		System.out.println("=================Inside Tick"+LocalDateTime.now().toString());
	    	UserActor.TimeMessage tMsg = new UserActor.TimeMessage(LocalDateTime.now().toString());
	        userActors.forEach(ar -> ar.tell(tMsg, self()));
    	}
    	
    	
    }
    
    
    public String notifyClients(){
    	return "check";
    }
    
    @Override
    public void preStart() {
    	System.out.println("=====================Inside Time actor prestart");
        getTimers().startPeriodicTimer("Timer", new Tick(), Duration.create(5, TimeUnit.SECONDS));
    }
    
    @Override
    public Receive createReceive() {
    	System.out.println("=====================Inside Time actor");
        return receiveBuilder()
        		.match(RegisterMsg.class, msg -> userActors.add(sender()))
        	    .match(Tick.class, msg -> notifyClients())
                .build();
    }
    
    
    static public class RegisterMsg { 	
    	public RegisterMsg(){
           
    	}
    }

    public static Props getProps() {
    	System.out.println("==================Get props method");
        return Props.create(TimeActor.class);
    }
    
   
}