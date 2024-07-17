package Day13;

 class ThreadLifecycleDemo {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());

        // State: NEW
        System.out.println("State after creating thread: " + thread.getState());

        thread.start();
        // State: RUNNABLE
        System.out.println("State after starting thread: " + thread.getState());

        // Ensure the thread has started and reached the synchronized block
        Thread.sleep(3000);

        synchronized (lock) {
            // State: BLOCKED
            System.out.println("State when thread is blocked: " + thread.getState());

            lock.notify();
        }

        // Wait for the thread to complete
        thread.join();
        // State: TERMINATED
        System.out.println("State after thread termination: " + thread.getState());
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    // State: WAITING
                    System.out.println("State before wait: " + Thread.currentThread().getState());
                    lock.wait();
                    // State: RUNNABLE (after being notified)
                    System.out.println("State after notify: " + Thread.currentThread().getState());

                    // State: TIMED_WAITING
                    Thread.sleep(3000);
                    System.out.println("State during sleep: " + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

