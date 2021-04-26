import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;

public class SecondForm {

    private final JLabel fioLabel = new JLabel("Ф.И.О. (Фамилия и Имя обязательны !)");
    private final JTextField fio = new JTextField("", 50);

    private final JLabel errorMessage = new JLabel("ERROR! Try again!");
    private final JButton button = new JButton("EXPAND");

    public SecondForm(String line) {
        JFrame frame = new JFrame();
        frame.setTitle("SWING GUI SAMPLE. COLLAPSED");
        frame.setBounds(300, 200, 600, 120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(2,2,9,9));
        container.add(fioLabel);
        fio.setText(line);
        container.add(fio);
        container.add(errorMessage);
        errorMessage.setVisible(false);
        button.addActionListener(e -> {
            List<String> words = Arrays.asList(fio.getText().trim().split("\s+"));
            if (words.size() < 2 || words.size() > 3) {
                errorMessage.setVisible(true);
            } else {
                errorMessage.setVisible(false);
                frame.setVisible(false);
                new FirstForm(words);
            }
        });
        container.add(button);
        frame.setVisible(true);
    }
}