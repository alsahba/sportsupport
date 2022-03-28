package com.sport.support.plan.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeletePlanCommand {

   private Long trainerId;
   private Long planId;

}
