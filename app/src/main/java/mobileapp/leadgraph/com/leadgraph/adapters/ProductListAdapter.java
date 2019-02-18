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
import mobileapp.leadgraph.com.leadgraph.models.ProductListModel;

/**
 * @author neeraj on 31/12/18.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private Context context;
    List<ProductListModel.ValueBean> proList;
    CompanyActionListener companyActionListener;



    public void setCompanyActionListener(CompanyActionListener companyActionListener) {
        this.companyActionListener = companyActionListener;
    }

    public ProductListAdapter(Context  context, List<ProductListModel.ValueBean> proList) {
        this.context=context;
        this.proList=proList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_list_item,viewGroup,false);
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
        TextView companyStatus,companyName,eff_date,product_cost,cat_name,product_name;
        ImageView img_delete,img_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
            cat_name=itemView.findViewById(R.id.cat_name);
            product_cost=itemView.findViewById(R.id.product_cost);
            eff_date=itemView.findViewById(R.id.eff_date);
            companyName=itemView.findViewById(R.id.companyName);
            companyStatus=itemView.findViewById(R.id.companyStatus);
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
            ProductListModel.ValueBean valueBean=proList.get(position);
           companyName.setText(valueBean.getComp_Name());
               companyStatus.setText(valueBean.getStatus());
            eff_date.setText(valueBean.getEffDT());
            product_cost.setText(valueBean.getProduct_Cost());
            cat_name.setText(valueBean.getCategory_Name());
            product_name.setText(valueBean.getProduct_Name());

        }
    }
    public interface CompanyActionListener{
        void edit(int position);
        void delete(int position);
    }
}
