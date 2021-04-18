package th.ac.ku.cs.sci.lukchinx.Model;

public class Customer {
    private int id;

    private String email;
    private String name;
    private String tel;
    private String password;

    public Customer(int id, String name, String email, String tel, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.password = password;
    }

    public Customer(String name, String email, String tel, String password){
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
