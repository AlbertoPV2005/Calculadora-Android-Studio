package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fathzer.soft.javaluator.DoubleEvaluator;



public class MainActivity extends AppCompatActivity {
    private TextView tvRes; // mostrar el resultat
    private StringBuilder expressio = new StringBuilder(); // ex: "33+5+15")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvRes = findViewById(R.id.tvRes);
        Button btnAC = findViewById(R.id.buttonAC);
        Button btnPoint = findViewById(R.id.buttonPoint);
        Button btn0 = findViewById(R.id.button0);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);
        Button btn7 = findViewById(R.id.button7);
        Button btn8 = findViewById(R.id.button8);
        Button btn9 = findViewById(R.id.button9);
        Button btnDivision = findViewById(R.id.buttonDivision);
        Button btnX = findViewById(R.id.buttonX);
        Button btnPlus = findViewById(R.id.buttonPlus);
        Button btnMinus = findViewById(R.id.buttonMinus);
        Button btnEquals = findViewById(R.id.buttonEqual);

        // Listeners
        btn1.setOnClickListener(v -> afegirNum("1"));
        btn2.setOnClickListener(v -> afegirNum("2"));
        btn3.setOnClickListener(v -> afegirNum("3"));
        btn4.setOnClickListener(v -> afegirNum("4"));
        btn5.setOnClickListener(v -> afegirNum("5"));
        btn6.setOnClickListener(v -> afegirNum("6"));
        btn7.setOnClickListener(v -> afegirNum("7"));
        btn8.setOnClickListener(v -> afegirNum("8"));
        btn9.setOnClickListener(v -> afegirNum("9"));
        btnPlus.setOnClickListener(v -> operacio("+"));
        btnMinus.setOnClickListener(v -> operacio("-"));
        btnX.setOnClickListener(v -> operacio("*"));
        btnDivision.setOnClickListener(v -> operacio("/"));
        btnEquals.setOnClickListener(v -> evaluar());
        btnAC.setOnClickListener(v -> reset());
        btnPoint.setOnClickListener(v -> afegirNum("."));
        btn0.setOnClickListener(v -> afegirNum("0"));

        actualitzar();
    }

    private void afegirNum(String c) {
        expressio.append(c);
        tvRes.setText(expressio.toString());
        actualitzar();
    }

    private void operacio(String simbolo) {

        if (expressio.length() > 0) {
            char ultimoCaracter = expressio.charAt(expressio.length() - 1);

            if (esOperador(ultimoCaracter)) {

                return;
            }
        } else if (!simbolo.equals("-")) {

            return;
        }


        expressio.append(simbolo);
        // tvRes.setText(expressio.toString()); // actualitzar() ya hace esto
        actualitzar();
    }

    // Método auxiliar para comprobar si un carácter es un operador
    private boolean esOperador(char caracter) {
        return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/';
    }


    private void reset() {
        expressio.setLength(0);
        tvRes.setText(expressio.toString());


    }

    private void evaluar() {
        // https://mvnrepository.com/artifact/com.fathzer/javaluator
        // https://github.com/fathzer/javaluator  3.0.6
        // "(2^3-1)*sin(pi/4)/ln(pi^2)" = 2.1619718020347976

        // evaluem
        DoubleEvaluator evaluator = new DoubleEvaluator();
        Double result = evaluator.evaluate(expressio.toString());

        // mostrem resultat
        // try/catch
        tvRes.setText(result.toString());
        expressio.setLength(0);

    }

    private void actualitzar() {
        tvRes.setText(expressio.toString());
    }
}