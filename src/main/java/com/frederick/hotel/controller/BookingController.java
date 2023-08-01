package com.frederick.hotel.controller;

import com.frederick.hotel.dto.RoleDto;
import com.frederick.hotel.dto.UserDto;
import com.frederick.hotel.models.Role;
import com.frederick.hotel.security.SecurityUtil;
import com.frederick.hotel.dto.BookingDto;
import com.frederick.hotel.models.Booking;
import com.frederick.hotel.models.User;
import com.frederick.hotel.service.BookingService;
import com.frederick.hotel.service.RoleService;
import com.frederick.hotel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {
    private BookingService bookingService;
    private UserService userService;
    private RoleService roleService;

    public BookingController(BookingService bookingService, UserService userService, RoleService roleService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/bookings")
    public String bookingList(Model model) {
        User user = new User();
        List<BookingDto> bookingDtoList = bookingService.findAllBookings();
        RoleDto roleDto = new RoleDto();
        String userSession = SecurityUtil.getSessionUser();

        if (userSession != null) {
            user = userService.findByEmail(userSession);
            Long roleId = user.getRoles().stream().map(r -> r.getId()).collect(Collectors.toList()).get(0);
            roleDto = roleService.findRoleById(roleId);
            System.out.println("Role -> " + roleDto.getName());
            model.addAttribute("user", user);
            model.addAttribute("roleDto", roleDto);
        }
        model.addAttribute("user", user);
        model.addAttribute("bookingDtoList", bookingDtoList);
        return "bookings-list";
    }

    @GetMapping("/bookings/{bookingId}")
    public String viewBooking(@PathVariable("bookingId") Long bookingId, Model model) {
        User user = new User();
        RoleDto roleDto;
        BookingDto bookingDto = bookingService.findByBookingId(bookingId);
        String userSession = SecurityUtil.getSessionUser();
        if (userSession != null) {
            user = userService.findByEmail(userSession);
            Long roleId = user.getRoles().stream().map(role -> role.getId()).collect(Collectors.toList()).get(0);
            roleDto = roleService.findRoleById(roleId);
            System.out.println("Role -> " + roleDto.getName());
            model.addAttribute("user", user);
            model.addAttribute("role", roleDto);
        }
        model.addAttribute("user", user);
        model.addAttribute("booking", bookingDto);
        return "bookings-detail";
    }

    @GetMapping(value = "/bookings/{userId}/new")
    public String createBookingForm(@PathVariable("userId") Long userId, Model model) {
        Booking booking = new Booking();
        model.addAttribute("userId", userId);
        model.addAttribute("booking", booking);
        return "bookings-create";
    }

    @PostMapping(value = "/bookings/{userId}")
    public String createBooking(@PathVariable("userId") Long userId,
                                @ModelAttribute("booking") BookingDto bookingDto,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("booking", bookingDto);
            return "bookings/create";
        }
        bookingService.createBooking(userId, bookingDto);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings/{bookingId}/edit")
    public String editBookingForm(@PathVariable("bookingId") long bookingId, Model model) {
        BookingDto bookingDto = bookingService.findByBookingId(bookingId);
        model.addAttribute("booking", bookingDto);
        return "bookings-edit";
    }

    @PostMapping("/bookings/{bookingId}/edit")
    public String updateBooking(@PathVariable("bookingId") long bookingId,
                             @Valid @ModelAttribute("booking")
                             BookingDto booking,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("booking", booking);
            return "bookings-edit";
        }
        BookingDto bookingDto = bookingService.findByBookingId(bookingId);
        booking.setId(bookingId);
        booking.setUser(bookingDto.getUser());
        System.out.println("User ID: " + booking.getUser().getId());
        bookingService.updateBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings/{bookingId}/delete")
    public String deleteBooking(@PathVariable("bookingId") Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return "redirect:/bookings";
    }

    @GetMapping(value = "/bookings/search")
    public String searchBooking(@RequestParam(value = "query") String query, Model model) {
        User user = new User();
        RoleDto roleDto = new RoleDto();
        String userSession = SecurityUtil.getSessionUser();
        System.out.println("Query: " + query);
        List<BookingDto> bookingDtoList = bookingService.searchBookings(query);
        System.out.println(bookingDtoList);
        if (userSession != null) {
            user = userService.findByEmail(userSession);
            Long roleId = user.getRoles().stream().map(r -> r.getId()).collect(Collectors.toList()).get(0);
            roleDto = roleService.findRoleById(roleId);
            System.out.println("Role -> " + roleDto.getName());
            model.addAttribute("user", user);
            model.addAttribute("roleDto", roleDto);
        }
        model.addAttribute("user", user);
        model.addAttribute("bookingDtoList", bookingDtoList);
        return "bookings-list";
    }
}
