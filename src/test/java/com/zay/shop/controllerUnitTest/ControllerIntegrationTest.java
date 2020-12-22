package com.zay.shop.controllerUnitTest;

import com.zay.shop.model.Customer;
import com.zay.shop.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllCustomers() throws Exception {
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:"+port+"/customer", List.class);

        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
    }

    @Test
    public void getCustomerById() throws Exception {
        ResponseEntity<Customer> response =
                this.restTemplate.getForEntity("http://localhost:"+port+"/customer/1", Customer.class);

        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
    }

    @Test
    public void getAllOrders() throws Exception {
        ResponseEntity<List> response = 
                this.restTemplate.getForEntity("http://localhost:"+port+"/order",List.class);
        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
    }

    @Test
    public void getOrderById() throws Exception {
        ResponseEntity<Order> response =
                this.restTemplate.getForEntity("http://localhost:"+port+"/order/1", Order.class);

        assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
    }
    
}
