package domain.diet;

import java.util.concurrent.BlockingQueue;

public class Dietitian implements Runnable {
    private final BlockingQueue<DietRequest> queue;

    public Dietitian(BlockingQueue<DietRequest> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                DietRequest request = queue.take(); // Блокируется, если очередь пуста
                System.out.println("[Диетолог] Обрабатываю: " + request.getName());
                Thread.sleep(4000); // Имитация обработки
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}