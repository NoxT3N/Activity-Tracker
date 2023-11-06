import java.sql.SQLOutput;
import java.util.ArrayList;

public class MainApp {

    public static double avgDistance(ArrayList<Activity> activities, String type) {

        int count = 0;
        double total = 0;
        for(Activity act : activities)
        {
            if(act.getType().equalsIgnoreCase(type))
            {
                count++;
                total += act.getDistance();
            }
        }
        return total/count;
    }

    public static double calories(ArrayList<Activity> activities) {
        double total = 0;

        for (Activity act : activities) {
            double calBurned = act.calcCaloriesBurned();
//            total =+ calBurned;
            total = total + calBurned;

        }

        return total/activities.size();

    }
    public static void main(String[] args) {

        activityReader reader = new activityReader();
        reader.fileList();
        reader.userChoice();

        ArrayList<Activity> activities = reader.activitiesList;



        String[] types = {"Swimming", "Running", "Cycling"};
        for(String type: types)
        {
            System.out.println(type +" " + avgDistance(activities, type));
        }

        System.out.println("You have burned " + calories(activities) + " calories on average in this set.");


//        for (Activity a:activities) {
//            System.out.println(a.toString());
//        }

        System.out.println("\n"+activities.size());

    }
}
