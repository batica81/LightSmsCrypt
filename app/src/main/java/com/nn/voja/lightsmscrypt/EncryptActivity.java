package com.nn.voja.lightsmscrypt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncryptActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
    }

    public void onClickEncrypt(View view){
        EditText textView = findViewById(R.id.editText);

        EditText passwordField = findViewById(R.id.editText2);

        String password = String.valueOf(passwordField.getText());

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        byte[] hashedPassword = (md.digest(password.getBytes()));

        String plaintext = String.valueOf(textView.getText());

        byte [] bytePlaintext = plaintext.getBytes();

        SecureRandom sr = new SecureRandom();
        byte[] values = new byte[16];
        sr.nextBytes(values);
        byte[] encIV = values;

//        textView.setText("PLAIN:" + plaintext);

        Encryptor enc = new Encryptor();

        try {
            byte[] tempCypherText = enc.encrypt(hashedPassword, bytePlaintext, encIV);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

//        textView.setText(tempCypherText);

    }

    public void onClickSend(View view) {

        EditText textView = findViewById(R.id.editText);

        String cypherText = String.valueOf(textView.getText());

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, cypherText);

        startActivity(intent);
    }
}
