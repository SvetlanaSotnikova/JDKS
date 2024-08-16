    package Sem1.server.server.ui;

    import Sem1.server.server.domain.ServerController;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;


    public class ServerWindow extends JFrame implements ServerView {
        private static final int WINDOW_WIDTH = 555;
        private static final int WINDOW_HEIGHT = 555;
        private static final int WINDOW_POSX = 400;
        private static final int WINDOW_POSY = 200;

        private final JButton startButton = new JButton("Start");
        private final JButton stopButton = new JButton("Stop");
        private final JTextArea log = new JTextArea();

        private ServerController controller;


        public ServerWindow() {
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
            add(buttonPanel, BorderLayout.SOUTH);
            setVisible(true);
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
