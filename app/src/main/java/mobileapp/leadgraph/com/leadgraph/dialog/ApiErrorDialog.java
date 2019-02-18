package mobileapp.leadgraph.com.leadgraph.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import mobileapp.leadgraph.com.leadgraph.R;


/**
 * Created by neeraj on 2/4/18.
 */

public class ApiErrorDialog extends Dialog implements View.OnClickListener {
    TextView retry,message_txt;

    public void setRetryListener() {
    }

    public ApiErrorDialog(@NonNull Context context, String message) {
        super(context);
        init(message);
    }
    public void init(String message){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_api_error);
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        retry=(TextView)findViewById(R.id.retry);
        message_txt=(TextView)findViewById(R.id.message);
        message_txt.setText(message);
        retry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.retry:
                dismiss();
                break;
        }
    }

}
