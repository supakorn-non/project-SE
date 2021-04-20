package th.ac.ku.cs.sci.lukchinx.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cs.sci.lukchinx.Model.Customer;

public class CustomerService {

    private RestTemplate restTemplate;
    private static final String URL_PATH = "http://localhost:8000/customer";

    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createCustomer(Customer customer){
        restTemplate.postForObject(URL_PATH+"/create",customer,Customer.class);
    }


    public Customer login(Customer request){
        try{
            ResponseEntity<Customer> customer = restTemplate.postForEntity(URL_PATH+"/login",request,Customer.class);
            System.out.println(customer.getBody());
            return customer.getBody();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void sendOTP(Customer request){
        ResponseEntity<Customer> customer = restTemplate.postForEntity(URL_PATH+"/get/email",request, Customer.class);
        restTemplate.postForEntity(URL_PATH+"/forget/send/"+customer.getBody().getId(),request,Customer.class);
    }

    public void changePassword(Customer request){
        ResponseEntity<Customer> customer = restTemplate.postForEntity(URL_PATH+"/get/email",request, Customer.class);
        ResponseEntity<Boolean> isCorrect = restTemplate.postForEntity(URL_PATH+"/forget/receive/"+customer.getBody().getId(),request,boolean.class);
        if(isCorrect.getBody()){
            restTemplate.postForEntity(URL_PATH+"/"+customer.getBody().getId()+"/changePassword",request,Customer.class);
        }else{
            System.out.println("fail");
        }
    }

//    public boolean checkOTP(Customer request){
//        ResponseEntity<Boolean> isCorrect = restTemplate.postForEntity(URL_PATH+"/forget/receive/" + request.getId(),request
//                .getOTP(),boolean.class);
//        return isCorrect.getBody();
//    }

//    public void changePassword(Customer request){
//        ResponseEntity<Customer> response = restTemplate.getForEntity(URL_PATH+"/"+request.getId()+"/changePassword",Customer.class);
//    }

}
