package actors;
import akka.actor.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;


public final class UserActor extends AbstractActor {
    private final ActorRef ws;

    public UserActor(final ActorRef wsOut) {
    	ws =  wsOut;
    }

    public static Props props(final ActorRef wsout) {
    	System.out.println("===========In user actor PROPS");
        return Props.create(UserActor.class, wsout);
    }
    
    @Override
    public void preStart() {
    	System.out.println("===========In prestart");
        context().actorSelection("/user/timeActor")
        .tell(new TimeActor.RegisterMsg(), self());
    }
    
    @Override
    public Receive createReceive() {
    	System.out.println("===========In user actor");
        return receiveBuilder().matchAny(s->System.out.println("hello "+s)).match(TimeMessage.class, this::sendTime).build();
    }
    
    static public class TimeMessage {
        public final String time;
        public TimeMessage(String time) {
            this.time = time;
        	//System.out.println("===========In Time message :: "+time);
        }       
    }
    
    public void sendTime(TimeMessage msg) {
        //System.out.println("=================Sending response time :: "+msg.time);
        final ObjectNode response = Json.newObject();
        response.put("time", msg.time);
        ws.tell(response, self());
    }
   
}