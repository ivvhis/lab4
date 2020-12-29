package bmstu;

import akka.actor.*;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import scala.concurrent.Future;

public class ActorRouter extends AbstractActor {

    public static final int TIMEOUT_MILLIS = 5000;
    public static final int NR = 5;
    Props propsJsTestExecProp = Props.create(JSTestExecutorActor.class);
    Props propsResultsSupplyProp = Props.create(bmstu.ResultsSupplyActor.class);
    private ActorRef storeActor = getContext().actorOf(propsResultsSupplyProp , "Store");
    private ActorRef testExecutorActor = getContext().actorOf(
            new RoundRobinPool(NR)
                    .props(propsJsTestExecProp)
        );

    public String makeRequest(String packageId){
        return packageId;
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        bmstu.JavaScriptFunctionStore.class,
                        jsFunc -> {
                            for (int i = 0 ; i < jsFunc.jsFunctionParam.size() ; i++) {
                                testExecutorActor.tell(
                                new OneTest(jsFunc.jsFunctionName,
                                            jsFunc.jsFunctionParam.get(i).testName,
                                            jsFunc.jsFunctionParam.get(i).expRes,
                                            jsFunc.jsFunctionParam.get(i).params,
                                            jsFunc.jsFunctionBody,
                                            jsFunc.packageId), getSelf());
                            }
                        }
                )
                .match(
                        bmstu.JavaScriptFunctionRes.class,
                        output -> {
                            storeActor.tell(output , self());
                        }
                )
                .match(
                        String.class,
                        id -> {
                            Future<Object> result = Patterns.ask(storeActor , id , TIMEOUT_MILLIS);
                            getSender().tell(result , self());
                        }
                )

                .build();
    }
}
