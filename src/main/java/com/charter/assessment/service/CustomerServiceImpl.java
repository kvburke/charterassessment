package com.charter.assessment.service;

import com.charter.assessment.dto.CustomerRequestDTO;
import com.charter.assessment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Long getCustomerRewardPoints(CustomerRequestDTO name) {
        //Sum of rewards points
        Long rewardSum;
        //Get purchases of customer
        List<Long> prices= customerRepository.findPurchasesByName(name.getFirstName(), name.getLastName());

        Predicate<Long> onePoint= range -> {
            if(range>50 && range<=100){
                return true;
            }
            return false;
        };

        //Filter and sum points for purchases in 50-100 dollar range
        List<Long> pricesForOnePoint = prices.stream().filter(onePoint).collect(Collectors.toList());

        Long sumPricesForOnePoint= pricesForOnePoint.stream().reduce(0L, (a, b) -> a + b) -50L;

        //Recalibrate to 0 customer did not gain any rewards points
        if(sumPricesForOnePoint<0L){
            sumPricesForOnePoint= 0L;
        }

        Predicate<Long> twoPoints= range -> {
            if(range>100){
                return true;
            }
            return false;
        };

        //Filter and sum points for purchases in 100+ dollar range
        List<Long> pricesForTwoPoint= prices.stream().filter(twoPoints).collect(Collectors.toList());

        Long sumPricesForTwoPoints= pricesForTwoPoint.stream().map(x->x*2).reduce(0L, (a, b) -> a + b) -150L;

        //Recalibrate to 0 customer did not gain any rewards points
        if(sumPricesForTwoPoints<0L){
            sumPricesForTwoPoints= 0L;
        }

        //Calculate and return total points
        rewardSum= sumPricesForOnePoint + sumPricesForTwoPoints;

        return rewardSum;
    }





}
