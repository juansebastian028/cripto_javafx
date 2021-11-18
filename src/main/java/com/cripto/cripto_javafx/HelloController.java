package com.cripto.cripto_javafx;

import cipher.SymmetricCipher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import util.Util;

import java.io.*;
import java.util.Objects;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.*;

public class HelloController {
    public TextField txtCipherText;
    public TextField txtDecryptedText;
    public TextField txtTextToCipher;
    public Button btnLoadKey;
    public Button btnSaveKey;
    public Button btnGenerateKey;
    public Button btnDecryptedText;
    public Button btnCipher;
    SecretKey secretKey = null;
    SymmetricCipher s = null;
    String fileName = null;
    byte[] cipherText = null;
    Integer count = 0;


    public String getFileName () {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files (*.txt)", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    public void enableButtons () {
        btnSaveKey.setDisable(false);
        btnCipher.setDisable(false);
        btnDecryptedText.setDisable(false);
    }

    public void onCipher(ActionEvent actionEvent) {
        long timebefore = System.currentTimeMillis();

        String clearText = txtTextToCipher.getText();
        s = new SymmetricCipher(secretKey, "DES/ECB/PKCS5Padding");
        cipherText = s.encriptMessage(clearText);
        txtCipherText.setText("{" + Util.byteArrayIntToString(cipherText) + "}");

        long timeafter = System.currentTimeMillis();
        JOptionPane.showMessageDialog(null, "Tiempo trancurrido: " + (timeafter - timebefore) + " milisegundos");
    }

    public void onDecryptedText(ActionEvent actionEvent) {
        long timebefore = System.currentTimeMillis();
        s = new SymmetricCipher(secretKey, "DES/ECB/PKCS5Padding");
        if (cipherText != null) {
            String clearText = s.decryptMessage(cipherText);
            txtDecryptedText.setText(clearText);
            long timeafter = System.currentTimeMillis();
            JOptionPane.showMessageDialog(null, "Tiempo trancurrido: " + (timeafter - timebefore) + " milisegundos");
        }
    }

    public void onGenerateKey(ActionEvent actionEvent) {
        try {
            secretKey = KeyGenerator.getInstance("DES").generateKey();
            enableButtons();
            txtTextToCipher.setEditable(true);
            JOptionPane.showMessageDialog(null, "Llave generada con éxito");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void onSaveKey(ActionEvent actionEvent) {
        count ++;
        FileOutputStream fileOut;
        ObjectOutputStream out;
        try {
            fileOut = new FileOutputStream(Objects.requireNonNullElse(fileName, "C:\\Users\\Juan\\Downloads\\secretKey ("+ count +") .txt"));
            JOptionPane.showMessageDialog(null, "La secret Key se guardado en Descargas con éxito");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(secretKey);
            out.flush();
            out.close();
        } catch (Exception e) {

        }
    }

    public void onLoadKey(ActionEvent actionEvent) {
        String fileName = getFileName();
        this.fileName = fileName;
        FileInputStream fileIn;
        ObjectInputStream in;
        try {
            fileIn = new FileInputStream(fileName);
            in = new ObjectInputStream(fileIn);
            secretKey = (SecretKey) in.readObject();
            enableButtons();
            txtTextToCipher.setEditable(true);
        } catch (Exception e) {

        }
    }
}