package com.example.diploma.rearranging;



import com.example.diploma.entities.TaskEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Schedule {
    private List<TaskEntity> tasks;
    private double fitness;

    public Schedule() {
        tasks = new ArrayList<>();
        fitness = 0.0;
    }

    public Schedule(List<TaskEntity> tasks) {
        Random random = new Random();
        for (int i = 0; i < tasks.size(); i++) {
            if (random.nextDouble() < 0.1) {
                TaskEntity task = tasks.get(i);
                Timestamp deadlineTime = task.getDeadlineTime();
                Timestamp plannedTime = task.getPlannedTime();
                Timestamp currentTime = Timestamp.from(Instant.now());

                if (plannedTime.before(deadlineTime) && plannedTime.after(currentTime)) {
                    // Генерируем новое значение plannedTime в диапазоне между currentTime и deadlineTime
                    long currentTimeMillis = currentTime.getTime();
                    long deadlineTimeMillis = deadlineTime.getTime();
                    long newPlannedTimeMillis = generateRandomTime(currentTimeMillis, deadlineTimeMillis);

                    // Обновляем plannedTime задачи
                    task.setPlannedTime(new Timestamp(newPlannedTimeMillis));
                }
            }
        }
        this.tasks = tasks;
    }

    private long generateRandomTime(long startTime, long endTime) {
        // Генерируем случайное время в диапазоне между startTime и endTime
        long randomTime = startTime + (long) (Math.random() * (endTime - startTime));
        return randomTime;
    }

    public void addTask(TaskEntity task) {
        tasks.add(task);
    }

    public void removeTask(TaskEntity task) {
        tasks.remove(task);
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }
}
