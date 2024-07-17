package Day13;

class PrintNumbers implements Runnable {
    private String threadName;

    PrintNumbers(String threadName) {
        this.threadName = threadName;
    }

    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println(threadName + ": " + i);
                Thread.sleep(1000); // 1-second delay
            }
        } catch (InterruptedException e) {
            System.out.println(threadName + " interrupted.");
        }
    }
}

 class Main {
    public static void main(String[] args) {
        // Create two threads
        Thread thread1 = new Thread(new PrintNumbers("Thread 2"));
        Thread thread2 = new Thread(new PrintNumbers("Thread 1"));

        // Start the threads
        thread1.start();
        thread2.start();

        try {
            // Wait for both threads to complete
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Both threads have finished execution.");
    }
}

