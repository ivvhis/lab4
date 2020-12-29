package bmstu;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;

public class JsonJsPackage {
    public String packageId;
    public String jsScript;
    public String functionName;
    public ArrayList<bmstu.JavaScriptFunctionStore> test;

    @JsonCreator
    public JsonJsPackage(String packageId, String jsScript,
                         String functionName, ArrayList<bmstu.JavaScriptFunctionStore> test) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.test = test;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public void setJsScript(String jsScript) {
        this.jsScript = jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
