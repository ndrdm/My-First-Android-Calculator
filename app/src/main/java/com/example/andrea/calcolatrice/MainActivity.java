package com.example.andrea.calcolatrice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    //private double mCounter;
    private double mNumber;
    private double mNumberB;
    private double mResult;
    private String mTextResult;
    private char mOperator;
    private TextView mText;
    private TextView mDisplay;
    private boolean mIsFloat=false;
    private boolean mIsNewOp=true;


    //CONSTANT TO STORE BUNDLE
    private static final String TAG = "LIFECYCLE";
    private static final String RESULT = "result";
    private static final String OPERATION = "operation";
    private static final String NUMBER = "number";
    private static final String NUMBERB = "numberB";
    private static final String ISNEWOP = "isNewOp";
    private static final String LASTINSERT = "lastinsert";
    private static final String DISPLAY = "display";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Inizio");
        setContentView(R.layout.layout);
        super.onCreate(savedInstanceState);
        mText =findViewById(R.id.results);
        mDisplay=findViewById(R.id.display);
        //saveDisplay();

        if (savedInstanceState==null){
            Log.d(TAG, "Lancio");
            mText.setText("");

        } else {
            mResult=savedInstanceState.getDouble(RESULT);
            mOperator=savedInstanceState.getChar(OPERATION);
            mNumber=savedInstanceState.getDouble(NUMBER);
            mNumberB=savedInstanceState.getDouble(NUMBERB);
            mIsNewOp=savedInstanceState.getBoolean(ISNEWOP);
            mDisplay.setText(""+savedInstanceState.getString(DISPLAY));
            mText.setText(mResult+"");
        }

        //BINDING LAYOUT WITH NUMBER BUTTONS
        Button vButton1 = findViewById(R.id.n1);
        Button vButton2 = findViewById(R.id.n2);
        Button vButton3 = findViewById(R.id.n3);
        Button vButton4 = findViewById(R.id.n4);
        Button vButton5 = findViewById(R.id.n5);
        Button vButton6 = findViewById(R.id.n6);
        Button vButton7 = findViewById(R.id.n7);
        Button vButton8 = findViewById(R.id.n8);
        Button vButton9 = findViewById(R.id.n9);
        Button vButton0 = findViewById(R.id.n0);

        //BINDING LAYOUT WITH OPERATION BUTTONS
        Button vPlus = findViewById(R.id.buttonPlus);
        Button vSub = findViewById(R.id.buttonSub);
        Button vMol = findViewById(R.id.buttonMol);
        Button vDiv= findViewById(R.id.buttonDiv);
        Button vPer= findViewById(R.id.buttonPercent);
        Button vEqual=  findViewById(R.id.buttonEqual);
        Button vComma=  findViewById(R.id.buttonComma);
        Button vSign=  findViewById(R.id.buttonSign);
        Button vAc=  findViewById(R.id.buttonReset);

        //ADDCTION BUTTON
        vPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDisplay();
                mResult=Double.parseDouble(mText.getText().toString());
                mOperator='+';
                mIsNewOp=true;
                mDisplay.setText(mDisplay.getText()+"+");
                //dopo l'iserimento di un segno il numero seguente di default non è in virgola mobile
                mIsFloat=false;

            }
        });


        //SUBTRACTION BUTTON
        vSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumber=Double.parseDouble(mText.getText().toString());
                mOperator='-';
                mIsNewOp=true;
                mDisplay.setText(mDisplay.getText()+"-");
                //mText.setText("0");
                mIsFloat=false;

            }
        });


        //MOLTIPLICATION BUTTON
        vMol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumber=Double.parseDouble(mText.getText().toString());
                mOperator= '*';
                mIsNewOp=true;
                mDisplay.setText(mDisplay.getText()+"*");

                mText.setText("0");
                mIsFloat=false;
                //saveDisplay();
            }
        });


        //DIVISION BUTTON
        vDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNumber=Double.parseDouble(mText.getText().toString());
                mOperator='/';
                mIsNewOp=true;
                mDisplay.setText(mDisplay.getText()+"/");
                mText.setText("0");
                mIsFloat=false;
                //saveDisplay();
            }
        });

        //PERCENTAGE BUTTON
        vPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mResult=Double.parseDouble(mText.getText().toString())/100;
                mDisplay.setText(mResult+"% = ");
                mTextResult=String.valueOf(mResult);
                mText.setText(mTextResult);
                mDisplay.setText(mDisplay.getText()+mTextResult);
                //saveDisplay();

                }
        });



        //EQUALS TO BUTTON
        vEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mIsNewOp=true;
                mDisplay.setText(mDisplay.getText()+"=");


                if (mOperator=='+'){
                    mNumberB=Double.parseDouble(mText.getText().toString());
                    mResult=addiction();
                    mTextResult=String.valueOf(mResult);
                    mText.setText(mTextResult);
                    //saveDisplay();


                }

                else if (mOperator=='-'){
                    mNumberB=Double.parseDouble(mText.getText().toString());
                    mResult=subtraction();
                    mTextResult=String.valueOf(mResult);
                    mText.setText(mTextResult);
                    //saveDisplay();

                }
                else if (mOperator=='*'){
                    mNumberB=Double.parseDouble(mText.getText().toString());
                    mResult=moltiplication();
                    mTextResult=String.valueOf(mResult);
                    mText.setText(mTextResult);
                    //saveDisplay();


                }
                else if (mOperator=='/'){
                    mNumberB=Double.parseDouble(mText.getText().toString());
                    mResult=division();
                    mTextResult=String.valueOf(mResult);
                    mText.setText(mTextResult);
                    //saveDisplay();

                }

                else {
                    mText.setText("Err");
                    mDisplay.setText("");

                }

                mDisplay.setText(mDisplay.getText()+mTextResult);

            }
        });


        //AC BUTTON
        vAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText.setText("0");
                mDisplay.setText("");
                mIsNewOp=true;
                mIsFloat=false;
                mResult=0;
                mNumberB=0;
                mOperator=0;

            }
        });


        //metodi onClick dei numeri
        vButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {
                    mText.setText("1");
                    mIsNewOp=false;
                    //saveDisplay();
                } else {
                    mText.setText(mText.getText() + "1");

                }
                mDisplay.setText(mDisplay.getText()+"1");
                //saveDisplay();

            }
        });

        vButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {
                    mText.setText("2");
                    mIsNewOp=false;
                } else {
                    mText.setText(mText.getText() + "2");
                }
                mDisplay.setText(mDisplay.getText()+"2");
                //saveDisplay();

            }
        });

        vButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {
                    mText.setText("3");
                    mIsNewOp=false;
                } else {
                    mText.setText(mText.getText() + "3");

                }
                mDisplay.setText(mDisplay.getText()+"3");
                //saveDisplay();
            }

        });

        vButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {
                    mText.setText("4");
                    mIsNewOp=false;
                } else {
                    mText.setText(mText.getText() + "4");
                }
                mDisplay.setText(mDisplay.getText()+"4");
                //saveDisplay();

            }
        });

        vButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {
                    mText.setText("5");
                    mIsNewOp=false;
                } else {
                    mText.setText(mText.getText() + "5");
                }
                mDisplay.setText(mDisplay.getText()+"5");
                //saveDisplay();

            }
        });

        vButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {
                    mText.setText("6");
                    mIsNewOp=false;
                } else {
                    mText.setText(mText.getText() + "6");
                }
                mDisplay.setText(mDisplay.getText()+"6");
                //saveDisplay();

            }
        });

        vButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {
                    mText.setText("7");
                    mIsNewOp=false;
                } else {
                    mText.setText(mText.getText() + "7");
                }
                mDisplay.setText(mDisplay.getText()+"7");
                saveDisplay();

            }
        });


        vButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {
                    mText.setText("8");
                    mIsNewOp=false;
                } else {
                    mText.setText(mText.getText() + "8");
                }
                mDisplay.setText(mDisplay.getText()+"8");
                saveDisplay();

            }
        });

        vButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {
                    mText.setText("9");
                    mIsNewOp=false;
                } else {
                    mText.setText(mText.getText() + "9");
                }
                mDisplay.setText(mDisplay.getText()+"9");
                saveDisplay();

            }
        });

        vButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsNewOp) {

                    mText.setText("0");

                    mIsNewOp=false;
                } else {
                    if (mText.getText()!="0")
                    mText.setText(mText.getText() + "0");

                }
                mDisplay.setText(mDisplay.getText()+"0");

                saveDisplay();

            }
        });

        //COMMA
        vComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsNewOp) {
                    mText.setText("0.");
                    mDisplay.setText(mDisplay.getText()+"0.");
                    mIsNewOp=false;
                }else {
                     if (mIsFloat==false){mText.setText(mText.getText() + ".");
                         mDisplay.setText(mDisplay.getText()+".");
                         mIsFloat=true; }
                     else {  }
                     }
                saveDisplay();

            }
        });

        vSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int vNumberPos=((Integer.parseInt(mText.getText().toString()))*(-1));
                    mText.setText(vNumberPos+"");
                    //TODO display del cambio segno
                    saveDisplay();

            }

        });

        Log.d(TAG, "onCreate"); //classe di Java che permette di mostrare un TAG, scrive sulla console

    }


    @Override
    protected void onStart() {
        super.onStart();
        mOperator=0;
        mNumber=0;
        Log.d(TAG,"onStart");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");

    }


    //salvataggio dei dati in caso di cambio orientamento schermo

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(RESULT, mResult);
        outState.putChar(OPERATION, mOperator);
        outState.putDouble(NUMBERB, mNumberB);
        outState.putBoolean(ISNEWOP, mIsNewOp);
       // outState.putDouble(LASTINSERT, mCounter);
        outState.putString(DISPLAY, mDisplay.getText().toString());

        Log.d(TAG,"onSaveIstanceState");
    }

    private void saveDisplay() {
           // mCounter = Double.parseDouble(mText.getText().toString());
    }



    public double addiction(){

        return (mResult+mNumberB);


    }

    public double subtraction(){
        return (mNumber-mNumberB);

    }

    public double moltiplication(){
        return (mNumber*mNumberB);

    }

    public double division(){
        return (mNumber/mNumberB);

    }



}
