package th.ac.ku.cs.sci.lukchinx.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;

    private int cid;
    private String name;
    private String address;
    private String tel;
    private String typeShrimp;
    private int weightShrimp;
    private String sizeShrimp;
    private double price;

    public Order(String name, String address, String tel, String typeShrimp, String sizeShrimp, int weightShrimp, double price){
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.typeShrimp = typeShrimp;
        this.sizeShrimp = sizeShrimp;
        this.weightShrimp = weightShrimp;
        this.price = price;
    }

    public Order(String typeShrimp, String sizeShrimp, int weightShrimp, double price){
        this.typeShrimp = typeShrimp;
        this.sizeShrimp = sizeShrimp;
        this.weightShrimp = weightShrimp;
        this.price = price;
    }
}
