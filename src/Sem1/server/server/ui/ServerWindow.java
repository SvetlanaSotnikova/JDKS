package Sem1.server.server.ui;

import Sem1.server.server.domain.ServerController;
import Sem1.server.theme.DarkTheme;
import Sem1.server.theme.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerWindow extends JFrame implements ServerView {
    private static final int WINDOW_WIDTH = 555;
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_POSX = 400;
    private static final int WINDOW_POSY = 200;

    private final JTextArea log = new JTextArea();

    private ServerController controller;
    private final Theme theme = new DarkTheme();

    public ServerWindow() {
        settings();
        add(getLogSettings(), BorderLayout.CENTER);
        add(getButtonPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    private void settings() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Chat Server");
    }

    private Component getLogSettings() {
        setLayout(new GridLayout(1, 2));
        log.setEditable(false);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        theme.applyTheme(log);
        return new JScrollPane(log);
    }

    private Component getButtonPanel() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");

        theme.applyTheme(startButton);
        theme.applyTheme(stopButton);

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopServer();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startServer();
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        return buttonPanel;
    }

    @Override
    public void appendLog(String message) {
        log.append(message + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    @Override
    public void setServerController(ServerController serverController) {
        this.controller = serverController;
    }

}
