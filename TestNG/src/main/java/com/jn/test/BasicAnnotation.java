package com.jn.test;

import org.testng.annotations.*;

public class BasicAnnotation {
    @Test
    public void test1(){
        System.out.println("第一个testng测试用例");
    }

    @Test
    public void test2(){
        System.out.println("第二个testng测试用例");
    }

    @Test(enabled = false)
    public void test3(){
        System.out.println("忽略测试");
    }

    @Test(timeOut = 1000)
    public void test4(){
        while(true){

        }
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod在每个test方法之前运行");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod在每个test方法之后运行");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass在当前这个类的第一个test方法运行之前运行");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("AfterClass在当前这个类的最后一个test方法运行之后运行");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite在test suite中的所有test运行之前运行");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite在test suite中的所有test运行之后运行");
    }
}
