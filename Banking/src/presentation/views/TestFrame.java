package presentation.views;

import presentation.views.palette.*;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Container  container;
    ClassLoader cl = getClass().getClassLoader();

    private void initContainer(){

    }


    public TestFrame(String title){
        container = getContentPane();
        container.setLayout(new BorderLayout());
        setLocation(0,0);
        setSize(screenSize.width,screenSize.height-90);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
