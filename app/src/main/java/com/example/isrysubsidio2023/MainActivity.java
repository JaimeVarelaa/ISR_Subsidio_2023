package com.example.isrysubsidio2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = getIntent().getStringExtra("name");
        TextView saludo = findViewById(R.id.Saludo);
        saludo.append(name);

        String[] periodo = {"Diario", "Semanal", "Decenal", "Quincenal", "Mensual"};
        ArrayAdapter<String>arrayPeriodo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, periodo);
        Spinner spinPeriodo = findViewById(R.id.periodo);
        arrayPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPeriodo.setAdapter(arrayPeriodo);

        EditText amount = findViewById(R.id.Amounts);
        Button btnAc = findViewById(R.id.BtnAccept);
        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sAmount = amount.getText().toString();

                if(sAmount.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese un monto",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(view.getContext(), Calculadora.class);
                    intent.putExtra("name", name);
                    intent.putExtra("amount", sAmount);
                    intent.putExtra("period", spinPeriodo.getItemAtPosition(spinPeriodo.getSelectedItemPosition()).toString());
                    startActivityForResult(intent, 0);

                }
            }
        });

        Button exit = findViewById(R.id.BtnSalir);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}