package presentation.views.adminFrames.logs;

import dao.Database;
import presentation.views.adminFrames.AdminChoixCRUDFrame;
import presentation.views.palette.SimpleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogsFrame extends JFrame {

    private JLabel headerLabel;
    private JPanel header;

    private LogsTablePanel logsTablePanel;

    private SimpleButton reloadButton;

    private JPanel footer;

    private void initComponents(){
        headerLabel = new JLabel("Session [ADMIN] - CRUD Logs");
        headerLabel.setFont(new Font("Optima", Font.BOLD, 20));
        headerLabel.setForeground(new Color(255, 255, 255));
        header = new JPanel(new FlowLayout());
        header.setBackground(new Color(208, 146, 45));
        header.add(headerLabel);

        logsTablePanel = new LogsTablePanel();

        reloadButton = new SimpleButton("Rafraîchir");
        footer = new JPanel(new BorderLayout());
        footer.add(reloadButton, BorderLayout.CENTER);
    }

    private void initActions(){
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logsTablePanel.getTableModel().initData(Database.getLogDAO().findAll()
                        .stream()
                        .sorted((o1, o2) -> {
                            if(o1.getDate().isAfter(o2.getDate())) return -1;
                            else if(o1.getDate().isBefore(o2.getDate())) return 1;
                            else return 0;
                        })
                        .toList());
            }
        });
    }

    private void initFrame(){
        setLayout(new BorderLayout());
        add(logsTablePanel, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);
        setResizable(false);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public LogsFrame(){
        initComponents();
        initActions();
        initFrame();
    }
}
