package com.clinpride.SecurityPostgres.MakePayment.Services.PaymentsServiceImpl;

import com.clinpride.SecurityPostgres.MakePayment.Model.MakePayment;
import com.clinpride.SecurityPostgres.MakePayment.Repo.PaymentRepo;
import com.clinpride.SecurityPostgres.MakePayment.Services.PaymentServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.paynow.constants.MobileMoneyMethod;
import zw.co.paynow.core.Payment;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.responses.MobileInitResponse;
import zw.co.paynow.responses.StatusResponse;
import zw.co.paynow.responses.WebInitResponse;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ServicesImpl implements PaymentServices {
    private final PaymentRepo paymentRepo;

    @Override
    public List<MakePayment> getAllPayment() {
        return paymentRepo.findAll();
    }

    @Override
    public String makeWebPayment(MakePayment makePayment) {
        try {

            Paynow paynow = new Paynow("16739", "7d15ff0f-9a44-4767-897f-f6b41a33fcc5");
            Payment payment = paynow.createPayment(makePayment.getNewInvoice(), "codicosoftwares@gmail.com");
            payment.add("Athletes", makePayment.getAmountAsInt());
            System.out.println(makePayment.getAmountAsInt());
            WebInitResponse response = paynow.send(payment);
            if (response.isRequestSuccess()) {
                String redirectURL = response.redirectURL();
                System.out.println(redirectURL);
                String pollUrl = response.pollUrl();
                StatusResponse status = paynow.pollTransaction(pollUrl);
                System.out.println("pollurl: "+pollUrl);
                makePayment.setPollUrl(pollUrl);
                paymentRepo.save(makePayment);
                return redirectURL;
            } else {
                System.out.println(response.errors());
                return response.errors();
            }
        }
        catch (Exception e){
            throw e;
        }
    }



    @Override
    public String makeMobile(String phoneNumber, MakePayment makePayment) {
        try {
            Paynow paynow = new Paynow("16739", "7d15ff0f-9a44-4767-897f-f6b41a33fcc5");
            Payment payment = paynow.createPayment("636363", "codicosoftwares@gmail.com");
            payment.add("Furniture", 1.1);

            MobileInitResponse response = paynow.sendMobile(payment, makePayment.getPhoneNumber(), MobileMoneyMethod.ECOCASH);
            if (response.isRequestSuccess()) {
                String pollUrl = response.pollUrl();
                System.out.println("pollurl: "+pollUrl);
                makePayment.setPollUrl(pollUrl);
                paymentRepo.save(makePayment);
                System.out.println(makePayment);
                return "Transaction was successful enter pin on your mobile phone";
            } else {
                return response.errors();
            }
        }catch (Exception e){
            throw e;
        }
    }
    @Override
    public List<MakePayment> getPaymentPerEvent(String eventId) {
        System.out.println(eventId);
        return paymentRepo.findAllByEventId(eventId);
    }

    @Override
    public String updatePaymentStatus(MakePayment makePayment) {
        try {
            Optional<MakePayment> existingPayment = paymentRepo.findById(makePayment.getId());
            if(existingPayment.isPresent()){
                MakePayment updatePayment = existingPayment.get();
                updatePayment.setAmountAsInt(makePayment.getAmountAsInt());
                updatePayment.setStatus(makePayment.getStatus());
                updatePayment.setInvoice(makePayment.getInvoice());
                updatePayment.setPaymentMethods(updatePayment.getPaymentMethods());
                updatePayment.setPollUrl(makePayment.getPollUrl());
                updatePayment.setAmountPaid(updatePayment.getAmountPaid());
                updatePayment.setEventId(updatePayment.getEventId());
                updatePayment.setNewInvoice(makePayment.getNewInvoice());
                updatePayment.setPhoneNumber(makePayment.getPhoneNumber());
                updatePayment.setStatusBoolean(makePayment.isStatusBoolean());
                updatePayment.setEventName(makePayment.getEventName());
                paymentRepo.save(updatePayment);
                return "payment updated successfully ";
            }
            else {
                return "payment not found";
            }
        }catch (Exception e){
            throw e;
        }
    }
}
