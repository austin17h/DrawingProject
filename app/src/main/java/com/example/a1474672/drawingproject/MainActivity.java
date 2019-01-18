package com.example.a1474672.drawingproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private Button mYellow;
    private Button mRed;
    private Button mBlue;
    private SquareView mSquareView;
    private Button mEraser;
    private SeekBar mSeekBar;
    private CheckBox mCheckBox;
    private Button mBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSquareView = (SquareView) findViewById(R.id.squareCanvas);

        mButton = (Button) findViewById(R.id.clearer);
        mButton.setOnClickListener(this);
        Log.i("button", "instantiated!");

        mYellow = (Button) findViewById(R.id.yellow_color);
        mYellow.setOnClickListener(this);
        mBlue = (Button) findViewById(R.id.blue_color);
        mBlue.setOnClickListener(this);
        mRed = (Button) findViewById(R.id.red_color);
        mRed.setOnClickListener(this);
        mEraser = (Button) findViewById(R.id.eraser);
        mEraser.setOnClickListener(this);
        mBlack = (Button) findViewById(R.id.black_color);
        mBlack.setOnClickListener(this);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value = 10;

            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                mSquareView.changeSize(value);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mCheckBox = findViewById(R.id.checkBox);
        mCheckBox.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == mButton.getId()) {
            Log.i("Clicked", "onClicked!");
            mSquareView.clearCircles();
        }
        else if(v.getId() == mYellow.getId()) {
            Log.i("Clicked", "toYellow!");
            mSquareView.changeColor(3);
        }
        else if(v.getId() == mRed.getId()) {
            Log.i("Clicked", "toRed!");
            mSquareView.changeColor(2);
        }
        else if(v.getId() == mBlue.getId()) {
            Log.i("Clicked", "toBlue!");
            mSquareView.changeColor(1);
        }
        else if(v.getId() == mEraser.getId()) {
            Log.i("Clicked", "toWhite!");
            mSquareView.changeColor(0);
        }
        else if(v.getId() == mBlack.getId()) {
            Log.i("Clicked", "toBlack!");
            mSquareView.changeColor(4);
        }

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkBox:
                if (checked) {
                    mSquareView.setChecked(true);
                    mSquareView.startDissolving();
                }
                else {
                    mSquareView.setChecked(false);
                    mSquareView.stopDissolving();
                }
                break;
        }
    }
}