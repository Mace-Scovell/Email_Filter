import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class InputOutput{
    private Scanner myReader;
    private File myFile;
    private int lines = 0;
    public InputOutput(String fileName){
        try {
            myFile = new File(fileName + ".csv");
            myReader = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public InputOutput(Email[] list, String fileName){
        try {
            File myObj = new File(fileName + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            java.io.FileWriter myWriter = new java.io.FileWriter(fileName + ".txt");
            for (Email email : list) {
                myWriter.write(String.format(email.toString()));
            }
            myWriter.close();
            System.out.println("Successfully wrote to the text file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            File myObj2 = new File(fileName + ".csv");
            if (myObj2.createNewFile()) {
                System.out.println("File created: " + myObj2.getName());
            } else {
                System.out.println("File already exists.");
            }
            java.io.FileWriter myWriter2 = new java.io.FileWriter( fileName + ".csv");
            myWriter2.write(String.format("EMAIL NUMBER,IS SPAM,FITNESS,WORD COUNT,CONTENTS%n"));
            for (Email email : list) {
                myWriter2.write(String.format(email.output()));
            }
            myWriter2.close();
            System.out.println("Successfully wrote to the csv file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Scanner getScanner() throws FileNotFoundException {
        myReader = new Scanner(myFile);
        return myReader;
    }
    public int getSize() throws FileNotFoundException {
        myReader = new Scanner(myFile);
        while(myReader.hasNextLine()){
            lines++;
            myReader.nextLine();
        }
        myReader.close();
        return lines;
    }

}
