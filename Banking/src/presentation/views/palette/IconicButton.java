package presentation.views.palette;

import javax.swing.*;
import java.awt.*;

public class IconicButton extends JButton {

    public IconicButton(Icon btnIcon, Color btnBgColor){
        setIcon(btnIcon);
        setBackground(btnBgColor);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setBorderPainted(false);
        setFocusable(false);
    }

    public IconicButton(Icon btnIcon, String btnText, Color btnBgColor, Color btnFgColor){
        setText(btnText);
        setIcon(btnIcon);
        setBackground(btnBgColor);
        setForeground(btnFgColor);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setBorderPainted(false);
        setFocusable(false);
    }

    public IconicButton(Icon btnIcon, String btnText, Color btnBgColor, Color btnFgColor, Font btnFont){
        setText(btnText);
        setIcon(btnIcon);
        setBackground(btnBgColor);
        setForeground(btnFgColor);
        setFont(btnFont);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setBorderPainted(false);
        setFocusable(false);
    }
}
