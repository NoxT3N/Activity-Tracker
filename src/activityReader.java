import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class activityReader {
    private int fileChoice;
    private String filename;
    private File actFolder = new File("src/activities");
    private File[] listOfFiles = actFolder.listFiles();
    public String userInput = "-1";
    public ArrayList<Activity> activitiesList = new ArrayList<>();

    // fileList runs through the contents of the 'activities' folder and prints them out, which is where csv files are stored.
    public void fileList() {
//code to read folder from: https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                System.out.println("File " + (i+1) + " " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + (i+1) + " " + listOfFiles[i].getName());
            }

        }
    }

// userChoice works as a menu system. It takes in the user's choice of file.
//    public void userChoice () {
//        Scanner keyboard = new Scanner(System.in);
//        int choice = Integer.parseInt(userInput);
//
//        System.out.println("Please choose a file number");
//        do  {
//            try {
//                userInput = keyboard.nextLine();
//                choice = Integer.parseInt(userInput);
//                fileChoice = choice-1;
//                if(choice > numFiles || choice <= 0){
//                    System.out.println(choice + " is not a valid file number, please choose a valid file.");
//                }
//
//            } catch (Exception e){
//                System.out.println(userInput + " is not a number, please try again.");
//            }
//        } while (choice > numFiles || choice <= 0);
//
//        filename = "src/activities/" +listOfFiles[fileChoice].getName();
//        activity();
//    }

    public void userChoice () {
        int choice = Integer.parseInt(userInput);

        do  {
            try {
                choice = Integer.parseInt(userInput);
                fileChoice = choice-1;
                if(choice > listOfFiles.length || choice <= 0){
                    System.out.println(choice + " is not a valid file number, please choose a valid file.");
                }

            } catch (Exception e){
                System.out.println(userInput + " is not a number, please try again.");
            }
        } while (choice > listOfFiles.length || choice <= 0);

        filename = "src/activities/" +listOfFiles[fileChoice].getName();
        System.out.println("File chosen: "+ filename);
        activity();
    }


    //activity reads the user's chosen csv file. It then creates an activity object for each entry. It also checks for duplicates for each entry in the arraylist.
    public void activity() {
// code to read csv files: https://github.com/logued/oop-csv-read-text-file/blob/master/src/main/java/org/example/Main.java

        try (Scanner fileReader = new Scanner(new File(filename))) {

            if (fileReader.hasNextLine())
                fileReader.nextLine();

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String [] tokens = line.split(", ");

                String type = tokens [0];
                String date = tokens [1];
                int duration = Integer.parseInt(tokens[2]);
                double distance = Double.parseDouble(tokens[3]);
                int heartRate = Integer.parseInt(tokens[4]);

                Activity activity = new Activity(type,duration,date,distance,heartRate);
                if(!activitiesList.contains(activity)){
                    activitiesList.add(activity);
                }

//                System.out.println(type + date + duration + distance + heartRate);
//                System.out.printf("%-20s %5s %5d %5.2f %5d %n",type,date,duration,distance,heartRate);
//
//                this.activitiesList.add(new Activity(type,duration,date,distance,heartRate));


            }

        } catch (FileNotFoundException e) {
            System.out.println("That file doesn't seem to exist.");
            e.printStackTrace();
        }
// file read code which included e.printStackTrace: https://www.w3schools.com/java/java_files_read.asp
    }

}
