package Logic;

public class OrderItem {
    private Product product;
    private int amount;

    public OrderItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public String getNameProduct(){ return product.getName();}
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Вывод стоимости позиции товара
     * @return стоимость позиции
     */
    public int cost() {
        return product.getPrice() * amount;
    }

    @Override
    public String toString() {
        return  "product = " + product +
                ", amount = " + amount +
                ", cost = " + cost() + "\n";
    }
}
