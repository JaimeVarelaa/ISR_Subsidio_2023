package com.example.isrysubsidio2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        EditText name = findViewById(R.id.Name);
        ImageButton accept = findViewById(R.id.Accept); 
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = name.getText().toString();

                if(user.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese un nombre",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(view.getContext(),MainActivity.class);
                    intent.putExtra("name", user);
                    startActivityForResult(intent, 0);
                    finish();
                }
            }
        });
    }
}