import java.util.Comparator;

public class CaloriesComparator implements Comparator<Activity> {
    @Override
    public int compare(Activity a1, Activity a2){
        return Double.compare(a1.calcCaloriesBurned(), a2.calcCaloriesBurned());
    }
}
