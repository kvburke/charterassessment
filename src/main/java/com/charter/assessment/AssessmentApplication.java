package com.charter.assessment;

import com.charter.assessment.entity.Customer;
import com.charter.assessment.entity.Purchase;
import com.charter.assessment.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			Customer firstCustomer = new Customer("Kevin", "Abc");

			List<Purchase> purchaseList= new ArrayList<>();
			purchaseList.add(new Purchase("1/21 Cable", new BigDecimal(51.00), firstCustomer));
			purchaseList.add(new Purchase("1/21 Internet", new BigDecimal(101.00), firstCustomer));
			purchaseList.add(new Purchase("1/21 Landline", new BigDecimal(101.00), firstCustomer));
			purchaseList.add(new Purchase("1/21 Landline", new BigDecimal(150.00), firstCustomer));

			firstCustomer.setPurchases(purchaseList);

			customerRepository.save(firstCustomer);

			Customer secondCustomer = new Customer("Anna", "Xyz");

			purchaseList= new ArrayList<>();
			purchaseList.add(new Purchase("1/21 Cable", new BigDecimal(50.00), secondCustomer));
			purchaseList.add(new Purchase("1/21 Internet", new BigDecimal(45.00), secondCustomer));
			purchaseList.add(new Purchase("1/21 Land Line", new BigDecimal(15.00), secondCustomer));

			secondCustomer.setPurchases(purchaseList);

			customerRepository.save(secondCustomer);

			Customer thirdCustomer = new Customer("Dude", "Man");

			purchaseList= new ArrayList<>();
			purchaseList.add(new Purchase("1/21 Cable", new BigDecimal(101.00), thirdCustomer));
			purchaseList.add(new Purchase("1/21 Internet", new BigDecimal(52.00), thirdCustomer));

			thirdCustomer.setPurchases(purchaseList);

			customerRepository.save(thirdCustomer);


		};
	}
}
