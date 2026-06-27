package hotelbooking.service;

import hotelbooking.entity.Room;

import java.time.LocalDate;
import java.util.Date;
import java.util.TreeMap;

public interface RoomBookingService {
    boolean availableForTheDate(LocalDate startingDate, LocalDate endingDate, TreeMap<LocalDate,LocalDate> map);
}
