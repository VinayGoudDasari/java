package Day13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ComplexCalculationTask implements Runnable {
    private int id;
    private int iterations;

    public ComplexCalculationTask(int id, int iterations) {
        this.id = id;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        System.out.println("Task " + id + " started");

        // Simulate complex calculation or I/O operation
        for (int i = 0; i < iterations; i++) {
            double result = Math.sqrt(i * i + 42);
            // System.out.println("Task " + id + ": " + result); // uncomment to see intermediate results
        }

        System.out.println("Task " + id + " completed");
    }
}

 class ThreadPoolExample {
    public static void main(String[] args) {
        int numThreads = 5; // fixed-size thread pool
        int numTasks = 10; // number of tasks to submit

        ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numTasks; i++) {
            int iterations = (int) (Math.random() * 1000) + 100; // random iterations for each task
            ComplexCalculationTask task = new ComplexCalculationTask(i, iterations);
            threadPool.submit(task);
        }

        threadPool.shutdown(); // shut down the thread pool

        try {
            threadPool.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES); // wait for all tasks to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
