package com.clinpride.SecurityPostgres.MakePayment.Controller;

import com.clinpride.SecurityPostgres.MakePayment.Model.MakePayment;
import com.clinpride.SecurityPostgres.MakePayment.Services.PaymentServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/mr-pace-online-registration/make-payment")
public class PaymentController {
    private final PaymentServices paymentServices;
    @PostMapping("/create-web")
    public ResponseEntity<String> webBasedPayment(@RequestBody MakePayment makePayment){
        String  createPayment  = paymentServices.makeWebPayment(makePayment);
        return ResponseEntity.ok(createPayment);
    }

    @PostMapping("/create-mobile")
    public ResponseEntity<String> mobileBasedPayment(@RequestBody MakePayment makePayment){
        String createPayment  = paymentServices.makeMobile(makePayment.getPhoneNumber(),makePayment);
        return ResponseEntity.ok(createPayment);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<MakePayment>> getAllPayments(){
        List<MakePayment> getAllPayments = paymentServices.getAllPayment();
        return ResponseEntity.ok(getAllPayments);
    }

    @PostMapping("/update")
    public ResponseEntity<String> createUpdatePayment(@RequestBody MakePayment makePayment) {
        try {
            String updateResult = paymentServices.updatePaymentStatus(makePayment);
            return ResponseEntity.ok(updateResult);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PostMapping("/find-payment-by-id")
    public ResponseEntity<List<MakePayment>> findPayment(@RequestParam  String id){
        System.out.println(id);
            List<MakePayment> updateForEvent = paymentServices.getPaymentPerEvent(id);
            return ResponseEntity.ok(updateForEvent);
    }

}
