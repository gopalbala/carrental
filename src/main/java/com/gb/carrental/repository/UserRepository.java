package com.gb.carrental.repository;

import com.gb.carrental.exceptions.AccountDoesNotExistsException;
import com.gb.carrental.model.account.Account;
import com.gb.carrental.model.account.User;
import com.gb.carrental.model.reservation.VehicleReservation;
import com.gb.carrental.model.vehicle.HireableVehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRepository implements AccountRepository {
    public static Map<String, User> userMap = new HashMap<>();
    public static List<User> users = new ArrayList<>();

    public Account createAccount(Account account) {
        userMap.putIfAbsent(account.getEmail(), (User) account);
        return account;
    }

    public void resetPassword(String userId, String password) throws AccountDoesNotExistsException {
        if (userMap.get(userId) == null)
            throw new AccountDoesNotExistsException("Account does not exist.");
        userMap.get(userId).setPassword(password);
    }

    public List<HireableVehicle> getHiredVehicles(String userId) {

        List<VehicleReservation> vehicleReservationList =
                VehicleReservationRepository.vehicleReservations
                        .stream().filter(vehicleReservation ->
                        vehicleReservation.getUsrId().equalsIgnoreCase(userId))
                        .collect(Collectors.toList());
        return vehicleReservationList.stream()
                .map(vehicleReservation -> vehicleReservation.getVehicle())
                .collect(Collectors.toList());
    }

    public List<HireableVehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                                  LocalDateTime endDate) {
        List<VehicleReservation> vehicleReservationList =
                VehicleReservationRepository.vehicleReservations
                        .stream().filter(vehicleReservation ->
                        vehicleReservation.getUsrId().equalsIgnoreCase(userId) &&
                                vehicleReservation.getReservationDate().isAfter(startDate) &&
                                vehicleReservation.getReservationDate().isBefore(endDate))
                        .collect(Collectors.toList());
        return vehicleReservationList.stream()
                .map(vehicleReservation -> vehicleReservation.getVehicle())
                .collect(Collectors.toList());
    }

}
