package com.example.isrysubsidio2023;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.lang.Float;
import java.util.List;
import java.util.ArrayList;


import androidx.appcompat.app.AppCompatActivity;


public class Calculadora extends AppCompatActivity {

    List<List<Float>> datos = new ArrayList<>();
    List<List<Float>> datosSub = new ArrayList<>();

    float limiteInf, diferencia, tasa, impuestoMar, cuotaFija, impuestoPrev, subsidio, impuestosRet, percepcionMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora_layout);

        String name = getIntent().getStringExtra("name");
        String amount = getIntent().getStringExtra("amount");
        String period = getIntent().getStringExtra("period");

        TextView txt1 = findViewById(R.id.text1);
        txt1.setText("Bien "+name+" ya tengo los cálculos.");

        TextView txt2 = findViewById(R.id.Details);
        txt2.setText("Este es el desglose en un periodo "+period+":");

        ListView listview = findViewById(R.id.Calculos);
        String[] parametros = {"Ingreso: ", "Límite inferior: ", "Diferencia: ", "Tasa: ", "Impuesto marginal: ", "Cuota fija: ", "Impuesto previo", "Subsidio", "Impuesto a retener: ", "Percepción menos impuestos: "};
        ArrayAdapter<String> adaptadorpar = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parametros);
        listview.setAdapter(adaptadorpar);

        asignacionDatos(period);
        calculo(Float.parseFloat(amount));

        ListView listview2 = findViewById(R.id.Parametros);
        String[] valores = {"$"+amount, "$"+limiteInf, "$"+diferencia, "$"+tasa, "$"+impuestoMar, "$"+cuotaFija, "$"+impuestoPrev, "$"+subsidio, "$"+impuestosRet, "$"+percepcionMI};
        ArrayAdapter<String> adaptadorval = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valores);
        listview2.setAdapter(adaptadorval);

        Button exit = findViewById(R.id.BtnSalir);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void asignacionDatos(String period){
        if(period.matches("Diario")) {
            datos.add(filaISR(0.01f, 24.54f, 0.00f, 1.92f));
            datos.add(filaISR(24.55f, 208.29f, 0.47f, 6.40f));
            datos.add(filaISR(208.30f, 366.05f, 12.23f, 10.88f));
            datos.add(filaISR(366.06f, 425.52f, 29.40f, 16.00f));
            datos.add(filaISR(425.53f, 509.46f, 38.91f, 17.92f));
            datos.add(filaISR(509.47f, 1027.52f, 53.95f, 21.36f));
            datos.add(filaISR(1027.53f, 1619.51f, 164.61f, 23.52f));
            datos.add(filaISR(1619.52f, 3091.90f, 303.85f, 30.00f));
            datos.add(filaISR(3091.91f, 4122.54f, 745.56f, 32.00f));
            datos.add(filaISR(4122.55f, 12367.62f, 1075.37f, 34.00f));
            datos.add(filaISR(12367.63f, Float.MAX_VALUE, 3878.69f, 35.00f));

            datosSub.add(filaSub(0.01f, 58.19f, 13.39f));
            datosSub.add(filaSub(58.20f, 87.28f, 13.38f));
            datosSub.add(filaSub(87.29f, 114.24f, 13.38f));
            datosSub.add(filaSub(114.25f, 116.38f, 12.92f));
            datosSub.add(filaSub(116.39f, 146.25f, 12.58f));
            datosSub.add(filaSub(146.26f, 155.17f, 11.65f));
            datosSub.add(filaSub(155.18f, 175.51f, 10.69f));
            datosSub.add(filaSub(175.52f, 204.76f, 9.69f));
            datosSub.add(filaSub(204.77f, 234.01f, 8.34f));
            datosSub.add(filaSub(234.02f, 242.84f, 7.16f));
            datosSub.add(filaSub(242.85f, Float.MAX_VALUE, 0.00f));

        } else if (period.matches("Semanal")) {
            datos.add(filaISR(0.01f, 171.78f, 0f, 1.92f));
            datos.add(filaISR(171.79f, 1458.03f, 3.29f, 6.4f));
            datos.add(filaISR(1458.04f, 2562.35f, 85.61f, 10.88f));
            datos.add(filaISR(2562.36f, 2978.64f, 205.8f, 16f));
            datos.add(filaISR(2978.65f, 3566.22f, 272.37f, 17.92f));
            datos.add(filaISR(3566.23f, 7192.64f, 377.65f, 21.36f));
            datos.add(filaISR(7192.65f, 11336.57f, 1152.27f, 23.52f));
            datos.add(filaISR(11336.58f, 21643.30f, 2126.95f, 30f));
            datos.add(filaISR(21643.31f, 28857.78f, 5218.92f, 32f));
            datos.add(filaISR(28857.79f, 86573.34f, 7527.59f, 34f));
            datos.add(filaISR(86573.35f, Float.MAX_VALUE, 27150.83f, 35f));

            datosSub.add(filaSub(0.01f, 407.33f, 93.73f));
            datosSub.add(filaSub(407.34f, 610.96f, 93.66f));
            datosSub.add(filaSub(610.97f, 799.68f, 93.66f));
            datosSub.add(filaSub(799.69f, 814.66f, 90.44f));
            datosSub.add(filaSub(814.67f, 1023.75f, 88.06f));
            datosSub.add(filaSub(1023.76f, 1086.19f, 81.55f));
            datosSub.add(filaSub(1086.20f, 1228.57f, 74.83f));
            datosSub.add(filaSub(1228.58f, 1433.32f, 67.83f));
            datosSub.add(filaSub(1433.33f, 1638.07f, 58.38f));
            datosSub.add(filaSub(1638.08f, 1699.88f, 50.12f));
            datosSub.add(filaSub(1699.89f, Float.MAX_VALUE, 0f));

        } else if (period.matches("Decenal")) {
            datos.add(filaISR(0.01f, 245.40f, 0f, 1.92f));
            datos.add(filaISR(245.41f, 2082.90f, 4.70f, 6.40f));
            datos.add(filaISR(2082.91f, 3660.50f, 122.30f, 10.88f));
            datos.add(filaISR(3660.51f, 4255.20f, 294.00f, 16.00f));
            datos.add(filaISR(4255.21f, 5094.60f, 389.10f, 17.92f));
            datos.add(filaISR(5094.61f, 10275.20f, 539.50f, 21.36f));
            datos.add(filaISR(10275.21f, 16195.10f, 1646.10f, 23.52f));
            datos.add(filaISR(16195.11f, 30919.00f, 3038.50f, 30.00f));
            datos.add(filaISR(30919.01f, 41225.40f, 7455.60f, 32.00f));
            datos.add(filaISR(41225.41f, 123676.20f, 10753.70f, 34.00f));
            datos.add(filaISR(123676.21f, Float.MAX_VALUE, 38786.90f, 35.00f));

            datosSub.add(filaSub(581.91f, 872.80f, 133.80f));
            datosSub.add(filaSub(872.81f, 1142.40f, 133.80f));
            datosSub.add(filaSub(1142.41f, 1163.80f, 129.20f));
            datosSub.add(filaSub(1163.81f, 1462.50f, 125.80f));
            datosSub.add(filaSub(1462.51f, 1551.70f, 116.50f));
            datosSub.add(filaSub(1551.71f, 1755.10f, 106.90f));
            datosSub.add(filaSub(1755.11f, 2047.60f, 96.90f));
            datosSub.add(filaSub(2047.61f, 2340.10f, 83.40f));
            datosSub.add(filaSub(2340.11f, 2428.40f, 71.60f));
            datosSub.add(filaSub(2428.41f, Float.MAX_VALUE, 0.00f));
            
        } else if (period.matches("Quincenal")) {
            datos.add(filaISR(0.01f, 368.1f, 0f, 1.92f));
            datos.add(filaISR(368.11f, 3124.35f, 7.05f, 6.4f));
            datos.add(filaISR(3124.36f, 5490.75f, 183.45f, 10.88f));
            datos.add(filaISR(5490.76f, 6382.80f, 441f, 16f));
            datos.add(filaISR(6382.81f, 7641.90f, 583.65f, 17.92f));
            datos.add(filaISR(7641.91f, 15412.80f, 809.25f, 21.36f));
            datos.add(filaISR(15412.81f, 24292.65f, 2469.15f, 23.52f));
            datos.add(filaISR(24292.66f, 46378.50f, 4557.75f, 30f));
            datos.add(filaISR(46378.51f, 61838.10f, 11183.40f, 32f));
            datos.add(filaISR(61838.11f, 185514.30f, 16130.55f, 34f));
            datos.add(filaISR(185514.31f, Float.MAX_VALUE, 58180.35f, 35f));

            datosSub.add(filaSub(0.01f, 872.85f, 200.85f));
            datosSub.add(filaSub(872.86f, 1309.20f, 200.7f));
            datosSub.add(filaSub(1309.21f, 1713.60f, 200.7f));
            datosSub.add(filaSub(1713.61f, 1745.70f, 193.8f));
            datosSub.add(filaSub(1745.71f, 2193.75f, 188.7f));
            datosSub.add(filaSub(2193.76f, 2327.55f, 174.75f));
            datosSub.add(filaSub(2327.56f, 2632.65f, 160.35f));
            datosSub.add(filaSub(2632.66f, 3071.40f, 145.35f));
            datosSub.add(filaSub(3071.41f, 3510.15f, 125.1f));
            datosSub.add(filaSub(3510.16f, 3642.60f, 107.4f));
            datosSub.add(filaSub(3642.61f, Float.MAX_VALUE, 0f));

        } else if (period.matches("Mensual")) {
            datos.add(filaISR(0.01f, 746.04f, 0f, 1.92f));
            datos.add(filaISR(746.05f, 6332.05f, 14.32f, 6.4f));
            datos.add(filaISR(6332.06f, 11128.01f, 371.83f, 10.88f));
            datos.add(filaISR(11128.02f, 12935.82f, 893.63f, 16f));
            datos.add(filaISR(12935.83f, 15487.71f, 1182.88f, 17.92f));
            datos.add(filaISR(15487.72f, 31236.49f, 1640.18f, 21.36f));
            datos.add(filaISR(31236.5f, 49233f, 5004.12f, 23.52f));
            datos.add(filaISR(49233.01f, 93993.9f, 9236.89f, 30f));
            datos.add(filaISR(93993.91f, 125325.2f, 22665.17f, 32f));
            datos.add(filaISR(125325.21f, 375975.61f, 32691.18f, 34f));
            datos.add(filaISR(375975.62f, Float.MAX_VALUE, 117912.32f, 35f));

            datosSub.add(filaSub(0.01f, 1768.96f, 407.02f));
            datosSub.add(filaSub(1768.97f, 2653.38f, 406.83f));
            datosSub.add(filaSub(2653.39f, 3472.84f, 406.62f));
            datosSub.add(filaSub(3472.85f, 3537.87f, 392.77f));
            datosSub.add(filaSub(3537.88f, 4446.15f, 382.46f));
            datosSub.add(filaSub(4446.16f, 4717.18f, 354.23f));
            datosSub.add(filaSub(4717.19f, 5335.42f, 324.87f));
            datosSub.add(filaSub(5335.43f, 6224.67f, 294.63f));
            datosSub.add(filaSub(6224.68f, 7113.90f, 253.54f));
            datosSub.add(filaSub(7113.91f, 7382.33f, 217.61f));
            datosSub.add(filaSub(7382.34f, Float.MAX_VALUE, 0f));
        }
    }

    private List<Float> filaISR(float fila1, float fila2, float fila3, float fila4) {
        List<Float> filaISR = new ArrayList<>();
        filaISR.add(fila1);
        filaISR.add(fila2);
        filaISR.add(fila3);
        filaISR.add(fila4);
        return filaISR;
    }

    private List<Float> filaSub(float fila1, float fila2, float fila3) {
        List<Float> filaSub = new ArrayList<>();
        filaSub.add(fila1);
        filaSub.add(fila2);
        filaSub.add(fila3);
        return filaSub;
    }

    private void calculo(float amout) {
        for (int i = 0; i < 11; i++){
            if(amout >= datos.get(i).get(0) && amout <= datos.get(i).get(1)){
                limiteInf = datos.get(i).get(0);
                diferencia = amout - limiteInf;
                tasa = datos.get(i).get(3);
                impuestoMar = diferencia * (tasa/100);
                cuotaFija = datos.get(i).get(2);
                impuestoPrev= impuestoMar + cuotaFija;
                for(int j = 0; j < 11; j++){
                    if(amout >= datosSub.get(j).get(0) && amout <= datosSub.get(j).get(1)){
                        subsidio = datosSub.get(j).get(2);
                        break;
                    }
                }
                impuestosRet = impuestoPrev - subsidio;
                percepcionMI = amout - impuestosRet;
                break;
            }
        }
    }
}