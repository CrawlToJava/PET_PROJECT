package stringLessons;

import java.util.Date;

public class ConcatString {
    public static void main(String[] args) throws NullStringInputException{
        ConcatString concatString = new ConcatString();
        concatString.equals("Igor", "Igor");
        concatString.countSymbols("");


    }

    public void concat(String str1, String str2) {

        if (str1 == null || str1.isEmpty())
            throw new NullStringInputException("Нельзя скелить т.к первая строка пустая");
        if (str2 == null  || str2.isEmpty())
            throw new NullStringInputException("Нельзя скелить т.к вторая строка пустая");
        else
            System.out.println(str1 + " " + str2);
    }

    public void equals(String str1, String str2) {
        if (str1 == null || str1.isEmpty())
            throw new NullStringInputException("Первая строка пустая");
        if (str2 == null  || str2.isEmpty())
            throw new NullStringInputException("Вторая строка пустая");
        if (str1.equals(str2))
            System.out.println("Строки равны");
        else {
            Date date = new Date();
            System.out.println(date);
        }
    }

    public void countSymbols(String str1) {
        int count = 0;
        str1 = str1.replace(" ", "");
        if (str1.isEmpty())
            throw new NullStringInputException("Строка пустая");

        for (int i = 0; i < str1.length(); i++) {
            count++;
        }
        System.out.println(count);
    }
}
