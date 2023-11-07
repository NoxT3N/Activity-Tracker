import java.util.Comparator;

public class TypeComparator implements Comparator<Activity> {
    @Override
    public int compare(Activity a1, Activity a2){
        return a1.getType().compareTo(a2.getType());
    }


}
