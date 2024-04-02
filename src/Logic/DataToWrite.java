package Logic;

import java.util.List;

/*
записывает в файл данные
в зависимости от типа класса
пользователь - все поля
товары - все поля
заказы - поля ID, ID покупателя, дата, скидка, название товара, количество товара
 */
public class DataToWrite {
    public static String dataTo (List objects) {
        StringBuilder info = new StringBuilder();
        int i = 0;
        for (Object infoObject :
                objects) {
            InfoObject temp = (InfoObject) infoObject;
            info.append(temp.getInfo());
            i++;
            if (i < objects.size()) info.append("\n");
        }
        return info.toString();
    }

}
