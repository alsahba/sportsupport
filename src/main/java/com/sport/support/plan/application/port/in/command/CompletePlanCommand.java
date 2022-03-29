package com.sport.support.plan.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompletePlanCommand {

   private Long planId;
   private Long userId;

}
