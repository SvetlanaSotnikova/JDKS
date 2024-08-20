package Sem1.server.client.ui;

import Sem1.server.client.domain.ClientController;
import Sem1.server.theme.DarkTheme;
import Sem1.server.theme.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ClientGUI extends JFrame implements ClientView {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final JTextArea log = new JTextArea();
    private final Theme theme = new DarkTheme();

    private ClientController clientController;

    public ClientGUI() {
        initUI();
    }

    private void initUI() {
        settings();
        applyTheme();
        createPanel();
        setVisible(true);
    }

    private void applyTheme() {
        theme.applyTheme(this);
        theme.applyTheme(log);
    }

    public void createPanel() {
        add(getHeaderPanel(), BorderLayout.NORTH);
        add(getFooter(), BorderLayout.SOUTH);
        add(getContentPanel());
    }

    private void settings() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");
    }

    private Component getHeaderPanel() {
        JPanel panelHeader = new JPanel(new GridLayout(2, 3));
        JTextField tfIPAddress = new JTextField("127.0.0.1");
        JTextField tfPort = new JTextField("8080");
        JTextField tfLogin = new JTextField("admin");
        JTextField tfPassword = new JPasswordField("*******");

        JButton btnLogin = new JButton("Login");

        theme.applyTheme(tfIPAddress);
        theme.applyTheme(tfPort);
        theme.applyTheme(tfLogin);
        theme.applyTheme(tfPassword);
        theme.applyTheme(btnLogin);

        panelHeader.add(tfIPAddress);
        panelHeader.add(tfPort);
        panelHeader.add(tfPassword);
        panelHeader.add(tfLogin);
        panelHeader.add(btnLogin);

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
        return panelHeader;
    }

    private Component getFooter() {
        JPanel panelBottom = new JPanel(new BorderLayout());

        JTextField tfMessage = new JTextField();

        JButton btnSendMessage = new JButton("Send");

        theme.applyThemeNoCenter(tfMessage);
        theme.applyTheme(btnSendMessage);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSendMessage, BorderLayout.EAST);

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

        return panelBottom;
    }

    private Component getContentPanel() {
        log.setEditable(false);
        return new JScrollPane(log);
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

    @Override
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
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

