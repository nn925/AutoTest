package com.jn.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ExtentReportTest {
    @Test
    public void test1(){
        Assert.assertEquals(1,2);
    }

    @Test
    public void test2(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void test3(){
        Assert.assertEquals("aaa","aaa");
    }
    @Test
    public void logTest(){
        Reporter.log("这是自己写的日志");
        throw new RuntimeException("这是自己写的异常");
    }


}
