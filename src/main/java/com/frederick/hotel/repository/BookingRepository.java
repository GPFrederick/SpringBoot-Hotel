package com.frederick.hotel.repository;

import com.frederick.hotel.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.id LIKE CONCAT('%', :query, '%')")
    List<Booking> searchBookings(String query);
}
