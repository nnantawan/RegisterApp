package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.AppDatabase;
import com.example.myapplication.model.entity.User;
import com.example.myapplication.repository.UserRepository;

import static com.example.myapplication.model.AppDatabase.DATABASE_NAME;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        repository = new UserRepository(this);

        setButtonOnClickListener();
    }

    private void setButtonOnClickListener(){

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserLogin();
            }
        });

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getUserLogin(){
        User user = repository.getUserLogin(
                binding.usernameEditText.getText().toString(),
                binding.passwordEdittext.getText().toString()
        );

        if (user == null){
            new AlertDialog.Builder(LoginActivity.this)
                    .setCancelable(false)
                    .setTitle("ไม่พบข้อมูลผู้ใช้นี้ในระบบ")
                    .setNegativeButton("ปิด", null)
                    .create()
                    .show();
        }else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

}
