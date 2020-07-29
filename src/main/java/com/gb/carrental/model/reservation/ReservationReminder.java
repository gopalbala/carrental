package com.gb.carrental.model.reservation;

import com.gb.carrental.model.common.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationReminder {
    private LocalDateTime reservationDate;
    private String userId;
    private LocalDateTime createdDate;
    private NotificationStatus notificationStatus;
}
