/*
import domain.diet.DietRequest;

import java.util.concurrent.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;

class SleepingDietitian {
    private static final int MAX_QUEUE_SIZE = 3;
    private static final int TOTAL_CLIENTS = 10;
    private static final int DIETITIAN_COUNT = 2;
    private static final int CONSULTATION_TIME_MS = 4000;
    private static final int CLIENT_INTERVAL_MS = 1000;

    private static final BlockingQueue<DietRequest> queue = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);
    private static final Object lock = new Object();
    private static int sleepingDietitians = DIETITIAN_COUNT;

    public static void main(String[] args) {
        System.out.println(" Система запущена ");
        System.out.printf("Доступно диетологов: %d, размер очереди: %d\n\n",
                DIETITIAN_COUNT, MAX_QUEUE_SIZE);

        // Запуск диетологов (спящих)
        ExecutorService executor = Executors.newFixedThreadPool(DIETITIAN_COUNT);
        for (int i = 0; i < DIETITIAN_COUNT; i++) {
            final int dietitianId = i + 1;
            executor.execute(() -> {
                while (true) {
                    DietRequest request;
                    synchronized (lock) {
                        // Диетолог засыпает, если нет клиентов
                        if (queue.isEmpty()) {
                            try {
                                sleepingDietitians++;
                                System.out.printf("[Диетолог-%d] спит \n",
                                        dietitianId, sleepingDietitians);
                                lock.wait();
                                sleepingDietitians--;
                                System.out.printf("[Диетолог-%d] просыпается\n", dietitianId);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                        request = queue.poll();
                    }

                    // Консультация
                    try {
                        System.out.printf("[Диетолог-%d] начал консультацию %s\n",
                                dietitianId, request.getName());
                        Thread.sleep(CONSULTATION_TIME_MS);
                        System.out.printf("[Диетолог-%d] завершил консультацию %s\n",
                                dietitianId, request.getName());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            });
        }

        // Генератор клиентов
        new Thread(() -> {
            try {
                for (int i = 0; i < TOTAL_CLIENTS; i++) {
                    DietRequest request = new DietRequest(
                            "Клиент-" + (i + 1), 70, 175, 30, null);

                    synchronized (lock) {
                        if (queue.size() < MAX_QUEUE_SIZE) {
                            queue.add(request);
                            System.out.printf("[Клиент-%d] встал в очередь (размер: %d)\n",
                                    i + 1, queue.size());

                            // Будим диетолога, если есть спящие
                            if (sleepingDietitians > 0) {
                                lock.notify();

                            }
                        } else {
                            System.out.printf("[Клиент-%d] ушел (очередь переполнена)\n", i + 1);
                        }
                    }
                    Thread.sleep(CLIENT_INTERVAL_MS);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
*/


import domain.products.Product;
import domain.products.ProductFactory;

import core.UserInputHandler;
import core.ProductSelectionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Получаем сервис из контекста
        ProductSelectionService service = context.getBean(ProductSelectionService.class);

        // Проверяем внедренные зависимости
        System.out.println("=== DI Dependency Check ===");
        // PlantProductFactory и AnimalProductFactory если отключить на время xml вместо прокси
        System.out.println("Plant Factory: " + service.getPlantFactory().getClass().getSimpleName());
        System.out.println("Animal Factory: " + service.getAnimalFactory().getClass().getSimpleName());

        // Дополнительная проверка через создание продуктов
        System.out.println("\n=== Product Creation Test ===");
        System.out.println("Apple: " + service.getPlantFactory().createProduct("Test Apple", 50, 0, 0, 10));
        System.out.println("Chicken: " + service.getAnimalFactory().createProduct("Test Chicken", 100, 20, 5, 0));

        UserInputHandler handler = context.getBean(UserInputHandler.class);
        handler.start();
    }
}