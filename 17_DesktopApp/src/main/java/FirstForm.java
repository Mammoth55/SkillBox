import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FirstForm {

    private final JLabel lastNameLabel = new JLabel("Фамилия (обязательно)");
    private final JTextField lastName = new JTextField("", 20);

    private final JLabel firstNameLabel = new JLabel("Имя (обязательно)");
    private final JTextField firstName = new JTextField("", 20);

    private final JLabel middleNameLabel = new JLabel("Отчество");
    private final JTextField middleName = new JTextField("", 20);

    private final JLabel errorMessage = new JLabel("ERROR! Try again!");
    private final JButton button = new JButton("COLLAPSE");

    public FirstForm(List<String> fio) {
        JFrame frame = new JFrame();
        frame.setTitle("SWING GUI SAMPLE. EXPANDED");
        frame.setBounds(300, 200, 600, 170);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(4,2,9,9));
        container.add(lastNameLabel);
        lastName.setText(fio.get(0));
        container.add(lastName);
        container.add(firstNameLabel);
        firstName.setText(fio.get(1));
        container.add(firstName);
        container.add(middleNameLabel);
        if (fio.size() == 3) {
            middleName.setText(fio.get(2));
        }
        container.add(middleName);
        container.add(errorMessage);
        errorMessage.setVisible(false);
        button.addActionListener(e -> {
            String part1 = lastName.getText().trim();
            String part2 = firstName.getText().trim();
            String part3 = middleName.getText().trim();
            if (part1.length() == 0 || part2.length() == 0) {
                errorMessage.setVisible(true);
            } else {
                errorMessage.setVisible(false);
                frame.setVisible(false);
                new SecondForm(part1 + ' ' + part2 + ' ' + part3);
            }
        });
        container.add(button);
        frame.setVisible(true);
    }
}