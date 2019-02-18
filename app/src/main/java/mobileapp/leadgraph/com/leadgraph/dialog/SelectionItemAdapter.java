package mobileapp.leadgraph.com.leadgraph.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mobileapp.leadgraph.com.leadgraph.R;


/**
 * @author neeraj on 27/11/18.
 */
public class SelectionItemAdapter extends BaseAdapter {
    ImageView image_view;
    TextView text_view;
    private Context context;
    private List<String> stringList;

    public SelectionItemAdapter(List<String> stringList, Context context) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.item_layout,viewGroup,false);

        text_view=view.findViewById(R.id.text_view);
        text_view.setText(stringList.get(i));
        return view;
    }
}
