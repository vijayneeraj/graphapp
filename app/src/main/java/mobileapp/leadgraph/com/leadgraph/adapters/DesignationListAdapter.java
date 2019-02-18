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
import mobileapp.leadgraph.com.leadgraph.models.CompanyListModel;
import mobileapp.leadgraph.com.leadgraph.models.DesignationListModel;

/**
 * @author neeraj on 31/12/18.
 */
public class DesignationListAdapter extends RecyclerView.Adapter<DesignationListAdapter.ViewHolder> {
    private Context context;
    List<DesignationListModel.ValueBean> dsgList;
    CompanyActionListener companyActionListener;



    public void setCompanyActionListener(CompanyActionListener companyActionListener) {
        this.companyActionListener = companyActionListener;
    }

    public DesignationListAdapter(Context  context, List<DesignationListModel.ValueBean> dsgList) {
        this.context=context;
        this.dsgList=dsgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.designation_list_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
              viewHolder.setData(i);
    }

    @Override
    public int getItemCount() {
        return dsgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView companyStatus,userType,companyDesignation,companyName;
        ImageView img_delete,img_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyDesignation=itemView.findViewById(R.id.companyDesignation);
            companyName=itemView.findViewById(R.id.companyName);
            companyStatus=itemView.findViewById(R.id.companyStatus);
            userType=itemView.findViewById(R.id.userType);
            img_delete=itemView.findViewById(R.id.img_delete);
            img_edit=itemView.findViewById(R.id.img_edit);
            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int pos= (int) img_edit.getTag();
                    UserAskDialog   userAskDialog=new UserAskDialog(context,"Are you sure you want to delete this Designation?");
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
            DesignationListModel.ValueBean valueBean=dsgList.get(position);
           companyName.setText(valueBean.getCompany_Name());
           companyDesignation.setText(valueBean.getDesignation_Name());
           if (valueBean.getIs_Active().equalsIgnoreCase("1")){
               companyStatus.setText("Active");
           }else {
               companyStatus.setText("In Active");
           }
           if (valueBean.getIsAdmin().equalsIgnoreCase("1")){
               userType.setText("Admin");
           }else {
               userType.setText("Not Admin");
           }

        }
    }
    public interface CompanyActionListener{
        void edit(int position);
        void delete(int position);
    }

}
