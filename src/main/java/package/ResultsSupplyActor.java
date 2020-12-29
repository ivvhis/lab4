package bmstu;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultsSupplyActor extends AbstractActor {

    private Map<String , ArrayList<String>> store = new HashMap<>();
    private void resultStoring(bmstu.JavaScriptFunctionRes item){
        ArrayList<String> tmpArray;
        if (store.containsKey(item.getPackageID())){
            tmpArray = store.get(item.getPackageID());
        }else {
            tmpArray = new ArrayList<>();
        }
        tmpArray.add(item.getFunctionRes());
        store.put(item.getPackageID() , tmpArray);
    }
    private bmstu.JavaScriptFunctionRes packageIdPrinter(String packageID) {
        if (store.containsKey(packageID)) {
            ArrayList<String> tmpArray = store.get(packageID);
            for (int i = 0; i < tmpArray.size(); i++) {
                return new bmstu.JavaScriptFunctionRes( packageID , tmpArray.get(i) );
            }
        }
        return new bmstu.JavaScriptFunctionRes(packageID , "There are no tests" );
    }

    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        bmstu.JavaScriptFunctionRes.class,
                        this::resultStoring
                )
                .match(
                        String.class,
                        id -> {
                            getSender().tell(packageIdPrinter(id) , ActorRef.noSender());
                        }
                )
                .build();
    }
}