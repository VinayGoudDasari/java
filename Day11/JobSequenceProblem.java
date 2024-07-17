package Day11;

import java.util.*;

class Job {
    int id;
    int deadline;
    int profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

 class Main {
    static List<Job> JobSequencing(List<Job> jobs) {
        // Sort the jobs based on their profit in descending order
        Collections.sort(jobs, (a, b) -> b.profit - a.profit);

        // Create a boolean array to store the availability of slots
        boolean[] slots = new boolean[jobs.get(0).deadline + 1];

        // Initialize the result list
        List<Job> result = new ArrayList<>();

        // Iterate over the sorted jobs
        for (Job job : jobs) {
            // Find a free slot for the current job
            for (int i = job.deadline; i > 0; i--) {
                if (!slots[i]) {
                    slots[i] = true;
                    result.add(job);
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(1, 2, 50));
        jobs.add(new Job(2, 1, 40));
        jobs.add(new Job(3, 2, 30));
        jobs.add(new Job(4, 1, 20));
        jobs.add(new Job(5, 3, 60));
        jobs.add(new Job(6, 3, 10));

        List<Job> result = JobSequencing(jobs);

        for (Job job : result) {
            System.out.println("Job " + job.id + " with profit " + job.profit);
        }
    }
}
