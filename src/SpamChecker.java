import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SpamChecker {
    private final Email[] Emails;
    private int progress = 0;
    private int emailsDone = 0;
    private final int twoPercent;
    private final double marginOfError;
    private double averageFitness = 0;
    private final Map<String, Integer> mapTrue = new HashMap<>();
    private final Map<String, Integer> mapFalse = new HashMap<>();
    private final Map<String, Integer> map = new HashMap<>();
    public SpamChecker(String fileName, int margin) throws FileNotFoundException {
        GridText grid = new GridText(fileName);
        Emails = new Email[grid.getSize()];
        twoPercent = (int) Math.ceil(Emails.length * 0.02);
        System.out.println("RUNNING");
        for (int i = 0; i < grid.getSize(); i++){
            String email = grid.getEmail(i);
            Emails[i] = new Email(email, i+1);
            commonWords(email);
            emailsDone++;
            progressBar();
        }
        marginOfError = margin * 0.01;
        setAverageFitness();
        setSpam();
        System.out.printf("%nDONE%n");
    }
    public void output(String fileName) throws FileNotFoundException {
        new InputOutput(Emails, fileName);
    }
    private void setAverageFitness(){
        for (Email email : Emails){
            averageFitness += email.fitness;
        }
        averageFitness = averageFitness / Emails.length;
        averageFitness = averageFitness + (averageFitness * marginOfError);
    }
    private void setSpam(){
        for (Email email : Emails){
            if (email.fitness > averageFitness) {
                email.spamTag = "LIKELY";
                commonWordsTrue(email.getContents());
            } else {
                commonWordsFalse(email.getContents());
            }
        }
    }
    private void progressBar(){
        if ((emailsDone % twoPercent) == 0){
            progress += 2;
            if (progress % 10 == 0){
                System.out.printf("%d ", progress);
            } else {
                System.out.print(". ");
            }
        }
        if (emailsDone == Emails.length && progress != 100){
            if (progress % 10 != 0){
                while (progress % 10 != 0){
                    progress += 2;
                    if (progress % 10 != 0){
                        System.out.print(". ");
                    }
                }
                System.out.printf("%d ", progress);
            }
            while (progress != 100){
                progress += 10;
                System.out.printf(". . . . %d ", progress);
            }
        }
    }
    public void getCommonWords(){
        ValueComparator<String, Integer> comparatorTrue = new ValueComparator<> (mapTrue);
        Map<String, Integer> sortedMapTrue = new TreeMap<>(comparatorTrue);
        sortedMapTrue.putAll(mapTrue);

        ValueComparator<String, Integer> comparatorFalse = new ValueComparator<> (mapFalse);
        Map<String, Integer> sortedMapFalse = new TreeMap<>(comparatorFalse);
        sortedMapFalse.putAll(mapFalse);

        ValueComparator<String, Integer> comparator = new ValueComparator<> (map);
        Map<String, Integer> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(map);

        System.out.println("Most Common Words: ");
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()){
            System.out.printf("%s : %d%n", entry.getKey(), entry.getValue());
        }
        System.out.println("____________________________________________________________________________________________________________________________________________________________________");
        System.out.println("Most Common Words in LIKELY: ");
        for (Map.Entry<String, Integer> entry : sortedMapTrue.entrySet()){
            System.out.printf("%s : %d%n", entry.getKey(), entry.getValue());
        }
        System.out.println("____________________________________________________________________________________________________________________________________________________________________");
        System.out.println("Most Common Words in UNLIKELY: ");
        for (Map.Entry<String, Integer> entry : sortedMapFalse.entrySet()){
            System.out.printf("%s : %d%n", entry.getKey(), entry.getValue());
        }
    }
    private void getCommonWords(String email, Map<String, Integer> map) {
        for (String word : email.split("\\W")){
            if (word.isEmpty()){
                continue;
            }
            if (map.containsKey(word)){
                map.put(word, map.get(word)+1);
            } else {
                map.put(word, 1);
            }
        }
    }
    private void commonWords(String email){
        getCommonWords(email, map);
    }
    private void commonWordsTrue(String email){
        getCommonWords(email, mapTrue);
    }
    private void commonWordsFalse(String email){
        getCommonWords(email, mapFalse);
    }
    static class ValueComparator<K, V extends Comparable<V>> implements Comparator<K> {

        Map<K, V> map;

        public ValueComparator(Map<K, V> base) {
            this.map = base;
        }

        @Override
        public int compare(K o1, K o2) {
            return map.get(o2).compareTo(map.get(o1));
        }
    }
}


