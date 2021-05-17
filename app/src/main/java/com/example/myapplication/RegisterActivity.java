package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.ActivityRegisterBinding;
import com.example.myapplication.model.AppDatabase;
import com.example.myapplication.model.entity.User;
import com.example.myapplication.repository.UserRepository;

import static com.example.myapplication.model.AppDatabase.DATABASE_NAME;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private UserRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mRepository = new UserRepository(this);

        setButtonOnClickListener();
    }

    private void setButtonOnClickListener(){

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(RegisterActivity.this)
                        .setCancelable(false)
                        .setTitle("ยืนยันการบันทึก")
                        .setNegativeButton("ยกเลิก", null)
                        .setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                saveData();
                            }
                        }).create()
                        .show();
            }
        });
    }

    private void saveData(){
        User user = new User();
        user.setUsername(binding.usernameEditText.getText().toString());
        user.setEmail(binding.emailEditText.getText().toString());
        user.setPassword(binding.passwordEdittext.getText().toString());

        mRepository.insert(user);

        finish();
    }
}
