package com.zp.parking.service.imp;

import cn.hutool.json.JSONUtil;
import com.zp.parking.entity.ParkingInfo1;
import com.zp.parking.mapper.TestMapper;
import com.zp.parking.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImp implements TestService {

    @Resource
    TestMapper testMapper;
    @Override
    public String getTestList(ParkingInfo1 parkingInfo1) {
//        return testMapper.getTestList();

        Map<String, String> testList = testMapper.getTestList(parkingInfo1);// alt+enter
        String json = JSONUtil.toJsonPrettyStr(testList);
        System.out.println("这是json字符串: "+json);
        return json;
    }

    @Override
    public int updateCurrentSpaceByName(ParkingInfo1 parkingInfo1) {
        return testMapper.updateCurrentSpaceByName(parkingInfo1);
    }

}
