package com.passenger.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passenger.payment.model.PassengerInfo;

public interface PassengerInfoRepository extends JpaRepository <PassengerInfo,Long>{

}
