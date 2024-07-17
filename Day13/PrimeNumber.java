import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

 class PrimeNumberCalculator {

    private static final int NUM_THREADS = 4;

    public static void main(String[] args) {
        int maxNumber = 100; // Calculate prime numbers up to this number

        // Create a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        try {
            List<Future<List<Integer>>> futures = new ArrayList<>();
            for (int i = 0; i < NUM_THREADS; i++) {
                int start = i * (maxNumber / NUM_THREADS) + 1;
                int end = (i + 1) * (maxNumber / NUM_THREADS);
                futures.add(executor.submit(new PrimeTask(start, end)));
            }

            List<Integer> primeNumbers = new ArrayList<>();
            for (Future<List<Integer>> future : futures) {
                primeNumbers.addAll(future.get());
            }

            // Use CompletableFuture to write the results to a file asynchronously
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    Files.write(Paths.get("prime_numbers.txt"), primeNumbers.toString().getBytes(), StandardOpenOption.CREATE);
                    System.out.println("Prime numbers written to file.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Wait for the write operation to complete
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    static class PrimeTask implements Callable<List<Integer>> {
        private final int start;
        private final int end;

        PrimeTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() {
            List<Integer> primes = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
            return primes;
        }

        private boolean isPrime(int number) {
            if (number <= 1) return false;
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) return false;
            }
            return true;
        }
    }
}
