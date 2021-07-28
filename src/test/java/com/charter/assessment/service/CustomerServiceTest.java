package com.charter.assessment.service;


import com.charter.assessment.dto.CustomerRequestDTO;
import com.charter.assessment.exception.ApiRequestException;
import com.charter.assessment.repository.CustomerRepository;
import com.charter.assessment.repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import java.util.ArrayList;



public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PurchaseRepository purchaseRepository;

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
        when(purchaseRepository.findPurchasesByName(any(), any())).thenReturn(mockedPurchases);
        when(customerRepository.doesCustomerExist(any(), any())).thenReturn(1L);
        //When
        Long total=underTest.getCustomerRewardPoints(name);
        //Then
        assert(total).equals(53L);
    }

    @Test
    public void ifCustomerNameHasNoText(){
        //Given
        CustomerRequestDTO name = new CustomerRequestDTO();
        name.setFirstName("");
        name.setLastName("");
        ArrayList<Long> mockedPurchases = new ArrayList<Long>();
        mockedPurchases.add(51L);
        mockedPurchases.add(101L);
        when(purchaseRepository.findPurchasesByName(any(), any())).thenReturn(mockedPurchases);
        when(customerRepository.doesCustomerExist(any(), any())).thenReturn(0L);
        //When

        //Then
        assertThatThrownBy(() -> underTest.getCustomerRewardPoints(name))
                .isInstanceOf(ApiRequestException.class).hasMessageContaining("no name provided");
    }

    @Test
    public void ifCustomerNameDoesNotExistTest(){
        //Given
        CustomerRequestDTO name = new CustomerRequestDTO();
        name.setFirstName("Kevin");
        name.setLastName("Does not Exist");
        ArrayList<Long> mockedPurchases = new ArrayList<Long>();
        mockedPurchases.add(51L);
        mockedPurchases.add(101L);
        when(purchaseRepository.findPurchasesByName(any(), any())).thenReturn(mockedPurchases);
        when(customerRepository.doesCustomerExist(any(), any())).thenReturn(0L);
        //When

        //Then
        assertThatThrownBy(() -> underTest.getCustomerRewardPoints(name))
                .isInstanceOf(ApiRequestException.class).hasMessageContaining("name does not exist");
    }


}
