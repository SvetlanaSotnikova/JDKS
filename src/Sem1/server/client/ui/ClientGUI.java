package Sem1.server.client.ui;

import Sem1.server.client.domain.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ClientGUI extends JFrame implements ClientView {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8080");
    private final JTextField tfLogin = new JTextField("admin");
    private final JTextField tfPassword = new JTextField("*******");
    private final JTextField tfMessage = new JTextField();

    private final JButton btnLogin = new JButton("Login");
    private final JButton btnSendMessage = new JButton("Send");
    private final JPanel panelBottom = new JPanel(new BorderLayout());



    private ClientController clientController;


    @Override
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }


    public ClientGUI() {
        initUI();
    }

    private void initUI() {
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

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientController.getClientName() == null) {
                    clientController.connectToServer(tfLogin.getText());
                } else {
                    if (!tfLogin.getText().equals(clientController.getClientName())) {
                        clientController.updateLogin(tfLogin.getText());
                    } else {
                        appendLog("You cannot use old login");
                    }
                }
            }
        });
        btnSendMessage.addActionListener(e -> clientController.sendMessageToServer(tfMessage.getText()));
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    clientController.sendMessageToServer(tfMessage.getText());
                    tfMessage.setText("");
                }
            }
        });

        setVisible(true);
    }


    public void appendLog(String message) {
        log.append(message + "\n");
    }

    @Override
    public void sendMessageToServer(String message) {
        appendLog(message);
    }

    @Override
    public void disconnectedFromServer() {
        clientController.disconnectFromServer();
    }

    /**
     * Метод срабатывающий при важных событиях связанных с графическим окном (например окно в фокусе)
     *
     * @param e the window event
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            this.disconnectedFromServer();
        }
    }
}

