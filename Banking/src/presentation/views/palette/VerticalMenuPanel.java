package presentation.views.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class VerticalMenuPanel extends JPanel {

    Color bgColor = Color.WHITE;
    Color btnBgColor = Color.white, btnFgColor = Color.GRAY;
    Color btnFgHoverColor = Color.WHITE, btnBgHoverColor = Color.BLUE;
    Font btnFont = new Font("Optima", Font.BOLD, 18);
    Font btnHoverFont = new Font("Optima", Font.BOLD, 18);
    int width = 150;

    ClassLoader cl = getClass().getClassLoader();
    private LinkedHashMap<String, SimpleButton> buttons;

    public LinkedHashMap<String, SimpleButton> getButtons() {
        return buttons;
    }

    private void initActions(){
        buttons.forEach((name, button) -> {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {

                    button.setForeground(btnFgHoverColor);
                    button.setBackground(btnBgHoverColor);
                    button.setFont(btnHoverFont);

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setForeground(btnFgColor);
                    button.setBackground(btnBgColor);
                    button.setFont(btnFont);

                }
            });
        });
    }

    private void initButtons(String... buttonsNames){

        buttons = new LinkedHashMap<>();

        List<String > btnNames = new ArrayList<>(Arrays.asList(buttonsNames));

        btnNames.forEach(nameOfButton -> {

            SimpleButton btn = new SimpleButton(nameOfButton);
            btn.setPreferredSize(new Dimension(width, 50));
            btn.setMaximumSize(new Dimension(width, 50));
            btn.setMargin(new Insets(5,5,5,5));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setBackground(btnBgColor);
            btn.setForeground(btnFgColor);
            btn.setFont(btnFont);
            //btn.setIcon(icon);
            //btn.setHorizontalTextPosition(JButton.CENTER);
            //btn.setVerticalTextPosition(JButton.BOTTOM);

            buttons.put(nameOfButton, btn);
        });

    }


    private void initPanel(String...buttonsNames){
        initButtons(buttonsNames);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(bgColor);

        buttons.forEach((names, btn)-> {
            add(btn);
        });

        setVisible(false);
    }


    public VerticalMenuPanel(String... buttonsNames){
        initPanel(buttonsNames);
        initActions();
    }
    public VerticalMenuPanel(int buttonsWidth, String... buttonsNames){
        this.width = buttonsWidth;
        initPanel(buttonsNames);
        initActions();
    }
}
