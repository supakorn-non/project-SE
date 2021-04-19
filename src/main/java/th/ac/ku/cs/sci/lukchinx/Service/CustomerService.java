package th.ac.ku.cs.sci.lukchinx.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;

import java.util.NoSuchElementException;

public class CustomerService {

    private RestTemplate restTemplate;
    private static final String URL_PATH = "http://localhost:8000/api/customer";

    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createCustomer(Customer customer){
        restTemplate.postForObject(URL_PATH,customer,Customer.class);
    }

//    public Customer getByEmail(String email){
//        try {
//            ResponseEntity<Customer> response = restTemplate.getForEntity(URL_PATH,Customer.class);
//            Customer customer = response.getBody();
//            return customer;
//        }
//        catch (Exception e){
//            return null;
//        }
//    }

    public Customer login(Customer request){
        try{
            ResponseEntity<Customer> response = restTemplate.getForEntity(URL_PATH+"/customer/login",Customer.class);
            Customer customer = response.getBody();
            System.out.println(customer.getPassword().toLowerCase());
            return customer;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void changePassword(Customer request){
        ResponseEntity<Customer> response = restTemplate.getForEntity(URL_PATH+"/customer/update",Customer.class);
    }

}
