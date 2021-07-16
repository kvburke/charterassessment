package com.charter.assessment.controller;

import com.charter.assessment.dto.CustomerRequestDTO;
import com.charter.assessment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/getCustomerRewardStats")
    public Long getCustomerRewardsStats(@RequestBody CustomerRequestDTO nameRequest){
        return customerService.getCustomerRewardPoints(nameRequest);
    }
}
