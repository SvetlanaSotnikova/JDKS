package Sem1.server.server;

import Sem1.server.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int WINDOW_WIDTH = 555;
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_POSX = 400;
    private static final int WINDOW_POSY = 200;

    private final JButton startButton = new JButton("Start");
    private final JButton stopButton = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private static boolean isServerWorks;
    private final List<ClientGUI> clients = new ArrayList<>();

    public ServerWindow() {
        isServerWorks = false;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Chat Server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(1, 2));
        log.setEditable(false);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        JScrollPane logPane = new JScrollPane(log);
        add(logPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorks) {
                    isServerWorks = false;
                    appendLog("Stopping server...");
                } else {
                    appendLog("Server is not working");
                }
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorks) {
                    isServerWorks = true;
                    appendLog("Starting server...");
                } else {
                    appendLog("Server is working");
                }
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void appendLog(String message) {
        if (!isServerWorks) return;
        log.append(message + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    public void registerClient(ClientGUI clientGUI) {
        if (!isServerWorks) return;
        clients.add(clientGUI);
        appendLog(clientGUI.getClientName() + " connected to server");
    }

    public void unregisterClient(ClientGUI clientGUI) {
        if (!isServerWorks) return;
        clients.remove(clientGUI);
        appendLog(clientGUI.getClientName() + " disconnected from server");
    }

    public void broadcastMessage(String message, ClientGUI clientGUI) {
        if (!isServerWorks) return;
        for (ClientGUI client : clients) {
            if (client != clientGUI) {
                client.receiveMessage(message);
            }
        }
        appendLog(message);
    }

}
