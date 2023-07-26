import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestClassGreet {

    //Проверяем на корректность соединения и это первый пункт дз
    @Test
    public void checkConnection(){
        String url="http://test-microcam.relex.ru:40000/greet";
        Specifications.installSpecification(Specifications.requestSpec(url), Specifications.responseSpecOK200());
        Map<String, String> user = new HashMap<>();
        Response response = given()
                .body(user)
                .when()
                .get(url)
                .then().log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());

    }

}
