import java.util.ArrayList;
import java.util.Collections;

public class MainApp {

    public static double minDistance = 6.00;
    public static int minDuration = 60;
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
            total += calBurned;

        }

        return total/activities.size();

    }

    public static void typeViewer(ArrayList<Activity> activities, String type){
        for(Activity act : activities)
        {
            if(act.getType().equalsIgnoreCase(type))
            {
                System.out.printf("%-20s %5s %5d %5.2f %5d %n",type,act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
            }
        }
    }

    public static void aboveDistance(ArrayList<Activity> activities){
        for(Activity act : activities){

            if(act.getDistance() > minDistance){
                System.out.printf("%-20s %5s %5d %5.2f %5d %n",act.getType(),act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
            }
        }
    }

    public static void aboveDuration(ArrayList<Activity> activities){
        for(Activity act : activities){

            if(act.getDuration() > minDuration){
                System.out.printf("%-20s %5s %5d %5.2f %5d %n",act.getType(),act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
            }
        }
    }


    public static void sortBy(ArrayList<Activity>activities,String sortRef,boolean asc){
        switch(sortRef.toLowerCase()){
            case "type":
                TypeComparator typeCompare = new TypeComparator();
                Collections.sort(activities,typeCompare);
            break;
            case "distance":
                DistanceComparator distCompare = new DistanceComparator();
                Collections.sort(activities,distCompare);
            break;
            case "calories":
                CaloriesComparator calCompare = new CaloriesComparator();
                Collections.sort(activities,calCompare);
            break;
        }

        if(!asc){
            Collections.reverse(activities);
        }

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

        typeViewer(activities, types[1]);

        aboveDistance(activities);

        aboveDuration(activities);
//        for (Activity a:activities) {
//            System.out.println(a.toString());
//        }
//
//        System.out.println("\n"+activities.size());

    }

}
