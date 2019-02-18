package mobileapp.leadgraph.com.leadgraph.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import mobileapp.leadgraph.com.leadgraph.R;
import mobileapp.leadgraph.com.leadgraph.models.LoginMOdel;


/**
 * Created by neeraj on 2/4/18.
 */

public class LoginAsDialog extends Dialog implements View.OnClickListener {

    LoginAsListener loginAsListener;
    RecyclerView rec_view;
    List<LoginMOdel.ValueBean> list;
    Context context;

    public void setLoginAsListener(LoginAsListener loginAsListener) {
        this.loginAsListener = loginAsListener;
    }

    public LoginAsDialog(@NonNull Context context, List<LoginMOdel.ValueBean> list) {
        super(context);
        this.context = context;
        this.list = list;
        init();

    }

    public void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_login_multi);
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        rec_view = findViewById(R.id.rec_view);
        LoginMultiAdapter loginMultiAdapter = new LoginMultiAdapter(context);
        rec_view.setAdapter(loginMultiAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    public interface LoginAsListener {
        void onLoginAs(LoginMOdel.ValueBean valueBean);
    }

    public class LoginMultiAdapter extends RecyclerView.Adapter<LoginMultiAdapter.ViewHolder> {
        public LoginMultiAdapter(Context context) {

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_login_dialog, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.setData(i);
        }

        @Override
        public int getItemCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView login_btn, employee_name, comapny_name;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                login_btn = itemView.findViewById(R.id.login_btn);
                employee_name = itemView.findViewById(R.id.employee_name);
                comapny_name = itemView.findViewById(R.id.comapny_name);
            }

            public void setData(final int position) {
                employee_name.setText(list.get(position).getEmployeeName());
                comapny_name.setText(list.get(position).getCompanyName());
                login_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (loginAsListener != null) {
                            loginAsListener.onLoginAs(list.get(position));
                            dismiss();
                        }
                    }
                });

            }
        }
    }
}
