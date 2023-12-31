import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainApp {

    //public static double minDistance;
    //public static int minDuration;
    public static ArrayList<Activity> activities;

    public static void displayAll(){
        for (Activity a:activities) {
            System.out.println(a.toString());
        }
        System.out.println();
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

    public  static void printFbannerAct(){
        System.out.printf("%-20s %4s %16s %9s %9s %n","Type","Date","Duration","Distance","Avg BPM");
    }

    public static void printFormatAct(Activity act){
        System.out.printf("%-20s %5s %5d %10.2f %8d %n",act.getType(),act.getDate(),act.getDuration(),act.getDistance(),act.getAverageHeartRate());
    }


    public static void subTypeViewerEnergy(String str){
        String energy = str;
        if(energy.contains(" ")){
            energy = str.replace(' ','_');
        }
        for(Activity act : activities) {
            if(act.getIntensity().toString().equalsIgnoreCase(energy)) {
                printFormatAct(act);
            }
        }
    }

    public static void subTypeViewer(String subType,double min){
        for(Activity act : activities) {
            switch (subType){
                case "distance":
                    if(act.getDistance() > min){
                        printFormatAct(act);
                    }
                break;
                case "duration":
                    if(act.getDuration() > (int)min){
                        printFormatAct(act);
                    }
                break;
            }
        }
    }
    public static void subTypeViewer(String type){
        for(Activity act : activities) {
            if(act.getType().equalsIgnoreCase(type)) {
                printFormatAct(act);
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
        activities = reader.activitiesList;
        int subType = 1;
        boolean exit = false;
        boolean back = false;
        String userInput="-1";

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please choose a file number");
        try {
            userInput = keyboard.nextLine();
            reader.userChoice(userInput);
        } catch (Exception e){
            System.out.println(userInput + " is not a number, please try again.");
        }
        do  {
            System.out.println("Please choose what to do with the file:\n");
            System.out.println("1 - View activity");
            System.out.println("2 - View a subset of your activity");
            System.out.println("3 - View statistics");
            System.out.println("4 - Find specific activity");
            System.out.println("0 - exit\n");

            int userInputNum = keyboard.nextInt();

            switch(userInputNum){
                case 1:
                    do{
                        System.out.println("Sort by: ");
                        System.out.println("1 - Calories burned");
                        System.out.println("2 - Date");
                        System.out.println("3 - Activity Duration");
                        System.out.println("4 - Type of Activity");
                        System.out.println("5 - Distance");
                        System.out.println("0 - Back to Menu\n");

                        userInputNum = keyboard.nextInt();

                        switch(userInputNum){
                            case 1 -> {sortBy(activities,"calories",false); displayAll();}
                            case 2 ->{
                                System.out.println("\n1 - Ascending\n2 - Descending");
                                userInputNum = keyboard.nextInt();
                                boolean asc = userInputNum == 1;
                                sortBy(activities,"date",asc);
                                displayAll();
                            }
                            case 3 -> {
                                System.out.println("\n1 - Ascending\n2 - Descending");
                                userInputNum = keyboard.nextInt();
                                boolean asc = userInputNum == 1;
                                sortBy(activities,"duration",asc);
                                displayAll();
                            }
                            case 4 -> {sortBy(activities,"type",false); displayAll();}
                            case 5 -> {
                                System.out.println("\n1 - Ascending\n2 - Descending");
                                userInputNum = keyboard.nextInt();
                                boolean asc = userInputNum == 1;
                                sortBy(activities,"distance",asc);
                                displayAll();
                            }
                            case 0 -> back = true;
                            default -> System.out.println("Invalid input");
                        }

                    }while(!back);
                break;
                case 2:
                    do {
                        System.out.println("1 - Activity type");
                        System.out.println("2 - Above a minimum distance");
                        System.out.println("3 - Type of energy expended");
                        System.out.println("4 - Above a minimum duration");
                        System.out.println("0 - Back to Menu\n");

                        userInputNum = keyboard.nextInt();

                        switch (userInputNum){
                            case 1 :
                                System.out.println("1 - Swimming\n2 - Running\n3 - Cycling");
                                int type = keyboard.nextInt();
                                switch (type){
                                    case 1 -> {printFbannerAct(); subTypeViewer("Swimming");}
                                    case 2 -> {printFbannerAct();subTypeViewer("Running");}
                                    case 3 -> {printFbannerAct();subTypeViewer("Cycling");}
                                }
                                break;
                            case 2:
                                System.out.println("Enter minimum distance: ");
                                double minDistance = keyboard.nextDouble();
                                printFbannerAct();
                                subTypeViewer("distance",minDistance);
                                break;
                            case 3:
                                System.out.println("Enter type of energy");
                                keyboard.nextLine();
                                String choice = keyboard.nextLine();
                                printFbannerAct();
                                subTypeViewerEnergy(choice);
                            break;
                            case 4:
                                System.out.println("Enter minimum duration: ");
                                int minDuration = keyboard.nextInt();
                                printFbannerAct();
                                subTypeViewer("duration",minDuration);
                            break;
                            case 0 : back = true;
                            break;
                            default:
                                System.out.println("invalid input");
                            break;
                        }

                    }while(!back);

                break;
                case 3:
                        System.out.println("1 - Average distance per activity");
                        System.out.println("2 - Average calories burned");
                        int choice = keyboard.nextInt();
                        if (choice == 1) {
                            String[] types = {"Swimming", "Running", "Cycling"};
                            String[] pastTypes = {"swam", "ran", "cycled"};

                            for (int i = 0; i < types.length; i++) {
                                System.out.printf("You have " + pastTypes[i] +" an average distance of %.2f km in this set.\n", avgDistance(activities, types[i]) );
                            }

                        } else {
                            System.out.println("You have burned " + avgCalories(activities) + " calories on average in this set.");
                        }
                break;
                case 4:
                    System.out.println("Please enter the parameters of the activity");
                    keyboard.nextLine();
                    System.out.println("Type:");
                    String type = keyboard.nextLine();
                    System.out.println("Date:");
                    String date = keyboard.nextLine();
                    System.out.println("Duration");
                    int duration = keyboard.nextInt();
                    System.out.println("Distance");
                    double distance = keyboard.nextDouble();
                    System.out.println("Average Heart Rate");
                    int avgBPM = keyboard.nextInt();

                    Activity ref = new Activity(type, duration, date, distance, avgBPM);
                    Collections.sort(activities);
                    int index = Collections.binarySearch(activities,ref);

                    printFormatAct(activities.get(index));
                    System.out.println("\nCalories burned: "+activities.get(index).calcCaloriesBurned());
                    System.out.println("Intensity: "+activities.get(index).getIntensity());
                    break;
                case 0:
                    exit = true;
                break;
                default:
                    System.out.println("Invalid input");
                break;
            }
        } while (!exit);
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Activity Tracker");
        menu();
    }

}
