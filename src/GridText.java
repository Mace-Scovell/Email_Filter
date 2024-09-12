import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GridText{

    public ArrayList<String> gridText;
    public GridText(String fileName) throws FileNotFoundException {
        InputOutput input = new InputOutput(fileName);
        Scanner source = input.getScanner();
        int numLines = input.getSize();
        gridText = new ArrayList<>();
        String str;
        ArrayList<String> contractions = new ArrayList<>(Arrays.asList("t", "d", "m", "s", "ll", "ve", "re"));
        ArrayList<String> contractionWords = new ArrayList<>(Arrays.asList("ain", "aren", "can", "couldn", "didn", "doesn", "don", "get", "hadn", "hasn", "haven", "he", "here", "how", "i", "isn", "it", "let", "mightn", "mustn", "must", "needn", "shan", "she", "should", "shouldn", "that", "there", "they", "wasn", "we", "weren", "what", "where", "who", "won", "wouldn", "you"));
        if (source.nextLine().equals("email,label")){
            while(source.hasNextLine() && (gridText.size() < numLines)) {
                str = source.nextLine();
                ArrayList<String> contentsArray = getStrings(str.substring(0, str.length() - 2), contractions, contractionWords);
                StringBuilder strBui = new StringBuilder();
                for (String s : contentsArray){
                    strBui.append(s).append(" ");
                }
                gridText.add(strBui.toString());
            }
        } else {
            while(source.hasNextLine() && (gridText.size() < numLines)) {
                str = source.nextLine();
                ArrayList<String> contentsArray = getStrings(str, contractions, contractionWords);
                StringBuilder strBui = new StringBuilder();
                for (String s : contentsArray){
                    strBui.append(s).append(" ");
                }
                gridText.add(strBui.toString());
            }
        }
    }

    private static ArrayList<String> getStrings(String str, ArrayList<String> contractions, ArrayList<String> contractionWords) {
        int i = 1;
        ArrayList<String> contentsArray = new ArrayList<>(List.of(str.split(" ")));
        while (i < contentsArray.size()) {
            if (contractions.contains(contentsArray.get(i)) && contractionWords.contains(contentsArray.get(i - 1))) {
                contentsArray.set(i - 1, contentsArray.get(i - 1) + contentsArray.get(i));
                contentsArray.remove(i);
            }
            i++;
        }
        return contentsArray;
    }

    public String getEmail(int i) {return (gridText.get(i));}
    public int getSize() {return gridText.size();}
}