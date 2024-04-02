package com.clinpride.SecurityPostgres.MakePayment.Model;
import com.clinpride.SecurityPostgres.Athlete.model.Athlete;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document
public class MakePayment {
    @Id
    private String id;
    private List<Athlete> registeredAthletes;
    private String pollUrl;
    private String amountPaid;
    private int amountAsInt;
    private String invoice;
    private String phoneNumber;
    private String eventName;
    private String eventId;
    private String status;
    private String paymentMethods;
    private String newInvoice;
    private boolean statusBoolean;
}
