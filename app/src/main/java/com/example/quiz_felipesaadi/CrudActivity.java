package com.example.quiz_felipesaadi;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CrudActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText editNome, editId;
    TextView listaJogadores;
    Button btnAdd, btnUpd, btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        db = new DatabaseHelper(this);

        editNome = findViewById(R.id.editNomeJogador);
        editId = findViewById(R.id.editIdJogador);
        listaJogadores = findViewById(R.id.listaJogadores);
        btnAdd = findViewById(R.id.btnAdicionar);
        btnUpd = findViewById(R.id.btnAtualizar);
        btnDel = findViewById(R.id.btnDeletar);

        listarJogadores(); // Carrega a lista ao abrir

        // CREATE
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editNome.getText().toString();
                if (!nome.isEmpty()) {
                    db.addPlayer(nome);
                    listarJogadores();
                    editNome.setText("");
                    Toast.makeText(CrudActivity.this, "Jogador Adicionado!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // UPDATE
        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = editId.getText().toString();
                String nome = editNome.getText().toString();
                if (!idStr.isEmpty() && !nome.isEmpty()) {
                    db.updatePlayer(Integer.parseInt(idStr), nome);
                    listarJogadores();
                    Toast.makeText(CrudActivity.this, "Atualizado!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // DELETE
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = editId.getText().toString();
                if (!idStr.isEmpty()) {
                    db.deletePlayer(Integer.parseInt(idStr));
                    listarJogadores();
                    editId.setText("");
                    Toast.makeText(CrudActivity.this, "Deletado!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // READ
    private void listarJogadores() {
        Cursor res = db.getAllPlayers();
        if (res.getCount() == 0) {
            listaJogadores.setText("Nenhum jogador cadastrado.");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("ID: ").append(res.getString(0)).append("\n");
            buffer.append("Nome: ").append(res.getString(1)).append("\n");
            buffer.append("----------------\n");
        }
        listaJogadores.setText(buffer.toString());
    }
}