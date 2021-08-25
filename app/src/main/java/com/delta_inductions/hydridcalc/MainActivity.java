package com.delta_inductions.hydridcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Keyboard_input.Keyboard_inputlistner {
private Keyboard_input input;
private Keyboard_output output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = new Keyboard_input();
         output = new Keyboard_output();
        getSupportFragmentManager().beginTransaction().replace(R.id.container2,input).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,output).commit();
    }

    @Override
    public void input(double inputsend) {
        output.updatetextview(inputsend);
    }
}