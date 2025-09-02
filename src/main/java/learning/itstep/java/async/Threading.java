package learning.itstep.java.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Threading {
    
    public void demo() {
        demoPercent();
    }
    
    /*
    Задача: є деякий сервіс, що повертає дані про рівень місячної інфляції
    (у відсотках). Необхідно розрахувати річну інфляцію, утворивши 12 запитів
    до сервісу (асинхронно)
    */
    static class MonthPercent implements Callable<Double> {
        // Передача аргумента до виконавця - робиться через поле об'єкта
        private final int month;
        
        public MonthPercent(int month) {
            this.month = month;
        }
        
        @Override
        public Double call() throws Exception {
            Thread.sleep(1000);   // імітація часу на виконання запиту НТТР
            return this.month / 10.0;
        }        
    }
    
    private void demoPercent() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        // 1 - 12 s
        // 2 - 6 s
        // 3 - 4 s
        // 4 - 3 s
        // 6 - 2 s
        // 12 - 1.02 s
        List<Future<Double>> tasks = new ArrayList<>();
        long t = System.nanoTime();   // мітка часу початку роботи
        for (int i = 1; i <= 12; i++) {
            tasks.add( threadPool.submit( new MonthPercent(i) ) );            
        }
        try {
            Double sum = 100.0;
            for(Future<Double> task : tasks) {
                Double res = task.get();
                System.out.println(res);
                sum *= (1.0 + res / 100.0);
            }
            System.out.println("---------------------");
            System.out.printf("%.1f ms: %.3f\n", (System.nanoTime() - t) / 1e6, sum);
        } 
        catch (InterruptedException | ExecutionException ex) {
            System.out.println(ex.getMessage());
        }        
        threadPool.shutdown();
    }
    
    
    private void demo2() {
        // керовані запуски потоків - виконавці
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        Future<?> task1 = threadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task 2 - Runnable");
            }            
        });
        
        Future<String> task2 = threadPool.submit(new Callable<String>() {
            @Override  // Callable - з поверненням результату
            public String call() throws Exception {
                System.out.println("Task 2 - Callable executing");
                return "Task 2 - Callable executed";
            }            
        });
        // Виконавець (пул потоків) вимагає зупинення, інакше програма не 
        // зупиняється.
        String res2;
        try {
            res2 = task2.get(); // await task2  /  Task.Result
            System.out.println(res2);
        } 
        catch (InterruptedException | ExecutionException ex) {
            System.out.println(ex.getMessage());
        }
        threadPool.shutdown();
    }
    
    private void demo1() {
        // базовий спосіб використання потоків:
        Runnable task1 = new Runnable() {   // inline implementation
            @Override
            public void run() {
                System.out.println("Task 1 executed");
            }            
        };
        // task1.run();  // синхронний запуск
        new Thread(task1).start();  // запуск в окремому потоці
        // "+" максимальна швидкість виконання
        // "-" некерованість - ми не можемо зупунити потік
    }
}
/*
Асинхронність у Java. ч.1 Багатопоточність
Функціональний інтерфейс - клас, у якому описано
лише один метод. Це дає можливість казати про те,
що об'єкт такого інтерфейсу (класу) можна виконувати.
Такими інтерфейсами є:
Runnable - мінімальні можливості, тільки виконання,
           без повернення результатів та винятків
Callable<T> - з поверненням результату типу Т, а також
              передача винятку з тіла коду

Д.З. Реалізувати метод, що генерує випадкову послідовність
символів (String) заданої довжини. Кожен символ має генеруватись
окремою задачею
*/
