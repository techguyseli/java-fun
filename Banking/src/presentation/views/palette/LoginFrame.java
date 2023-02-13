package presentation.views.palette;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public abstract class LoginFrame extends JFrame {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int width = 370, height = 200;
    protected String title;
    protected String identifier = "Username";
    protected JLabel loginLabel;
    protected SimpleTextField loginTextField;
    protected JLabel passwordLabel;
    protected JPasswordField passwordField;
    protected SimpleButton loginButton;

    private void initComponents(){
        loginLabel = new JLabel(identifier);
        loginTextField = new SimpleTextField();
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        loginButton = new SimpleButton("Login");

        int componentsHeight = 30, componentsWidth = 240;

        int xSpacer=70, x1=22, x2=x1+xSpacer;
        int ySpacer=48, y1=22, y2=y1+ySpacer, y3=y1+ySpacer*2;

        loginLabel.setBounds(x1,y1,componentsWidth,componentsHeight);
        loginTextField.setBounds(x2,y1,componentsWidth,componentsHeight);

        passwordLabel.setBounds(x1,y2,componentsWidth,componentsHeight);
        passwordField.setBounds(x2,y2,componentsWidth,componentsHeight);

        loginButton.setBounds(width/2 - componentsWidth/4,y3,componentsWidth/2,componentsHeight);
    }

    private void initContainer(){
        Container c = getContentPane();
        c.setLayout(null);
        c.add(loginLabel);
        c.add(passwordLabel);
        c.add(loginTextField);
        c.add(passwordField);
        c.add(loginButton);
        setTitle(title);
        setVisible(true);
        int x = (screenSize.width - width)/2, y = (screenSize.height - height)/2;
        setBounds(x,y,width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    abstract public void initActions();

    public LoginFrame(String title){
        this.title = title;
        initComponents();
        initContainer();
        initActions();
    }

    public LoginFrame(String title, String identifier){
        this.title = title;
        this.identifier = identifier;
        initComponents();
        initContainer();
        initActions();
    }

    public HashMap<String, String> getFormValues(){
        HashMap<String, String> values = new HashMap<>();
        values.put(identifier.toLowerCase(), loginTextField.getText());
        values.put("password", String.valueOf(passwordField.getPassword()));
        return values;
    }

}
