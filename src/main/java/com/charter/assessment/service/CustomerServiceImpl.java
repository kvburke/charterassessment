package com.charter.assessment.service;

import com.charter.assessment.dto.CustomerRequestDTO;
import com.charter.assessment.exception.ApiRequestException;
import com.charter.assessment.repository.CustomerRepository;
import com.charter.assessment.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public Long getCustomerRewardPoints(CustomerRequestDTO name) {
        //Sum of rewards points
        Long rewardSum;

        //Check error conditions
        if("".equals(name.getFirstName())||"".equals(name.getLastName())||(name.getFirstName()==null)||(name.getLastName()==null)){
            throw new ApiRequestException("no name provided");
        }
        Long size= customerRepository.doesCustomerExist(name.getFirstName(), name.getLastName());

        if(size==0){
            throw new ApiRequestException("name does not exist");
        }

        //Get purchases of customer
        List<Long> prices= purchaseRepository.findPurchasesByName(name.getFirstName(), name.getLastName());

        Predicate<Long> onePoint= range -> {
            if(range>50 && range<=100){
                return true;
            }
            return false;
        };

        //Filter and sum points for purchases in 50-100 dollar range
        List<Long> pricesForOnePoint = prices.stream().filter(onePoint).collect(Collectors.toList());

        Long sumPricesForOnePoint= pricesForOnePoint.stream().reduce(0L, (a, b) -> a + b) -(50L* pricesForOnePoint.size());

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

        Long sumPricesForTwoPoints= pricesForTwoPoint.stream().map(x->x*2).reduce(0L, (a, b) -> a + b) - (150L* pricesForTwoPoint.size());

        //Recalibrate to 0 customer did not gain any rewards points
        if(sumPricesForTwoPoints<0L){
            sumPricesForTwoPoints= 0L;
        }

        //Calculate and return total points
        rewardSum= sumPricesForOnePoint + sumPricesForTwoPoints;

        return rewardSum;
    }





}
