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

import java.util.ArrayList;
import java.util.List;

import mobileapp.leadgraph.com.leadgraph.R;


/**
 * dialog for list of items
 *
 * @author neeraj on 27/11/18.
 */
public class ItemDialog extends Dialog implements AdapterView.OnItemClickListener {
    private Context context;
    SelectionListener selectionListener;
    TextView text_title;
    ListView list_view;
    SelectionItemAdapter selectionItemAdapter;
    List<String> listString=new ArrayList<>();
    String tag;

    public void setSelectionListener(SelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    public ItemDialog(@NonNull Context context, String title, String tag, List<String> list) {
        super(context);
        this.context = context;
        this.tag=tag;
        this.listString.clear();
        this.listString.addAll(list);
        listString.add(0,"Select One");
        init(title);
    }

    private void init(String title) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_item_layout);
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.gravity = Gravity.CENTER;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        text_title = findViewById(R.id.text_title);
        list_view = findViewById(R.id.list_view);
        text_title.setText(title);

        selectionItemAdapter = new SelectionItemAdapter(listString, context);
        list_view.setAdapter(selectionItemAdapter);
        list_view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (selectionListener != null) {
            if (i==0){
                selectionListener.onItemSelect(listString.get(i),tag,-1);

            }else {
                selectionListener.onItemSelect(listString.get(i),tag,i-1);

            }
            dismiss();
        }
    }

    public interface SelectionListener {
        void onItemSelect(String item, String tag, int position);
    }
}
