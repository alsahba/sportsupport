package com.sport.support.membership.adapter.out.publisher;

import com.sport.support.membership.application.port.out.PublishWithdrawMoneyPort;
import com.sport.support.shared.common.money.Money;
import com.sport.support.shared.configuration.kafka.KafkaTopic;
import com.sport.support.shared.exception.KafkaException;
import com.sport.support.wallet.application.port.in.command.ChangeBalanceCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class WithdrawMoneyPublisher implements PublishWithdrawMoneyPort {

   private final KafkaTemplate<String, Object> kafkaTemplate;

   @Override
   public void publishWithdrawMoney(Long userId, Money money) {
      try {
         kafkaTemplate.send(KafkaTopic.CHANGE_BALANCE_TOPIC.getTopic(), UUID.randomUUID().toString(),
             new ChangeBalanceCommand(userId, money, true)).get(60, TimeUnit.SECONDS);
      } catch (Exception e) {
         throw new KafkaException(e.getMessage());
      }
   }
}
