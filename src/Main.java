import DAO.WorkingFile;
import Logic.*;
import Logic.Exception.UserNotFoudException;
import UI.Menu;


/*
Егерев Андрей
гр 6413
Задача: Эмуляция интернет-магазина
1.Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий)
во вновь созданную папку ./backup
2.Решить задачи из презентации к семинару

либо

Дописать сохранение объектов магазина в файлы, соответсвующие типам объектов.
При записи можно
а) перезаписывать файл полностью
б) * дописывать новые объекты в тот же файл

Любые доработки и улучшения на Ваше усмотрение.
 */
public class Main {
    public static void main(String[] args) {
        Magazine magazine = new Magazine();

        boolean runFlag = true;
        String choiceUser;
        boolean loadData = false;
        while (runFlag) {
            choiceUser = Menu.mainMenu();
            switch (choiceUser) {
                case "1":// работа с магазином
                    selectOne(magazine, loadData);
                    break;
                case "2":// загрузить данные в магазин
                    loadData = loadData(magazine);
                    break;
                case "3":// сделать резервные копии
                    selectTree();
                    break;
                case "4"://выход
                    runFlag = false;
                    System.out.println("Выход");
                    break;
                default:
                    System.out.println("Неверный ввод");
            }
        }
    }

    public static void saveData(Magazine magazine) {
        WorkingFile workingFile = new WorkingFile();
        workingFile.writeData("Product",DataToWrite.dataTo(magazine.getGoods()));
        workingFile.writeData("Customers",DataToWrite.dataTo(magazine.getCustomers()));
        workingFile.writeData("Orders",DataToWrite.dataTo(magazine.getOrders()));
        System.out.println("Data saved");
    }

    public static boolean loadData(Magazine magazine) {
        WorkingFile workingFile = new WorkingFile();
        magazine.addInfo("Product", workingFile.readData("Product"));
        magazine.addInfo("Customer", workingFile.readData("Customers"));
        magazine.addInfo("Order", workingFile.readData("Orders"));
        System.out.println("Data load");
        return true;
    }
    public static void selectOne(Magazine magazine, boolean loadData){
        if (!loadData) magazine.addInfo();
        boolean runFlag = true;
        String choiceUser;
        while (runFlag) {
            choiceUser = Menu.magazineMenu();
            switch (choiceUser) {
                case "1":// покупка
                    Magazine.buy(magazine,1,"milk;2;bread;1", Celebration.NEW_YEAR);
                    System.out.println("Совершена покупка ");
                    break;
                case "2":// просмотр информации о магазине
                    System.out.println(magazine);;
                    break;
                case "3":// сохранить данные в файл
                    saveData(magazine);
                    break;
                case "4"://выход
                    runFlag = false;
                    System.out.println("Выход");
                    break;
                default:
                    System.out.println("Неверный ввод");
            }
        }
    }

    public static void selectTree(){
        WorkingFile workingFile = new WorkingFile();
        workingFile.backupData();
    }
}