package com.jpmc.midascore;

import com.jpmc.midascore.component.MidasCore;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.C;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private final MidasCore midasCore;


    public KafkaConsumer(MidasCore midasCore){
        this.midasCore = midasCore;
    }
    @KafkaListener(topics = "${general.kafka-topic}", groupId = "my-group" )
    public void listen(Transaction transaction){

        logger.info("Transaction Received: {}", transaction);
        midasCore.ifValidUpdate(transaction);
    }


}
