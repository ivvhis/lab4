package bmstu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Test {
    private final String TEST_NAME = "testName";
    private final String EXPECTED_RESULT = "expectedResult";
    private final String PARAMS = "params";

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getExpRes() {
        return expRes;
    }

    public void setExpRes(String expRes) {
        this.expRes = expRes;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }

    public void setParams(ArrayList<Integer> params) {
        this.params = params;
    }

    @JsonProperty(TEST_NAME)
    public String testName;

    @JsonProperty(EXPECTED_RESULT)
    public String expRes;

    @JsonProperty(PARAMS)
    public ArrayList<Integer> params;

    @JsonCreator
    public Test(@JsonProperty(TEST_NAME) String testName,
                @JsonProperty(EXPECTED_RESULT) String expRes,
                @JsonProperty(PARAMS) ArrayList<Integer> params)
    {
        this.testName = testName;
        this.expRes = expRes;
        this.params = params;
    }
}
