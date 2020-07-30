package com.gb.rental;

import com.gb.rental.model.common.Address;
import com.gb.rental.model.common.Coordinates;
import com.gb.rental.model.vehicle.*;

import java.util.UUID;

public class TestData {
    public static HireableVehicle getSuvCar() {
        HireableVehicle car = new Car();
        String id = UUID.randomUUID().toString();
        car.setId(id);
        car.setLicensePlateNumber("ka01aa4561");
        car.setQrCode(id);
        car.setMake("TATA");
        car.setMake("Harrier");
        car.setYearOfManufacture(2019);
        car.setMileage(12156);
        car.setNumberOfSeats(5);
        car.setVehicleCategory(VehicleCategory.PASSENGER);
        car.setVehicleStatus(VehicleStatus.AVAILALBE);
        car.setVehicleType(VehicleType.SUV);
        VehicleLocation vehicleLocation = new VehicleLocation();
        vehicleLocation.setAddress(getAddress());
        vehicleLocation.setCoordinates(getCoordinates());
        return car;
    }

    public static Address getAddress() {
        Address address = new Address();
        address.setAddressLine1("Bannerghatta road parking");
        address.setAddressLine2("Bilekkahalli");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setPinCode("600071");
        return address;
    }

    public static Coordinates getCoordinates() {
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(12.459454);
        coordinates.setLatitude(79.459454);
        return coordinates;
    }
}
