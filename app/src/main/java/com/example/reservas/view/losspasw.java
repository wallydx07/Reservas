package com.example.reservas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class losspasw extends AppCompatActivity implements View.OnClickListener {
    EditText emailEditText;
    Button resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.losspasw);
        emailEditText = findViewById(R.id.emailEditText);
        resetPasswordButton=findViewById(R.id.resetPasswordButton);
        resetPasswordButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String email = emailEditText.getText().toString();
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "El correo de recuperación se envió correctamente", Toast.LENGTH_SHORT).show();


                            // El correo de recuperación se envió correctamente
                        } else {
                            Toast.makeText(getApplicationContext(), "Ocurrió un error al enviar el correo de recuperación", Toast.LENGTH_SHORT).show();

                            // Ocurrió un error al enviar el correo de recuperación
                        }
                    }
                });

finish();
    }
}