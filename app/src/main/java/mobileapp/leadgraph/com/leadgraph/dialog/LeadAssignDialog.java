package mobileapp.leadgraph.com.leadgraph.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import mobileapp.leadgraph.com.leadgraph.R;


/**
 * dialog for list of items
 *
 * @author neeraj on 27/11/18.
 */
public class LeadAssignDialog extends Dialog  {
    private Context context;


    public LeadAssignDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_lead_assign);
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.gravity = Gravity.CENTER;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }



}
