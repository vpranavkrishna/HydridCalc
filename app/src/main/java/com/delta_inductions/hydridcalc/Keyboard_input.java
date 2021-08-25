package com.delta_inductions.hydridcalc;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Keyboard_input extends Fragment implements View.OnClickListener {
    private static final String TAG = "Keyboard_input";
    private static final int add = 100;
    private static final int sub = 101;
    private static final int divide = 102;
    private static final int mult = 103;

    private TextView input;
    private View v;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button div;
    private Button minus;
    private Button plus;
    private Button X;
    private Button AC;
    private Button del;
    private Button dot;
    private Button equal;
    private double calc = 0;
    private double calcvalue;
    private int positionop;
    private int optype;
    private Keyboard_inputlistner listener;
    private double num1;
    private double num2;
    boolean op = false;
    private int index;
    private double ansnum;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_input, container, false);
        zero = v.findViewById(R.id.zero);
        one = v.findViewById(R.id.one);
        two = v.findViewById(R.id.two);
        three = v.findViewById(R.id.three);
        four = v.findViewById(R.id.four);
        five = v.findViewById(R.id.five);
        six = v.findViewById(R.id.six);
        seven = v.findViewById(R.id.seven);
        eight = v.findViewById(R.id.eight);
        nine = v.findViewById(R.id.nine);
        div = v.findViewById(R.id.div);
        minus = v.findViewById(R.id.minus);
        plus = v.findViewById(R.id.plus);
        X = v.findViewById(R.id.X);
        AC = v.findViewById(R.id.AC);
        del = v.findViewById(R.id.del);
        dot = v.findViewById(R.id.dot);
        equal = v.findViewById(R.id.equal);
        input = v.findViewById(R.id.input);
        input.setText("");
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        dot.setOnClickListener(this);
        X.setOnClickListener(this);
        AC.setOnClickListener(this);
        del.setOnClickListener(this);
        equal.setOnClickListener(this);
        div.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.equal) {
            String inputtext = input.getText().toString();

                if (!inputtext.isEmpty()) {
                    char lastchar = inputtext.charAt(inputtext.length()-1);
                    if (!(lastchar == '/' || lastchar == 'x' || lastchar == '+' || lastchar == '-')) {

                        if (op = false) {
                            try {
                                String ans = input.getText().toString();
                                ansnum = Double.parseDouble(ans);
                                listener.input(ansnum);
                                input.setText("");
                                index = 0;
                            } catch (Exception e) {
                                Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            try {
                                String inputtextt = input.getText().toString();
                                inputtextt = inputtextt.substring(index);
                                num2 = Double.parseDouble(inputtextt);
                                switch (optype) {
                                    case add: {
                                        num1 = num1 + num2;
                                        input.setText(String.valueOf(num1));
                                    }
                                    break;
                                    case sub: {
                                        num1 = num1 - num2;
                                        input.setText(String.valueOf(num1));
                                    }
                                    break;
                                    case mult: {
                                        num1 = num1 * num2;
                                        input.setText(String.valueOf(num1));
                                    }
                                    break;
                                    case divide: {
                                        num1 = num1 / num2;
                                        input.setText(String.valueOf(num1));
                                    }
                                    break;
                                }
                                op = false;
                                index = 0;
                                input.setText("");
                                listener.input(num1);
                                num1 = 0;
                            } catch (Exception e) {
                                Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), "Must not end with a operator", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                listener.input(0);
            }

        if (v.getId() == R.id.del) {
            String inputtext = String.valueOf(input.getText());
            char lastchar = inputtext.charAt(inputtext.length()-1);
            if (!inputtext.isEmpty()) {
                if (lastchar == '+' || lastchar == '-' || lastchar == 'x' || lastchar == '/')
                    op = false;
                input.setText(inputtext.substring(0, inputtext.length() - 1));
            }
        }
        if (v.getId() == R.id.AC) {
            index = 0;
            input.setText("");
            listener.input(0);
            op = false;
        }
        if (v.getId() == R.id.div || v.getId() == R.id.X || v.getId() == R.id.minus || v.getId() == R.id.plus || v.getId() == R.id.dot) {
            String inputtext = String.valueOf(input.getText());
            if (!inputtext.isEmpty()) {
                char lastchar = inputtext.charAt(inputtext.length() - 1);
                Log.d(TAG, "onClick: " + lastchar);
                if (!(lastchar == '/' || lastchar == 'x' || lastchar == '-' || lastchar == '+') ){
                    switch (v.getId()) {
                        case R.id.div: {
                            if (!op) {
                                try {
                                    String inputtextt = input.getText().toString();
                                    num1 = Double.parseDouble(inputtextt);
                                    optype = divide;
                                    input.append("/");
                                    index = input.length();
                                    op = true;
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                try {
                                    String inputtextt = input.getText().toString();
                                    inputtextt = inputtextt.substring(index);
                                    num2 = Double.parseDouble(inputtextt);
                                    switch (optype) {
                                        case add: {
                                            num1 = num1 + num2;
                                            input.setText(num1 + "/");
                                        }
                                        break;
                                        case sub: {
                                            num1 = num1 - num2;
                                            input.setText(num1 + "/");
                                        }
                                        break;
                                        case mult: {
                                            num1 = num1 * num2;
                                            input.setText(num1 + "/");
                                        }
                                        break;
                                        case divide: {
                                            num1 = num1 / num2;
                                            input.setText(num1 + "/");
                                        }
                                        break;
                                    }
                                    optype = divide;
                                    index = input.getText().toString().length();
                                    listener.input(num1);
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;
                        case R.id.X: {
                            if (!op) {
                                try {
                                    String inputtextt = input.getText().toString();
                                    num1 = Double.parseDouble(inputtextt);
                                    optype = mult;
                                    input.append("x");
                                    index = input.length();
                                    op = true;
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                try {
                                    String inputtextt = input.getText().toString();
                                    inputtextt = inputtextt.substring(index);
                                    num2 = Double.parseDouble(inputtextt);
                                    switch (optype) {
                                        case add: {
                                            num1 = num1 + num2;
                                            input.setText(num1 + "x");
                                        }
                                        break;
                                        case sub: {
                                            num1 = num1 - num2;
                                            input.setText(num1 + "x");
                                        }
                                        break;
                                        case mult: {
                                            num1 = num1 * num2;
                                            input.setText(num1 + "x");
                                        }
                                        break;
                                        case divide: {
                                            num1 = num1 / num2;
                                            input.setText(num1 + "x");
                                        }
                                        break;
                                    }
                                    optype = mult;
                                    index = input.getText().toString().length();
                                    listener.input(num1);
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        break;
                        case R.id.minus: {
                            if (!op) {
                                try {
                                    String inputtextt = input.getText().toString();
                                    num1 = Double.parseDouble(inputtextt);
                                    optype = sub;
                                    input.append("-");
                                    index = input.length();
                                    op = true;
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                try {
                                    String inputtextt = input.getText().toString();
                                    inputtextt = inputtextt.substring(index);
                                    num2 = Double.parseDouble(inputtextt);
                                    switch (optype) {
                                        case add: {
                                            num1 = num1 + num2;
                                            input.setText(num1 + "-");
                                        }
                                        break;
                                        case sub: {
                                            num1 = num1 - num2;
                                            input.setText(num1 + "-");
                                        }
                                        break;
                                        case mult: {
                                            num1 = num1 * num2;
                                            input.setText(num1 + "-");
                                        }
                                        break;
                                        case divide: {
                                            num1 = num1 / num2;
                                            input.setText(num1 + "-");
                                        }
                                        break;
                                    }
                                    optype = sub;
                                    index = input.getText().toString().length();
                                    listener.input(num1);
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                        break;
                        case R.id.plus: {
                            if (!op) {
                                try {
                                    String inputtextt = input.getText().toString();
                                    num1 = Double.parseDouble(inputtextt);
                                    optype = add;
                                    input.append("+");
                                    index = input.length();
                                    op = true;
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                                }
                            }
                         else{
                             try {
                                 String inputtextt = input.getText().toString();
                                 inputtextt = inputtextt.substring(index);
                                 num2 = Double.parseDouble(inputtextt);
                                 switch (optype) {
                                     case add: {
                                         num1 = num1 + num2;
                                         input.setText(num1 + "+");
                                     }
                                     break;
                                     case sub: {
                                         num1 = num1 - num2;
                                         input.setText(num1 + "+");
                                     }
                                     break;
                                     case mult: {
                                         num1 = num1 * num2;
                                         input.setText(num1 + "+");
                                     }
                                     break;
                                     case divide: {
                                         num1 = num1 / num2;
                                         input.setText(num1 + "+");
                                     }
                                     break;
                                 }
                                 optype = add;
                                 index = input.getText().toString().length();
                                 listener.input(num1);
                             }
                             catch (Exception e)
                             {
                                 Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                             }
                        }
                    }
                        break;
                    }
                }
            }


        }


                switch (v.getId()) {
                    case R.id.zero: {
                        input.append("0");
                    }
                    break;
                    case R.id.one: {
                        input.append("1");
                    }
                    break;
                    case R.id.two: {
                        input.append("2");
                    }
                    break;
                    case R.id.three: {
                        input.append("3");
                    }
                    break;
                    case R.id.four: {
                        input.append("4");
                    }
                    break;
                    case R.id.five: {
                        input.append("5");

                    }
                    break;
                    case R.id.six: {
                        input.append("6");
                    }
                    break;
                    case R.id.seven: {
                        input.append("7");
                    }
                    break;
                    case R.id.eight: {
                        input.append("8");
                    }
                    break;
                    case R.id.nine: {
                        input.append("9");
                    }
                    break;
                    case R.id.dot: {
                        input.append(".");
                    }
                    break;

                }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Keyboard_inputlistner) {
            listener = (Keyboard_inputlistner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Keyboard_inputlistner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    public interface Keyboard_inputlistner
    {
        void input(double inputsend);
    }

}
