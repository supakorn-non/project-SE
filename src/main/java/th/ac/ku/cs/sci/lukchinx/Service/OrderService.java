package th.ac.ku.cs.sci.lukchinx.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;
import th.ac.ku.cs.sci.lukchinx.Model.Order;

import java.util.Collection;

public class OrderService {
    private RestTemplate restTemplate;
    private static final String URL_PATH = "http://localhost:8000/order";

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createOrder(Order order){
        restTemplate.postForObject(URL_PATH+"/create",order,Order.class);
    }

}
