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
import mobileapp.leadgraph.com.leadgraph.models.DesignationListModel;
import mobileapp.leadgraph.com.leadgraph.models.StaffListModel;

/**
 * @author neeraj on 31/12/18.
 */
public class StafffListAdapter extends RecyclerView.Adapter<StafffListAdapter.ViewHolder> {
    private Context context;
    List<StaffListModel.ValueBean> staffList;
    CompanyActionListener companyActionListener;

    public void setCompanyActionListener(CompanyActionListener companyActionListener) {
        this.companyActionListener = companyActionListener;
    }

    public StafffListAdapter(Context  context, List<StaffListModel.ValueBean> staffList) {
        this.context=context;
        this.staffList=staffList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.staff_list_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
              viewHolder.setData(i);
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView companyStatus,companyName,email,contact,designation,employname;
        ImageView img_delete,img_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            employname=itemView.findViewById(R.id.employname);
            designation=itemView.findViewById(R.id.designation);
            contact=itemView.findViewById(R.id.contact);
            email=itemView.findViewById(R.id.email);
            companyName=itemView.findViewById(R.id.companyName);
            companyStatus=itemView.findViewById(R.id.companyStatus);

            img_delete=itemView.findViewById(R.id.img_delete);
            img_edit=itemView.findViewById(R.id.img_edit);
            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int pos= (int) img_edit.getTag();
                    UserAskDialog   userAskDialog=new UserAskDialog(context,"Are you sure you want to delete this Staff?");
                    userAskDialog.setAskDialogListener(new UserAskDialog.AskDialogListener() {
                        @Override
                        public void onYes() {
                            if (companyActionListener!=null){
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
                    int pos= (int) img_delete.getTag();
                    if (companyActionListener!=null){
                        companyActionListener.edit(pos);
                    }
                }
            });
        }
        public void setData(int position){
            img_edit.setTag(position);
            img_delete.setTag(position);
            StaffListModel.ValueBean valueBean=staffList.get(position);
           companyName.setText(valueBean.getComp_Name());
           email.setText(valueBean.getUser_Email());
           designation.setText(valueBean.getDesignation_Name());
           contact.setText(valueBean.getUser_MobileNo());
           employname.setText(valueBean.getUser_Name());
           if (valueBean.getIs_Active().equalsIgnoreCase("1")){
               companyStatus.setText("Active");
           }else {
               companyStatus.setText("In Active");
           }
        }
    }
    public interface CompanyActionListener{
        void edit(int position);
        void delete(int position);
    }
}
