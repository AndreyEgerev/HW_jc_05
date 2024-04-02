package Logic;

public enum Celebration {

    NEW_YEAR("01-01"),
    FEBRUARY_23("02-23"),
    MARCH_8("03-08");

    private String date;
    private Celebration(String date){
        this.date = date;
    }
}
