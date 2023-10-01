package com.jn.test;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyHttpClientForGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    /**
     * 第一个HttpClient例子
     * @throws IOException
     */
    @Test(enabled = false)
    public void test() throws IOException {
        String result;
        HttpGet get = new HttpGet("http://www.baidu.com");
        //HttpClient client = new DefaultHttpClient();
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

    /**
     * 读取文件配置，获取url信息
     */
    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void test2() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        HttpGet get = new HttpGet(url + uri);
        //HttpClient client = new DefaultHttpClient();
        store = new BasicCookieStore();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
        CloseableHttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //获取cookies信息
        List<Cookie> cookies = store.getCookies();
        for(Cookie cookie : cookies){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = " + name);
            System.out.println("cookie value = " + value);
        }
    }

    @Test(dependsOnMethods = {"test2"})
    public void test3() throws IOException {
        String result;
        String uri = bundle.getString("getWithCookies.uri");
        HttpGet get = new HttpGet(url + uri);
        //HttpClient client = new DefaultHttpClient();
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
        CloseableHttpResponse response = client.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("状态码为" + statusCode);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }
}
