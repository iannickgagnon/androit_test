package com.example.agendent2;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmerPresence extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmer_presence);

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // Si le téléphone supporte NFC
        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled()) {
                // Demander à l'utilisateur d'activer NFC
                Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                startActivity(intent);
            }
        } else {
            // L'appareil ne supporte pas NFC
            Toast.makeText(this, "L'appareil ne supporte pas NFC", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            // Puce NFC détectée, le client peut être présent
            // Effectuez les opérations nécessaires pour confirmer sa présence
            confirmerPresence();
        }
    }

    private void confirmerPresence() {
        Toast.makeText(this, "confirmer presence", Toast.LENGTH_SHORT).show();
        // Vérifier si le client est connecté à son compte dans l'application
        if (patientEstConnecte()) {
            // Mettre à jour la base de données pour confirmer la présence
            confirmerPresenceBD();
        } else {
            // Demander au client de se connecter d'abord
            // Lancer l'activité de connexion
        }
    }

    private boolean patientEstConnecte() {
        // Vérifier si le client est connecté à son compte
        return false;
    }

    private void confirmerPresenceBD() {
        // Requete à la BD
    }
}