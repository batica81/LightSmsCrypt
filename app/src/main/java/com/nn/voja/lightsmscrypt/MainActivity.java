package com.nn.voja.lightsmscrypt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickEncryptActivity(View view){
        Intent intent = new Intent(this, EncryptActivity.class);
        startActivity(intent);
    }

    public void onClickDecryptActivity(View view){
        Intent intent = new Intent(this, DecryptActivity.class);
        startActivity(intent);
    }

}
