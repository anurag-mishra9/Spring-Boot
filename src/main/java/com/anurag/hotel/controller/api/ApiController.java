package com.anurag.hotel.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.hotel.service.ReservationsService;

@RestController
@RequestMapping(value = "/webapi")
public class ApiController {
	
	@Autowired
	ReservationsService reservationsService;

	@GetMapping(value = "/reservations")
	public Object getReservations (@RequestParam (name = "date" , required = false ) String dateString  ) {
		return reservationsService.getRoomReservationsForDate(dateString);
		
	}
	

}
