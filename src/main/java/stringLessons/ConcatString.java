package stringLessons;

import java.time.LocalDateTime;


public class ConcatString {
    public static void main(String[] args) throws NullStringInputException{
        ConcatString concatString = new ConcatString();



    }

    public void concat(String str1, String str2) {

            checkEmptyOrNullCase(str1,str2);
            System.out.println(str1 + " " + str2);
    }

    public void countSymbols(String str1) {
        int count = 0;
        if (str1 == null || str1.isEmpty()) {
            throw new NullStringInputException("Строка пустая");
        }
        str1 = str1.replace(" ", "");



        for (int i = 0; i < str1.length(); i++) {
            count++;
        }
        System.out.println(count);
    }

    public void equals(String str1, String str2) {
        checkEmptyOrNullCase(str1,str2);
        if (str1.equals(str2)){
            System.out.println("Строки равны");
        } else {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        }

    }

    private void checkEmptyOrNullCase(String str1, String str2) {
        if (str1 == null || str1.isEmpty()){
            throw new NullStringInputException("Первая строка пустая");}
        if (str2 == null  || str2.isEmpty()){
            throw new NullStringInputException("Вторая строка пустая");}
    }
}
