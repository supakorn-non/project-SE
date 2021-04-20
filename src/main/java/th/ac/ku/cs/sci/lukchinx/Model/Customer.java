package th.ac.ku.cs.sci.lukchinx.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties
@NoArgsConstructor
public class Customer {
    private int id;
    private String email;
    private String name;
    private String tel;
    private String password;
    private String OTP;

    public Customer(String email, String password){
        this.email = email;
        this.password = password;
    }


    public Customer(String email, String name, String tel, String password){
        this.email = email;
        this.name = name;
        this.tel = tel;
        this.password = password;
    }

}

