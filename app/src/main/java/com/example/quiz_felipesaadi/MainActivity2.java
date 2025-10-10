package com.example.quiz_felipesaadi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    // Estrutura para representar uma pergunta
    private static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    // Lista de todas as perguntas do quiz
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    // Referências para os elementos da UI
    private TextView questionTextView;
    private TextView scoreTextView;
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private Button option4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Inicializa os elementos da UI
        questionTextView = findViewById(R.id.question_textview);
        scoreTextView = findViewById(R.id.score_textview);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);
        option3Button = findViewById(R.id.option3_button);
        option4Button = findViewById(R.id.option4_button);

        // Carrega as perguntas e exibe a primeira
        loadQuestions();
        displayCurrentQuestion();

        // Define o que acontece quando um botão de opção é clicado
        View.OnClickListener answerClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                int selectedOptionIndex = 0;

                if (clickedButton.getId() == R.id.option1_button) {
                    selectedOptionIndex = 0;
                } else if (clickedButton.getId() == R.id.option2_button) {
                    selectedOptionIndex = 1;
                } else if (clickedButton.getId() == R.id.option3_button) {
                    selectedOptionIndex = 2;
                } else if (clickedButton.getId() == R.id.option4_button) {
                    selectedOptionIndex = 3;
                }

                checkAnswer(selectedOptionIndex);
            }
        };

        option1Button.setOnClickListener(answerClickListener);
        option2Button.setOnClickListener(answerClickListener);
        option3Button.setOnClickListener(answerClickListener);
        option4Button.setOnClickListener(answerClickListener);
    }

    // Método para carregar as perguntas do quiz
    // Método para carregar as perguntas do quiz
    private void loadQuestions() {
        questionList = new ArrayList<>();
        // Abaixo estão as novas perguntas de conhecimentos gerais
        questionList.add(new Question(
                "Qual é a capital da Austrália?",
                new String[]{"Sydney", "Melbourne", "Canberra", "Auckland"},
                2 // A resposta correta é a terceira opção (índice 2)
        ));
        questionList.add(new Question(
                "De quem é a famosa frase 'Penso, logo existo'?",
                new String[]{"Platão", "Sócrates", "Galileu Galilei", "René Descartes"},
                3 // A resposta correta é a quarta opção (índice 3)
        ));
        questionList.add(new Question(
                "Qual elemento químico tem o símbolo 'Au'?",
                new String[]{"Prata", "Alumínio", "Ouro", "Cobre"},
                2 // A resposta correta é a terceira opção (índice 2)
        ));
        questionList.add(new Question(
                "Quem pintou a obra 'Mona Lisa'?",
                new String[]{"Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Michelangelo"},
                1 // A resposta correta é a segunda opção (índice 1)
        ));
        questionList.add(new Question(
                "Qual o maior planeta do Sistema Solar?",
                new String[]{"Terra", "Marte", "Saturno", "Júpiter"},
                3 // A resposta correta é a quarta opção (índice 3)
        ));
    }

    // Método para exibir a pergunta atual e as opções
    private void displayCurrentQuestion() {
        if (currentQuestionIndex < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.questionText);
            option1Button.setText(currentQuestion.options[0]);
            option2Button.setText(currentQuestion.options[1]);
            option3Button.setText(currentQuestion.options[2]);
            option4Button.setText(currentQuestion.options[3]);
        } else {
            // Fim do quiz
            showFinalScore();
        }
    }

    // Método para verificar se a resposta está correta
    private void checkAnswer(int selectedOptionIndex) {
        Question currentQuestion = questionList.get(currentQuestionIndex);

        if (selectedOptionIndex == currentQuestion.correctAnswerIndex) {
            // Resposta correta
            score++;
            Toast.makeText(this, "Correto!", Toast.LENGTH_SHORT).show();
        } else {
            // Resposta incorreta
            Toast.makeText(this, "Incorreto!", Toast.LENGTH_SHORT).show();
        }

        // Atualiza a pontuação na tela
        scoreTextView.setText("Pontuação: " + score);

        // Passa para a próxima pergunta
        currentQuestionIndex++;
        displayCurrentQuestion();
    }

    // Método para mostrar o resultado final
    private void showFinalScore() {
        questionTextView.setText("Quiz finalizado!\nSua pontuação final: " + score + " de " + questionList.size());
        // Esconde os botões de opção
        option1Button.setVisibility(View.GONE);
        option2Button.setVisibility(View.GONE);
        option3Button.setVisibility(View.GONE);
        option4Button.setVisibility(View.GONE);
    }
}