package com.passenger.payment.response;

import com.passenger.payment.model.PassengerInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightBookingAcknowledgement {
	
	private String Status;
	private double totalFare;
	private String pnrNO;
	private PassengerInfo passengerInfo;
	
	
	public static FlightBookingAcknowledgement mapFromPassengerInfo(PassengerInfo passengerInfo, String status, double totalFare, String pnrNo) {
        FlightBookingAcknowledgement acknowledgement = new FlightBookingAcknowledgement();
        acknowledgement.setStatus(status);
        acknowledgement.setTotalFare(totalFare);
        acknowledgement.setPnrNO(pnrNo);
        acknowledgement.setPassengerInfo(passengerInfo);
        return acknowledgement;
    }
	

}
