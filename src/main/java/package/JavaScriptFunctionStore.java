package bmstu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class JavaScriptFunctionStore {
    private final String ID = "packageId";
    private final String FUNCTION = "jsScript";
    private final String FUNCTION_NAME = "functionName";
    private final String FUNCTION_PARAM = "tests";

    @JsonProperty(ID)
    public String packageId;
    @JsonProperty(FUNCTION)
    public String jsFunctionBody;
    @JsonProperty(FUNCTION_NAME)
    public String jsFunctionName;
    @JsonProperty(FUNCTION_PARAM)
    public ArrayList<Test> jsFunctionParam;


    @JsonCreator
    public JavaScriptFunctionStore( @JsonProperty(ID) String packageId,
                                    @JsonProperty(FUNCTION) String jsFunction,
                                    @JsonProperty(FUNCTION_NAME) String jsFunctionName,
                                    @JsonProperty(FUNCTION_PARAM) ArrayList<Test> jsFunctionParam
                                    )
    {
        this.jsFunctionName = jsFunctionName;
        this.jsFunctionParam = jsFunctionParam;
        this.jsFunctionBody = jsFunction;
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getJsFunctionName() {
        return jsFunctionName;
    }

    public void setJsFunctionName(String jsFunctionName) {
        this.jsFunctionName = jsFunctionName;
    }


    public ArrayList<Test> getJsFunctionParam() {
        return jsFunctionParam;
    }

    public void setJsFunctionParam(ArrayList<Test> jsFunctionParam) {
        this.jsFunctionParam = jsFunctionParam;
    }

    public String getJsFunctionBody() {
        return jsFunctionBody;
    }

    public void setJsFunctionBody(String jsFunctionBody) {
        this.jsFunctionBody = jsFunctionBody;
    }
}
