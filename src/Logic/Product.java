package Logic;

import java.util.Objects;

public class Product implements InfoObject {
    private static int idCount = 1;
    private int id;
    private String name;
    private int price;

    public Product(int id, String name, int price) {
        this.id = id;
        idCount = ++id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, int price) {
        this.id = idCount;
        idCount++;
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    @Override
    public String getInfo(){
        return id+";"+name+";"+price;
    }

    @Override
    public String toString() {
        return "id - " + id +
                ", name - " + name  +
                ", price - " + price +"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }



}
