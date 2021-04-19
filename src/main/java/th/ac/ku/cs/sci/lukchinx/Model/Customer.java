package th.ac.ku.cs.sci.lukchinx.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {

    private String email;
    private String name;
    private String tel;
    private String password;

    public Customer(String email, String password){
        this.email = email;
        this.password = password;
    }


}

