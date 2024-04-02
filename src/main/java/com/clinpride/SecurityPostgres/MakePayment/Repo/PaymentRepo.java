package com.clinpride.SecurityPostgres.MakePayment.Repo;

import com.clinpride.SecurityPostgres.MakePayment.Model.MakePayment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepo extends MongoRepository<MakePayment, String> {
    List<MakePayment> findAllByEventId(String eventId);

}
