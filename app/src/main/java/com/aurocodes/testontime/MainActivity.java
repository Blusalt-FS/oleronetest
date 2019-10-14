package com.aurocodes.testontime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.aurocodes.oleronepayment.UPJsonMapper;
import com.aurocodes.oleronepayment.UPwidget;

public class MainActivity extends AppCompatActivity {
    UPwidget uPwidget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uPwidget = (UPwidget) findViewById(R.id.nice_widget);
        uPwidget.init("FIDELDESIGN", "2BC80A5EB5BB6A64A772F9806A7E9A0B16702043AB475DC4");
    }
}
