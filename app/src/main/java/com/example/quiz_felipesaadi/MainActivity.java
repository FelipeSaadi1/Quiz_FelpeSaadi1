package com.example.quiz_felipesaadi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textFrase;
    Button botaoNovaFrase;
    Button btnClique;

    String[] frases = {
            "Acredite nos seus sonhos.",
            "Você é capaz de tudo que quiser.",
            "Persista! O resultado vem com o tempo.",
            "O esforço de hoje é o sucesso de amanhã.",
            "Não desista. Grandes coisas levam tempo."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botão do quiz (abre a nova tela)
        btnClique = findViewById(R.id.BotaoEnviar);
        btnClique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir para a segunda tela (MainActivity2)
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // Novo TextView e Botão de frases
        textFrase = findViewById(R.id.textFrase);
        botaoNovaFrase = findViewById(R.id.botaoNovaFrase);

        botaoNovaFrase.setOnClickListener(v -> {
            int index = new Random().nextInt(frases.length);
            textFrase.setText(frases[index]);
        });
    }
}
