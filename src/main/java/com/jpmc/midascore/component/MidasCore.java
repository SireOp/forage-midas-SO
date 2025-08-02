package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.UserRepository;
import org.h2.engine.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MidasCore {

    private static final Logger logger = LoggerFactory.getLogger(MidasCore.class);
    private final UserRepository userRepository;

    public MidasCore(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void ifValidUpdate(Transaction transaction ) {

        UserRecord sender = userRepository.findById(transaction.getSenderId());
        UserRecord recipient = userRepository.findById(transaction.getRecipientId());

        if (sender == null || recipient == null) {
            System.out.println("sender or recipient not found");
            return;
        }


        float senderBalance = sender.getBalance();
        float amount = transaction.getAmount();

        logger.info(" Sender: {} (ID: {}) | Balance: {}", sender.getName(), sender.getId(), senderBalance);
        logger.info(" Recipient: {} (ID: {}) | Balance: {}", recipient.getName(), recipient.getId(), recipient.getBalance());
        logger.info(" Requested Transaction Amount: {}", amount);

        if (senderBalance < amount) {
            logger.info("Sender Insufficient balance "+ transaction.getSenderId()+ ",",transaction.getRecipientId());
            return;
        }

        if(senderBalance >= amount) {
            sender.setBalance(senderBalance - amount);
            recipient.setBalance(amount + recipient.getBalance());

            userRepository.save(sender);
            userRepository.save(recipient);

            logger.info("Transaction completed: {} sent ${} to {}. New Balances: sender=${}, recipient=${}",
                    sender.getName(), amount, recipient.getName(), sender.getBalance(), recipient.getBalance());
        }
    }



}
