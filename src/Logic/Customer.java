package Logic;

import java.time.LocalDate;

public class Customer implements InfoObject{
    public enum Gender {
        MALE, FEMALE
    }
    private static int idCountCustomer = 1;
    private int idCustomer;
    private String name;
    private LocalDate birthday;
    private String phone;
    private Gender gender;

    public Customer(int idCustomer, String name, LocalDate birthday, String phone, Gender gender) {
        this.idCustomer = idCustomer;
        idCountCustomer = ++idCustomer;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
    }

    public Customer(String name, LocalDate birthday, String phone, Gender gender) {
        this.idCustomer = idCountCustomer;
        idCountCustomer++;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthday(LocalDate birthday){
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getInfo(){
        return idCustomer + ";" + name + ";" + birthday + ";" + phone + ";" + gender;
    }

    @Override
    public String toString() {
        return "Customer " +
                "id = " + idCustomer +
                ", name = " + name +
                ", birthday = " + birthday +
                ", phone = " + phone +
                ", gender = " + gender + "\n";
    }
}
