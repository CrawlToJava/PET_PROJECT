package stringLessons;

import java.time.LocalDateTime;


public class ConcatString {
    public String concat(String str1, String str2) {
            checkEmptyOrNullCase(str1,str2);
            return str1 + " " + str2;
    }

    public int countSymbols(String str1) {
        int count = 0;
        if (str1 == null || str1.isEmpty()) {
            throw new NullStringInputException("Строка пустая");
        }
        str1 = str1.replace(" ", "");
        for (int i = 0; i < str1.length(); i++) {
            count++;
        }
        return count;
    }

    public boolean equals(String str1, String str2) {
        checkEmptyOrNullCase(str1,str2);
        if (str1.equals(str2)){
            return true;
        } else {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        return false;
        }
    }

    private void checkEmptyOrNullCase(String str1, String str2) {
        if (str1 == null || str1.isEmpty()){
            throw new NullStringInputException("Первая строка пустая");}
        if (str2 == null  || str2.isEmpty()){
            throw new NullStringInputException("Вторая строка пустая");}
    }
}
