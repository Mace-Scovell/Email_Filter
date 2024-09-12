public class Email {
    private final String contents;
    private final int emailNumber;
    public String spamTag = "UNLIKELY";
    private int wordCount = 0;
    private final String[] keyWords = new String[] {"NUMBER", "URL", "unsubscribe", "sponsored", "spam", "hyperlink", "mailing", "_______________________________________________"};
    public double fitness = 0.0;
    public Email(String contents, int emailNumber){
        this.emailNumber = emailNumber;
        this.contents = contents;
        calculateFitness();
    }
    public void calculateFitness(){
        String[] splitSentence= contents.split(" ");
        for (String s : splitSentence) {
            if (!s.isEmpty()) {
                wordCount++;
                fitness += findEmbedded(s, keyWords);
            }
        }
    }
    public String toString(){
        return "EMAIL NUMBER: " + emailNumber +
                "%nIS SPAM: " + spamTag +
                "%nFITNESS: " + String.format("%.2f", fitness) +
                "%nWORD COUNT: " + wordCount +
                "%nCONTENTS: " + contents +
                "%n%n";
    }
    public String output(){
        return String.format("%d,%s,%.2f,%d,%s%n",
                emailNumber, spamTag, fitness, wordCount, contents);
    }
    private double findEmbedded(String str, String[] keyWords){
        int count = 0;
        for (String findStr : keyWords) {
            int lastIndex = 0;
            while (lastIndex != -1) {
                lastIndex = str.indexOf(findStr, lastIndex);
                if (lastIndex != -1) {
                    if (findStr.equals("unsubscribe") || findStr.equals("sponsored") || findStr.equals("hyperlink")){
                        count += 20;
                    } else {
                        count++;
                    }
                    lastIndex += findStr.length();
                }
            }
        }
        return count;
    }
    public String getContents() {return contents;}
}

