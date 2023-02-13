package presentation.views.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {

    private IconicButton btn_west;
    private IconicButton btn_east;

    public IconicButton getBtn_west() {
        return btn_west;
    }
    public IconicButton getBtn_east() {
        return btn_east;
    }

    private void initButtons(Icon btnWestIcon, Icon btnEastIcon, Color bgColor){
        btn_west = new IconicButton(btnWestIcon, bgColor);
        btn_east = new IconicButton(btnEastIcon, bgColor);
    }

    private void initButtons(
            Icon btnWestIcon, String btnWestText, Color btnWestBgColor, Color btnWestFgColor,
            Icon btnEastIcon, String btnEastText, Color btnEastBgColor, Color btnEastFgColor,
            Font btnFont
    ){
        btn_west = new IconicButton(btnWestIcon, btnWestText, btnWestBgColor, btnWestFgColor, btnFont);

        btn_east = new IconicButton(btnEastIcon, btnEastText, btnEastBgColor, btnEastFgColor, btnFont);
    }

    public HeaderPanel(Icon btnWestIcon, Icon btnEastIcon, Color bgColor){
        initButtons( btnWestIcon, btnEastIcon, bgColor);
        setLayout(new BorderLayout());
        //setBorder(new EmptyBorder(10,20,10,20));
        setBackground(bgColor);

        add(btn_west, BorderLayout.WEST);
        add(btn_east, BorderLayout.EAST);
    }

    public  HeaderPanel(Color bgColor,
                        Icon btnWestIcon, String btnWestText, Color btnWestBgColor, Color btnWestFgColor,
                        Icon btnEastIcon, String btnEastText, Color btnEastBgColor, Color btnEastFgColor,
                        Font btnFont){

        initButtons( btnWestIcon,  btnWestText,  btnWestBgColor,  btnWestFgColor,
                btnEastIcon, btnEastText, btnEastBgColor, btnEastFgColor,
                btnFont);
        setLayout(new BorderLayout());
        setBackground(bgColor);
        //setBorder(new EmptyBorder(10,20,10,20));

        add(btn_west, BorderLayout.WEST);
        add(btn_east, BorderLayout.EAST);
    }

}
