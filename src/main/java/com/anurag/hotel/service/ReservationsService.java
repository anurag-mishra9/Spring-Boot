package com.anurag.hotel.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anurag.hotel.dataInterface.GuestRepository;
import com.anurag.hotel.dataInterface.ReservationRepository;
import com.anurag.hotel.dataInterface.RoomRepository;
import com.anurag.hotel.dataobjects.Guest;
import com.anurag.hotel.dataobjects.Reservation;
import com.anurag.hotel.dataobjects.Room;
import com.anurag.hotel.objects.RoomReservation;

@Service
public class ReservationsService {

	private final RoomRepository roomRepository;
	private final GuestRepository guestRepository;
	private final ReservationRepository reservationRepository ;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public ReservationsService(RoomRepository roomRepository, GuestRepository guestRepository,
			ReservationRepository reservationRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}
	
	public List<RoomReservation> getRoomReservationsForDate(String dateString){
		Date date = this.changeDateStringtoDate(dateString);
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room->{
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if(null!=reservations){
            reservations.forEach(reservation -> {
                Optional<Guest> guest = this.guestRepository.findById(reservation.getGuestId());
                if(null!=guest){
                	Guest guestResponse = guest.get();
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guestResponse.getFirstName());
                    roomReservation.setLastName(guestResponse.getLastName());
                    roomReservation.setGuestId(guestResponse.getId());
                }
            });
        }
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long roomId:roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));
        }
        return roomReservations;
    }
	
	
	public Date changeDateStringtoDate(String dateString){
		Date date = null ; 
		if (dateString != null) {
			try {
				date = DATE_FORMAT.parse(dateString);
			} catch (ParseException e) {
				 System.out.println(e);
			}
		}else {
			date = new Date();
		}
		return date;
	}

	public List<Guest> getHotelGuests() {
		List<Guest> guests = (List<Guest>) this.guestRepository.findAll();
		return guests;
	}
	
	
	
}
