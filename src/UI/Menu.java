package UI;

public class Menu {

    public static String mainMenu(){
        System.out.println("Выберете действие\n" +
                "1 - Начать работу с магазином\n" +
                "2 - Загрузить данные из файла\n" +
                "3 - Сделать резервные копии файлов\n" +
                "4 - Выход ");
        return InputUserData.getData();
    }

    public static String magazineMenu(){
        System.out.println("""
                Выберете действие
                1 - Совершить тестовую покупку
                2 - Посмотреть информацию о магазине
                3 - Сохранить информацию
                4 - Возврат в предыдущее меню""");
        return InputUserData.getData();
    }
}
