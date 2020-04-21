package com.anurag.hotel.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anurag.hotel.dataobjects.Guest;
import com.anurag.hotel.objects.RoomReservation;
import com.anurag.hotel.service.ReservationsService;

@Controller
@RequestMapping(value="/hotel")
public class ReservationController {
	

	@Autowired
	ReservationsService reservationService;

	
	@RequestMapping(method= RequestMethod.GET , value = "/reservations")
	public String getReservations(@RequestParam(value = "date", required = false )String dateString , Model model ) {
		List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(dateString);
		model.addAttribute("roomReservations",roomReservations);
		return "reservations" ;
	}
	
	@RequestMapping(method= RequestMethod.GET , value = "/guests")
	public String getGuests(Model model ) {
		List<Guest> guests = this.reservationService.getHotelGuests();
		model.addAttribute("guests",guests);
		return "guests" ;
	}
}
