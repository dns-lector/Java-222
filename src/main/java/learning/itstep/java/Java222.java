package learning.itstep.java;

import learning.itstep.java.async.Threading;

public class Java222 {

    /**
     * Entry Point
     * @param args cmd-line arguments
     */
    public static void main(String[] args) {
        // new Intro().demo();
        new Threading().demo();
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
