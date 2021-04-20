package th.ac.ku.cs.sci.lukchinx.List;


import th.ac.ku.cs.sci.lukchinx.Model.Order;

public class OrderList {
    private static Order order;

    public static Order getOrder(){
        return order;
    }

    public static void setOrder(Order order) {OrderList.order = order; }
}
