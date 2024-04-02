package Logic;

import Logic.Exception.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Magazine {
    private final static int DISCOUNT_MALE = 15;
    private final static int DISCOUNT_FEMALE = 15;
    private final static int DISCOUNT_ALL = 20;
    private final static int DISCOUNT_BIRTHDAY = 10;
    public final static int DISCOUNT_NONE = 0;
    private final String FEMALE = "f";
    private final String MALE = "m";
    private List<Order> orders;
    private List<Customer> customers;
    private List<Product> goods;

    public Magazine() {
        orders = new ArrayList<Order>();
        customers = new ArrayList<Customer>();
        goods = new ArrayList<Product>();
    }

    /**
     * Добавление нового покупателя
     * @param name имя покупателя
     * @param birthday день рождения
     * @param phone телефон
     * @param gender пол
     */
    public void addCustomer(String name, String birthday, String phone, String gender) {
        System.out.println("step 2");
        try {
            if (checkName(name) && checkDayFormat(birthday) && checkPhone(phone) && checkGender(gender)){
                System.out.println("step 3");
                if (gender.equals(MALE) || gender.equals(Customer.Gender.MALE.name())) {
                    customers.add(new Customer(name, LocalDate.parse(birthday), phone, Customer.Gender.MALE));
                    System.out.println("customers m");
                } else if (gender.equals(FEMALE) || gender.equals(Customer.Gender.FEMALE.name())){
                    customers.add(new Customer(name, LocalDate.parse(birthday), phone, Customer.Gender.FEMALE));
                    System.out.println("customers f");
                }
            }
        }catch (WrongDateFormat | WrongPhoneFormat| WrongGenderFormat | WrongNameFormat e){
            System.out.println("Ошибка с добавлением пользователя. " + e.getMessage());
        }
    }
    public void addCustomer(String data) {
        String[] dataParse = data.split(";");
        addCustomer(dataParse[1],dataParse[2],dataParse[3],dataParse[4]);

//        try {
//            //String id, String name, String birthday, String phone, String gender
//            System.out.println(data);
//            String[] dataParse = data.split(";");
//            System.out.println(Arrays.toString(dataParse));
//            if (checkName(dataParse[1]) && checkDayFormat(dataParse[2]) && checkPhone(dataParse[3]) && checkGender(dataParse[4])){
//                if (dataParse[4].equals(MALE)) {
//                    customers.add(new Customer(Integer.parseInt(dataParse[0]), dataParse[1],
//                            LocalDate.parse(dataParse[2]), dataParse[3], Customer.Gender.MALE));
//                } else if (dataParse[4].equals(FEMALE)){
//                    customers.add(new Customer(Integer.parseInt(dataParse[0]), dataParse[1],
//                            LocalDate.parse(dataParse[2]), dataParse[3], Customer.Gender.FEMALE));
//                }
//                return true;
//            }
//        }catch (WrongDateFormat | WrongPhoneFormat| WrongGenderFormat | WrongNameFormat | NumberFormatException
//                | IndexOutOfBoundsException e){
//            System.out.println("Ошибка с добавлением пользователя. " + e.getMessage());
//        }

    }

    /**
     * Поиск покупателя по Id в списке
     * @param idCustomer Id покупателя
     * @return объект типа Customer
     * @throws UserNotFoudException ошибка при неправильном ID
     */
    public Customer getCustomer (int idCustomer) throws UserNotFoudException{
        idCustomer--;
        if (idCustomer < 0)
                throw new UserNotFoudException("id должен быть больше 0. " + (idCustomer));
        if (idCustomer >= customers.size()) {
            throw new UserNotFoudException("Пользователь с id " + (idCustomer) + " не найден");
        }
        return customers.get(idCustomer);
    }

    public List<Product> getGoods() {
        return goods;
    }

    public List<Order> getOrders() {
        return orders;
    }
    public List<Customer> getCustomers() {
        return customers;
    }

    public int getOrdersAmount(){
        return orders.size();
    }

    /**
     * Добавление нового продукта в перечень
     * @param name Наименование продукта
     * @param cost стоимость
     */
    public void addProduct(String name, int cost){
        try {
            if (checkName(name)){
                goods.add(new Product(name,cost));
            }
        }catch (WrongNameFormat e){
            System.out.println("Ошибка с добавлением товара " + e.getMessage());
        }
    }

    public void addProduct(String data){
        try {
            String[] dataParse = data.split(";");
            if (checkName(dataParse[1])){
                goods.add(new Product(Integer.parseInt(dataParse[0]),// ID Product
                        dataParse[1],// name Product
                        Integer.parseInt(dataParse[2])));//cost Product
            }
        }catch (WrongNameFormat | IndexOutOfBoundsException | NumberFormatException e){
            System.out.println("Ошибка с добавлением товара " + e.getMessage());
        }
    }

    /**
     * Добавление тестовой информации
     */
    public void addInfo(){
        //add Goods
        addProduct("milk", 60);
        addProduct("bread", 40);
        addProduct("beer",100);
        addProduct("cheese",200);
        addProduct("meat",390);
        //add Customers
        addCustomer("Tom", "1980-12-10","89997778884","m");
        addCustomer("Bob", "1995-02-20","89993334445","m");
        addCustomer("Alice", "1990-07-15","89994448887","f");

    }
    public void addInfo(String type, List<String> data){
        switch (type){
            case "Product":
                if (!goods.isEmpty()) goods.clear();
                for (String loadData:
                     data) {
                    addProduct(loadData);
                }
                break;
            case "Customer":
                if (!customers.isEmpty()) customers.clear();
                for (String loadData:
                        data) {
                    addCustomer(loadData);
                }
                break;
            case "Order":
                orders.clear();
                for (String loadData:
                        data) {
                    addOrder(loadData);
                }
                break;
            default:
                System.out.println("Некоректный ввод данных");
                break;
        }
    }

    public int addOrder(String data){

//        idOrder; ID customer; dateOrder; discount;
//        name Product; amount Product
        System.out.println(data);

        int idOrder = -1;
        try {
            String[] dataParse = data.split(";");

//            addOrder(getCustomer(Integer.parseInt(dataParse[1])), dataParse[4], ,Integer.parseInt(dataParse[3])
//            );
            int size = dataParse.length;
            List<OrderItem> orderItems = new ArrayList<>();
            for (int i = 4; i < size; i = i + 2){
                orderItems.add(new OrderItem( searchProduct(goods, dataParse[i]),Integer.parseInt(dataParse[i+1])));
            }


            orders.add(new Order(Integer.parseInt(dataParse[0]) , //ID order
                    orderItems,//Product
                    getCustomer(Integer.parseInt(dataParse[1])), //customer
                    LocalDate.parse(dataParse[2]),//dateOrder
                    Integer.parseInt(dataParse[3])));//discount
            idOrder = Integer.parseInt(data.split(";")[0]);
        } catch (ProductNotFoundException | UserNotFoudException
                 | NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return idOrder;
    }
    /**
     * Добавление нового заказа
     * @param customer покупатель
     * @param product название товара
     * @param amount количество
     * @param discount скидка на заказ
     * @return ID заказа
     */
     public int addOrder(Customer customer,String product, int amount, int discount){
        int idNewOrder = -1;
        try {
             orders.add(new Order(new OrderItem(searchProduct(this.goods,product),checkAmountProduct(amount)),
                     customer, discount));
             idNewOrder = orders.size()-1;
         } catch (ProductNotFoundException | QuantityIsNegativeException e) {
             System.out.println(e.getMessage());
         }
         return idNewOrder;
     }
    /**
     * Добавление нового заказа
     * @param customer покупатель
     * @param goodsInOrder перечень товаров с количеством
     * @param discount скидка на заказ
     * @return ID заказа
     */
     public int addOrder(Customer customer, List<OrderItem> goodsInOrder, int discount) {
         int idNewOrder = -1;
         orders.add(new Order(goodsInOrder,customer,discount));
         idNewOrder = orders.size()-1;
        return idNewOrder;
     }
    /**
     * Добавление товара в существующий заказ
     * @param idOrder ID заказа
     * @param nameProduct название товара
     * @param amount количество
     * @return удалость добавить или нет
     * @throws ProductNotFoundException ошибка при неизвестном продукте
     * @throws WrongIdException ошибка при неправильном ID
     * @throws QuantityIsNegativeException ошибка при получении отрицательного или нулевого значения количества
     */
     public boolean addIntoOrder(int idOrder, String nameProduct, int amount) throws ProductNotFoundException,
             WrongIdException, QuantityIsNegativeException {
        if (idOrder >= orders.size())
            throw new WrongIdException(idOrder);
        orders.get(idOrder).addIntoOrder(searchProduct(this.goods,nameProduct),amount);
        return true;
     }

    /**
     * покупка товара
     * @param magazine экземпляр магазина
     * @param idCustomer ID покупателя
     * @param goodsList перечень товаров в формате "название;количество" "milk;3;bread;1"
     * @param celebration праздничная дата
     */
     public static void buy(Magazine magazine, int idCustomer, String goodsList, Celebration celebration){
         try {
             int idOrder;
             List<OrderItem> goodsForOrder = parseData(magazine.getGoods(),goodsList);
             switch (celebration){
                 case MARCH_8 -> {
                     if (magazine.getCustomer(idCustomer).getGender().equals(Customer.Gender.FEMALE)){
                         idOrder = magazine.addOrder(magazine.getCustomer(idCustomer),goodsForOrder,DISCOUNT_FEMALE);
                     }else
                         idOrder = magazine.addOrder(magazine.getCustomer(idCustomer),goodsForOrder,DISCOUNT_NONE);
                     break;
                 }
                 case FEBRUARY_23 -> {
                     if (magazine.getCustomer(idCustomer).getGender().equals(Customer.Gender.MALE)) {
                         idOrder = magazine.addOrder(magazine.getCustomer(idCustomer), goodsForOrder, DISCOUNT_MALE);
                     } else {
                         idOrder = magazine.addOrder(magazine.getCustomer(idCustomer),goodsForOrder,DISCOUNT_NONE);
                     }
                     break;
                 }
                 case NEW_YEAR -> {
                     idOrder = magazine.addOrder(magazine.getCustomer(idCustomer), goodsForOrder, DISCOUNT_ALL);
                     break;
                 }
                 default -> {
                     if (magazine.checkBirthDayNow(magazine.getCustomer(idCustomer).getBirthday())) {
                         idOrder = magazine.addOrder(magazine.getCustomer(idCustomer), goodsForOrder, DISCOUNT_BIRTHDAY);
                     } else {
                         idOrder = magazine.addOrder(magazine.getCustomer(idCustomer),goodsForOrder,DISCOUNT_NONE);
                     }
                 }
             }
             System.out.println(magazine.getOrders().get(idOrder));
         } catch (UserNotFoudException | WrongGoodsFormat e) {
             System.out.println(e.getMessage());
         }
     }

    /**
     * Расшифровка строки с перечнем товаров и количеством
     * @param goods перечень существующих товаров в магазине
     * @param goodsList перечень новых товаров
     * @return перечень товаров в виде строк для заказа
     * @throws WrongGoodsFormat ошибка при неправильном формате строки с товарами
     */
     private static List<OrderItem> parseData(List<Product> goods, String goodsList) throws WrongGoodsFormat{
        if (goodsList == null)  throw new WrongGoodsFormat("Данные отсутствуют");
        String[] parseGoods =  goodsList.split(";");
         if (parseGoods.length%2 != 0) throw new WrongGoodsFormat("Нехватает данных для преобразования" );
         List<OrderItem> goodsAfterParse = new ArrayList<OrderItem>();
         for (int i = 0; i < parseGoods.length; i=i+2) {
             try {
                 goodsAfterParse.add(new OrderItem(
                         searchProduct(goods, parseGoods[i]),
                         checkAmountProduct(Integer.parseInt(parseGoods[i+1]))));
             }catch (NumberFormatException e){
                 System.out.println("Неправильный формат количества " + parseGoods[i+1]);
             }catch (ProductNotFoundException e){
                 System.out.println(e.getMessage());
             }catch (QuantityIsNegativeException e){
                 System.out.println(e.getMessage());
             }
         }
         return goodsAfterParse;
     }

    @Override
    public String toString() {
         StringBuilder magazineInfo = new StringBuilder("Magazine\norders\n");
        for (Order order:
             orders) {
            magazineInfo.append(order);
        }
        magazineInfo.append("customers \n");
        for (Customer customer:
             customers) {
            magazineInfo.append(customer);
        }
        magazineInfo.append("goods\n");
        for (Product product:
             goods) {
            magazineInfo.append(product);
        }
        magazineInfo.append("Всего заказов - ").append(getOrdersAmount());
        return magazineInfo.toString();
    }
    /**
     * Поиск продукта в товарах магазина по названию
     * @param goods перечень товаров магазина
     * @param nameProduct название продукта
     * @return экземпляр товара
     * @throws ProductNotFoundException ошибка если товар не найден
     */
    private static Product searchProduct(List<Product> goods, String nameProduct) throws ProductNotFoundException {
        for (Product product:
             goods) {
            if (product.getName().equals(nameProduct)) {
                return product;
            }
        }
        throw new ProductNotFoundException(nameProduct);
    }
    /**
     * Проверка на день рождения
     * @param birthDay дата для проверки
     * @return результат проверки true если сегодня день рождения
     */
    private boolean checkBirthDayNow(LocalDate birthDay){
        int todayMount = LocalDate.now().getMonthValue();
        int todayDay = LocalDate.now().getDayOfMonth();
        return birthDay.getMonthValue() == todayMount && birthDay.getDayOfMonth() == todayDay;
    }
    /**
     * Проверка количества товара
     * @param amountProduct количество товара
     * @return количество товара
     * @throws QuantityIsNegativeException ошибка если количество нулевое или меньше 0
     */
    private static int checkAmountProduct (int amountProduct) throws QuantityIsNegativeException {
        if (amountProduct > 0) return amountProduct;
        else throw new QuantityIsNegativeException("Неправильное количество товара - " + amountProduct);
    }
    /**
     * Проверка формата даты
     * @param day строка с датой
     * @return true если формат даты правильный
     * @throws WrongDateFormat ошибка если неправильный формат даты
     */
    private boolean checkDayFormat(String day) throws WrongDateFormat {
        if (day == null ) throw new WrongDateFormat("Отсутствует дата");
        if (day.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
            return true;
        }else throw new WrongDateFormat(day);
    }
    /**
     * Проверка формата телефона
     * @param phone строка с телефоном
     * @return true если формат даты правильный
     */
    private boolean checkPhone(String phone) throws WrongPhoneFormat{
        if (phone == null ) throw new WrongPhoneFormat("Отсутствует телефон");
        if (!phone.matches("^[0-9]{11}") ) throw new WrongPhoneFormat(phone);
        return true;
    }
    /**
     * Проверка формата пола
     * @param gender строка с указанием пола
     * @return если формат даты правильный
     */
    private boolean checkGender(String gender) throws WrongGenderFormat{
        if (gender == null ) throw new WrongGenderFormat("Отсутствует указание пола");
        if (gender.equals(MALE) || gender.equals(FEMALE) ||
                gender.equals(Customer.Gender.MALE.name()) ||
                gender.equals(Customer.Gender.FEMALE.name()))
            return true;
        else throw new WrongGenderFormat(gender);
    }
    /**
     * Проверка формата имени
     * @param name строка с именем
     * @return если формат имени названия правильный
     */
    private boolean checkName(String name) throws WrongNameFormat{
        if (name == null ) throw new WrongNameFormat("Отсутствует имя/ название");
        if (name.isEmpty()) throw new WrongNameFormat("Имя не заполнено");
        return true;
    }
}
