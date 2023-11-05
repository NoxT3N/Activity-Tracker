import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

public class activityReader {

    private int numFiles= 0;
    private int fileChoice;
    private String filename;
    private File activities = new File("src/activities");
    private File[] listOfFiles = activities.listFiles();
    public ArrayList<Activity> activitiesList = new ArrayList<>();

    public void namePicker(){
        filename = "src/activities/" +listOfFiles[fileChoice].getName();
    }

// fileList runs through the contents of the 'activities' folder and prints them out, which is where csv files are stored.
    public void fileList() {
//code to read folder from: https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder

        numFiles = listOfFiles.length;

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                System.out.println("File " + (i+1) + " " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + (i+1) + " " + listOfFiles[i].getName());
            }

        }
    }


    public void userChoice () {
        Scanner keyboard = new Scanner(System.in);
        String userInput = "-1";
        int choice = Integer.parseInt(userInput);


        System.out.println("Please choose a file number");
        do  {
            try {
                userInput = keyboard.nextLine();
                choice = Integer.parseInt(userInput);
                fileChoice = choice-1;
                if(choice > numFiles || choice <= 0){
                    System.out.println(choice + " is not a valid file number, please choose a valid file.");
                }

            } catch (Exception e){
                System.out.println(userInput + " is not a number, please try again.");
            }
        } while (choice > numFiles || choice <= 0);

        namePicker();
//        System.out.println(filename);
        activity();
    }


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
                //System.out.printf("%-20s %5s %5d %5.2f %5d %n",type,date,duration,distance,heartRate);

                //this.activitiesList.add(new Activity(type,duration,date,distance,heartRate));


            }

        } catch (FileNotFoundException e) {
            System.out.println("That file doesn't seem to exist.");
            e.printStackTrace();
        }
// file read code which included e.printStackTrace: https://www.w3schools.com/java/java_files_read.asp
    }

}
