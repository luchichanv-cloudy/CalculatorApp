package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnMulti, btnAdd, btnSub, btnAC, btnDel, btnDivide, btnEqual;
    private TextView textViewHistory, textViewResult;
    private String number;
    double firstnum=0, lastnum=0;
    boolean operator=false,dot=true;
    DecimalFormat formatter= new DecimalFormat("####.####");
    String status; // to know which math operator is doing
    String History,CurrentResult;
    public void Initvalue()
    {

        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);

        btnMulti=findViewById(R.id.btnMulti);
        btnSub=findViewById(R.id.btnSub);
        btnDivide=findViewById(R.id.btnDivide);
        btnAdd=findViewById(R.id.btnAdd);

        btnDot=findViewById(R.id.btnDot);
        btnDel=findViewById(R.id.btnDel);
        btnAC=findViewById(R.id.btnAC);
        btnEqual=findViewById(R.id.btnEqual);

        textViewHistory=findViewById(R.id.textViewHistory);
        textViewResult=findViewById(R.id.textViewResult);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initvalue();
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("9");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot=true;
                History=textViewHistory.getText().toString();
                CurrentResult=textViewResult.getText().toString();
                textViewHistory.setText(History+CurrentResult+"+");
                if (operator)
                {
                    checkstatus();
                }

                status="Add"; operator=false;
                number=null;

            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot=true;
                History=textViewHistory.getText().toString();
                CurrentResult=textViewResult.getText().toString();
                textViewHistory.setText(History+CurrentResult+"/");
                if (operator)
                {
                    checkstatus();
                }
                status="Divide"; operator=false;
                number=null;

            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot=true;
                History=textViewHistory.getText().toString();
                CurrentResult=textViewResult.getText().toString();
                textViewHistory.setText(History+CurrentResult+"-");
                if (operator)
                {
                    checkstatus();
                }
                status="Sub"; operator=false;
                number=null;

            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot=true;
                History=textViewHistory.getText().toString();
                CurrentResult=textViewResult.getText().toString();
                textViewHistory.setText(History+CurrentResult+"*");
                if (operator)
                {
                    checkstatus();
                }
                status="Multiply"; operator=false;
                number=null;

            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!number.contains("."))
                {
                    if (number==null)
                    {
                        number="0.";
                    }
                    else
                    {
                        number+=".";
                    }
                    textViewResult.setText(number);
                    dot=false;
                }

            }
        });
        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot=true;
               status=null;
               textViewResult.setText("0");
               textViewHistory.setText("");
               firstnum=0; lastnum=0;
                number="0";
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot=true;
                if (number.length()>=1)
                {
                    number=number.substring(0,number.length()-1);
                    textViewResult.setText(number);
                }

            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                History="";dot=true;
                CurrentResult=textViewResult.getText().toString();
                textViewHistory.setText(History);
                if (operator=true)
                {
                    if (status=="Add")
                    {
                        plus();
                    }
                    else if(status=="Sub")
                    {
                        minus();
                    }
                    else if(status=="Divide")
                    {
                        divide();
                    }
                    else if(status=="Multiply")
                    {
                        multiply();
                    }
                    else {
                        firstnum=Double.parseDouble(textViewResult.getText().toString());
                    }
                }
//                textViewResult.setText(formatter.format(firstnum));
                operator=false; number=textViewResult.getText().toString();
            }
        });
    }

    public void numberClick(String view)
    {
        String check=textViewHistory.getText().toString();
        if (number==null)
        {
            number=view;
        }
        else if (check.length()<1)
        {
            number=view;firstnum=0;
        }
        else
        {
            number=number+view;
        }
        operator=true;
        textViewResult.setText(number);
    }


    public void plus()
    {
        lastnum=Double.parseDouble(textViewResult.getText().toString());
        firstnum=firstnum+lastnum;
        textViewResult.setText(formatter.format(firstnum));
    }

    public void checkstatus()
    {
        if (status=="Add")
        {
            plus();
        }
        else if(status=="Sub")
        {
            minus();
        }
        else if(status=="Divide")
        {
            divide();
        }
        else
        {
            multiply();
        }
    }

    public void minus()
    {
        if (firstnum==0)
        {
            firstnum=Double.parseDouble(textViewResult.getText().toString());
        }
        else
        {
            lastnum=Double.parseDouble(textViewResult.getText().toString());
            firstnum=firstnum-lastnum;
        }
        textViewResult.setText(formatter.format(firstnum));
    }

    public void multiply()
    {
        if (firstnum==0)
        {
            firstnum=1;
            lastnum=Double.parseDouble(textViewResult.getText().toString());
            firstnum=firstnum*lastnum;
        }
        else
        {
            lastnum=Double.parseDouble(textViewResult.getText().toString());
            firstnum=firstnum*lastnum;
        }
        textViewResult.setText(formatter.format(firstnum));
    }

    public void divide()
    {
        if (firstnum==0)
        {

            lastnum=Double.parseDouble(textViewResult.getText().toString());
            firstnum=lastnum/1;
        }
        else
        {
            lastnum=Double.parseDouble(textViewResult.getText().toString());
            firstnum=firstnum/lastnum;
        }
        textViewResult.setText(formatter.format(firstnum));
    }
}