package com.passenger.payment.response;


import com.passenger.payment.model.PassengerInfo;
import com.passenger.payment.model.PaymentInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightBookingRequest {
	
	private PassengerInfo passengerInfo;
	private PaymentInfo paymentInfo;

}
