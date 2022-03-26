package com.sport.support.branch.application.port.out;

import com.sport.support.branch.adapter.out.persistence.entity.District;

public interface LoadDistrictPort {
   District loadDistrict(Long id);
}
