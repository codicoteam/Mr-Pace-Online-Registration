package com.clinpride.SecurityPostgres.MakePayment.Services;

import com.clinpride.SecurityPostgres.MakePayment.Model.MakePayment;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PaymentServices {
    List<MakePayment> getAllPayment();
    String makeWebPayment(MakePayment makePayment);
    String makeMobile(String phoneNumber, MakePayment makePayment);
    List<MakePayment> getPaymentPerEvent(String eventId);
    String updatePaymentStatus(MakePayment makePayment);
}
