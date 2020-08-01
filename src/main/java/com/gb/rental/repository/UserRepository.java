package com.gb.rental.repository;

import com.gb.rental.exceptions.AccountDoesNotExistsException;
import com.gb.rental.model.account.Account;
import com.gb.rental.model.account.User;
import com.gb.rental.model.reservation.VehicleReservation;
import com.gb.rental.model.vehicle.HireableVehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRepository implements AccountRepository {
    public static Map<String, User> userMap = new HashMap<>();
    public static Map<String, User> userUserIdMap = new HashMap<>();
    public static List<User> users = new ArrayList<>();

    public Account createAccount(Account account) {
        userMap.putIfAbsent(account.getEmail(), (User) account);
        userUserIdMap.putIfAbsent(account.getId(), (User) account);
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
                .map(vehicleReservation ->
                        VehicleRepository.vehicleMap
                                .get(vehicleReservation.getAccocatedVehicleId()))
                .collect(Collectors.toList());
    }

    public List<HireableVehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                                  LocalDateTime endDate) {
        List<VehicleReservation> vehicleReservationList =
                VehicleReservationRepository.vehicleReservations
                        .stream().filter(vehicleReservation ->
                        vehicleReservation.getUsrId().equalsIgnoreCase(userId) &&
                                ((vehicleReservation.getDueDate() != null &&
                                        startDate.isBefore(vehicleReservation.getDueDate()))
                                        && (vehicleReservation.getFromDate() != null
                                        && endDate.isAfter(vehicleReservation.getFromDate()))))
                        .collect(Collectors.toList());
        return vehicleReservationList.stream()
                .map(vehicleReservation -> VehicleRepository.vehicleMap
                        .get(vehicleReservation.getAccocatedVehicleId()))
                .collect(Collectors.toList());
    }

}
