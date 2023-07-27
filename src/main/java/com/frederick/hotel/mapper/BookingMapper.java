package com.frederick.hotel.mapper;

import com.frederick.hotel.dto.BookingDto;
import com.frederick.hotel.models.Booking;

public class BookingMapper {

    public static Booking mapToBooking(BookingDto bookingDto) {
        return Booking.builder()
                .id(bookingDto.getId())
                .price(bookingDto.getPrice())
                .methodOfPayment(bookingDto.getMethodOfPayment())
                .startTime(bookingDto.getStartTime())
                .endTime(bookingDto.getEndTime())
                .createdOn(bookingDto.getCreatedOn())
                .updatedOn(bookingDto.getUpdatedOn())
                .user(bookingDto.getUser())
                .build();
    }

    public static BookingDto mapToBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .price(booking.getPrice())
                .methodOfPayment(booking.getMethodOfPayment())
                .startTime(booking.getStartTime())
                .endTime(booking.getEndTime())
                .createdOn(booking.getCreatedOn())
                .updatedOn(booking.getUpdatedOn())
                .user(booking.getUser())
                .build();
    }
}
