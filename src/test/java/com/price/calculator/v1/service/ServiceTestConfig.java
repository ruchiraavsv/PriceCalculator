package com.price.calculator.v1.service;


import com.price.calculator.v1.repository.ItemRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"com.price.calculator.v1.service"})
public class ServiceTestConfig {

    @MockBean
    ItemRepository itemRepository;

}
