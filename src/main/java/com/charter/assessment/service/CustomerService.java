package com.charter.assessment.service;

import com.charter.assessment.dto.CustomerRequestDTO;

public interface CustomerService {

    Long getCustomerRewardPoints(CustomerRequestDTO name);
}
