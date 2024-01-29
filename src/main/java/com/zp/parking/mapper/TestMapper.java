package com.zp.parking.mapper;

import com.zp.parking.entity.ParkingInfo1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {
    String test_table = "ParkingInfo";

    @Select(
            "SELECT * FROM " + test_table + " where ParkingName = #{ParkingName}"
    )
    Map<String,String> getTestList(ParkingInfo1 parkingInfo1);

    @Update("update " + test_table + " set CurrentSpace=#{CurrentSpace} where ParkingName = #{ParkingName}")
    int updateCurrentSpaceByName(ParkingInfo1 parkingInfo1);




}
