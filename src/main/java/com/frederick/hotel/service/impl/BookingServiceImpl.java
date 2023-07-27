package com.frederick.hotel.service.impl;

import com.frederick.hotel.security.SecurityUtil;
import com.frederick.hotel.dto.BookingDto;
import com.frederick.hotel.mapper.BookingMapper;
import com.frederick.hotel.models.Booking;
import com.frederick.hotel.models.User;
import com.frederick.hotel.repository.BookingRepository;
import com.frederick.hotel.repository.UserRepository;
import com.frederick.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.frederick.hotel.mapper.BookingMapper.mapToBooking;
import static com.frederick.hotel.mapper.BookingMapper.mapToBookingDto;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createBooking(Long clubId, BookingDto bookingDto) {
        String username = SecurityUtil.getSessionUser();
        User user = userRepository.findById(clubId).get();
        User userUsername = userRepository.findByUsername(username);
        Booking booking = BookingMapper.mapToBooking(bookingDto);
        booking.setUser(user);
        bookingRepository.save(booking);
    }

    @Override
    public List<BookingDto> findAllBookings() {
        List<Booking> bookingList = bookingRepository.findAll();
        return bookingList.stream().map(BookingMapper::mapToBookingDto).collect(Collectors.toList());
        //return bookingList.stream().map(booking -> BookingMapper.mapToBookingDto(booking)).collect(Collectors.toList());
    }

    @Override
    public BookingDto findByBookingId(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).get();
        return mapToBookingDto(booking);
    }

    @Override
    public void updateBooking(BookingDto bookingDto) {
        String username = SecurityUtil.getSessionUser();
        User user = userRepository.findByUsername(username);
        Booking booking = mapToBooking(bookingDto);
        booking.setUser(user);
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<BookingDto> searchBookings(String query) {
        List<Booking> bookings = bookingRepository.searchBookings(query);
        return bookings.stream().map(BookingMapper::mapToBookingDto).collect(Collectors.toList());
    }

}
