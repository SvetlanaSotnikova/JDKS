package Sem1.server.theme;

import javax.swing.*;
import java.awt.*;

public class DarkTheme implements Theme {
    private final Color backgroundColor = Color.BLACK;
    private final Color textColor = Color.WHITE;
    private final Color buttonColor = Color.DARK_GRAY;

    @Override
    public void applyTheme(JFrame frame) {
        frame.getContentPane().setBackground(backgroundColor);
    }

    @Override
    public void applyTheme(JPanel panel) {
        panel.setBackground(backgroundColor);
    }

    @Override
    public void applyTheme(JTextArea textArea) {
        textArea.setBackground(backgroundColor);
        textArea.setForeground(textColor);
    }

    @Override
    public void applyTheme(JTextField textField) {
        textField.setBackground(backgroundColor);
        textField.setForeground(textColor);
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    @Override
    public void applyTheme(JButton button) {
        button.setBackground(buttonColor);
        button.setForeground(textColor);
    }

    @Override
    public void applyThemeWthCenter(JTextField textField) {
        textField.setBackground(backgroundColor);
        textField.setForeground(textColor);
    }
}
