package Utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;


public class Utils {

    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {

        APIDetails baseURI = APIDetails.valueOf("baseURI");
        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(baseURI.getResource())
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

        public static String getGlobalValue(String key) throws IOException {
            Properties prop =new Properties();
            FileInputStream fis =new FileInputStream("Utilities/config.properties");
            prop.load(fis);
            return prop.getProperty(key);
        }

    public static int getJsonPath(Response response, String key)
    {
        String resp=response.asString();
        JsonPath js = new JsonPath(resp);
        return js.getInt(key);
    }


}
