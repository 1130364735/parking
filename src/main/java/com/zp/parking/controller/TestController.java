package com.zp.parking.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zp.parking.entity.ParkingInfo1;
import com.zp.parking.service.TestService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/parking")
@Component
@EnableAsync
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping(value = "/getTestList")
    public String getTestList() {
        ParkingInfo1 pi = new ParkingInfo1();
        pi.setParkingName("和平街");
        return testService.getTestList(pi);
    }

//    @GetMapping(value = "/update")
//    public int update() {
//        ParkingInfo1 pi = new ParkingInfo1();
//        pi.setParkingName("温德姆大酒店");
//        int i = testService.updateCurrentSpaceByName(pi);
//
//        // 需要定时执行的任务
//        Runnable runnable = new Runnable() {
//            public void run() {
//                System.out.println("-----定时器-----");
//                testService.updateCurrentSpaceByName(pi);
//            }
//        };
//        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
//        //立即执行，并且每5秒执行一次
//        ses.scheduleAtFixedRate(runnable, 0, 3000, TimeUnit.MILLISECONDS);
//
//
//        return 1;
//    }

    @Async
    @PostMapping(value = "/dujvan")
    @Scheduled(cron = "0 0/6 8-20 * * ?")
    public void dujvan() throws IOException {

        //登陆
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://pkapi.91.plus/api/park/auth/login");
        httpPost.addHeader("Content-type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appKey", "tcpkkeacurcrs5cfnhiv");
        jsonObject.put("appSecret", "iqm1c1xt84fzqgiqk0pmniqpfhv6gz3p");
        StringEntity s = new StringEntity(jsonObject.toString());
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");
        httpPost.setEntity(s);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString));
        JSONObject json = JSONUtil.parseObj(bodyAsString);
        String data = json.getStr("data");
        System.out.println(data);
        JSONObject json2 = JSONUtil.parseObj(data);
        String token = json2.getStr("token");
        System.out.println(token);


        //查询车位数量
        CloseableHttpClient httpClient2 = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://pkapi.91.plus/api/park/parking/space");
        httpGet.addHeader("Content-type", "application/json");
        httpGet.setHeader("Authorization", "Bearer " + token);

        CloseableHttpResponse response2 = httpClient2.execute(httpGet);
        String bodyAsString2 = EntityUtils.toString(response2.getEntity());
        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString2));

        JSONObject json3 = JSONUtil.parseObj(bodyAsString2);
        String data2 = json3.getStr("data");
        System.out.println(data2);
        JSONObject json4 = JSONUtil.parseObj(data2);
        int totalSpace = json4.getInt("totalSpace");
        int usedSpace = json4.getInt("usedSpace");
        int orderSpace = json4.getInt("orderSpace");
        int leftSpace = totalSpace - usedSpace - orderSpace;
        System.out.println("剩余车位:" + leftSpace);


        //更新数据库
        ParkingInfo1 pi = new ParkingInfo1();
        pi.setParkingName("杜鹃公园");
        pi.setCurrentSpace(leftSpace);
        int i = testService.updateCurrentSpaceByName(pi);


    }

    @Async
    @PostMapping(value = "/beimencaishi")
    @Scheduled(cron = "0 0/6 8-20 * * ?")
    public void beimencaishi() throws IOException {

        //登陆
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://pkapi.91.plus/api/park/auth/login");
        httpPost.addHeader("Content-type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appKey", "tcpkvt4kmdnltgwmbmxi");
        jsonObject.put("appSecret", "h5z5hu3e1tnjn2xjx6r6aqjqk4wy2lix");
        StringEntity s = new StringEntity(jsonObject.toString());
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");
        httpPost.setEntity(s);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString));
        JSONObject json = JSONUtil.parseObj(bodyAsString);
        String data = json.getStr("data");
        System.out.println(data);
        JSONObject json2 = JSONUtil.parseObj(data);
        String token = json2.getStr("token");
        System.out.println(token);


        //查询车位数量
        CloseableHttpClient httpClient2 = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://pkapi.91.plus/api/park/parking/space");
        httpGet.addHeader("Content-type", "application/json");
        httpGet.setHeader("Authorization", "Bearer " + token);

        CloseableHttpResponse response2 = httpClient2.execute(httpGet);
        String bodyAsString2 = EntityUtils.toString(response2.getEntity());
        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString2));

        JSONObject json3 = JSONUtil.parseObj(bodyAsString2);
        String data2 = json3.getStr("data");
        System.out.println(data2);
        JSONObject json4 = JSONUtil.parseObj(data2);
        int totalSpace = json4.getInt("totalSpace");
        int usedSpace = json4.getInt("usedSpace");
        int orderSpace = json4.getInt("orderSpace");
        int leftSpace = totalSpace - usedSpace - orderSpace;
        System.out.println("剩余车位:" + leftSpace);


        //更新数据库
        ParkingInfo1 pi = new ParkingInfo1();
        pi.setParkingName("北门菜市");
        pi.setCurrentSpace(leftSpace);
        int i = testService.updateCurrentSpaceByName(pi);


    }

    @Async
    @PostMapping(value = "/piduquzhengwuzhongxin")
    @Scheduled(cron = "0 0/6 8-20 * * ?")
    public void piduquzhengwuzhongxin() throws IOException {

        //登陆
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://pkapi.91.plus/api/park/auth/login");
        httpPost.addHeader("Content-type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appKey", "tcpksrd70qyls39bwo7t");
        jsonObject.put("appSecret", "wl4gik0cz1lldabp2qzyuajc137v1tzn");
        StringEntity s = new StringEntity(jsonObject.toString());
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");
        httpPost.setEntity(s);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString));
        JSONObject json = JSONUtil.parseObj(bodyAsString);
        String data = json.getStr("data");
        System.out.println(data);
        JSONObject json2 = JSONUtil.parseObj(data);
        String token = json2.getStr("token");
        System.out.println(token);


        //查询车位数量
        CloseableHttpClient httpClient2 = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://pkapi.91.plus/api/park/parking/space");
        httpGet.addHeader("Content-type", "application/json");
        httpGet.setHeader("Authorization", "Bearer " + token);

        CloseableHttpResponse response2 = httpClient2.execute(httpGet);
        String bodyAsString2 = EntityUtils.toString(response2.getEntity());
        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString2));

        JSONObject json3 = JSONUtil.parseObj(bodyAsString2);
        String data2 = json3.getStr("data");
        System.out.println(data2);
        JSONObject json4 = JSONUtil.parseObj(data2);
        int totalSpace = json4.getInt("totalSpace");
        int usedSpace = json4.getInt("usedSpace");
        int orderSpace = json4.getInt("orderSpace");
        int leftSpace = 600-totalSpace - usedSpace - orderSpace;
        System.out.println("剩余车位:" + leftSpace);


        //更新数据库
        ParkingInfo1 pi = new ParkingInfo1();
        pi.setParkingName("郫都区政务中心");
        pi.setCurrentSpace(leftSpace);
        int i = testService.updateCurrentSpaceByName(pi);


    }

    @Async
    @PostMapping(value = "/heping")
    @Scheduled(cron = "0 0/6 8-20 * * ?")
    public void heping() throws  IOException {

        //登陆
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://pkapi.91.plus/api/park/auth/login");
        httpPost.addHeader("Content-type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appKey", "tcpkwfb6piiuqn5mosrr");
        jsonObject.put("appSecret", "bekdexhm0dwtywe8u7wejthoulnm1ros");
        StringEntity s = new StringEntity(jsonObject.toString());
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");
        httpPost.setEntity(s);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString));
        JSONObject json = JSONUtil.parseObj(bodyAsString);
        String data = json.getStr("data");
        System.out.println(data);
        JSONObject json2 = JSONUtil.parseObj(data);
        String token = json2.getStr("token");
        System.out.println(token);

        //查询车位数量
        CloseableHttpClient httpClient2 = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://pkapi.91.plus/api/park/parking/space");
        httpGet.addHeader("Content-type", "application/json");
        httpGet.setHeader("Authorization", "Bearer " + token);

        CloseableHttpResponse response2 = httpClient2.execute(httpGet);
        String bodyAsString2 = EntityUtils.toString(response2.getEntity());
        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString2));

        JSONObject json3 = JSONUtil.parseObj(bodyAsString2);
        String data2 = json3.getStr("data");
        System.out.println(data2);
        JSONObject json4 = JSONUtil.parseObj(data2);
        int totalSpace = json4.getInt("totalSpace");
        int usedSpace = json4.getInt("usedSpace");
        int orderSpace = json4.getInt("orderSpace");
        int leftSpace = totalSpace - usedSpace - orderSpace + 20;
        if (leftSpace < 10) {
            leftSpace = leftSpace + 10;
            if (leftSpace<0){
                leftSpace = 0;
            }
        }
//        int leftSpace = totalSpace - usedSpace;
//        if (leftSpace<0){
//            leftSpace = 0;
//        }
        System.out.println("剩余车位:" + leftSpace);

        //更新数据库
        ParkingInfo1 pi = new ParkingInfo1();
        pi.setParkingName("和平街");
        pi.setCurrentSpace(leftSpace);
        int i = testService.updateCurrentSpaceByName(pi);


    }






//    @Async
//    @PostMapping(value = "/wendemu")
//    @Scheduled(cron = "0 0/11 8-18 * * ?")
//    public void wendemu() throws InterruptedException {
//
//        //温德姆大酒店
//        int random1 = RandomUtil.randomInt(35, 55);
//        System.out.println(random1);
//        List random2 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random2.add(random1 + r);
//        }
//        System.out.print(random2);
//        //郫县金融中心
//        int random3 = RandomUtil.randomInt(85, 195);
//        System.out.println(random3);
//        List random4 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random4.add(random3 + r);
//        }
//        System.out.print(random4);
//        for (int i = 0; i < 10; i++) {
//            int o = (int) random2.get(i);
//            System.out.println(o);
//            ParkingInfo1 pi = new ParkingInfo1();
//            pi.setCurrentSpace(o);
//            pi.setParkingName("温德姆大酒店");
//            testService.updateCurrentSpaceByName(pi);
//
//            int e = (int) random4.get(i);
//            System.out.println(e);
//            ParkingInfo1 pi2 = new ParkingInfo1();
//            pi2.setCurrentSpace(e);
//            pi2.setParkingName("郫县金融中心");
//            testService.updateCurrentSpaceByName(pi2);
//            Thread.sleep(1000 * 60);
//        }
//        System.out.println("结束一轮");
//    }
//
    @Async
    @PostMapping(value = "/aotelaisi")
    @Scheduled(cron = "0 0/11 8-18 * * ?")
    public void aotelaisi() throws InterruptedException {

//        //花样世界奥特莱斯
//        int random1 = RandomUtil.randomInt(975, 994);
//        System.out.println(random1);
//        List random2 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random2.add(random1 + r);
//        }
//        System.out.print(random2);
        //绿地缤纷城
        int random3 = RandomUtil.randomInt(25, 45);
        System.out.println(random3);
        List random4 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int r = RandomUtil.randomInt(-5, 6);
            random4.add(random3 + r);
        }
        System.out.print(random4);
        for (int i = 0; i < 10; i++) {
//            int o = (int) random2.get(i);
//            System.out.println(o);
//            ParkingInfo1 pi = new ParkingInfo1();
//            pi.setCurrentSpace(o);
//            pi.setParkingName("花样世界奥特莱斯");
//            testService.updateCurrentSpaceByName(pi);

            int e = (int) random4.get(i);
            System.out.println(e);
            ParkingInfo1 pi2 = new ParkingInfo1();
            pi2.setCurrentSpace(e);
            pi2.setParkingName("绿地缤纷城");
            testService.updateCurrentSpaceByName(pi2);
            Thread.sleep(1000 * 60);
        }
        System.out.println("结束一轮");

    }
//
//    @Async
//    @PostMapping(value = "/zhengwuzhongxin")
//    @Scheduled(cron = "0 0/11 8-18 * * ?")
//    public void zhengwuzhongxin() throws InterruptedException {
//
//        //郫都区政务中心
//        int random1 = RandomUtil.randomInt(85, 145);
//        System.out.println(random1);
//        List random2 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random2.add(random1 + r);
//        }
//        System.out.print(random2);
//        //北门菜市
//        int random3 = RandomUtil.randomInt(155, 295);
//        System.out.println(random3);
//        List random4 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random4.add(random3 + r);
//        }
//        System.out.print(random4);
//        for (int i = 0; i < 10; i++) {
//            int o = (int) random2.get(i);
//            System.out.println(o);
//            ParkingInfo1 pi = new ParkingInfo1();
//            pi.setCurrentSpace(o);
//            pi.setParkingName("郫都区政务中心");
//            testService.updateCurrentSpaceByName(pi);
//
//            int e = (int) random4.get(i);
//            System.out.println(e);
//            ParkingInfo1 pi2 = new ParkingInfo1();
//            pi2.setCurrentSpace(e);
//            pi2.setParkingName("北门菜市");
//            testService.updateCurrentSpaceByName(pi2);
//            Thread.sleep(1000 * 60);
//        }
//        System.out.println("结束一轮");
//
//    }
    @Async
    @PostMapping(value = "/hengchuangshudu")
    @Scheduled(cron = "0 0/11 8-18 * * ?")
    public void hengchuangshudu() throws InterruptedException {

//        //恒创蜀都
//        int random1 = RandomUtil.randomInt(105, 145);
//        System.out.println(random1);
//        List random2 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random2.add(random1 + r);
//        }
//        System.out.print(random2);
        //蜀都新天地
        int random3 = RandomUtil.randomInt(65, 85);
        System.out.println(random3);
        List random4 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int r = RandomUtil.randomInt(-5, 6);
            random4.add(random3 + r);
        }
        System.out.print(random4);
        for (int i = 0; i < 10; i++) {
//            int o = (int) random2.get(i);
//            System.out.println(o);
//            ParkingInfo1 pi = new ParkingInfo1();
//            pi.setCurrentSpace(o);
//            pi.setParkingName("恒创蜀都");
//            testService.updateCurrentSpaceByName(pi);

            int e = (int) random4.get(i);
            System.out.println(e);
            ParkingInfo1 pi2 = new ParkingInfo1();
            pi2.setCurrentSpace(e);
            pi2.setParkingName("蜀都新天地");
            testService.updateCurrentSpaceByName(pi2);
            Thread.sleep(1000 * 60);
        }
        System.out.println("结束一轮");

    }

    @Async
    @PostMapping(value = "/zhongtieshiji")
    @Scheduled(cron = "0 0/11 8-18 * * ?")
    public void zhongtieshiji() throws InterruptedException {

        //中铁世纪中心
        int random1 = RandomUtil.randomInt(125, 145);
        System.out.println(random1);
        List random2 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int r = RandomUtil.randomInt(-5, 6);
            random2.add(random1 + r);
        }
        System.out.print(random2);
//        //百伦广场
//        int random3 = RandomUtil.randomInt(305, 385);
//        System.out.println(random3);
//        List random4 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random4.add(random3 + r);
//        }
//        System.out.print(random4);

        for (int i = 0; i < 10; i++) {
            int o = (int) random2.get(i);
            System.out.println(o);
            ParkingInfo1 pi = new ParkingInfo1();
            pi.setCurrentSpace(o);
            pi.setParkingName("中铁世纪中心");
            testService.updateCurrentSpaceByName(pi);

//            int e = (int) random4.get(i);
//            System.out.println(e);
//            ParkingInfo1 pi2 = new ParkingInfo1();
//            pi2.setCurrentSpace(e);
//            pi2.setParkingName("百伦广场");
//            testService.updateCurrentSpaceByName(pi2);
            Thread.sleep(1000 * 60);
        }
        System.out.println("结束一轮");

    }
//
//    @Async
//    @PostMapping(value = "/keyunzhongxin")
//    @Scheduled(cron = "0 0/11 8-18 * * ?")
//    public void keyunzhongxin() throws InterruptedException {
//
//        //客运中心
//        int random1 = RandomUtil.randomInt(75, 80);
//        System.out.println(random1);
//        List random2 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random2.add(random1 + r);
//        }
//        System.out.print(random2);
//        //万达广场
//        int random3 = RandomUtil.randomInt(205, 295);
//        System.out.println(random3);
//        List random4 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random4.add(random3 + r);
//        }
//        System.out.print(random4);
//
//        for (int i = 0; i < 10; i++) {
//            int o = (int) random2.get(i);
//            System.out.println(o);
//            ParkingInfo1 pi = new ParkingInfo1();
//            pi.setCurrentSpace(o);
//            pi.setParkingName("客运中心");
//            testService.updateCurrentSpaceByName(pi);
//
//            int e = (int) random4.get(i);
//            System.out.println(e);
//            ParkingInfo1 pi2 = new ParkingInfo1();
//            pi2.setCurrentSpace(e);
//            pi2.setParkingName("万达广场");
//            testService.updateCurrentSpaceByName(pi2);
//            Thread.sleep(1000 * 60);
//        }
//        System.out.println("结束一轮");
//
//    }







}
