package presentation.views.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchPanel extends JPanel {
    ClassLoader cl = getClass().getClassLoader();
    private IconicButton btn_search;
    private HintTextField txt_search;
    private Color bgColor;

    private TableCrudPanel crudButtonsPanel;

    public TableCrudPanel getCrudPanel() {
        return crudButtonsPanel;
    }

    public IconicButton getBtn_search() {
        return btn_search;
    }
    public HintTextField getTxt_search() {
        return txt_search;
    }

    private void initButton(){
            btn_search = new IconicButton(
                    new ImageIcon(cl.getResource("images/icons/search.png")),
                    bgColor
            );

        btn_search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_search.setIcon(new ImageIcon(cl.getResource("images/icons/searchHover.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_search.setIcon(new ImageIcon(cl.getResource("images/icons/search.png")));

            }
        });
    }
    private void initTextField(){

       txt_search = new HintTextField("mot-clé");
       txt_search.setHorizontalAlignment(SwingConstants.CENTER);
       txt_search.setPreferredSize(new Dimension(200, 25));
       txt_search.setMaximumSize(new Dimension(200, 55));
    }
    private void initComponents(){
        initTextField();
        initButton();
        crudButtonsPanel = new TableCrudPanel(bgColor);
    }



    public SearchPanel(Color bgColor){
        this.bgColor = bgColor;
        initComponents();
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(btn_search); pane.add(txt_search);
        pane.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        pane.setBackground(Color.white);


        //setLayout(new FlowLayout(FlowLayout.RIGHT));
        setLayout(new BorderLayout());
        setBackground(bgColor);
        setBorder(new EmptyBorder(5,5,5,25));
        //setBorder(new RoundedBorder(true, Color.blue, 15));

        add(pane, BorderLayout.EAST);
        add(crudButtonsPanel, BorderLayout.WEST);
    }



}
