package Sem1.server.theme;

import javax.swing.*;

public interface Theme {
    void applyTheme(JFrame frame);

    void applyTheme(JPanel panel);

    void applyTheme(JTextArea textArea);

    void applyTheme(JTextField textField);

    void applyTheme(JButton button);

    void applyThemeNoCenter(JTextField textField);
}
