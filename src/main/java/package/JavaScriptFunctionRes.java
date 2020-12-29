package bmstu;

public class JavaScriptFunctionRes {
    private String packageID , functionRes;

    public JavaScriptFunctionRes(String packageID, String functionRes) {
        this.packageID = packageID;
        this.functionRes = functionRes;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getFunctionRes() {
        return functionRes;
    }

    @Override
    public String toString() {
        return "JavaScriptFunctionRes{" +
                "packageID='" + packageID + '\'' +
                ", functionRes='" + functionRes + '\'' +
                '}';
    }

    public void setFunctionRes(String functionRes) {
        this.functionRes = functionRes;
    }
}
