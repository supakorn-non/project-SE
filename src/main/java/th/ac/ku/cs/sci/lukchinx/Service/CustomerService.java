package th.ac.ku.cs.sci.lukchinx.Service;

import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;

public class CustomerService {
    private RestTemplate restTemplate;

    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createCustomer(Customer customer){
        String url = "http://localhost:8000/api/customer/";
        restTemplate.postForObject(url,customer,Customer.class);
    }
}
