package Utilities;

public enum APIDetails {
    baseURI("http://dummy.restapiexample.com"),
    createEmployee("/api/v1/create"),
    getEmployee("/api/v1/employee/"),
    deleteEmployee("/api/v1/delete/");
    private String resource;

    APIDetails(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;
    }
}
