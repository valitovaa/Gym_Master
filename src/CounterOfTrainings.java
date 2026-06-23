public class CounterOfTrainings implements Comparable<CounterOfTrainings>{
    String coachName;
    int numberOfTrainings;

    public CounterOfTrainings(String coachName, int numberOfTrainings) {
        this.coachName = coachName;
        this.numberOfTrainings = numberOfTrainings;
    }

    public int getCount() {
        return numberOfTrainings;
    }


    @Override
    public int compareTo(CounterOfTrainings o) {

        return Integer.compare(this.numberOfTrainings, o.numberOfTrainings);
    }
}
