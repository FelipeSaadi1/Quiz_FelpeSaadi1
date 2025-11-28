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
    Button btnCrud; // Declaração do novo botão

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

        // 1. Configuração do Botão do Quiz (abre a tela do jogo)
        btnClique = findViewById(R.id.BotaoEnviar);
        btnClique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // 2. Configuração das Frases Motivacionais
        textFrase = findViewById(R.id.textFrase);
        botaoNovaFrase = findViewById(R.id.botaoNovaFrase);

        botaoNovaFrase.setOnClickListener(v -> {
            int index = new Random().nextInt(frases.length);
            textFrase.setText(frases[index]);
        });

        // 3. Configuração do Botão CRUD (abre a tela de Banco de Dados)
        btnCrud = findViewById(R.id.botaoCrud);
        btnCrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vai para a tela de cadastro (CrudActivity)
                Intent intent = new Intent(MainActivity.this, CrudActivity.class);
                startActivity(intent);
            }
        });
    }
}