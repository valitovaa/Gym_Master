import java.util.*;


public class Timetable {

    private final HashMap<DayOfWeek, TreeMap<TimeOfDay, LinkedList<TrainingSession>>> timetable = new HashMap<>();
    public HashMap<String, Integer> coachTrainingCount = new HashMap<>();


    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        TreeMap<TimeOfDay, LinkedList<TrainingSession>> scheduleOfTheDay;
        if (timetable.containsKey(trainingSession.getDayOfWeek())) {
            scheduleOfTheDay = timetable.get(trainingSession.getDayOfWeek());
            LinkedList<TrainingSession> trainingsForTheTime = scheduleOfTheDay.get(trainingSession.getTimeOfDay());
            trainingsForTheTime.add(trainingSession);
            scheduleOfTheDay.put(trainingSession.getTimeOfDay(), trainingsForTheTime);
        } else {
            scheduleOfTheDay = new TreeMap<>();
            LinkedList<TrainingSession> trainingsForTheTime = new LinkedList<>();
            trainingsForTheTime.add(trainingSession);
            scheduleOfTheDay.put(trainingSession.getTimeOfDay(), trainingsForTheTime);
        }
        timetable.put(trainingSession.getDayOfWeek(), scheduleOfTheDay);

    }

    public TreeMap<TimeOfDay, LinkedList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        return timetable.get(dayOfWeek);
    }

    public LinkedList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        TreeMap<TimeOfDay, LinkedList<TrainingSession>> scheduleOfTheDay = timetable.get(dayOfWeek);
        return scheduleOfTheDay.get(timeOfDay);

    }

    public LinkedList<CounterOfTrainings> getCountByCoaches() {
        // Возвращать количество тренировок каждого тренера по убыванию
        LinkedList<CounterOfTrainings> counterOfTrainings = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : coachTrainingCount.entrySet()) {
            CounterOfTrainings coach = new CounterOfTrainings(entry.getKey(), entry.getValue());
            counterOfTrainings.add(coach);
        }

        counterOfTrainings.sort(Comparator.comparing(CounterOfTrainings::getCount).reversed());

        return counterOfTrainings;
    }


    public void countTrainingsByCoach() {

        for (Map.Entry<DayOfWeek, TreeMap<TimeOfDay, LinkedList<TrainingSession>>> entry : timetable.entrySet()) {
            TreeMap<TimeOfDay, LinkedList<TrainingSession>> sessions = entry.getValue();

            for (Map.Entry<TimeOfDay, LinkedList<TrainingSession>> sessionEntry : sessions.entrySet()) {
                LinkedList<TrainingSession> trainingSessions = sessionEntry.getValue();

                for (TrainingSession trainingSession : trainingSessions) {
                    String coachName = trainingSession.getCoach().getName();
                    coachTrainingCount.put(coachName, coachTrainingCount.getOrDefault(coachName, 0) + 1);
                }
            }
        }

    }
}
