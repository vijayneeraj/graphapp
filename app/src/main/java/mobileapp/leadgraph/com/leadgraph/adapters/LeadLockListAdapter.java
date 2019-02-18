package mobileapp.leadgraph.com.leadgraph.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mobileapp.leadgraph.com.leadgraph.R;
import mobileapp.leadgraph.com.leadgraph.dialog.UserAskDialog;
import mobileapp.leadgraph.com.leadgraph.models.LeadListModel;
import mobileapp.leadgraph.com.leadgraph.models.ProductListModel;

/**
 * @author neeraj on 31/12/18.
 */
public class LeadLockListAdapter extends RecyclerView.Adapter<LeadLockListAdapter.ViewHolder> {
    private Context context;
    List<LeadListModel.ValueBean> proList;
    CompanyActionListener companyActionListener;


    public void setCompanyActionListener(CompanyActionListener companyActionListener) {
        this.companyActionListener = companyActionListener;
    }

    public LeadLockListAdapter(Context context, List<LeadListModel.ValueBean> proList) {
        this.context = context;
        this.proList = proList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.lead_lock_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(i);
    }

    @Override
    public int getItemCount() {
        return proList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prospectName, date, asigned_to, staff_name, product_name;
        ImageView img_delete, img_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            date = itemView.findViewById(R.id.date);
            asigned_to = itemView.findViewById(R.id.asigned_to);
            staff_name = itemView.findViewById(R.id.staff_name);
            prospectName = itemView.findViewById(R.id.prospectName);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int pos = (int) img_edit.getTag();
                    UserAskDialog userAskDialog = new UserAskDialog(context, "Are you sure you want to delete this Lead?");
                    userAskDialog.setAskDialogListener(new UserAskDialog.AskDialogListener() {
                        @Override
                        public void onYes() {
                            if (companyActionListener != null) {
                                companyActionListener.delete(pos);
                            }
                        }
                        @Override
                        public void onNo() {

                        }
                    });
                    userAskDialog.show();
                }
            });
            img_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) img_delete.getTag();
                    if (companyActionListener != null) {
                        companyActionListener.edit(pos);
                    }
                }
            });
        }

        public void setData(int position) {
            img_edit.setTag(position);
            img_delete.setTag(position);
            LeadListModel.ValueBean valueBean = proList.get(position);
            String pros = valueBean.getComp_Name() + "\n" + valueBean.getLead_ContactName() + "\n" + valueBean.getLead_ContactName() + ","
                    + valueBean.getAlter_No();
            prospectName.setText(pros);
            String prod="";
            if (valueBean.getPref_Lang().equalsIgnoreCase("1")){
                prod= valueBean.getProduct_Name()+"\n"+
                        "Lang: Hindi ,"+"Price:" + valueBean.getLead_Cost();
            }else {
                prod= valueBean.getProduct_Name()+"\n"+
                        "Lang: English ,"+"Price:" + valueBean.getLead_Cost();
            }
            product_name.setText(prod);
            String staff=valueBean.getStaff_Name()+"\n"+valueBean.getLead_ContactNumber();
            staff_name.setText(staff);
            date.setText(valueBean.getLead_Date()+"\n"+"Demoat:"+valueBean.getDemo_Time());
            asigned_to.setText(valueBean.getAssinged_To()+"\n");
        }
    }

    public interface CompanyActionListener {
        void edit(int position);
        void delete(int position);
    }
}
