package br.com.matheusandrad3.aws.lambda.s3sns;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BillManagementLambda {

    ObjectMapper objectMapper = new ObjectMapper();

    public void handler(SNSEvent event) {
        event.getRecords().forEach(snsRecord -> {
            try {
                PatientCheckoutEvent patientCheckoutEvent = objectMapper
                        .readValue(snsRecord.getSNS().getMessage(), PatientCheckoutEvent.class);
                System.out.println(patientCheckoutEvent);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
