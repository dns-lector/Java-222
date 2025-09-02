package learning.itstep.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Lector
 */
public class Java222 {

    /**
     * Entry Point
     * @param args cmd-line arguments
     */
    public static void main(String[] args) {
        // primitives - єдині value-типи. Інші value-типи створити не можна
        // reference-типи -- дозволені для створення користувачами
        byte  b;  // 8 bit
        short s;  // 16 bit
        int   i;  // 32 bit
        long  l;  // 64 bit
        // b = 250; - помилка, всі типи знакові
        b = -125;   // беззнакових варіацій не існує
        
        float  f = 0.001f;  // 32 bit
        double d = 1.5e-2;  // 64 bit
        
        boolean bl = true;
        char c = 'Ї';  // 16 bit, UTF-16
        // робота з примітивами сильно обмежена через те, що вони не є об'єкти
        // зокрема, їх неможна вкладати у колекції
        // List<int> collection = new ArrayList<>(); -- помилка
        // для таких задач кожен тип має покажчиковий аналог
        List<Integer> collection = new ArrayList<>();
        collection.add(10);
        collection.add(20);
        collection.add(30);
        collection.add(40);
        Integer x = 10;        
        Integer y = x;
        x = 20;        
        System.out.println(y);
        int[] arr = new int[10];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = j + 2;            
        }
        for(int k : collection) {
            System.out.printf("%d ", k);
        }
        System.out.println();
        
        String str1 = "Hello";
        String str2 = new String("Hello");
        if(str1 == str2) {  // оператор == - референс-порівняння
            System.out.println("str1 == str2");
        }
        else {
            System.out.println("str1 =/= str2");
        }
        if(str1.equals(str2)) {   // якщо певні, що str1 != null
            System.out.println("str1 equals str2");
        }
        else {
            System.out.println("str1 not equals str2");
        }
        if(Objects.equals(str1, str2)) {   // якщо не певні, що хтось != null
            System.out.println("str1 equals str2");
        }
        else {
            System.out.println("str1 not equals str2");
        }
        System.out.println("----------------------------");
        str2 = "Hello";     // string pooling: якщо компілятор зустрічає
        if(str1 == str2) {  // такий же рядок, як був раніше, то він не 
            System.out.println("str1 == str2");  // створює новий, а бере старий
        }
        else {
            System.out.println("str1 =/= str2");
        }
    }
}
/*
Java
 - Транслятор: компілює у проміжний код (для платформи JRE)
 - Парадигма: ООП
 - Покоління мов: 4GL
 - Вихідний код: файл.java, проміжний код: файл.class
 - ! прив'язка до файлової системи
  = пакет (package) має теж ім'я, що й шлях до нього (директорії)
  = назва класу збігається з назвою файлу

Д.З. Реалізувати програму, яка обчислює добуток матриці на вектор
 - оголосити матрицю-константу, вектор константу та визначити результат
| 1 2 | . | 5 |
| 3 4 |   | 6 |
*/
