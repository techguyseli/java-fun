package presentation.controllers;

import dao.daoFiles.FileBasePaths;
import presentation.views.BankLoginFrame;

public class Main {
    public static void main(String[] args) {

        FileBasePaths.createFileBase();

        new BankLoginFrame("Bienvenue à SK Bank");

    }
}
