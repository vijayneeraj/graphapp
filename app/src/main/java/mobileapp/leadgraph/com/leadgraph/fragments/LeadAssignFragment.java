package mobileapp.leadgraph.com.leadgraph.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mobileapp.leadgraph.com.leadgraph.MainActivity;
import mobileapp.leadgraph.com.leadgraph.R;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyMasterController;
import mobileapp.leadgraph.com.leadgraph.controller.ProductMasterController;
import mobileapp.leadgraph.com.leadgraph.dialog.ItemDialog;
import mobileapp.leadgraph.com.leadgraph.models.CompanyDropDown;
import mobileapp.leadgraph.com.leadgraph.models.ProductListModel;

/**
 * @author neeraj on 5/12/18.
 */
public class LeadAssignFragment extends Fragment implements View.OnClickListener, CompanyMasterController.CompanyDropDownListner, ItemDialog.SelectionListener, ProductMasterController.ProductMasterListener {
    View view;
    TextView spin_company, to_date, from_date, lead_status, spin_product;
    EditText prospect_company_name, mobile_number, prospect_name, staff_name;
    CompanyMasterController companyMasterController;
    ProductMasterController productMasterController;
    List<CompanyDropDown.ValueBean> companyBeans = new ArrayList<>();
    List<String> companyList = new ArrayList<>();
    String st_parent, st_product;
    List<ProductListModel.ValueBean> productBeans = new ArrayList<>();
    List<String> productsList = new ArrayList<>();
    List<String> leadStatus = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_assign_lead, container, false);
        }
        initViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setSelected(false, false, false, false, false, false, true, false, false, false);
        ((MainActivity) getActivity()).setBottomNavigation(false, false, false);

        ((MainActivity) getActivity()).setTitle("Lead Assign");
    }

    private void initViews() {
        productMasterController = new ProductMasterController(getActivity());
        productMasterController.setProductMasterListener(this);
        companyMasterController = new CompanyMasterController(getActivity());
        companyMasterController.setCompanyDropDownListner(this);
        prospect_name = view.findViewById(R.id.prospect_name);
        from_date = view.findViewById(R.id.from_date);
        to_date = view.findViewById(R.id.to_date);
        spin_company = view.findViewById(R.id.spin_company);
        staff_name = view.findViewById(R.id.staff_name);
        mobile_number = view.findViewById(R.id.mobile_number);
        prospect_company_name = view.findViewById(R.id.prospect_company_name);
        spin_product = view.findViewById(R.id.spin_product);
        lead_status = view.findViewById(R.id.lead_status);
        lead_status.setOnClickListener(this);
        from_date.setOnClickListener(this);
        to_date.setOnClickListener(this);
        spin_company.setOnClickListener(this);
        leadStatus.add("All");
        leadStatus.add("Un Assigned");
        leadStatus.add("Assigned");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.spin_company:
                ItemDialog itemDialog = new ItemDialog(getActivity(), "Select", "pcomp", companyList);
                itemDialog.setSelectionListener(this);
                itemDialog.show();
                break;
            case R.id.to_date:
                openDatePicker(1);
                break;
            case R.id.from_date:
                openDatePicker(0);
                break;
            case R.id.spin_product:
                ItemDialog itemDialog1 = new ItemDialog(getActivity(), "Select", "prod", productsList);
                itemDialog1.setSelectionListener(this);
                itemDialog1.show();
                break;
            case R.id.lead_status:
                ItemDialog itemDialog2 = new ItemDialog(getActivity(), "Select", "status", leadStatus);
                itemDialog2.setSelectionListener(this);
                itemDialog2.show();
                break;
        }
    }


    @Override
    public void dropDownList(List<CompanyDropDown.ValueBean> valueBeans) {
        companyBeans.clear();
        companyBeans.addAll(valueBeans);
        companyList.clear();
        for (CompanyDropDown.ValueBean valueBean : valueBeans
                ) {
            companyList.add(valueBean.getCompany_Name());
        }
    }

    private void openDatePicker(final int flag) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                String text = datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear();
                if (flag == 0) {
                    from_date.setText(text);

                } else if (flag == 1) {
                    to_date.setText(text);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onItemSelect(String item, String tag, int position) {
        if (tag.equalsIgnoreCase("pcomp")) {
            spin_company.setText(item);
            if (position==-1){
                st_parent = "";

            }else {
                st_parent = companyBeans.get(position).getCompanyUser_Idno();

            }
            productMasterController.getProducts(st_parent, "", "", "");
        } else if (tag.equalsIgnoreCase("prod")) {
            spin_product.setText(item);
            if (position==-1){
                st_product = "";
            }else {
                st_product = productBeans.get(position).getProduct_Idno();
            }
        }else if (tag.equalsIgnoreCase("status")){
            lead_status.setText(item);

        }
    }

    @Override
    public void productAdded() {

    }

    @Override
    public void productList(List<ProductListModel.ValueBean> valueBeans) {
        productBeans.clear();
        productBeans.addAll(valueBeans);
        productsList.clear();
        for (ProductListModel.ValueBean valueBean : valueBeans
                ) {
            productsList.add(valueBean.getProduct_Name());

        }
    }

    @Override
    public void productUpdated() {

    }

    @Override
    public void productDeleted() {

    }
}
