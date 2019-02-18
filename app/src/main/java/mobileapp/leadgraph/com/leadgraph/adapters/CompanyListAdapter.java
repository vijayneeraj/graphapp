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

/**
 * @author neeraj on 31/12/18.
 */
public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.ViewHolder> {
    private Context context;
    List<CompanyListModel.ValueBean> companyList;
    CompanyActionListener companyActionListener;



    public void setCompanyActionListener(CompanyActionListener companyActionListener) {
        this.companyActionListener = companyActionListener;
    }

    public CompanyListAdapter(Context  context, List<CompanyListModel.ValueBean> companyList) {
        this.context=context;
        this.companyList=companyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.company_list_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
              viewHolder.setData(i);
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView companyStatus,companyContact,companyOwner,companyAddress,companyName;
        ImageView img_delete,img_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyAddress=itemView.findViewById(R.id.companyAddress);
            companyContact=itemView.findViewById(R.id.companyContact);
            companyName=itemView.findViewById(R.id.companyName);
            companyStatus=itemView.findViewById(R.id.companyStatus);
            companyOwner=itemView.findViewById(R.id.companyOwner);
            img_delete=itemView.findViewById(R.id.img_delete);
            img_edit=itemView.findViewById(R.id.img_edit);
            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int pos= (int) img_edit.getTag();
                    UserAskDialog   userAskDialog=new UserAskDialog(context,"Are you sure you want to delete this company?");
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
            CompanyListModel.ValueBean valueBean=companyList.get(position);
           companyName.setText(valueBean.getCompany_Name()+"\n"+"["+valueBean.getCompany_GSTNo()+"]");
           companyAddress.setText(valueBean.getCompanyAddress());
           companyStatus.setText(valueBean.getCompanyStatus());
           String owner=valueBean.getOwner_Name()+"\n"+valueBean.getOwner_MobileNo()+"\n"+valueBean.getOwner_EmailId();
            companyOwner.setText(owner);
            companyContact.setText(valueBean.getOwner_Name()+"\n"+valueBean.getContactPerson_MobileNo()+"\n"+valueBean.getContactPerson_EmailId());
            //String contact=valueBean.getCont
        }
    }
    public interface CompanyActionListener{
        void edit(int position);
        void delete(int position);
    }

}
