package com.example.mvvm.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.R;
import com.example.mvvm.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);
        button = findViewById(R.id.button);


        //MainActivityViewModel mainActivityViewModel = new MainActivityViewModel();  // this line will cause a refresh while rotating the phone
        MainActivityViewModel mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);  // this ViewModelProvider will prevent auto refresh on rotating ; need to extends ViewModel
        LiveData<String> getRandomNumber = mainActivityViewModel.getMyRandomNumber();


        getRandomNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        //textView.setText(getRandomNumber);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityViewModel.createNumber();
            }
        });
    }
}
