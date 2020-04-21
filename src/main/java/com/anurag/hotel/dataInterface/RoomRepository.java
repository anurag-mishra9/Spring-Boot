package com.anurag.hotel.dataInterface;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anurag.hotel.dataobjects.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
    Room findByNumber(String number);
}
