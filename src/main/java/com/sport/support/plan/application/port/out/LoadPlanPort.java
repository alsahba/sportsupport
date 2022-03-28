package com.sport.support.plan.application.port.out;

import com.sport.support.plan.adapter.out.persistence.entity.Plan;

public interface LoadPlanPort {
   Plan load(Long id);
}
