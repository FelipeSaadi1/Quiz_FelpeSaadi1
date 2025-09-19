package com.example.quiz_felipesaadi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectar o botão do XML pelo id
        Button btnClique = findViewById(R.id.BotaoEnviar);

        // Evento de clique no botão
        btnClique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Botão clicado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
