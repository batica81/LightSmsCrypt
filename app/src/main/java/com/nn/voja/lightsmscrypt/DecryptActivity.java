package com.nn.voja.lightsmscrypt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import me.everything.providers.android.telephony.Sms;
import me.everything.providers.android.telephony.TelephonyProvider;

public class DecryptActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);
    }

    public void onClickChooseMessage(View view) {
        final EditText textView = findViewById(R.id.editText2);
        TelephonyProvider telephonyProvider = new TelephonyProvider(getApplicationContext());
        List<Sms> smses = telephonyProvider.getSms(TelephonyProvider.Filter.ALL).getList();
        ArrayList<String> messageBodies = new ArrayList<>();

        for (Sms sms : smses) {
            messageBodies.add(sms.body);
        }

        final Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, messageBodies);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view, int arg2, long arg3) {
                String selected_msg=spinner.getSelectedItem().toString();
                textView.setText(selected_msg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }


    public void onClickDecrypt(View view) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        EditText textView = findViewById(R.id.editText2);
        EditText passwordField = findViewById(R.id.editText4);
        String password = String.valueOf(passwordField.getText());
        String ciphertext = String.valueOf(textView.getText());
        Encryptor enc = new Encryptor();

        try {
            textView.setText(enc.decrypt(password, ciphertext));
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
    }
}
