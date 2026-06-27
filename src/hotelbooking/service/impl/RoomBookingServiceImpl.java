package hotelbooking.service.impl;

import hotelbooking.service.RoomBookingService;

import java.time.LocalDate;
import java.util.TreeMap;

public class RoomBookingServiceImpl implements RoomBookingService {
    @Override
    public boolean availableForTheDate(LocalDate startingDate, LocalDate endingDate, TreeMap<LocalDate, LocalDate> map) {
        LocalDate ceilingDate = map.ceilingKey(startingDate);
        LocalDate floorDate = map.floorKey(startingDate);
        if (floorDate != null && map.get(floorDate).isAfter(startingDate)) {
            return false;
        }
        return ceilingDate == null || !ceilingDate.isBefore(endingDate);
    }
}
