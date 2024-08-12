package Sem1.server.client;

import Sem1.server.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String LOG_FILE = "CHAT_LOG_FILE.txt";

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8080");
    private final JTextField tfLogin = new JTextField("admin");
    private final JTextField tfPassword = new JTextField("admin");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSendMessage = new JButton("Send");
    private final ServerWindow serverWindow;

    private PrintWriter logWriter;
    private BufferedReader logReader;
    private Socket socket;
    private String clientName;

    private boolean isServerWorks;

    public ClientGUI(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSendMessage, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(log);
        add(scrollPane);

        btnLogin.addActionListener(e -> updateLogin());
        btnSendMessage.addActionListener(e -> sendMessage());
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }

        });

        clientName = tfLogin.getText();
        serverWindow.registerClient(this);
        loadMessageFromFile();
        setVisible(true);
    }

    public String getClientName() {
        return clientName;
    }

    public void receiveMessage(String message) {
        log.append(message + "\n");
    }

    private void updateLogin() {
        String newName = tfLogin.getText().trim();
        if (newName != null && !clientName.equals(newName)) {
            serverWindow.unregisterClient(this);
        }
        clientName = newName;
        serverWindow.registerClient(this);
        log.append("Login Successful " + clientName + "\n");
    }

    private void sendMessage() {

        String message = tfMessage.getText().trim();
        if (!message.isEmpty()) {
            String formattedMessage = clientName + ": " + message;
            log.append(formattedMessage + "\n");
            serverWindow.broadcastMessage(clientName + ": " + message, this);
            saveMessageToFile(message);
            tfMessage.setText("");
        }
    }

    private void saveMessageToFile(String message) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE, true)))) {
            printWriter.println(clientName + ": " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMessageFromFile() {
        try {
            java.util.List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(LOG_FILE));
            for (String line : lines) {
                log.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

