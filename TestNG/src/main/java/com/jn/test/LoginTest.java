package com.jn.test;

import org.testng.annotations.*;

public class LoginTest {
    @Test
    public void login(){
        System.out.println("登录成功");

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
}
