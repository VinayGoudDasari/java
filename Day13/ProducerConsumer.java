import java.util.LinkedList;
import java.util.Queue;

class ProducerConsumer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int maxSize;

    public ProducerConsumer(int size) {
        this.maxSize = size;
    }

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() == maxSize) {
                    wait();
                }
                buffer.add(value);
                System.out.println("Produced: " + value);
                value++;
                notify(); // Notify the consumer that new data is available
                Thread.sleep(1000); // Simulate time taken to produce
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    wait();
                }
                int value = buffer.poll();
                System.out.println("Consumed: " + value);
                notify(); // Notify the producer that space is available
                Thread.sleep(1000); // Simulate time taken to consume
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer(5);

        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
