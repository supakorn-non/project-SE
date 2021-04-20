package th.ac.ku.cs.sci.lukchinx.List;

import th.ac.ku.cs.sci.lukchinx.Model.Customer;

public class CustomerList {
    private static Customer customer;

    public static Customer getCustomer(){
        return customer;
    }

    public static void setCustomer(Customer customer) {CustomerList.customer = customer; }

}
