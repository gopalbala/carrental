package com.gb.carrental.repository;

import com.gb.carrental.model.account.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository extends AccountRepository {
    public static Map<String, User> userMap = new HashMap<>();
    public static List<User> users = new ArrayList<>();
}
