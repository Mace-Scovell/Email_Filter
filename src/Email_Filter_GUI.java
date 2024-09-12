import javax.swing.*;
import java.io.FileNotFoundException;

public class Email_Filter_GUI extends JFrame {
    private JPanel panel1;
    private JSlider MarginSlider;
    private JButton RUNButton;
    private JTextField InputFile;
    private JTextField OutputFile;

    public Email_Filter_GUI(){
        setContentPane(panel1);
        setTitle("Group 8 Spam Checker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        RUNButton.addActionListener(e -> {
            try {
                SpamChecker sp = new SpamChecker(InputFile.getText(), MarginSlider.getValue());
                sp.output(OutputFile.getText());
                sp.getCommonWords();
                JOptionPane.showMessageDialog(panel1, "COMPLETE");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
