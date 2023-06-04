package com.example.diploma.rearranging;

import com.example.diploma.entities.TaskEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private static final int POPULATION_SIZE = 100;
    private static final int MAX_GENERATIONS = 100;
    private static final double MUTATION_RATE = 0.01;
    public static final int MAX_TASKS_PER_DAY = 4;

    private List<Schedule> population;

    public Schedule runGeneticAlgorithm(List<TaskEntity> tasks) {
        initializePopulation(tasks);

        for (int generation = 0; generation < MAX_GENERATIONS; generation++) {
            calculateFitness();
            List<Schedule> newGeneration = new ArrayList<>();

            for (int i = 0; i < POPULATION_SIZE; i++) {
                Schedule parent1 = selectParent();
                Schedule parent2 = selectParent();
                Schedule offspring = crossover(parent1, parent2);
                mutate(offspring);
                newGeneration.add(offspring);
            }

            population = newGeneration;
        }

        calculateFitness();
        return getBestSchedule();
    }

    private void initializePopulation(List<TaskEntity> tasks) {
        population = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            Schedule schedule = new Schedule(tasks); // Генерация случайного распределения задач
            population.add(schedule);
        }
    }

    private void calculateFitness() {
        for (Schedule schedule : population) {
            double fitness = evaluateFitness(schedule);
            schedule.setFitness(fitness);
        }
    }

    private Schedule selectParent() {
        int populationSize = population.size();
        int tournamentSize = populationSize/2;

        // Создаем список для хранения случайно выбранных особей
        List<Schedule> tournament = new ArrayList<>();

        // Заполняем список случайно выбранными особями
        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = (int) (Math.random() * populationSize);
            tournament.add(population.get(randomIndex));
        }

        // Выбираем особь с наиболее высокой приспособленностью из турнира
        Schedule winner = tournament.get(0); // Предполагается, что приспособленность хранится в поле fitness

        for (int i = 1; i < tournamentSize; i++) {
            Schedule current = tournament.get(i);
            if (current.getFitness() > winner.getFitness()) {
                winner = current;
            }
        }

        return winner;
    }

    private Schedule crossover(Schedule parent1, Schedule parent2) {
        int numberOfTasks = parent1.getTasks().size();

        // Случайно выбираем точку разделения
        int crossoverPoint = (int) (Math.random() * numberOfTasks);

        // Создаем новое расписание для потомка
        Schedule child = new Schedule();

        // Добавляем задачи от родительского расписания 1 до точки разделения
        for (int i = 0; i < crossoverPoint; i++) {
            TaskEntity taskFromParent1 = parent1.getTasks().get(i);
            child.addTask(taskFromParent1);
        }

        // Добавляем задачи от родительского расписания 2 после точки разделения
        for (int i = crossoverPoint; i < numberOfTasks; i++) {
            TaskEntity taskFromParent2 = parent2.getTasks().get(i);
            child.addTask(taskFromParent2);
        }

        return child;
    }

    private void mutate(Schedule schedule) {
        Random random = new Random();

        for (int i = 0; i < schedule.size(); i++) {
            if (random.nextDouble() < MUTATION_RATE) {
                TaskEntity task = schedule.getTasks().get(i);
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
    }

    private long generateRandomTime(long startTime, long endTime) {
        // Генерируем случайное время в диапазоне между startTime и endTime
        long randomTime = startTime + (long) (Math.random() * (endTime - startTime));
        return randomTime;
    }

    private Schedule getBestSchedule() {
        Schedule bestSchedule = population.get(0);

        for (Schedule schedule : population) {
            if (schedule.getFitness() > bestSchedule.getFitness()) {
                bestSchedule = schedule;
            }
        }

        return bestSchedule;
    }

    public double evaluateFitness(Schedule schedule) {
        double totalFitness = 0.0;

        // Создаем массив для отслеживания загруженности каждого дня недели
        int[] dailyWorkload = new int[7]; // Предполагается, что неделя состоит из 7 дней

        for (TaskEntity task : schedule.getTasks()) {
            double taskFitness = 0.0;

            // Оценка приспособленности для каждой задачи на основе времени между дедлайном и запланированным временем
            // Чем ближе запланированное время к дедлайну, тем меньше приспособленность
            long plannedTime = task.getPlannedTime().getTime(); // Запланированное время в миллисекундах
            long deadlineTime = task.getDeadlineTime().getTime(); // Дедлайн в миллисекундах

            long timeDifference = deadlineTime - plannedTime; // Разница во времени между дедлайном и запланированным временем

            // Вычисляем загруженность дня недели, на который запланирована задача
            int dayOfWeek = getDayOfWeek(plannedTime);

            // Увеличиваем загруженность для этого дня недели
            dailyWorkload[dayOfWeek]++;

            // Вычисляем приспособленность для этой задачи
            taskFitness = 1.0 / (1.0 + (double) timeDifference);

            totalFitness += taskFitness;
        }

        // Проверяем загруженность дней недели и корректируем приспособленность
        for (int i = 0; i < 7; i++) {
            // Если задачи отсутствуют в этот день, уменьшаем приспособленность
            if (dailyWorkload[i] == 0) {
                totalFitness *= 0.9; // Произвольный коэффициент уменьшения приспособленности
            }
            // Если задач слишком много в этот день, уменьшаем приспособленность
            else if (dailyWorkload[i] > MAX_TASKS_PER_DAY) { // Предполагается, что MAX_TASKS_PER_DAY - максимальное количество задач в день
                totalFitness *= 0.9; // Произвольный коэффициент уменьшения приспособленности
            }
        }

        return totalFitness;
    }

    private int getDayOfWeek(long timeMillis) {
        // Получаем день недели (0 - воскресенье, 1 - понедельник, и т.д.)
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }
}

