package com.javarush.buslovskii;

import com.javarush.buslovskii.controller.MainController;

public class Runner {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.start();
    }
}
