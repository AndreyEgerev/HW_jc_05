package Logic;

import Logic.Exception.QuantityIsNegativeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order implements InfoObject{
    private static int idCount = 1;
    private int idOrder;
    private List<OrderItem> orders;
    private Customer customer;
    private LocalDate dateOrder;
    private int discount;

    public Order(int idOrder, List<OrderItem> orders, Customer customer, LocalDate date, int discount) {
        this.idOrder = idOrder;
        idCount = ++idOrder;
        this.orders = orders;
        this.customer = customer;
        this.dateOrder = LocalDate.now();
        this.discount = discount;
    }
    public Order(int idOrder, List<OrderItem> orders, Customer customer, int discount) {
        this(idOrder, orders, customer, LocalDate.now(), discount);
    }

    public Order(List<OrderItem> orders, Customer customer, int discount){
        this(idCount,orders,customer,discount);
    }

    public Order(OrderItem order, Customer customer, int discount) {
        this(idCount,new ArrayList<OrderItem>(List.of(order)),customer,discount);
    }

    public Order(OrderItem order, Customer customer) {
        this(order, customer, Magazine.DISCOUNT_NONE);
    }

    public int getIdOrder() {
        return idOrder;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    /**
     * Добавление продукта в заказ. Если продукт уже есть в заказе, то изменяется его количество
     * @param product товар для добавления в заказ
     * @param amount количество товаров
     * @throws QuantityIsNegativeException исключение при попытке добавить количество равное или меньше 0
     */
    public void addIntoOrder(Product product, int amount) throws QuantityIsNegativeException {
        for (OrderItem existProduct:
             orders) {
            if (existProduct.getProduct().equals(product)) {
                if (existProduct.getAmount() + amount <= 0)
                    throw new QuantityIsNegativeException("Итоговое количество не может быть меньше 1 -" +
                            (existProduct.getAmount() + amount) );
                existProduct.setAmount(existProduct.getAmount() + amount);
                return;
            }
        }
        this.orders.add(new OrderItem(product,amount));
    }

    /**
     * Вывод суммарной стоимости заказа
     * @return суммарная стоимость по всем товарам в заказе
     */
    public int resultCost () {
        int cost = 0;
        for (OrderItem order:
             orders) {
            cost += order.cost();
        }
        return cost;
    }

    /**
     * Вывод суммарной стоимости заказа с учетом скидки
     * @return суммарная стоимость по всем товарам в заказе с учетом скидки
     */
    public double resultCostDiscount () {
       if (discount == 0) return resultCost();
       return resultCost() * ((double) (100-discount)/100);
    }

    public String getInfo(){
        StringBuilder infoOrder = new StringBuilder();
        infoOrder.append(idOrder).append(";").append(customer.getIdCustomer()).
                append(";").append(dateOrder).append(";").append(discount).append(";");
        int i = 0;
        for (OrderItem item:
             orders) {
            infoOrder.append(item.getProduct().getName()).append(";").append(item.getAmount());
            i++;
            if (i < orders.size()) infoOrder.append(";");
        }
        return infoOrder.toString();
    }

    @Override
    public String toString() {
        StringBuilder infoOrder = new StringBuilder("id Order - ");
        infoOrder.append(idOrder).append("\n").append(customer).append(" Date order ").append(dateOrder).append("\n");
        for (OrderItem item:
             orders) {
            infoOrder.append(item);
        }
        infoOrder.append("Cost - ").append(resultCostDiscount()).append(" Total discont - ").append(resultCost()-resultCostDiscount()).append("\n");

        return  infoOrder.toString();
    }
}
