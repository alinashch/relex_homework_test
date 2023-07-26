import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestClassDelete {

    //Проверяем на некорректные тесты. В этом случае на отказ в доступе
    @Test
    public void UncorrectDeleteUserUnregister(){
        String uuid="53b0a557-024d-4fa9-9cb1-f46eee27b425";
        String url="http://test-microcam.relex.ru:40000/users/"+uuid;
        Specifications.installSpecification(Specifications.requestSpec(url), Specifications.responseSpecError401());
        Map<String, String> user = new HashMap<>();
        user.put("uuid", uuid);
        Response response = given()
                .body(user)
                .when()
                .delete(url)
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(401, response.statusCode());

    }



    //Проверяем на некорректные тесты. В этом случае на ненайденный uuid
    @Test
    public void UncorrectDeleteUserNotFound(){
        String uuid="53b0a557-024d-4fa9-9cb1-f46eee27b425";
        String url="http://test-microcam.relex.ru:40000/users/"+uuid;
        Specifications.installSpecification(Specifications.requestSpec(url), Specifications.responseSpecError404());
        Map<String, String> user = new HashMap<>();
        user.put("uuid", uuid);
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG0xbiExMSIsIm5iZiI6MTY5MDM0MTY0Miwicm9sZXMiOlsiQURNSU4iXSwiaXNzIjoibWljcm9jYW0iLCJleHAiOjE2OTAzNDI4NDIsImlhdCI6MTY5MDM0MTY0Mn0.MiyBLQy_DDFNE8ZOFz8VKGz8sX1oQHgJ6acVGvxJey4Wk2IPmycnOXR1hmI3CHowKl6eaihXgvVdY_DjKczYyg";

        Response response = given()
                .header("Authorization","Bearer "+ token)
                .body(user)
                .when()
                .delete(url)
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("Not Found", jsonPath.get("message"));

    }


    //Проверяем удаление на успешный тест
    @Test
    public void correctDeleteUser(){
        TestClassCreate testClassCreate=new TestClassCreate();
        String uuid=testClassCreate.correctCreateUser();
        String url="http://test-microcam.relex.ru:40000/users/"+uuid;
        Specifications.installSpecification(Specifications.requestSpec(url), Specifications.responseSpecOK200());
        Map<String, String> user = new HashMap<>();
        user.put("uuid", uuid);

        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG0xbiExMSIsIm5iZiI6MTY5MDM0MDQxMywicm9sZXMiOlsiQURNSU4iXSwiaXNzIjoibWljcm9jYW0iLCJleHAiOjE2OTAzNDE2MTMsImlhdCI6MTY5MDM0MDQxM30.867YW3HoktuRlsJzygzOQQJo9NYF3h0ePHX9nhCk42fCoMDO0UNVAANqL5UDh-tGL2P0--UP_4lBVGJxPIKclA";
        Response response = given()
                .header("Authorization","Bearer "+ token)
                .body(user)
                .when()
                .get(url)
                .then().log().all()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
    }


}
