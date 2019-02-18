package mobileapp.leadgraph.com.leadgraph.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobileapp.leadgraph.com.leadgraph.MainActivity;
import mobileapp.leadgraph.com.leadgraph.R;
import mobileapp.leadgraph.com.leadgraph.SplashActivity;
import mobileapp.leadgraph.com.leadgraph.adapters.DesignationListAdapter;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyDesignationController;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyMasterController;
import mobileapp.leadgraph.com.leadgraph.dialog.ItemDialog;
import mobileapp.leadgraph.com.leadgraph.models.CompanyDropDown;
import mobileapp.leadgraph.com.leadgraph.models.DesignationListModel;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;

/**
 * @author neeraj on 6/12/18.
 */
public class ChannelDesignationFragment extends Fragment implements View.OnClickListener, CompanyMasterController.CompanyDropDownListner, ItemDialog.SelectionListener, CompanyDesignationController.DesignationListener, DesignationListAdapter.CompanyActionListener {
    View view;
    ImageView serach_btn, add_btn;
    CardView add_card;
    TextView save_btn, search_button, list_parent_comp;
    CompanyMasterController companyMasterController;
    List<CompanyDropDown.ValueBean> valueBeans = new ArrayList<>();
    List<String> companies = new ArrayList<>();
    EditText designation_name, spin_company, search_dsg_name;
    Switch switch_admin, switch_active;
    String dsg_name, parent_comp, st_parent_id;
    boolean is_admin, is_active;
    CompanyDesignationController companyDesignationController;
    List<DesignationListModel.ValueBean> designationList = new ArrayList<>();
    RecyclerView list_search;
    DesignationListAdapter designationListAdapter;
    int pos = 0;
    ImageView show_filter;
    LinearLayout lin_filters, search_card;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_designation_master, container, false);
        }
        initViews();
        return view;
    }

    private void initViews() {
        companyDesignationController = new CompanyDesignationController(getActivity());
        companyDesignationController.setDesignationListener(this);
        //st_parent_id = SPUtils.getString(getActivity(), SPUtils.COMPANY_USER_ID);
        companyMasterController = new CompanyMasterController(getActivity());
        companyMasterController.setCompanyDropDownListner(this);
        serach_btn = view.findViewById(R.id.serach_btn);
        add_card = view.findViewById(R.id.add_card);
        search_card = view.findViewById(R.id.search_card);
        add_btn = view.findViewById(R.id.add_btn);
        designation_name = view.findViewById(R.id.designation_name);
        spin_company = view.findViewById(R.id.spin_company);
        switch_admin = view.findViewById(R.id.switch_admin);
        switch_active = view.findViewById(R.id.switch_active);
        list_parent_comp = view.findViewById(R.id.list_parent_comp);
        list_parent_comp.setOnClickListener(this);
        switch_active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                     @Override
                                                     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                         is_active = b;
                                                     }
                                                 }
        );
        switch_admin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                is_admin = b;
            }
        });
        spin_company.setOnClickListener(this);
        add_btn.setOnClickListener(this);
        serach_btn.setOnClickListener(this);
        save_btn = view.findViewById(R.id.save_btn);
        search_button = view.findViewById(R.id.search_button);
        search_dsg_name = view.findViewById(R.id.search_dsg_name);
        save_btn.setOnClickListener(this);
        search_button.setOnClickListener(this);
        list_search = view.findViewById(R.id.list_search);
        designationListAdapter = new DesignationListAdapter(getActivity(), designationList);
        designationListAdapter.setCompanyActionListener(this);
        list_search.setAdapter(designationListAdapter);
        lin_filters = view.findViewById(R.id.lin_filters);
        show_filter = view.findViewById(R.id.show_filter);
        show_filter.setOnClickListener(this);
        companyMasterController.getComapnyDropDown(SPUtils.getString(getActivity(), SPUtils.COMPANY_USER_ID));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setSelected(false, false, false, false, true, false, false, false, false, false);
        ((MainActivity) getActivity()).setBottomNavigation(false, false, false);

        ((MainActivity) getActivity()).setTitle("Channel Designation");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.serach_btn:
                add_card.setVisibility(View.GONE);
                search_card.setVisibility(View.VISIBLE);

                break;
            case R.id.add_btn:
                search_card.setVisibility(View.GONE);
                add_card.setVisibility(View.VISIBLE);
                clearUi();
                break;
            case R.id.spin_company:
                ItemDialog itemDialog = new ItemDialog(getActivity(), "Select", "pcomp", companies);
                itemDialog.setSelectionListener(this);
                itemDialog.show();
                break;
            case R.id.save_btn:
                if (isValidated() && save_btn.getText().toString().equalsIgnoreCase("save")) {
                    companyDesignationController.insertDesignation(dsg_name, st_parent_id
                            , SPUtils.getString(getActivity(), SPUtils.LOGIN_ID), is_active, is_admin);
                    //call insert api
                } else if (isValidated() && save_btn.getText().toString().equalsIgnoreCase("update")) {
                    //call update api
                    companyDesignationController.updateDesignation(dsg_name, st_parent_id
                            , SPUtils.getString(getActivity(), SPUtils.LOGIN_ID), is_active, is_admin, designationList.get(pos).getDesignation_Idno());
                }
                break;
            case R.id.search_button:
                companyDesignationController.getDesignationList(st_parent_id, search_dsg_name.getText().toString());
                lin_filters.setVisibility(View.GONE);
                break;
            case R.id.list_parent_comp:
                ItemDialog itemDialog1 = new ItemDialog(getActivity(), "Select", "pcomp_search", companies);
                itemDialog1.setSelectionListener(this);
                itemDialog1.show();
                break;
            case R.id.show_filter:
                if (lin_filters.getVisibility() == View.VISIBLE) {
                    lin_filters.setVisibility(View.GONE);

                } else {
                    lin_filters.setVisibility(View.VISIBLE);
                }
                break;
        }
        ((MainActivity) getActivity()).didTapButton(view);
    }

    @Override
    public void dropDownList(List<CompanyDropDown.ValueBean> valueBeans) {
        this.valueBeans.clear();
        companies.clear();
        this.valueBeans.addAll(valueBeans);
        for (CompanyDropDown.ValueBean valueBean : valueBeans
                ) {
            companies.add(valueBean.getCompany_Name());
        }
        if (this.valueBeans.size() == 1) {
            //set parent company diffault
            onItemSelect(valueBeans.get(0).getCompany_Name(), "pcomp", 0);

        }
    }

    @Override
    public void onItemSelect(String item, String tag, int position) {
        if (tag.equalsIgnoreCase("pcomp")) {
            spin_company.setText(item);
            if (position==-1){
                st_parent_id ="";
            }else {
                st_parent_id = valueBeans.get(position).getCompanyUser_Idno();
            }
        } else if (tag.equalsIgnoreCase("pcomp_search")) {
            list_parent_comp.setText(item);
            if (position==-1){
                st_parent_id = "";

            }else {
                st_parent_id = valueBeans.get(position).getCompanyUser_Idno();

            }
        }
    }

    private boolean isValidated() {
        parent_comp = spin_company.getText().toString();
        dsg_name = designation_name.getText().toString();
        if (parent_comp.isEmpty()) {
            spin_company.setError("please select company!");
            return false;
        } else if (dsg_name.isEmpty()) {
            designation_name.setError("designation name required!");
            return false;
        }
        return true;
    }

    private void clearUi() {
        designation_name.setText("");
        switch_admin.setChecked(false);
        switch_active.setChecked(false);
    }

    @Override
    public void designatioList(List<DesignationListModel.ValueBean> valueBeans) {
        this.designationList.clear();
        designationList.addAll(valueBeans);
        designationListAdapter.notifyDataSetChanged();
    }

    @Override
    public void edit(int position) {
        add_card.setVisibility(View.VISIBLE);
        search_card.setVisibility(View.GONE);
        pos = position;
        updateUi(designationList.get(position));
    }

    @Override
    public void delete(int position) {
        //call delete api here
        companyDesignationController.deleteDesignation(designationList.get(position).getDesignation_Idno());
        designationList.remove(position);
        designationListAdapter.notifyDataSetChanged();
    }

    private void updateUi(DesignationListModel.ValueBean valueBean) {
        designation_name.setText(valueBean.getDesignation_Name());
        if (valueBean.getIsAdmin().equalsIgnoreCase("1")) {
            switch_admin.setChecked(true);
        } else {
            switch_admin.setChecked(false);
        }
        if (valueBean.getIs_Active().equalsIgnoreCase("1")) {
            switch_active.setChecked(true);
        } else {
            switch_active.setChecked(false);
        }
        for (CompanyDropDown.ValueBean valueBean1 : valueBeans
                ) {
            if (valueBean1.getCompanyUser_Idno().equalsIgnoreCase(valueBean.getCompanyUser_Idno())) {
                spin_company.setText(valueBean1.getCompany_Name());
                st_parent_id = valueBean1.getCompanyUser_Idno();
            }
        }

        save_btn.setText("UPDATE");
    }
}
