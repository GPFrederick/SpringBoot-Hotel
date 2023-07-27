package com.frederick.hotel.service;

import com.frederick.hotel.dto.BookingDto;
import com.frederick.hotel.models.Booking;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public interface BookingService {
    void createBooking(Long clubId, BookingDto bookingDto);
    List<BookingDto> findAllBookings();
    BookingDto findByBookingId(Long bookingId);
    void updateBooking(BookingDto bookingDto);
    void deleteBooking(Long bookingId);
    List<BookingDto> searchBookings(String query);
}
