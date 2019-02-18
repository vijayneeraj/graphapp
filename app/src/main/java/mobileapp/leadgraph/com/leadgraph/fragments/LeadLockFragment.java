package mobileapp.leadgraph.com.leadgraph.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mobileapp.leadgraph.com.leadgraph.MainActivity;
import mobileapp.leadgraph.com.leadgraph.R;
import mobileapp.leadgraph.com.leadgraph.adapters.LeadLockListAdapter;
import mobileapp.leadgraph.com.leadgraph.controller.ChannelStaffController;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyDesignationController;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyMasterController;
import mobileapp.leadgraph.com.leadgraph.controller.CountryApiCall;
import mobileapp.leadgraph.com.leadgraph.controller.LeadLockController;
import mobileapp.leadgraph.com.leadgraph.controller.ProductMasterController;
import mobileapp.leadgraph.com.leadgraph.dialog.ItemDialog;
import mobileapp.leadgraph.com.leadgraph.models.CityModel;
import mobileapp.leadgraph.com.leadgraph.models.CompanyDropDown;
import mobileapp.leadgraph.com.leadgraph.models.LeadListModel;
import mobileapp.leadgraph.com.leadgraph.models.ProductListModel;
import mobileapp.leadgraph.com.leadgraph.models.StaffListModel;
import mobileapp.leadgraph.com.leadgraph.models.StateModel;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;

/**
 * @author neeraj on 5/12/18.
 */
public class LeadLockFragment extends Fragment implements View.OnClickListener, ItemDialog.SelectionListener, CompanyMasterController.CompanyDropDownListner, ChannelStaffController.StaffControllerListner, ProductMasterController.ProductMasterListener, CountryApiCall.StateListener, CountryApiCall.CityListener, LeadLockController.LeadLockListners, LeadLockListAdapter.CompanyActionListener {
    View view;
    TextView spin_company, spin_staff, spin_product, spin_language, spin_leadstatus, spin_leadType, date_next_followup;
    ImageView serach_btn, add_btn;
    CardView add_card;
    TextView save_btn, search_button, date_date, city, state, demo_time, demo_date, to_date, from_date, search_spin_company;
    EditText alt_contact_number, contact_number, prospect_name, price_quoted, case_lock_number, remarks;
    Switch switch_k;
    String isAssignedToSelf = "0";
    CountryApiCall countryApiCall;
    CompanyMasterController companyMasterController;
    CompanyDesignationController companyDesignationController;
    ChannelStaffController channelStaffController;
    ProductMasterController productMasterController;
    List<CompanyDropDown.ValueBean> comapnyBeans = new ArrayList<>();
    List<String> companies = new ArrayList<>();
    String st_parent, st_staff, st_prospect, st_contact, st_altContact, st_product, st_price,
            st_lang, st_leadType, st_leadStatus, st_caseLockNumber, st_nextdate, st_date_date, st_state, st_city,
            st_demo_date, st_demo_time, st_remarks;
    List<StaffListModel.ValueBean> staffBeans = new ArrayList<>();
    List<String> staffs = new ArrayList<>();
    List<ProductListModel.ValueBean> productBeans = new ArrayList<>();
    List<String> products = new ArrayList<>();
    List<String> languages = new ArrayList<>();
    List<String> leadTypes = new ArrayList<>();
    List<String> leadStatus = new ArrayList<>();
    List<StateModel.ValueBean> stateBeans = new ArrayList<>();
    List<String> states = new ArrayList<>();
    List<CityModel.ValueBean> cityBeans = new ArrayList<>();
    List<String> cities = new ArrayList<>();
    LeadLockController leadLockController;
    RecyclerView list_search;
    List<LeadListModel.ValueBean> valueBeans = new ArrayList<>();
    LeadLockListAdapter leadLockListAdapter;
    ImageView show_filter;
    LinearLayout lin_filters, search_card;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_leadlock, container, false);
        }
        initViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setSelected(false, false, false, false, false, true, false, false, false, false);
        ((MainActivity) getActivity()).setBottomNavigation(true, false, false);
        ((MainActivity) getActivity()).setTitle("Lead Lock");
    }

    private void initViews() {
        productMasterController = new ProductMasterController(getActivity());
        leadLockController = new LeadLockController(getActivity());
        leadLockListAdapter = new LeadLockListAdapter(getActivity(), valueBeans);
        leadLockListAdapter.setCompanyActionListener(this);
        leadLockController.setLeadLockListners(this);
        productMasterController.setProductMasterListener(this);
        companyMasterController = new CompanyMasterController(getActivity());
        companyMasterController.setCompanyDropDownListner(this);
        channelStaffController = new ChannelStaffController(getActivity());
        countryApiCall = new CountryApiCall(getActivity());
        channelStaffController.setStaffControllerListner(this);
        spin_company = view.findViewById(R.id.spin_company);
        spin_staff = view.findViewById(R.id.spin_staff);
        spin_product = view.findViewById(R.id.spin_product);
        spin_language = view.findViewById(R.id.spin_language);
        spin_leadType = view.findViewById(R.id.spin_leadType);
        spin_leadstatus = view.findViewById(R.id.spin_leadstatus);
        spin_leadstatus.setOnClickListener(this);
        spin_leadType.setOnClickListener(this);
        spin_language.setOnClickListener(this);
        spin_product.setOnClickListener(this);
        spin_staff.setOnClickListener(this);
        spin_company.setOnClickListener(this);
        price_quoted = view.findViewById(R.id.price_quoted);
        prospect_name = view.findViewById(R.id.prospect_name);
        contact_number = view.findViewById(R.id.contact_number);
        alt_contact_number = view.findViewById(R.id.alt_contact_number);
        case_lock_number = view.findViewById(R.id.case_lock_number);
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
        languages.clear();
        languages.add("Hindi");
        languages.add("English");
        leadTypes.clear();
        leadTypes.add("Hot");
        leadTypes.add("Warm");
        leadTypes.add("Cold");
        leadStatus.clear();
        leadStatus.add("Demo Done");
        leadStatus.add("Next Followup");
        leadStatus.add("Deal Closed");
        leadStatus.add("Deal Lost");
        leadStatus.add("Demo Installed");
        leadStatus.add("Not Responding");
        leadStatus.add("Order Lifted");
        leadStatus.add("Not Interested");
        date_next_followup = view.findViewById(R.id.date_next_followup);
        date_next_followup.setOnClickListener(this);
        date_date = view.findViewById(R.id.date_date);
        date_date.setOnClickListener(this);
        city = view.findViewById(R.id.city);
        state = view.findViewById(R.id.state);
        demo_date = view.findViewById(R.id.demo_date);
        demo_date.setOnClickListener(this);
        demo_time = view.findViewById(R.id.demo_time);
        demo_time.setOnClickListener(this);
        state.setOnClickListener(this);
        city.setOnClickListener(this);
        remarks = view.findViewById(R.id.remarks);
        switch_k = view.findViewById(R.id.switch_k);
        from_date = view.findViewById(R.id.from_date);
        to_date = view.findViewById(R.id.to_date);
        from_date.setOnClickListener(this);
        to_date.setOnClickListener(this);
        search_spin_company = view.findViewById(R.id.search_spin_company);
        search_spin_company.setOnClickListener(this);
        list_search = view.findViewById(R.id.list_search);
        list_search.setAdapter(leadLockListAdapter);
        lin_filters = view.findViewById(R.id.lin_filters);
        show_filter = view.findViewById(R.id.show_filter);
        show_filter.setOnClickListener(this);
        switch_k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isAssignedToSelf = "1";
                } else {
                    isAssignedToSelf = "0";
                }

            }
        });
        Calendar calendar=Calendar.getInstance();
        String ct_date=calendar.get(Calendar.DAY_OF_MONTH)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR);
        date_next_followup.setText(ct_date);
        date_date.setText(ct_date);
        companyMasterController.getComapnyDropDown(SPUtils.getString(getActivity(), SPUtils.COMPANY_USER_ID));
        countryApiCall.callStates(this, "1");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.spin_company:
                ItemDialog itemDialog = new ItemDialog(getActivity(), "Select Company", "pcomp", companies);
                itemDialog.setSelectionListener(this);
                itemDialog.show();
                break;
            case R.id.serach_btn:
                add_card.setVisibility(View.GONE);
                search_card.setVisibility(View.VISIBLE);
                break;
            case R.id.add_btn:
                search_card.setVisibility(View.GONE);
                add_card.setVisibility(View.VISIBLE);
                clearUi();
                break;
            case R.id.spin_staff:
                ItemDialog itemDialog1 = new ItemDialog(getActivity(), "Select", "staff", staffs);
                itemDialog1.setSelectionListener(this);
                itemDialog1.show();
                break;
            case R.id.spin_product:
                ItemDialog itemDialog2 = new ItemDialog(getActivity(), "Select", "product", products);
                itemDialog2.setSelectionListener(this);
                itemDialog2.show();
                break;
            case R.id.spin_language:
                ItemDialog itemDialog3 = new ItemDialog(getActivity(), "Select", "lang", languages);
                itemDialog3.setSelectionListener(this);
                itemDialog3.show();
                break;
            case R.id.spin_leadType:
                ItemDialog itemDialog4 = new ItemDialog(getActivity(), "Select", "leadtype", leadTypes);
                itemDialog4.setSelectionListener(this);
                itemDialog4.show();
                break;
            case R.id.spin_leadstatus:
                ItemDialog itemDialog5 = new ItemDialog(getActivity(), "Select", "leadstatus", leadStatus);
                itemDialog5.setSelectionListener(this);
                itemDialog5.show();
                break;
            case R.id.date_next_followup:
                openDatePicker(0);
                break;
            case R.id.date_date:
                openDatePicker(1);
                break;
            case R.id.state:
                ItemDialog itemDialog6 = new ItemDialog(getActivity(), "Select", "states", states);
                itemDialog6.setSelectionListener(this);
                itemDialog6.show();
                break;
            case R.id.city:
                ItemDialog itemDialog7 = new ItemDialog(getActivity(), "Select", "cities", cities);
                itemDialog7.setSelectionListener(this);
                itemDialog7.show();
                break;
            case R.id.demo_time:
                openTimePickerDialog();
                break;
            case R.id.demo_date:
                openDatePicker(2);
                break;
            case R.id.save_btn:
                if (isValidated() && save_btn.getText().toString().equalsIgnoreCase("save")) {
                    //call save api here
                    leadLockController.insertLead(st_parent, spin_company.getText().toString(), st_staff, st_prospect, st_contact, st_altContact
                            , st_product, st_price, st_lang, st_leadType, st_leadStatus, st_nextdate, st_caseLockNumber, st_date_date, st_state, st_city, st_demo_date, st_demo_time, st_remarks, isAssignedToSelf,
                            SPUtils.getString(getActivity(), SPUtils.USER_ID));
                } else if (isValidated() && save_btn.getText().toString().equalsIgnoreCase("update")) {
                    leadLockController.updateLead(st_parent, spin_company.getText().toString(), st_staff, st_prospect, st_contact, st_altContact
                            , st_product, st_price, st_lang, st_leadType, st_leadStatus, st_nextdate, st_caseLockNumber, st_date_date, st_state, st_city, st_demo_date, st_demo_time, st_remarks, isAssignedToSelf,
                            SPUtils.getString(getActivity(), SPUtils.USER_ID), (String) save_btn.getTag());
                }
                break;
            case R.id.to_date:
                openDatePicker(3);
                break;
            case R.id.from_date:
                openDatePicker(4);
                break;
            case R.id.search_spin_company:
                ItemDialog itemDialog8 = new ItemDialog(getActivity(), "Select Company", "scomp", companies);
                itemDialog8.setSelectionListener(this);
                itemDialog8.show();
                break;
            case R.id.search_button:
                //call search api here
                leadLockController.leadList(st_parent, from_date.getText().toString(), to_date
                        .getText().toString());
                lin_filters.setVisibility(View.GONE);
                break;
            case R.id.show_filter:
                if (lin_filters.getVisibility() == View.VISIBLE) {
                    lin_filters.setVisibility(View.GONE);

                } else {
                    lin_filters.setVisibility(View.VISIBLE);
                }
                break;
        }
    }


    @Override
    public void onItemSelect(String item, String tag, int position) {
        if (tag.equalsIgnoreCase("pcomp")) {
            spin_company.setText(item);
            if (position==-1){
                st_parent = "";

            }else {
                st_parent = comapnyBeans.get(position).getCompanyUser_Idno();
            }
            st_staff = "";
            st_product = "";
            spin_staff.setText("Select");
            spin_product.setText("Select");
            channelStaffController.getStaffList("", st_parent, "", "");
            productMasterController.getProducts(st_parent, "", "", "");
        } else if (tag.equalsIgnoreCase("staff")) {
            spin_staff.setText(item);
            if (position==-1){
                st_staff = "";

            }else {
                st_staff = staffBeans.get(position).getUser_Idno();

            }

        } else if (tag.equalsIgnoreCase("product")) {
            spin_product.setText(item);
            if (position==-1){
                st_product = "";

            }else {
                st_product = productBeans.get(position).getProduct_Idno();

            }
        } else if (tag.equalsIgnoreCase("lang")) {

            spin_language.setText(item);
            if (position==-1){
                st_lang = "";

            }else {
                st_lang = String.valueOf(position + 1);

            }
        } else if (tag.equalsIgnoreCase("leadstatus")) {
            spin_leadstatus.setText(item);
            if (position==-1){
                st_leadStatus = "";
            }else {
                st_leadStatus = String.valueOf(position + 1);
            }

        } else if (tag.equalsIgnoreCase("leadtype")) {
            spin_leadType.setText(item);
            if (position==-1){
                st_leadType = "";

            }else {
                st_leadType = String.valueOf(position);

            }
        } else if (tag.equalsIgnoreCase("states")) {
            state.setText(item);
            if (position==-1){
                st_state = "";

            }else {
                st_state = stateBeans.get(position).getState_Idno();

            }
            countryApiCall.callCities(this, st_state);
            city.setText("");
            st_city="";
        } else if (tag.equalsIgnoreCase("cities")) {
            city.setText(item);
            if (position==-1){
                st_city = "";
            }else {
                st_city = cityBeans.get(position).getCity_Idno();
            }

        } else if (tag.equalsIgnoreCase("scomp")) {
            search_spin_company.setText(item);
            if (position==-1){
                st_parent = "";

            }else {
                st_parent = comapnyBeans.get(position).getCompanyUser_Idno();

            }
        }
    }

    @Override
    public void dropDownList(List<CompanyDropDown.ValueBean> valueBeans) {
        comapnyBeans.clear();
        comapnyBeans.addAll(valueBeans);
        companies.clear();
        for (CompanyDropDown.ValueBean valueBean : valueBeans
                ) {
            companies.add(valueBean.getCompany_Name());
        }
    }

    @Override
    public void staffList(List<StaffListModel.ValueBean> valueBeans) {
        staffBeans.clear();
        staffBeans.addAll(valueBeans);
        staffs.clear();
        for (StaffListModel.ValueBean valueBean : valueBeans
                ) {
            staffs.add(valueBean.getUser_Name());
        }

    }

    @Override
    public void productAdded() {

    }

    @Override
    public void productList(List<ProductListModel.ValueBean> valueBeans) {
        productBeans.clear();
        productBeans.addAll(valueBeans);
        products.clear();
        for (ProductListModel.ValueBean valueBean : valueBeans
                ) {
            products.add(valueBean.getProduct_Name());
        }
    }

    @Override
    public void productUpdated() {

    }

    @Override
    public void productDeleted() {

    }

    private void openDatePicker(final int flag) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                String text = datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear();
                if (flag == 0) {
                    date_next_followup.setText(text);

                } else if (flag == 1) {
                    date_date.setText(text);
                } else if (flag == 2) {
                    demo_date.setText(text);
                } else if (flag == 3) {
                    to_date.setText(text);
                } else if (flag == 4) {
                    from_date.setText(text);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void openTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String text = i + ":" + (i1);
                demo_time.setText(text);

            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    @Override
    public void allStates(StateModel stateModel) {
        stateBeans.clear();
        stateBeans.addAll(stateModel.getValue());
        states.clear();
        for (StateModel.ValueBean valueBean : stateModel.getValue()
                ) {
            states.add(valueBean.getState_Name());
        }
    }

    @Override
    public void allCities(CityModel cityModel) {
        cityBeans.clear();
        cityBeans.addAll(cityModel.getValue());
        cities.clear();
        for (CityModel.ValueBean valueBean : cityModel.getValue()
                ) {
            cities.add(valueBean.getCity_Name());
        }
    }

    private boolean isValidated() {
        st_prospect = prospect_name.getText().toString();
        st_contact = contact_number.getText().toString();
        st_altContact = alt_contact_number.getText().toString();
        st_price = price_quoted.getText().toString();
        st_nextdate = date_next_followup.getText().toString();
        st_caseLockNumber = case_lock_number.getText().toString();
        st_date_date = date_date.getText().toString();
        st_demo_date = demo_date.getText().toString();
        st_demo_time = demo_time.getText().toString();
        st_remarks = remarks.getText().toString();


        if (st_parent == null || st_parent.isEmpty()) {
            spin_company.setError("Select Company!");
            return false;
        } else if (st_staff == null || st_staff.isEmpty()) {
            spin_staff.setError("Select Staff!");
            return false;
        } else if (st_prospect.isEmpty()) {
            prospect_name.setError("prospect name required!");
            return false;
        } else if (st_contact.isEmpty()) {
            contact_number.setError("Contact number required!");
            return false;
        } else if (st_altContact.isEmpty()) {
            alt_contact_number.setError("Contact number required!");
            return false;
        } else if (st_product == null || st_product.isEmpty()) {
            spin_product.setError("Select product!");
            return false;
        } else if (st_price.isEmpty()) {
            price_quoted.setError("Quoted price required!");
            return false;
        } else if (st_lang == null || st_lang.isEmpty()) {
            spin_language.setError("Select preferred language!");
            return false;
        } else if (st_leadType == null || st_leadType.isEmpty()) {
            spin_leadType.setError("Select lead type!");
            return false;
        } else if (st_leadStatus == null || st_leadStatus.isEmpty()) {
            spin_leadstatus.setError("Select lead status!");
            return false;
        } else if (st_nextdate.isEmpty()) {
            date_next_followup.setError("date required!");
            return false;
        } else if (st_caseLockNumber.isEmpty()) {
            case_lock_number.setError("case lock number required!");
            return false;
        } else if (st_date_date.isEmpty()) {
            date_date.setError("date required!");
            return false;
        } else if (st_state == null || st_state.isEmpty()) {
            state.setError("select state!");
            return false;
        } else if (st_city == null || st_city.isEmpty()) {
            city.setError("city required!");
            return false;
        } else if (st_demo_date.isEmpty()) {
            demo_date.setError("date required!");
            return false;
        } else if (st_demo_time.isEmpty()) {
            demo_time.setError("time required!");
            return false;
        }
        return true;
    }

    @Override
    public void leadList(List<LeadListModel.ValueBean> leadList) {
        valueBeans.clear();
        valueBeans.addAll(leadList);
        leadLockListAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteLead() {
        if (position!=-1){
            valueBeans.remove(position);
            leadLockListAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void edit(int position) {
        add_card.setVisibility(View.VISIBLE);
        search_card.setVisibility(View.GONE);
        setData(valueBeans.get(position));
    }

    int position = -1;

    @Override
    public void delete(int position) {
        leadLockController.deleteLead(valueBeans.get(position).getLead_Idno());
        this.position = position;

    }

    private void setData(LeadListModel.ValueBean ui) {
        spin_company.setText(ui.getComp_Name());
        st_parent = ui.getCompanyUser_Idno();
        spin_staff.setText(ui.getStaff_Name());
        st_staff = "";
        prospect_name.setText(ui.getProspect_Name());
        contact_number.setText(ui.getContact_No());
        alt_contact_number.setText(ui.getAlter_No());
        spin_product.setText(ui.getProduct_Name());
        st_product = ui.getProduct_Idno();
        price_quoted.setText(ui.getLead_Cost());
        st_lang = ui.getPref_Lang();
        if (st_lang.equalsIgnoreCase("1")) {
            spin_language.setText("Hindi");
        } else {
            spin_language.setText("English");

        }
        st_leadType = ui.getLead_Type();
        if (st_leadType.equalsIgnoreCase("1")) {
            spin_leadType.setText("Hot");
        } else if (st_leadType.equalsIgnoreCase("2")) {
            spin_leadType.setText("Warm");
        } else {
            spin_leadType.setText("Cold");
        }
        st_leadStatus = ui.getLead_Status();
        spin_leadstatus.setText(leadStatus.get(Integer.parseInt(ui.getLead_Status()) - 1));
        date_next_followup.setText(ui.getLead_NextFollwUpDate());
        case_lock_number.setText(ui.getCaseLock_No());
        date_date.setText(ui.getLead_Date());
        st_state = ui.getState_Idno();
        st_city = ui.getCity_Idno();
        city.setText(ui.getCity_Name());
        demo_date.setText(ui.getDemo_Date());
        demo_time.setText(ui.getDemo_Time());
        if (ui.getAssignTo_Self().equalsIgnoreCase("1")) {
            switch_k.setChecked(true);
        } else {
            switch_k.setChecked(false);
        }
        remarks.setText(ui.getRemarks());
        save_btn.setText("update");
        state.setText(ui.getState_Name());
        save_btn.setTag(ui.getLead_Idno());

    }

    private void clearUi() {
        spin_company.setText("");
        spin_staff.setText("");
        prospect_name.setText("");
        contact_number.setText("");
        alt_contact_number.setText("");
        spin_product.setText("");
        price_quoted.setText("");
        spin_language.setText("");
        spin_leadType.setText("");

        spin_leadstatus.setText("");
        date_next_followup.setText("");
        case_lock_number.setText("");
        date_date.setText("");
        city.setText("");
        demo_date.setText("");
        demo_time.setText("");
        state.setText("");
        remarks.setText("");
    }
}
