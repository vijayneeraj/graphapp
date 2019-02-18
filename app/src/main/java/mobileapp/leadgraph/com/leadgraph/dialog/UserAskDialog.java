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
 * @author neeraj on 30/11/18.
 */
public class UserAskDialog extends Dialog implements View.OnClickListener {
    TextView txt_msg, btn_no, btn_yes;
    AskDialogListener askDialogListener;

    public void setAskDialogListener(AskDialogListener askDialogListener) {
        this.askDialogListener = askDialogListener;
    }

    public UserAskDialog(@NonNull Context context, String msg) {
        super(context);
        initDialog(msg);
    }

    private void initDialog(String message) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.dialog_askuser);
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.gravity = Gravity.CENTER;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        btn_yes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);
        txt_msg = findViewById(R.id.txt_msg);
        txt_msg.setText(message);
        btn_no.setOnClickListener(this);
        btn_yes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_no:
                if (askDialogListener!=null){
                    askDialogListener.onNo();
                }
                break;
            case R.id.btn_yes:
                if (askDialogListener!=null){
                    askDialogListener.onYes();
                }
                break;

        }
        dismiss();
    }
    public interface AskDialogListener{
        void onYes();
        void onNo();
    }
}
