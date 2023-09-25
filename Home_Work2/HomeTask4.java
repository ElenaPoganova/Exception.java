// Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку. 
// Пользователю должно показаться сообщение, что пустые строки вводить нельзя.

import java.io.IOException;
import java.util.Scanner;
public class HomeTask4 {
    public static void main(String[] args) throws EmptyStringException {
        System.out.print("Введите строку: ");
        System.out.println(InputNotEmptyString());
    }
    static public String InputNotEmptyString() throws EmptyStringException {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        if(result.isEmpty())
            throw new EmptyStringException("Нельзя вводить пустую строку!!!");
        return result;
    }
}

class EmptyStringException extends IOException {
    public EmptyStringException(String message) {
        super(message);
    }
        
}    

