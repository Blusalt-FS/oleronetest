package com.aurocodes.oleronepayment;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UnifiedPaymentDialog extends Dialog {

    LinearLayout statusLayout;

    TextView[] tv = new TextView[13];
    public UnifiedPaymentDialog(@NonNull Context context, String json_data) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.unified_payment_dialog);
       // statusLayout = (LinearLayout)findViewById(R.id.status_layout);
       // statusLayout.setVisibility(View.GONE);
        tv[1] = (TextView)findViewById(R.id.stat1);
        tv[2] = (TextView)findViewById(R.id.stat2);
        tv[3] = (TextView)findViewById(R.id.stat3);
        tv[4] = (TextView)findViewById(R.id.stat4);
        tv[5] = (TextView)findViewById(R.id.stat5);
        tv[6] = (TextView)findViewById(R.id.stat6);
        tv[7] = (TextView)findViewById(R.id.stat7);
        tv[8] = (TextView)findViewById(R.id.stat8);
        tv[9] = (TextView)findViewById(R.id.stat9);
        tv[10] = (TextView)findViewById(R.id.stat10);
        tv[11] = (TextView)findViewById(R.id.stat11);
        tv[12] = (TextView)findViewById(R.id.stat12);


        UPJsonMapper upJsonMapper = new UPJsonMapper(json_data);
        for(int x = 1; x < 13 ; x++){
            tv[x].setText(upJsonMapper.fetch_order(x));
        }
    }
}
