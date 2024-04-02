package Logic;

import java.util.List;

public class DataFromRead {
    public static String[] data(List<String> data){
        String[] parseData = data.get(0).split(";");
        return parseData;

    }
}
