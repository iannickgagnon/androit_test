package com.example.agendent2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ModificationMotDePasse extends AppCompatActivity {

    private EditText editMdpActuel;
    private EditText editMdpNouv;
    private EditText editMdpNouvConf;
    private Button btnModifMdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_mot_de_passe);

        editMdpActuel = findViewById(R.id.editTextMdpActuel);
        editMdpNouv = findViewById(R.id.editTextNouvMdp);
        editMdpNouvConf = findViewById(R.id.editTextNouvMdpConf);

        btnModifMdp = findViewById(R.id.buttonConfirmerModifMdp);
        btnModifMdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Accéder à la session
                SharedPreferences session = getSharedPreferences("Connexion", Context.MODE_PRIVATE);

                String courriel = session.getString("userId", null);
                String mdpActuel = editMdpActuel.getText().toString();
                String mdpNouveau = editMdpNouv.getText().toString();
                String mdpNouveauConf = editMdpNouvConf.getText().toString();

                // Nettoyer les champs

                // S'assurer que le nouveau mdp est valide

                // Effectuer une requête HTTP POST
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Créer une URL pour votre requête
                            URL url;
                            url = new URL("http://localhost:1235/api/modifier_mdp_patient.php");

                            // Ouvrir une connexion HttpURLConnection
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("POST");
                            connection.setDoOutput(true);

                            // Données à envoyer
                            String data = "courriel="+courriel+"&mot_de_passe="+mdpActuel+"&nouv_mot_de_passe="+mdpNouveau;

                            // Écrire les données dans le flux de sortie de la connexion
                            try (OutputStream os = connection.getOutputStream()) {
                                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                                os.write(input, 0, input.length);
                            }

                            // Lire la réponse de la requête
                            InputStream inputStream = connection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            StringBuilder response = new StringBuilder();
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                response.append(line);
                            }
                            bufferedReader.close();

                            /* Afficher la réponse obtenue
                            final String responseString = response.toString();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Réponse : " + responseString, Toast.LENGTH_SHORT).show();
                                }
                            });
                            */

                            // Fermer la connexion
                            connection.disconnect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}