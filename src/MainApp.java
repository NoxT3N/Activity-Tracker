import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainApp {

    public static double minDistance = 6.00;
    public static int minDuration = 60;
    public static ArrayList<Activity> activities;

    public void displayAll(){
        for (Activity a:activities) {
            System.out.println(a.toString());
        }
    }
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

    public static void subTypeViewer(ArrayList<Activity> activities, String type, int subType){
        for(Activity act : activities) {

//            if(act.getType().equalsIgnoreCase(type))
//            {
//                System.out.printf("%-20s %5s %5d %5.2f %5d %n",type,act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
//            }
//            else if(act.getDistance() > minDistance){
//                System.out.printf("%-20s %5s %5d %5.2f %5d %n",act.getType(),act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
//            }
//            else if(act.getDuration() > minDuration){
//                System.out.printf("%-20s %5s %5d %5.2f %5d %n",act.getType(),act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
//            }

            switch (subType){
                case 1:
                    if(act.getType().equalsIgnoreCase(type))
                    {
                        System.out.printf("%-20s %5s %5d %5.2f %5d %n",type,act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
                    }
                case 2:
                    if(act.getDistance() > minDistance){
                        System.out.printf("%-20s %5s %5d %5.2f %5d %n",act.getType(),act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
                    }
                case 3:
                    if(act.getDuration() > minDuration){
                        System.out.printf("%-20s %5s %5d %5.2f %5d %n",act.getType(),act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
                    }
            }
        }
    }

//    public static void aboveDistance(ArrayList<Activity> activities){
//        for(Activity act : activities){
//
//            if(act.getDistance() > minDistance){
//                System.out.printf("%-20s %5s %5d %5.2f %5d %n",act.getType(),act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
//            }
//        }
//    }
//
//    public static void aboveDuration(ArrayList<Activity> activities){
//        for(Activity act : activities){
//
//            if(act.getDuration() > minDuration){
//                System.out.printf("%-20s %5s %5d %5.2f %5d %n",act.getType(),act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
//            }
//        }
//    }


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
            case "date":
                DateComparator dateCompare = new DateComparator();
                Collections.sort(activities,dateCompare);
            break;
            default:
                Collections.sort(activities);
            break;
        }

        if(!asc){
            Collections.reverse(activities);
        }

    }

    public static void menu(){
        activityReader reader = new activityReader();
        reader.fileList();
        ArrayList<Activity> activities = reader.activitiesList;
        int subType = 1;
        boolean exit = false;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please choose a file number");
        do  {
            try {
                reader.userInput = keyboard.nextLine();
                reader.userChoice();
                exit = true;
            } catch (Exception e){
                System.out.println(reader.userInput + " is not a number, please try again.");
            }

            System.out.println("Please choose what to do with the file:");

        } while (!exit);
    }
    public static void main(String[] args) {

        menu();

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

//        subTypeViewer(activities, types[1], 1);

//        aboveDistance(activities);
//
//        aboveDuration(activities);
//        for (Activity a:activities) {
//            System.out.println(a.toString());
//        }
//
//        System.out.println("\n"+activities.size());

    }

}
