package com.anurag.hotel.dataInterface;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.anurag.hotel.dataobjects.Guest;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

}