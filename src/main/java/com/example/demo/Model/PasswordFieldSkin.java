package com.example.demo.Model;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;

public class PasswordFieldSkin extends TextFieldSkin {
    public static final char BULLET = '\u2700';

    public PasswordFieldSkin(PasswordField passwordField) {
        super(passwordField);
    }

    @Override protected String maskText(String txt) {
        TextField textField = getSkinnable();

        int n = textField.getLength();
        StringBuilder passwordBuilder = new StringBuilder(n);
        for (int i=0; i<n; i++) {
            passwordBuilder.append(BULLET);
        }

        return passwordBuilder.toString();
    }
}