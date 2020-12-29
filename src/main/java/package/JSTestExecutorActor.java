package bmstu;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;


public class JSTestExecutorActor extends AbstractActor {

    public static final String JS_COMPILER = "nashorn";

    public bmstu.JavaScriptFunctionRes jsExecutor(String jsFunction , String jsFuncName ,
                                                  ArrayList<Integer> jsFuncParam , String packageId)
            throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager()
                .getEngineByName(JS_COMPILER);
        engine.eval(jsFunction);
        Invocable invocable = (Invocable) engine;
        return  new bmstu.JavaScriptFunctionRes(packageId,
                invocable.invokeFunction(jsFuncName, jsFuncParam.toArray()).toString());
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        OneTest.class,
                        item->{
                            getSender().tell(jsExecutor(item.getFunction(), item.getName()
                                    ,item.getParams() , item.getPackageId()), ActorRef.noSender());
                        }
                )
                .build();
    }
}
