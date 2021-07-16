package com.charter.assessment.service;


import com.charter.assessment.dto.CustomerRequestDTO;
import com.charter.assessment.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;


public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl underTest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRewardPointsForCustomerNameTest(){
        //Given
        CustomerRequestDTO name = new CustomerRequestDTO();
        name.setFirstName("Kevin");
        name.setLastName("Abc");
        ArrayList<Long> mockedPurchases = new ArrayList<Long>();
        mockedPurchases.add(51L);
        mockedPurchases.add(101L);
        when(customerRepository.findPurchasesByName(any(), any())).thenReturn(mockedPurchases);
        //When
        Long total=underTest.getCustomerRewardPoints(name);
        //Then
        assert(total).equals(53L);
    }
}
