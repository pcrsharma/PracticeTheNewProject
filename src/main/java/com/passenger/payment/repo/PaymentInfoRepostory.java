package com.passenger.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passenger.payment.model.PaymentInfo;

public interface PaymentInfoRepostory extends JpaRepository<PaymentInfo,String> {

}
