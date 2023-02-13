package presentation.views;

import forms.LoginFormValidator;
import presentation.models.Client;
import presentation.models.Role;
import presentation.models.Utilisateur;
import presentation.views.adminFrames.AdminChoixCRUDFrame;
import presentation.views.clientsFrames.ClientChoixCompteFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class BankLoginFrame extends presentation.views.palette.LoginFrame {


    public BankLoginFrame(String title) {
        super(title);
    }

    @Override
    public void initActions() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> formValues = getFormValues();
                LoginFormValidator form = new LoginFormValidator();
                Utilisateur session = form.validerSession(formValues.get("username"), formValues.get("password"));

                if(session == null)
                    if(form.Errors().isEmpty()) JOptionPane.showMessageDialog(null, form.ResultMsg(), "Successfully Connected", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        String errorMessage = "";
                        int size = form.Errors().keySet().size();
                        String[] errorKeys = form.Errors().keySet().toArray(new String[0]);
                        for (int i = 0; i < size; i++) errorMessage += errorKeys[i] + " > " + form.Errors().get(errorKeys[i]) + "\n";
                        JOptionPane.showMessageDialog(null, errorMessage, "ERRORS", JOptionPane.ERROR_MESSAGE);
                    }

                else{
                    if(session.getRole().equals(Role.ADMIN)) new AdminChoixCRUDFrame();
                    else new ClientChoixCompteFrame((Client) session);
                    dispose();
                }

            }
        });
    }
}
