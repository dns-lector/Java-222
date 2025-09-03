package learning.itstep.java.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Chaining2 {
    private long t;
    private final Random random = new Random();
    
    public void demo() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        t = System.nanoTime();   // мітка часу початку роботи
        
        Future<?> task = CompletableFuture
                .supplyAsync(randomInt, threadPool)
                .thenApply(signAnalyzer)
                .thenAccept(printer);
        
        try {
            threadPool.shutdown();      // не зупиняє задачі, а лише перестає приймати нові
            threadPool.awaitTermination(3, TimeUnit.SECONDS);
            threadPool.shutdownNow();   // зупиняє всі задачі
        }
        catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private final Supplier<Integer> randomInt = () -> {
        printWithTime("Supplier start");
        sleep();
        printWithTime("Supplier finish");
        return random.nextInt();
    };
    
    private final Function<Integer, String> signAnalyzer = num -> {
        printWithTime("signAnalyzer start");
        sleep();
        printWithTime("signAnalyzer finish");
        return String.format(
                "Число %d %s", 
                num, 
                num > 0 ? "positive" : "0 or negative");                
    };
    
    private final Consumer<String> printer = str -> {
        printWithTime("printer start");
        sleep();
        printWithTime("printer finish with result: " + str);
    };
    
    private void printWithTime(String message) {
        System.out.printf("%.1f ms: %s\n", time(), message);
    }
    
    private double time() {
        return (System.nanoTime() - t) / 1e6;
    }
    
    private void sleep() {
        try {
            Thread.sleep(random.nextInt(100, 500));
        } 
        catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
/*
Робота з зміненими типами даних
Задача:
- згенерувати випадкове число
- сформувати повідомлення щодо знаку числа: позитивне чи негативне
- вивести повідомлення на консоль
= для всіх процесів додати затримку з випадковою тривалістю 100-500 мс

Завдання: додати проміжні результати до повідомлень
3,4 ms: Supplier start
416,5 ms: Supplier finish   WITH RESULT 1561778940
418,5 ms: signAnalyzer start   WITH INPUT 1561778940
609,6 ms: signAnalyzer finish   WITH RESULT 'Number 1561778940 positive'
610,2 ms: printer start
870,3 ms: printer finish with result: Number 1561778940 positive

Д.З. Описати власний клас Point(x,y)
Реалізувати ланцюг асинхронних операцій
- генерується два випадкові числа (масив)
- з масива утворюється точка (Point)
- формується повідомлення про належність точки до квадрантів координатної площини
- повідомлення виводиться на консоль
= для всіх процесів додати затримку з випадковою тривалістю 100-500 мс
*/