public class Test {
    public static void main(String[] args) {
        new Email_Filter_GUI();
    }
}

/**
    Test calls GUI, which takes file names from user and passes them into SpamChecker, then Grid Text, then InputOutput to get individual emails.

    SpamChecker makes an Email list and fills it with email objects.

    Email objects use keywords of various weights to generate a fitness score.

    Average fitness is calculated from all email's fitness's.

    Any email with a fitness higher than the average plus or minus the margin of error is marked as LIKELY spam.

    SpamChecker also generates a list of the most common words in all emails, LIKELY spam emails, and UNLIKELY spam emails,
    this is printed to the console.

    (version 1 only works with the provided email set)
 */