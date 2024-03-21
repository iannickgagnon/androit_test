package com.example.agendent2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PageConnexion extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_connexion2);

        editTextEmail = findViewById(R.id.emailInput);
        editTextPassword = findViewById(R.id.passwordInput);
        buttonLogin = findViewById(R.id.loginButton);
        buttonSignUp = findViewById(R.id.signupButton);

        buttonLogin.setOnClickListener(view -> {
            // Logique de connexion
            String courriel = editTextEmail.getText().toString();

            // Nettoyer les champs et valider

            // Si la connexion est validée
            //if...
                // Créer ou modifier un stockage persistant de données sous forme de paires clé-valeur
                // qui s'apparente à une session (pour la connexion)
                SharedPreferences session = getSharedPreferences("Connexion", Context.MODE_PRIVATE);

                // Modifier la session
                SharedPreferences.Editor editeurSession = session.edit();
                editeurSession.putString("courriel", courriel);
                editeurSession.apply();
        });

        buttonSignUp.setOnClickListener(view -> {
            // Logique d'inscription
        });

    }
}