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

    public static double avgCalories(ArrayList<Activity> activities) {
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
        String[] pastTypes = {"swam", "ran", "cycled"};
//        for(String type: types)
//        {
//            System.out.println(type +" " + avgDistance(activities, type));
//        }

        for (int i = 0; i < types.length; i++) {
            System.out.printf("You have " + pastTypes[i] +" an average distance of %.2f km in this set.\n", avgDistance(activities, types[i]) );
        }

        System.out.println("You have burned " + avgCalories(activities) + " calories on average in this set.");


//        for (Activity a:activities) {
//            System.out.println(a.toString());
//        }
//
//        System.out.println("\n"+activities.size());

    }
}
