package bmstu;

import java.util.ArrayList;

public class OneTest {
    private String name;
    private String testName;
    private  String expRes;
    private  ArrayList<Integer> params;
    private String function;
    private String packageId;

    public OneTest( String name, String testName, String expRes, ArrayList<Integer> params, String function, String packageId) {
        this.testName = testName;
        this.expRes = expRes;
        this.params = params;
        this.function = function;
        this.packageId = packageId;
        this.name = name;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpRes() {
        return expRes;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }

    public String getFunction() {
        return function;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getName() {
        return name;
    }
}
