import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


public class TestClassCreate{

    //Проверяем регистрацию на успешный тест

    @Test
    public String correctCreateUser(){
        Specifications.installSpecification(Specifications.requestSpec("http://test-microcam.relex.ru:40000/users"), Specifications.responseSpecOK201());
        Map<String, String> user = new HashMap<>();
        user.put("firstName", "qwerrtyukk");
        user.put("middleName", "qwerrtyukk");
        user.put("lastName", "qwerrtyukk");
        testprog tp = new testprog ();
        String s="";
        for (int i = 0; i < 100; i++) {
               s=  tp.getStr(i + 5);
        }
        user.put("login", String.valueOf(s));
        user.put("email", "qqqqqqqqqq@example.com");
        user.put("password", "1234546qqqqqqAAAA!!!!!");
        Response response = given()
                .body(user)
                .when()
                .post("http://test-microcam.relex.ru:40000/users")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        if(response.getStatusCode()==201) {
            Assertions.assertNotNull( jsonPath.get("uuid"));
        }else {
            Assertions.assertEquals("QpwL5tke4Pnpja7X4", jsonPath.get("uuid"));
        }

        return  jsonPath.get("uuid");
    }

    //Проверяем на некорректные тесты. В этом случае на неправильный password
    @Test
    public void UncorrectCreateUserUncorrectpassword(){
        Specifications.installSpecification(Specifications.requestSpec("http://test-microcam.relex.ru:40000/users"), Specifications.responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("firstName", "qwerrtyukk");
        user.put("middleName", "qwerrtyukk");
        user.put("lastName", "qwerrtcccyu555kk");
        testprog tp = new testprog ();
        String s="";
        for (int i = 0; i < 100; i++) {
            s=  tp.getStr(i + 5);
        }
        user.put("login", String.valueOf(s));
        user.put("email", "qqqq");
        user.put("password", "12");
        Response response = given()
                .body(user)
                .when()
                .post("http://test-microcam.relex.ru:40000/users")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals("Bad Request", jsonPath.get("message"));

    }
    //Проверяем на некорректные тесты. В этом случае на неправильный lastName
    @Test
    public void UncorrectCreateUserUncorrectlastName(){
        Specifications.installSpecification(Specifications.requestSpec("http://test-microcam.relex.ru:40000/users"), Specifications.responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("firstName", "qwerrtyukk");
        user.put("middleName", "qwerrtyukk");
        user.put("lastName", "qwerrtcccyu555kk");
        testprog tp = new testprog ();
        String s="";
        for (int i = 0; i < 100; i++) {
            s=  tp.getStr(i + 5);
        }
        user.put("login", String.valueOf(s));
        user.put("email", "qqqq");
        user.put("password", "qqqqqqqqqq@example.com");
        Response response = given()
                .body(user)
                .when()
                .post("http://test-microcam.relex.ru:40000/users")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals("Bad Request", jsonPath.get("message"));

    }
    //Проверяем на некорректные тесты. В этом случае на неправильный middleName
    @Test
    public void UncorrectCreateUserUncorrectmiddleName(){
        Specifications.installSpecification(Specifications.requestSpec("http://test-microcam.relex.ru:40000/users"), Specifications.responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("firstName", "qwerrtyukk");
        user.put("middleName", "qwerrt11yukk");
        user.put("lastName", "qwerrtyukk");
        testprog tp = new testprog ();
        String s="";
        for (int i = 0; i < 100; i++) {
            s=  tp.getStr(i + 5);
        }
        user.put("login", String.valueOf(s));
        user.put("email", "qqqq");
        user.put("password", "qqqqqqqqqq@example.com");
        Response response = given()
                .body(user)
                .when()
                .post("http://test-microcam.relex.ru:40000/users")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals("Bad Request", jsonPath.get("message"));

    }
    //Проверяем на некорректные тесты. В этом случае на неправильный firstName
    @Test
    public void UncorrectCreateUserUncorrectfirstName(){
        Specifications.installSpecification(Specifications.requestSpec("http://test-microcam.relex.ru:40000/users"), Specifications.responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("firstName", "qwe11111rrtyukk");
        user.put("middleName", "qwerrtyukk");
        user.put("lastName", "qwerrtyukk");
        testprog tp = new testprog ();
        String s="";
        for (int i = 0; i < 100; i++) {
            s=  tp.getStr(i + 5);
        }
        user.put("login", String.valueOf(s));
        user.put("email", "qqqq");
        user.put("password", "qqqqqqqqqq@example.com");
        Response response = given()
                .body(user)
                .when()
                .post("http://test-microcam.relex.ru:40000/users")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals("Bad Request", jsonPath.get("message"));

    }

//Проверяем на некорректные тесты. В этом случае на неправильный имаил
    @Test
    public void UncorrectCreateUserUncorrectMail(){
        Specifications.installSpecification(Specifications.requestSpec("http://test-microcam.relex.ru:40000/users"), Specifications.responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("firstName", "qwerrtyukk");
        user.put("middleName", "qwerrtyukk");
        user.put("lastName", "qwerrtyukk");
        testprog tp = new testprog ();
        String s="";
        for (int i = 0; i < 100; i++) {
            s=  tp.getStr(i + 5);
        }
        user.put("login", String.valueOf(s));
        user.put("email", "qqqq");
        user.put("password", "1234546qqqqqqAAAA!!!!!");
        Response response = given()
                .body(user)
                .when()
                .post("http://test-microcam.relex.ru:40000/users")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals("Bad Request", jsonPath.get("message"));

    }


    //Проверяем на некорректные тесты. В этом случае на повторный логин
    @Test
    public void UncorrectCreateUserUncorrectLogin(){
        Specifications.installSpecification(Specifications.requestSpec("http://test-microcam.relex.ru:40000/users"), Specifications.responseSpecError409());
        Map<String, String> user = new HashMap<>();
        user.put("firstName", "qwerrtyukk");
        user.put("middleName", "qwerrtyukk");
        user.put("lastName", "qwerrtyukk");

        user.put("login", "12345678");
        user.put("email", "qqqqqqqqqq@example.com");
        user.put("password", "1234546qqqqqqAAAA!!!!!");
        Response response = given()
                .body(user)
                .when()
                .post("http://test-microcam.relex.ru:40000/users")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals("Conflict", jsonPath.get("message"));

    }



}
