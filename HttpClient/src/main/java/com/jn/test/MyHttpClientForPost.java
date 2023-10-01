package com.jn.test;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyHttpClientForPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

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
        String uri = bundle.getString("postWithCookies.uri");
        HttpPost post = new HttpPost(url + uri);
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
        //设置请求头
        post.setHeader("content-type","application/json");
        //设置请求参数
        JSONObject param = new JSONObject();
        param.put("name","tony");
        param.put("age","23");
        StringEntity stringEntity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(stringEntity);
        //执行post请求
        CloseableHttpResponse response = client.execute(post);
        //判断返回的结果是否符合预期
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("状态码为" + statusCode);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        if(statusCode == 200){
            System.out.println(result);
        }else{
            System.out.println("访问/get/with/cookies接口失败");
        }

        //将返回的结果字符串转换为json对象
        JSONObject jsonObject = new JSONObject(result);
        String tony = (String)jsonObject.get("tony");
        Assert.assertEquals("success",tony);

    }
}
