package Day13;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public void decrement() {
        counter.decrementAndGet();
    }

    public int getValue() {
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeCounter counter = new ThreadSafeCounter();

        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });

        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.decrement();
            }
        });

        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

        System.out.println("Counter value: " + counter.getValue());

        // Immutable Point example
        ImmutablePoint point = new ImmutablePoint(10, 20);

        Thread thread1 = new Thread(() -> {
            ImmutablePoint newPoint = new ImmutablePoint(point.getX() + 1, point.getY() + 1);
            System.out.println("Thread 1: New point: (" + newPoint.getX() + ", " + newPoint.getY() + ")");
        });

        Thread thread2 = new Thread(() -> {
            ImmutablePoint newPoint = new ImmutablePoint(point.getX() * 2, point.getY() * 2);
            System.out.println("Thread 2: New point: (" + newPoint.getX() + ", " + newPoint.getY() + ")");
        });

        thread1.start();
        thread2.start();
    }
}

class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
