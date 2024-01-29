package com.zp.parking.service;

import com.zp.parking.entity.ParkingInfo1;

public interface TestService {
    public String getTestList(ParkingInfo1 parkingInfo1);

    int updateCurrentSpaceByName(ParkingInfo1 parkingInfo1);

}
