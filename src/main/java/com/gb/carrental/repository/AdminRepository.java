package com.gb.carrental.repository;

import com.gb.carrental.model.account.Admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminRepository extends AccountRepository {
    public static Map<String, Admin> adminMap = new HashMap<>();
    public static List<Admin> admins = new ArrayList<>();

    @Override
    public void resetPassword(String userId, String password) {

    }
}
