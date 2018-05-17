package com.inspirate.reader.service;

import static com.mobilebox.reader.core.Conts.API_VERSION;
import static com.jayway.restassured.RestAssured.given;
import static java.lang.String.format;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import spark.Spark;

import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import com.mobilebox.reader.core.LogAccess;
import com.mobilebox.reader.exception.ParameterException;
import com.mobilebox.reader.service.Service;

/**
 * Some test for {@link Service}.
 */
public class IntegrationTest {

  private static String port = "5001";
  private static String ip = "127.0.0.1";
  private String url = String.format("http://%s:%s/", ip, port);
  private String fullUrl = url + API_VERSION + "%s";

  @BeforeClass
  public static void beforeClass() throws InterruptedException, ParameterException {
    Service.main(new String[] {"-p", port});
    Spark.awaitInitialization();
  }

  @AfterClass
  public static void afterClass() {
    Spark.stop();
  }

  @Test(enabled = true, priority = 2)
  public void testPostLogs() {
    given()
        .body(
            "{\"params\":{\"message\":\"Console LogLevel: debug\",\"level\":\"level1\"}}")
        .when().post(url);
    given()
        .body(
            "{\"params\":{message\":\"Console LogLevel: debug\",\"level\":\"level2\"}}")
        .when().post(url);
    given()
        .body(
            "{\"params\":{\"message\":\"mensaje3\",\"level\":\"level3\"}}")
        .when().post(url);
    given()
        .body(
            "{\"params\":{\"message\":\"mensaje4\",\"level\":\"level4\"}}")
        .when().post(url);
    given()
        .body(
            "{\"params\":{\"message\":\"mensaje5\",\"level\":\"level5\"}}")
        .when().post(url);
  }

  @Test(enabled = true, priority = 1)
  public void testGetLogsWithoutLogs() {
    String url = format(fullUrl, "/logs");
    ResponseBodyExtractionOptions response =
        given().when().get(url).then().statusCode(200).extract().body();

    assertThatJson(response.asString()).node("data.items").isArray()
        .ofLength(LogAccess.INSTANCE.getLogContent().size());
    assertThatJson(response.asString()).node("data.size").isEqualTo(
        LogAccess.INSTANCE.getLogContent().size());
    assertJsonEquals(response.asString(), "{\"data\":{\"items\":[],\"size\":0}}");
  }

  @Test(enabled = true, priority = 3)
  public void testGetLogs() {
    String url = format(fullUrl, "/logs");
    ResponseBodyExtractionOptions response =
        given().when().get(url).then().statusCode(200).extract().body();

    assertThatJson(response.asString()).node("data.items").isArray()
        .ofLength(LogAccess.INSTANCE.getLogContent().size());
    assertThatJson(response.asString()).node("data.size").isEqualTo(
        LogAccess.INSTANCE.getLogContent().size());
  }

  @Test(enabled = true, priority = 1)
  public void testGetNLogsWithoutLogsNumberOne() {
    String url = format(fullUrl, "/logs/1");
    ResponseBodyExtractionOptions response =
        given().get(url).then().statusCode(200).extract().body();

    assertThatJson(response.asString()).node("data.items").isPresent();
    assertThatJson(response.asString()).node("data.size").isPresent();
  }

  @Test(enabled = true, priority = 3)
  public void testGetNLogsNumberOne() {
    String url = format(fullUrl, "/logs/1");
    ResponseBodyExtractionOptions response =
        given().when().get(url).then().statusCode(200).extract().body();
    System.err.println(response.asString());
    assertThatJson(response.asString()).node("data").isPresent();
    assertThatJson(response.asString()).node("data.size").isPresent().isEqualTo(1);
  }

  @Test(enabled = true, priority = 3)
  public void testGetNLogsString() {
    String url = format(fullUrl, "/logs/hola");
    given().when().get(url).then().statusCode(400).extract().body();
  }
  
  @Test(enabled = true, priority = 3)
  public void testGetNLogsNegativeNumber() {
    String url = format(fullUrl, "/logs/-1");
    given().when().get(url).then().statusCode(500).extract().body();
  }

  @Test(enabled = true, priority = 1)
  public void testGetLastLogsWithoutLogs() {
    String url = format(fullUrl, "/logs/last");
    ResponseBodyExtractionOptions response =
        given().when().get(url).then().statusCode(200).extract().body();

    assertThatJson(response.asString()).node("data.message").isPresent();
    assertThatJson(response.asString()).node("data.level").isPresent();
  }
}
