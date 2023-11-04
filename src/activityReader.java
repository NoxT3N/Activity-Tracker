import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

public class activityReader {

    private int numFiles= 0;
    private int fileChoice;
    private String filename;
    private File activities = new File("src/activities");
    private File[] listOfFiles = activities.listFiles();

    public void namePicker(){
        filename = "src/activities/" +listOfFiles[fileChoice].getName();
    }


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
        System.out.println(filename);
        activity();
    }


    public void activity() {
// code to read csv files: https://github.com/logued/oop-csv-read-text-file/blob/master/src/main/java/org/example/Main.java

        try (Scanner fileReader = new Scanner(new File(filename))) {

            if (fileReader.hasNextLine())
                fileReader.nextLine();

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String [] tokens = line.split(",");

                String type = tokens [0];
                String date = tokens [1];
                double duration = Double.parseDouble(tokens[2]);
                double distance = Double.parseDouble(tokens[3]);
                double heartRate = Double.parseDouble(tokens[4]);

//                System.out.println(type + date + duration + distance + heartRate);
                System.out.printf("%-20s %5s %5.2f %5.2f %5.2f %n",type,date,duration,distance,heartRate);
            }

        } catch (FileNotFoundException e) {
            System.out.println("That file doesn't seem to exist.");
            e.printStackTrace();
        }
// file read code which included e.printStackTrace: https://www.w3schools.com/java/java_files_read.asp
    }

}
