package com.gudigudigudi.mdtemplate.fragment;

@CucumberOptions(glue = "com.mytest.steps", format = {"junit:/data/data/com.mytest/JUnitReport.xml", "json:/data/data/com.mytest/JSONReport.json"}, tags = { "~@wip" }, features = "features")
public class CucumberTestCase {
}
