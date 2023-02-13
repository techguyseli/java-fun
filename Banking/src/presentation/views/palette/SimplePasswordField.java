package presentation.views.palette;

import javax.swing.*;
import java.awt.*;

public class SimplePasswordField  extends JPasswordField {

    private Font font = new Font("Optima", Font.PLAIN, 14);
    private Color fgColor = Color.BLACK;
    private Color bgColor = Color.WHITE;

    private void initTextField(){
        setFont(font);
        setForeground(fgColor);
        setBackground(bgColor);
        setPreferredSize(new Dimension(250, 30));
        setMaximumSize(new Dimension(250, 30));
    }

    public SimplePasswordField() {
        initTextField();
    }
}
