package com.inspirate.reader.core;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.mobilebox.reader.core.LogAccess;
import com.mobilebox.reader.model.Log;
import com.mobilebox.reader.model.Responses;


/**
 * Some test for {@link LogAccess}.
 */
public class LogAccessTest {

  Log appiumLog1 = new Log("message1", "level1");
  Log appiumLog2 = new Log("message2", "level2");
  Log appiumLog3 = new Log("message3", "level3");
  Log appiumLog4 = new Log("message4", "level4");
  Log appiumLog5 = new Log("message5", "level5");

  @AfterClass
  public static void afterClass() {
    LogAccess.INSTANCE.clear();
  }
  
  @Test
  public void testGetLogsShouldReturnTheListOfLogs() throws IOException {
    List<Log> appiumLogs = new ArrayList<Log>();
    appiumLogs.add(appiumLog1);
    appiumLogs.add(appiumLog2);

    LogAccess.INSTANCE.setLogContent(appiumLogs);

    Responses<Log> data = LogAccess.INSTANCE.getLogs();
    String json = new Gson().toJson(data);

    assertThatJson(json).node("items").isArray().ofLength(appiumLogs.size());
    assertThatJson(json).node("size").isEqualTo(appiumLogs.size());
  }

  @Test
  public void testGetLogsShouldReturnTheListOfLogsEmpty() throws IOException {
    List<Log> appiumLogs = new ArrayList<Log>();
    LogAccess.INSTANCE.setLogContent(appiumLogs);

    Responses<Log> data = LogAccess.INSTANCE.getLogs();
    String json = new Gson().toJson(data);

    assertThatJson(json).node("items").isArray().ofLength(appiumLogs.size());
    assertThatJson(json).node("size").isEqualTo(appiumLogs.size());
  }


  @Test
  public void testGetLastLogShouldReturnTheLastLogOfTheListEmpty() throws IOException {
    List<Log> appiumLogs = new ArrayList<Log>();
    LogAccess.INSTANCE.setLogContent(appiumLogs);

    Log data = LogAccess.INSTANCE.getLastLine();
    String json = new Gson().toJson(data);
    assertThatJson(json).isEqualTo("{\"time\":\"\", \"message\":\"\",\"level\":\"\"}");
  }


}
