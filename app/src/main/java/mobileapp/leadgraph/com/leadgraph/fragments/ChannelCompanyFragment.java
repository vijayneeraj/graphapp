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
import mobileapp.leadgraph.com.leadgraph.adapters.CompanyListAdapter;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyMasterController;
import mobileapp.leadgraph.com.leadgraph.controller.CountryApiCall;
import mobileapp.leadgraph.com.leadgraph.dialog.ItemDialog;
import mobileapp.leadgraph.com.leadgraph.models.CityModel;
import mobileapp.leadgraph.com.leadgraph.models.CompanyDropDown;
import mobileapp.leadgraph.com.leadgraph.models.CompanyListModel;
import mobileapp.leadgraph.com.leadgraph.models.StateModel;
import mobileapp.leadgraph.com.leadgraph.rest.ParamName;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;

/**
 * @author neeraj on 7/12/18.
 */

public class ChannelCompanyFragment extends Fragment implements View.OnClickListener, ItemDialog.SelectionListener, CountryApiCall.StateListener, CountryApiCall.CityListener, CompanyMasterController.CompanyMasterListeners, CompanyListAdapter.CompanyActionListener, CompanyMasterController.CompanyDropDownListner {
    View view;
    ImageView serach_btn, add_btn;
    CardView add_card;
    TextView save_btn, search_button;
    EditText industry_dropdown, parent_company, state_btn, city_btn;
    List<String> industry = new ArrayList<>();
    List<String> states = new ArrayList<>();
    List<String> cities = new ArrayList<>();
    CountryApiCall countryApiCall;
    CompanyMasterController companyMasterController;
    EditText edit_comp_name, edit_gst, edit_o_name, edit_o_email, edit_o_contact, edit_address, edit_area, edit_pin, edit_cp_name,
            edit_cp_email, edit_cp_phone, edit_allowComp, edit_allow_staff;
    Switch switch_k;
    String st_comp_name, st_gst, st_o_name, st_o_email, st_o_contact, st_address, st_area, st_pin, st_cp_name, st_cp_email, st_cp_phone,
            st_allow_comp, st_allowstaff, st_city_id, st_state, st_parent,st_industry;
    boolean isActive;
    RecyclerView list_search;
    CompanyListAdapter companyListAdapter;
    List<CompanyListModel.ValueBean> companyList;
    EditText search_contact, search_email, search_oname, search_gst, search_compName, search_city, search_state;
    ImageView show_filter;
    LinearLayout lin_filters, search_card;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_channel_company, container, false);
        }
        initViews();
        return view;
    }

    private void initViews() {
        countryApiCall = new CountryApiCall(getActivity());
        companyMasterController = new CompanyMasterController(getActivity(), this);
        edit_address = view.findViewById(R.id.edit_address);
        edit_allow_staff = view.findViewById(R.id.edit_allow_staff);
        edit_allowComp = view.findViewById(R.id.edit_allowComp);
        edit_area = view.findViewById(R.id.edit_area);
        edit_comp_name = view.findViewById(R.id.edit_comp_name);
        edit_gst = view.findViewById(R.id.edit_gst);
        edit_o_contact = view.findViewById(R.id.edit_o_contact);
        edit_o_email = view.findViewById(R.id.edit_o_email);
        edit_o_name = view.findViewById(R.id.edit_o_name);
        edit_pin = view.findViewById(R.id.edit_pin);
        edit_cp_email = view.findViewById(R.id.edit_cp_email);
        edit_cp_name = view.findViewById(R.id.edit_cp_name);
        edit_cp_phone = view.findViewById(R.id.edit_cp_phone);
        switch_k = view.findViewById(R.id.switch_k);
        switch_k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isActive = b;
            }
        });

        serach_btn = view.findViewById(R.id.serach_btn);
        add_card = view.findViewById(R.id.add_card);
        search_card = view.findViewById(R.id.search_card);
        add_btn = view.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);
        serach_btn.setOnClickListener(this);
        save_btn = view.findViewById(R.id.save_btn);
        search_button = view.findViewById(R.id.search_button);
        save_btn.setOnClickListener(this);
        search_button.setOnClickListener(this);
        industry_dropdown = view.findViewById(R.id.industry_dropdown);
        industry_dropdown.setOnClickListener(this);
        industry.add("IT Industry");
        industry.add("AutoMobile Industry");
        parent_company = view.findViewById(R.id.parent_company);
        parent_company.setOnClickListener(this);
        companyMasterController.checkCompanyAllow();
        countryApiCall.callStates(this, "1");
        city_btn = view.findViewById(R.id.city_btn);
        state_btn = view.findViewById(R.id.state_btn);
        city_btn.setOnClickListener(this);
        state_btn.setOnClickListener(this);
        list_search = view.findViewById(R.id.list_search);
        search_city = view.findViewById(R.id.search_city);
        search_state = view.findViewById(R.id.search_state);
        search_compName = view.findViewById(R.id.search_compName);
        search_contact = view.findViewById(R.id.search_contact);
        search_email = view.findViewById(R.id.search_email);
        search_gst = view.findViewById(R.id.search_gst);
        search_oname = view.findViewById(R.id.search_oname);
        search_state.setOnClickListener(this);
        search_city.setOnClickListener(this);
        companyList = new ArrayList<>();
        companyListAdapter = new CompanyListAdapter(getActivity(), companyList);
        companyListAdapter.setCompanyActionListener(this);
        list_search.setAdapter(companyListAdapter);
        companyMasterController.setCompanyDropDownListner(this);
        lin_filters = view.findViewById(R.id.lin_filters);
        show_filter = view.findViewById(R.id.show_filter);
        show_filter.setOnClickListener(this);
        companyMasterController.getComapnyDropDown(SPUtils.getString(getActivity(), SPUtils.COMPANY_USER_ID));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.serach_btn:
                add_card.setVisibility(View.GONE);
                search_card.setVisibility(View.VISIBLE);
                st_city_id="";
                //call search company api here

                break;
            case R.id.search_button:
                callCompanyListApi();
                lin_filters.setVisibility(View.GONE);
                break;
            case R.id.add_btn:
                search_card.setVisibility(View.GONE);
                add_card.setVisibility(View.VISIBLE);
                clearAllDatas();
                break;
            case R.id.industry_dropdown:
                ItemDialog itemDialog = new ItemDialog(getActivity(), "Select", "industry", industry);
                itemDialog.setSelectionListener(this);
                itemDialog.show();
                break;
            case R.id.state_btn:
                ItemDialog itemDialog1 = new ItemDialog(getActivity(), "Select", "state", states);
                itemDialog1.setSelectionListener(this);
                itemDialog1.show();
                break;
            case R.id.city_btn:
                ItemDialog itemDialog2 = new ItemDialog(getActivity(), "Select", "city", cities);
                itemDialog2.setSelectionListener(this);
                itemDialog2.show();
                break;
            case R.id.save_btn:
                if (validateParam() && save_btn.getText().toString().equalsIgnoreCase("save")) {
                    callAddCompanyAPi();
                } else {
                    //call update api here with the changes done
                    if (validateParam()) {
                        callUpdateCompanyAPi((String) save_btn.getTag());
                    }
                }
                break;
            case R.id.search_city:
                ItemDialog itemDialog3 = new ItemDialog(getActivity(), "Select", "city_search", cities);
                itemDialog3.setSelectionListener(this);
                itemDialog3.show();
                break;
            case R.id.search_state:
                ItemDialog itemDialog4 = new ItemDialog(getActivity(), "Select", "state_search", states);
                itemDialog4.setSelectionListener(this);
                itemDialog4.show();
                break;
            case R.id.parent_company:
                //show company dropdown
                ItemDialog itemDialog5 = new ItemDialog(getActivity(), "Select", "pcomp", showDropDowns);
                itemDialog5.setSelectionListener(this);
                itemDialog5.show();
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

    private void callAddCompanyAPi() {
        companyMasterController.addCompany(ParamName.APIVERSION, "InsertCompany",
                st_comp_name, st_parent,
                st_o_name, st_address, st_area, st_pin, st_o_email, st_o_contact, st_cp_name, st_cp_email,
                st_cp_phone, st_allow_comp, st_allowstaff, SPUtils.getString(getActivity(), SPUtils.USER_ID),
                isActive, st_gst, st_city_id);
    }

    private void callUpdateCompanyAPi(String comp_id) {
        companyMasterController.updateCompany(ParamName.APIVERSION, "UpCompany",
                st_comp_name, st_parent,
                st_o_name, st_address, st_area, st_pin, st_o_email, st_o_contact, st_cp_name, st_cp_email,
                st_cp_phone, st_allow_comp, st_allowstaff, SPUtils.getString(getActivity(), SPUtils.USER_ID),
                isActive, st_gst, st_city_id, comp_id);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setSelected(false, false, false, true, false, false, false, false, false, false);
        ((MainActivity) getActivity()).setBottomNavigation(false, false, false);
        ((MainActivity) getActivity()).setTitle("Channel Company");
    }


    @Override
    public void onItemSelect(String item, String tag, int position) {
        if (tag.equalsIgnoreCase("industry")) {
            industry_dropdown.setText(item);
            if (position==-1){
                st_industry="";
            }else {
                st_industry=String.valueOf(position+1);
            }
        } else if (tag.equalsIgnoreCase("state")) {
            state_btn.setText(item);
            if (position==-1){
                st_state="";
            }else {
                st_state=statesList.get(position).getState_Idno();
            }
            city_btn.setText("");
            st_city_id="";
            countryApiCall.callCities(this, st_state);
        } else if (tag.equalsIgnoreCase("city")) {
            city_btn.setText(item);
            if (position==-1){
                st_city_id = "";
            }else {
                st_city_id = citiesList.get(position).getCity_Idno();
            }
        } else if (tag.equalsIgnoreCase("city_search")) {
            search_city.setText(item);
            if (position==-1){
                st_city_id = "";

            }else {
                st_city_id = citiesList.get(position).getCity_Idno();

            }

        } else if (tag.equalsIgnoreCase("state_search")) {
            search_state.setText(item);
            if (position==-1){
                st_state = "";

            }else {
                st_state = statesList.get(position).getState_Idno();

            }
            st_city_id="";
            search_city.setText("");
            countryApiCall.callCities(this, st_state);

        } else if (tag.equalsIgnoreCase("pcomp")) {
            parent_company.setText(item);
            if (position==-1){
                st_parent = "";
            }else {
                st_parent = dropdownCompanies.get(position).getCompanyUser_Idno();
            }
        }
    }

    List<StateModel.ValueBean> statesList = new ArrayList<>();

    @Override
    public void allStates(StateModel stateModel) {
        //all states are make an string list and proceed accordingly
        statesList.clear();
        statesList.addAll(stateModel.getValue());
        for (StateModel.ValueBean valueBean : statesList
                ) {
            states.add(valueBean.getState_Name());
        }
    }

    List<CityModel.ValueBean> citiesList = new ArrayList<>();

    @Override
    public void allCities(CityModel cityModel) {
        citiesList.clear();
        citiesList.addAll(cityModel.getValue());
        for (CityModel.ValueBean valueBean : citiesList
                ) {
            cities.add(valueBean.getCity_Name());
        }
    }

    @Override
    public void companyAllowed(boolean v) {
        if (v) {
            //show save button
            save_btn.setVisibility(View.VISIBLE);
        } else {
            //else hide show button and show message
            save_btn.setVisibility(View.GONE);
            companyMasterController.getRestClient().showApiErrorMessage("Company limit reached cannot add more companies," +
                    "please contact Coagxim Administration to add more");
        }
    }

    @Override
    public void companyAddedSucessFully() {
        clearAllDatas();
    }

    @Override
    public void companyList(List<CompanyListModel.ValueBean> compList) {
        companyList.clear();
        companyList.addAll(compList);
        companyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleted() {
        //deleted success
        companyListAdapter.notifyDataSetChanged();
    }

    private void clearAllDatas() {
        edit_address.setText("");
        edit_cp_phone.setText("");
        edit_cp_name.setText("");
        edit_cp_email.setText("");
        edit_o_email.setText("");
        edit_o_contact.setText("");
        city_btn.setText("");
        state_btn.setText("");
        edit_o_name.setText("");
        edit_comp_name.setText("");
        edit_pin.setText("");
        edit_gst.setText("");
        edit_area.setText("");
        edit_allow_staff.setText("");
        edit_allowComp.setText("");
        save_btn.setText("SAVE");
        edit_allowComp.setEnabled(true);
        edit_allow_staff.setEnabled(true);
    }

    private boolean validateParam() {
        st_address = edit_address.getText().toString();
        st_allow_comp = edit_allowComp.getText().toString();
        st_allowstaff = edit_allow_staff.getText().toString();
        st_area = edit_area.getText().toString();
        st_gst = edit_gst.getText().toString();
        st_pin = edit_pin.getText().toString();
        st_comp_name = edit_comp_name.getText().toString();
        st_o_name = edit_o_name.getText().toString();
        st_o_contact = edit_o_contact.getText().toString();
        st_o_email = edit_o_email.getText().toString();
        st_cp_email = edit_cp_email.getText().toString();
        st_cp_name = edit_cp_name.getText().toString();
        st_cp_phone = edit_cp_phone.getText().toString();
        if (st_address.isEmpty()) {
            edit_address.setError("Please Enter Address!");
            return false;
        } else if (st_allow_comp.isEmpty()) {
            edit_allowComp.setError("Please Enter Number Of Allow Companies!");
            return false;
        } else if (st_allow_comp.isEmpty()) {
            edit_allowComp.setError("Please Enter Number Of Allow Companies!");
            return false;
        } else if (st_allowstaff.isEmpty()) {
            edit_allow_staff.setError("Please Enter Number Of Allow Staff's!");
            return false;
        } else if (st_area.isEmpty()) {
            edit_area.setError("Please Enter Area !");
            return false;
        } else if (st_gst.isEmpty()) {
            edit_gst.setError("Please Enter GST No!");
            return false;
        } else if (st_pin.isEmpty()) {
            edit_pin.setError("Please Enter Pin Code!");
            return false;
        } else if (!st_pin.isEmpty() && st_pin.length() < 6) {
            edit_pin.setError("Pin number should be 6 digit!");
            return false;
        } else if (st_comp_name.isEmpty()) {
            edit_comp_name.setError("Please Enter Company Name!");
            return false;
        } else if (st_o_name.isEmpty()) {
            edit_o_name.setError("Please Enter Owner Name!");
            return false;
        } else if (st_o_contact.isEmpty()) {
            edit_o_contact.setError("Please Enter Owner's Contact Number!");
            return false;
        } else if (!st_o_contact.isEmpty() && st_o_contact.length() < 10) {
            edit_o_contact.setError("Contact number should be 10 digit!");
            return false;
        } else if (st_o_email.isEmpty()) {
            edit_o_email.setError("Please Enter Owner Email!");
            return false;
        } else if (!st_o_email.isEmpty() && !SPUtils.isValidateEmail(st_o_email)) {
            edit_o_email.setError("Invalid Email!");

            return false;
        } else if (st_city_id.isEmpty()) {
            city_btn.setError("Please Enter City!");
            return false;
        } else if (!st_cp_phone.isEmpty() && st_cp_phone.length() < 10) {
            edit_cp_phone.setError("Contact number should be 10 digit!");
            return false;
        } else if (!st_cp_email.isEmpty() && !SPUtils.isValidateEmail(st_cp_email)) {
            edit_cp_email.setError("Invalid Email!");
            return false;
        }

        return true;
    }

    private void callCompanyListApi() {
        companyMasterController.getCompanyList(search_compName.getText().toString(), search_gst.getText().toString(),
                st_state, st_city_id, "", search_oname.getText().toString(),
                search_email.getText().toString(), search_contact.getText().toString(),
                SPUtils.getString(getActivity(), SPUtils.COMPANY_USER_ID));
    }

    @Override
    public void edit(int position) {
        add_card.setVisibility(View.VISIBLE);
        search_card.setVisibility(View.GONE);
        CompanyListModel.ValueBean valueBean = companyList.get(position);
        setUpdateUi(valueBean);
    }

    @Override
    public void delete(int position) {
        companyMasterController.deleteCompany(companyList.get(position).getCompanyUser_Idno());
        companyList.remove(position);
    }

    private void setUpdateUi(CompanyListModel.ValueBean valueBean) {
        if (valueBean.getInsertCompanyUser_Idno().equalsIgnoreCase("1")) {
            if (showDropDowns.size() > 0) {
                parent_company.setText(showDropDowns.get(0));
            }
            //most parent company
        } else {
            //find in list and set name and id
            for (CompanyDropDown.ValueBean valueBean1 : dropdownCompanies
                    ) {
                if (valueBean1.getCompanyUser_Idno().equalsIgnoreCase(valueBean.getInsertCompanyUser_Idno())) {
                    parent_company.setText(valueBean1.getCompany_Name());
                    st_parent = valueBean.getInsertCompanyUser_Idno();
                }
            }

        }
        edit_address.setText(valueBean.getCompanyAddress());
        edit_cp_phone.setText(valueBean.getContactPerson_MobileNo());
        edit_cp_name.setText("");
        edit_cp_email.setText(valueBean.getContactPerson_EmailId());
        edit_o_email.setText(valueBean.getOwner_EmailId());
        edit_o_contact.setText(valueBean.getOwner_MobileNo());
        city_btn.setText(valueBean.getCity_Name());
        state_btn.setText(valueBean.getState_Name());
        edit_o_name.setText(valueBean.getOwner_Name());
        edit_comp_name.setText(valueBean.getCompany_Name());
        edit_pin.setText(valueBean.getPincode());
        edit_gst.setText(valueBean.getCompany_GSTNo());
        edit_area.setText(valueBean.getCompany_Area());
        edit_allow_staff.setText(valueBean.getAllowed_Staff());
        edit_allowComp.setText(valueBean.getAllowed_Company());
        edit_allowComp.setEnabled(false);
        edit_allow_staff.setEnabled(false);
        edit_cp_name.setText(valueBean.getContactPerson_Name());
        if (valueBean.getIs_Active().equalsIgnoreCase("1")) {
            switch_k.setChecked(true);
        } else {
            switch_k.setChecked(false);
        }
        save_btn.setTag(valueBean.getCompanyUser_Idno());
        save_btn.setText("UPDATE");
        st_city_id = valueBean.getCity_Idno();
    }

    List<CompanyDropDown.ValueBean> dropdownCompanies = new ArrayList<>();
    List<String> showDropDowns = new ArrayList<>();

    @Override
    public void dropDownList(List<CompanyDropDown.ValueBean> valueBeans) {
        //company drop down select
        dropdownCompanies.clear();
        showDropDowns.clear();
        dropdownCompanies.addAll(valueBeans);

        for (CompanyDropDown.ValueBean valueBean : dropdownCompanies
                ) {
            showDropDowns.add(valueBean.getCompany_Name());
        }
        if (dropdownCompanies.size() == 1) {
            //set parent company diffault
            onItemSelect(dropdownCompanies.get(0).getCompany_Name(), "pcomp", 0);

        }
    }
}
