package com.gb.carrental.repository;

import com.gb.carrental.model.account.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepository extends AccountRepository {
    public static Map<String, Driver> driverMap = new HashMap<>();
    public static List<Driver> drivers = new ArrayList<>();
}
