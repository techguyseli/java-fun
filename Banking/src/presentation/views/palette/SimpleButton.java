package presentation.views.palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleButton extends JButton {

    private Color fgColor=Color.WHITE, bgColor = Color.BLUE, bgColorHover = new Color(215, 153, 33);

    private void initActions(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(bgColorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(bgColor);
            }
        });
    }

    public SimpleButton(String text){
        setText(text);
        setBorderPainted(false);
        setBackground(bgColor);
        setForeground(fgColor);
        setFont(new Font("Optima", Font.BOLD, 18));
        setFocusable(false);
        initActions();
    }

    public SimpleButton(String text, Color fgColor, Color bgColor){
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        setText(text);
        setBorderPainted(false);
        setBackground(bgColor);
        setForeground(fgColor);
        setFont(new Font("Optima", Font.BOLD, 18));
        setFocusable(false);
        initActions();
    }

    public SimpleButton(String text, Color fgColor, Color bgColor, Color bgColorHover){
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.bgColorHover = bgColorHover;
        setText(text);
        setBorderPainted(false);
        setBackground(bgColor);
        setForeground(fgColor);
        setFont(new Font("Optima", Font.BOLD, 18));
        setFocusable(false);
        initActions();
    }
}