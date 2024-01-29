//package com.zp.parking;
//
//import cn.hutool.core.util.RandomUtil;
//import cn.hutool.http.HttpUtil;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import com.zp.parking.entity.ParkingInfo1;
//import com.zp.parking.mapper.TestMapper;
//
//
//import com.zp.parking.tools.RandomHelper;
//import java.util.Base64;
//import com.zp.parking.tools.AesHelper;
//import com.zp.parking.tools.RsaHelper;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.StandardCharsets;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@SpringBootTest
//class ParkingApplicationTests {
//    @Resource
//    TestMapper testMapper;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void test() {
//        ParkingInfo1 pi = new ParkingInfo1();
//        pi.setParkingName("杜鹃公园");
////        List<ParkingInfo1> testList = testMapper.getTestList(pi); // alt+enter
////        for (ParkingInfo1 testEntity : testList) { // testList.for
////            System.out.println(testEntity.toString()); // sout
////        }
//    }
//
////    @Test
////    void update() {
////        ParkingInfo1 pi = new ParkingInfo1();
////        pi.setParkingName("温德姆大酒店");
////        int a = testMapper.updateCurrentSpaceByName(pi);
////        System.out.println(a);
////        System.out.println(pi.toString());
////    }
//
//    @Test
//    void t() {
//        // 需要定时执行的任务
//        Runnable runnable = new Runnable() {
//            public void run() {
//                System.out.println("-----定时器-----");
//            }
//        };
//        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
//        //立即执行，并且每5秒执行一次
//        ses.scheduleAtFixedRate(runnable, 0, 5000, TimeUnit.MILLISECONDS);
//
//
//    }
//
//    @Test
//    public void test1() throws IOException {
//
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("https://pkapi.91.plus/api/park/auth/login");
//        httpPost.addHeader("Content-type", "application/json");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("appKey", "tcpkwbyth7cjusxitonj");
//        jsonObject.put("appSecret", "fi7cbhh768nmeggicj6afume1beepjir");
//        StringEntity s = new StringEntity(jsonObject.toString());
//        s.setContentEncoding("UTF-8");
//        s.setContentType("application/json");
//        httpPost.setEntity(s);
//
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        String bodyAsString = EntityUtils.toString(response.getEntity());
//        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString));
//    }
//
//    @Test
//    public void t2() throws ParseException {
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//        String dateString = formatter.format(currentTime);
//        String fixTime = "00:00:00";
//        String fixTime2 = "06:00:00";
//        Date sd1=formatter.parse(dateString);
//        Date sd2=formatter.parse(fixTime);
//        Date sd3=formatter.parse(fixTime2);
////        if (dateString.compareTo(fixTime)){
////
////        }
//        if (sd1.after(sd2)&&sd1.before(sd3)){
//            System.out.println("现在是晚上");
//        }else {
//            System.out.println("现在是白天");
//        }
//    }
//
//    @Test
//    public void t3() {
//        int random1 = RandomUtil.randomInt(205, 295);
//        System.out.println(random1);
//        List random2 = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            int r = RandomUtil.randomInt(-5, 6);
//            random2.add(random1+r);
//        }
//        //int randomtwo = RandomUtil.randomInt(-5, 6);
//        System.out.println(random2);
//        for (int i =0;i<10;i++){
//            int o = (int) random2.get(i);
//            System.out.println(o);
//        }
//    }
//    @Test
//    public void t4() throws Exception {
//        Date date = new Date();
//        long timestamp = date.getTime() / 1000;
//        JSONObject detail = new JSONObject();
//        detail.put("school","eaf118ef064f469e88a8526e8441603b");
//        detail.put("idcard","650105200301071318");
//        detail.put("name","邱俊翔");
////        String key = RandomHelper.generateNumericAlpha(16);
//        String key = "6EA5E991E21D4273";
//        String enkey = RsaHelper.encryptToString(key, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDD87aMnAwn9VIwTU0JqAt5V27+bu2Y7GF8yBT2S1rxwoP0mZKtQNxUZ7GxosHr4dtkQ9kbBgsGfIbrwF5XzmH/xbi74eAzvqD0tNTF1Oq+LSpwM/SG5QX5YBwBB3aekmfJlju5Mz0UsFjyjRF1IpS28BJjKtwupz/evVYNCak/7wIDAQAB");
//        String encrypted = AesHelper.encryptToString(String.valueOf(detail), key);
//        String sign = RsaHelper.sign(encrypted, "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALp09Cl2ckke6aN+qILrrrqm6lC3IuaVob8lREfO3GcyLN32rYRcXszjQ1AZVRjEGDLUIJSpWPWRIaj/wH+6j+/Fq3ObHcfztaqywlwVK3L9UNi4exr/qkwwz08aX/fAvHkke4dlxYeSUQ8oG7xTZjDnaWXVhsVzHBtXic56RQBPAgMBAAECgYATl1JIQP/FGxBlc1t1Vhon1n3sOTHblIFIOjwwZ1XVKfp44ABfVe1IDeD2u5KrZX89Z/h8ZkbIlpU40paBVTLmSqjTfthHubd0E65LOXZ9IwCmeU1abhAIWPtz4QCsXZk6MCY4P5u1RngOmLdKLBMjAV3A/MhC8Qt/T7GLSAsuAQJBAOHRc3HcSkLBaPXz0RCUzUF0gUu9JDqBr4oWP78fBznsAxbd9fH1Ej7d6VjjXzprEsvFyiYXKcOAWvR5eBPGlYECQQDTYLlQctc2mS5dBn9tgyT41rXIDg2LS1HjVCFCQBIf2XdEGgWGZ4MmMXQmSutJHQ6fqcQwK5WwaPRj7JyIAp3PAkEArdjn/aJQcXAg9CjOrOtB0cB1we9NMtWgZLiYPvUORSNThYk/zRSVBV0mRk5Vg83m3IXbCNQQTPhGDaxuKxtZAQJBAMCO3oJw+6kbRmcsIi1rSOT/A75pPuFkDgcffuTRZTFqdAGwCtRzu7+xUXQIz+pc9BNzHewb6FM6aVgWFkq4x3UCQQCj5OMWtL2e0hs1e2szb2wBpnf1R1anZ6ing9TbHcgfri5B7LNqDqLUQ8fbT4jQA0pL+ymMTybAUq+V/ITZx4rP");
//
//        System.out.println("enkey:"+enkey);
//        System.out.println("encrypted:"+encrypted);
//        System.out.println("sign:"+sign);
//
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://beta.zst2.sztljyjt.com/api/pay");
//        httpPost.addHeader("Content-type", "application/json");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("app", "90001");
//        jsonObject.put("id", "123");
//        jsonObject.put("name", "order-search");
//        jsonObject.put("timestamp", timestamp);
//        jsonObject.put("env", "production");
//        jsonObject.put("data", encrypted);
//        jsonObject.put("key", enkey);
//        jsonObject.put("sign", sign);
//        StringEntity s = new StringEntity(jsonObject.toString());
//        System.out.println("00"+JSONUtil.toJsonPrettyStr(jsonObject));
//        httpPost.setEntity(s);
//
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        String bodyAsString = EntityUtils.toString(response.getEntity());
//        System.out.println(JSONUtil.toJsonPrettyStr(bodyAsString));
//        JSONObject json = JSONUtil.parseObj(bodyAsString);
//        String keyStr = json.getStr("key");
//        System.out.println("keyStr:"+keyStr);
//        String signStr = json.getStr("sign");
//        System.out.println("signStr:"+signStr);
//        String dataStr = json.getStr("data");
//        System.out.println("dataStr:"+dataStr);
////        String decrypt = RsaHelper.decrypt(keyStr, "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALp09Cl2ckke6aN+qILrrrqm6lC3IuaVob8lREfO3GcyLN32rYRcXszjQ1AZVRjEGDLUIJSpWPWRIaj/wH+6j+/Fq3ObHcfztaqywlwVK3L9UNi4exr/qkwwz08aX/fAvHkke4dlxYeSUQ8oG7xTZjDnaWXVhsVzHBtXic56RQBPAgMBAAECgYATl1JIQP/FGxBlc1t1Vhon1n3sOTHblIFIOjwwZ1XVKfp44ABfVe1IDeD2u5KrZX89Z/h8ZkbIlpU40paBVTLmSqjTfthHubd0E65LOXZ9IwCmeU1abhAIWPtz4QCsXZk6MCY4P5u1RngOmLdKLBMjAV3A/MhC8Qt/T7GLSAsuAQJBAOHRc3HcSkLBaPXz0RCUzUF0gUu9JDqBr4oWP78fBznsAxbd9fH1Ej7d6VjjXzprEsvFyiYXKcOAWvR5eBPGlYECQQDTYLlQctc2mS5dBn9tgyT41rXIDg2LS1HjVCFCQBIf2XdEGgWGZ4MmMXQmSutJHQ6fqcQwK5WwaPRj7JyIAp3PAkEArdjn/aJQcXAg9CjOrOtB0cB1we9NMtWgZLiYPvUORSNThYk/zRSVBV0mRk5Vg83m3IXbCNQQTPhGDaxuKxtZAQJBAMCO3oJw+6kbRmcsIi1rSOT/A75pPuFkDgcffuTRZTFqdAGwCtRzu7+xUXQIz+pc9BNzHewb6FM6aVgWFkq4x3UCQQCj5OMWtL2e0hs1e2szb2wBpnf1R1anZ6ing9TbHcgfri5B7LNqDqLUQ8fbT4jQA0pL+ymMTybAUq+V/ITZx4rP");
////        System.out.println(decrypt);
//    }
//
//    @Test
//    void t5(){
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("touser", "payParamsVO.getWxMpOpenId()");
//        jsonObject.put("template_id", "vTd4Z89w9xNGs4kl56GjoKFkNocFE6e0PJHtFzra434");
//        JSONObject data = new JSONObject();
//
//        // 添加键值对
//        JSONObject first = new JSONObject();
//        first.put("value", "通知");
//        data.put("first", first);
//
//        JSONObject keyword1 = new JSONObject();
//        keyword1.put("value", "四川省成都市成华区保和街道锦绣城富丽东方→双流机场");
//        data.put("keyword1", keyword1);
//
//        JSONObject keyword2 = new JSONObject();
//        keyword2.put("value", "1");
//        data.put("keyword2", keyword2);
//
//        JSONObject keyword3 = new JSONObject();
//        keyword3.put("value", "2023年6月12日11:26");
//        data.put("keyword3", keyword3);
//
//        JSONObject keyword4 = new JSONObject();
//        keyword4.put("value", "郑鹏");
//        data.put("keyword4", keyword4);
//
//        JSONObject keyword5 = new JSONObject();
//        keyword5.put("value", "13398137562");
//        data.put("keyword5", keyword5);
//
//        JSONObject remark = new JSONObject();
//        remark.put("value", "感谢使用");
//        data.put("remark", remark);
//
//
//        jsonObject.put("data", data);
//        System.out.println(jsonObject);
//    }
//
//    @Test
//    void t6() throws Exception {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + "69_6yG1bJdRQVKMicqd518rr79tCVhDFnz_Av9Yk8q_3a_IfOsyYYizuXT3hHYStbv4WhSnRug6NrEHjJGg9pmJcXxe7vHE1B7OB6Y-ZmIP6jFTVNdjZ9wR4v0Ze5gTQAcAHAGDN");
//        httpPost.addHeader("Content-type", "application/json");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("touser", "obTcX6rC9u7EouQRjmfKPn3-a2Sk");
//        jsonObject.put("template_id", "vTd4Z89w9xNGs4kl56GjoKFkNocFE6e0PJHtFzra434");
//        JSONObject data = new JSONObject();
//
//        // 添加键值对
//        JSONObject first = new JSONObject();
//        first.put("value", "通知");
//        data.put("first", first);
//
//        JSONObject keyword1 = new JSONObject();
//        keyword1.put("value", "四川省成都市成华区保和街道锦绣城富丽东方→双流机场");
//        data.put("keyword1", keyword1);
//
//        JSONObject keyword2 = new JSONObject();
//        keyword2.put("value", "1");
//        data.put("keyword2", keyword2);
//
//        JSONObject keyword3 = new JSONObject();
//        keyword3.put("value", "2023年6月12日11:26");
//        data.put("keyword3", keyword3);
//
//        JSONObject keyword4 = new JSONObject();
//        keyword4.put("value", "郑鹏");
//        data.put("keyword4", keyword4);
//
//        JSONObject keyword5 = new JSONObject();
//        keyword5.put("value", "13398137562");
//        data.put("keyword5", keyword5);
//
//        JSONObject remark = new JSONObject();
//        remark.put("value", "感谢使用");
//        data.put("remark", remark);
//
//        jsonObject.put("data", data);
//
//        StringEntity stringEntity = new StringEntity(jsonObject.toString());
//        stringEntity.setContentEncoding("UTF-8");
//        stringEntity.setContentType("application/json");
//        httpPost.setEntity(stringEntity);
//        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
//        String bodyString = EntityUtils.toString(httpResponse.getEntity());
//        System.out.println(JSONUtil.toJsonPrettyStr(bodyString));
//    }
//
//}
