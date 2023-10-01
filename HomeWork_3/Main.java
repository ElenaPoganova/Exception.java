
// Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке,
//  разделенные пробелом:
// Фамилия Имя Отчество датарождения номертелефона пол
// Форматы данных:
// фамилия, имя, отчество - строки
// датарождения - строка формата dd.mm.yyyy
// номертелефона - целое беззнаковое число без форматирования
// пол - символ латиницей f или m.
// Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, 
// вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше 
// данных, чем требуется.
// Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. 
// Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно
// использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано,
//  пользователю выведено сообщение с информацией, что именно неверно.
// Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну 
// строку должны записаться полученные данные, вида
// <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
// Создать словарь где ключ фамилия, значения Лист строк данных
// Если одинаковая фамилия, то записываем под этим же ключем
// Иначе, добавляем данные

package HomeWork3;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class Main {
   
       public static void main(String[] args)    {
       try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные: Фамилия Имя Отчество датарождения номертелефона пол");
            String input = scanner.nextLine();
            String[] data = input.split(" ");

            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных");
            }

            String lastName = checkValidName(data[0]);
            String firstName = checkValidName(data[1]);
            String middleName = checkValidName(data[2]);
            String birthDate = data[3];
            String phoneNumber = data[4];
            String gender = data[5];


            if (!isValidDate(birthDate)) {
                throw new IllegalArgumentException("Неверный формат даты рождения");
            }

            long phone = parsePhoneNumber(phoneNumber);

            if (!isValidGender(gender)) {
                throw new IllegalArgumentException("Неверный пол");
            }

            String fileName = lastName + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "Exception.java\\Home_Work2\\HomeWork3" + fileName, true));
            writer.append(lastName).append(firstName).append(middleName)
                    .append(birthDate).append(" ").append(String.valueOf(phone))
                    .append(gender).append(System.lineSeparator());
            writer.close();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String checkValidName(String name){
        if (name == null || !name.matches("[a-zA-Zа-яА-Я]+")) {
            throw new IllegalArgumentException("Недопустимые символы в ФИО: " + name);
        }

        return name;
    }


    private static boolean isValidDate(String date) {
        // Проверка формата даты
        if (date == null || !date.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);

        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private static long parsePhoneNumber(String phoneNumber) {
        // Парсинг номера телефона
        if (phoneNumber == null || !phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Неверный формат номера телефона");
        }

        try {
            return Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат номера телефона", e);
        }
    }

    private static boolean isValidGender(String gender) {
        // Проверка корректности значения пола
        return gender != null && gender.length() == 1 && (gender.equalsIgnoreCase("m") ||
                gender.equalsIgnoreCase("f"));
    }



}