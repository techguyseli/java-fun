package presentation.views.palette;

import javax.swing.*;
import java.awt.*;

public class TableCrudPanel extends JPanel {

    private ClassLoader cl = getClass().getClassLoader();
    private IconicButton btn_add, btn_edit, btn_remove;


    public IconicButton deleteBtn() {
        return btn_remove;
    }

    public IconicButton addBtn() {
        return btn_add;
    }

    public IconicButton editBtn() {
        return btn_edit;
    }

    private void initButtons(Color buttonsBgColor){

        btn_add = new IconicButton(new ImageIcon(cl.getResource("images/icons/add.png")), buttonsBgColor);

        btn_edit = new IconicButton(new ImageIcon(cl.getResource("images/icons/edit.png")), buttonsBgColor);

        btn_remove = new IconicButton(new ImageIcon(cl.getResource("images/icons/delete.png")), buttonsBgColor);

    }

    public TableCrudPanel(Color buttonsBgColor){
        initButtons(buttonsBgColor);
        setLayout(new FlowLayout());
        setBackground(Color.white);
        add(btn_add);
        add(btn_edit);
        add(btn_remove);
    }

}
