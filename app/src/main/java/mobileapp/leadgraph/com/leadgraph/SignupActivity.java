package mobileapp.leadgraph.com.leadgraph;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mobileapp.leadgraph.com.leadgraph.controller.CountryApiCall;
import mobileapp.leadgraph.com.leadgraph.dialog.ItemDialog;
import mobileapp.leadgraph.com.leadgraph.models.CityModel;
import mobileapp.leadgraph.com.leadgraph.models.SignupModel;
import mobileapp.leadgraph.com.leadgraph.models.StateModel;
import mobileapp.leadgraph.com.leadgraph.rest.ApiErrorMessages;
import mobileapp.leadgraph.com.leadgraph.rest.ApiUrls;
import mobileapp.leadgraph.com.leadgraph.rest.RestAdapter;
import mobileapp.leadgraph.com.leadgraph.rest.RestClient;

/**
 * @author neeraj on 6/12/18.
 */
public class SignupActivity extends AppCompatActivity implements CountryApiCall.StateListener, View.OnClickListener, ItemDialog.SelectionListener, CountryApiCall.CityListener, RestClient.ApiListeners {
    private EditText compName;
    private EditText gstinEdit;
    private EditText ownerName;
    private EditText email;
    private TextView countryCode;
    private EditText editNumber;
    private EditText state;
    private EditText city;
    private EditText address;
    private EditText pinCode;
    private EditText conpName;
    private EditText emailConp;
    private EditText numberConp;
    private EditText industry;
    private CheckBox switchK;
    private TextView login;
    private TextView signupBtn;
    private CountryApiCall countryApiCall;
    String st_state, st_city, st_industry, st_compName, st_gstin, st_ownerName, st_email, st_number,
            st_address, st_pinCode, st_conPName, st_email_contact, st_numberContact;
    private List<StateModel.ValueBean> stateBeans = new ArrayList<>();
    private List<String> states = new ArrayList<>();
    private List<CityModel.ValueBean> cityBeans = new ArrayList<>();
    List<String> cities = new ArrayList<>();
    List<String> industries = new ArrayList<>();
    boolean isTermed = false;
    private RestClient restClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }


    private void initView() {
        countryApiCall = new CountryApiCall(this);
        restClient = new RestClient(this, this);
        compName = (EditText) findViewById(R.id.comp_name);
        gstinEdit = (EditText) findViewById(R.id.gstin_edit);
        ownerName = (EditText) findViewById(R.id.owner_name);
        email = (EditText) findViewById(R.id.email);
        countryCode = (TextView) findViewById(R.id.country_code);
        editNumber = (EditText) findViewById(R.id.edit_number);
        state = (EditText) findViewById(R.id.state);
        city = (EditText) findViewById(R.id.city);
        address = (EditText) findViewById(R.id.address);
        pinCode = (EditText) findViewById(R.id.pin_code);
        conpName = (EditText) findViewById(R.id.conp_name);
        emailConp = (EditText) findViewById(R.id.email_conp);
        numberConp = (EditText) findViewById(R.id.number_conp);
        industry = (EditText) findViewById(R.id.industry);
        switchK = (CheckBox) findViewById(R.id.switch_k);
        login = (TextView) findViewById(R.id.login);
        signupBtn = (TextView) findViewById(R.id.signup_btn);
        state.setOnClickListener(this);
        city.setOnClickListener(this);
        industries.add("It Industry");
        industries.add("Automobile Industry");
        industry.setOnClickListener(this);
        countryApiCall.callStates(this, "1");
        switchK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isTermed = b;
            }
        });
        login.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
    }

    @Override
    public void allStates(StateModel stateModel) {
        stateBeans.clear();
        states.clear();
        stateBeans.addAll(stateModel.getValue());
        for (StateModel.ValueBean valueBean : stateModel.getValue()
                ) {
            states.add(valueBean.getState_Name());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.state:
                ItemDialog itemDialog = new ItemDialog(this, "Select", "state", states);
                itemDialog.setSelectionListener(this);
                itemDialog.show();
                break;
            case R.id.city:
                ItemDialog itemDialog1 = new ItemDialog(this, "Select", "city", cities);
                itemDialog1.setSelectionListener(this);
                itemDialog1.show();
                break;
            case R.id.industry:
                ItemDialog itemDialog2 = new ItemDialog(this, "Select", "industry", industries);
                itemDialog2.setSelectionListener(this);
                itemDialog2.show();
                break;
            case R.id.login:
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.signup_btn:
                if (isValidatedparam()) {
                         restClient.callApi(ApiUrls.SIGNUP_ID, RestAdapter.getAdapter().createCompany(st_compName,st_gstin,st_ownerName
                         ,st_email,st_number,st_city,st_address,"",st_pinCode,st_conPName,st_email_contact,st_numberContact,"0","0"));
                }
                break;
        }
    }

    @Override
    public void onItemSelect(String item, String tag, int position) {
        if (tag.equalsIgnoreCase("state")) {
            state.setText(item);
            if (position==-1){
                st_state="";
            }else {
                st_state = stateBeans.get(position).getState_Idno();
            }
            st_city = "";
            city.setText("");
            countryApiCall.callCities(this, st_state);
        } else if (tag.equalsIgnoreCase("city")) {
            city.setText(item);
            if (position==-1){
                st_city = "";
            }else {
                st_city = cityBeans.get(position).getCity_Idno();
            }
        } else if (tag.equalsIgnoreCase("industry")) {
            industry.setText(item);
            if (position==-1){
                st_industry = "";
            }else {
                st_industry = String.valueOf(position + 1);
            }

        }
    }

    @Override
    public void allCities(CityModel cityModel) {
        cityBeans.clear();
        cities.clear();
        cityBeans.addAll(cityModel.getValue());
        for (CityModel.ValueBean valueBean : cityModel.getValue()
                ) {
            cities.add(valueBean.getCity_Name());
        }
    }

    private boolean isValidatedparam() {
        st_compName = compName.getText().toString();
        st_ownerName = ownerName.getText().toString();
        st_gstin = gstinEdit.getText().toString();
        st_email = email.getText().toString();
        st_number = editNumber.getText().toString();
        st_address = address.getText().toString();
        st_pinCode = pinCode.getText().toString();
        st_conPName = conpName.getText().toString();
        st_email_contact = emailConp.getText().toString();
        st_numberContact = numberConp.getText().toString();
        if (st_conPName.isEmpty()) {
            compName.setError("company name required!");
            return false;
        } else if (st_ownerName.isEmpty()) {
            ownerName.setError("owner name required!");
            return false;
        } else if (st_gstin.isEmpty()) {
            gstinEdit.setError("gst number required!");
            return false;
        } else if (st_email.isEmpty()) {
            email.setError("company email required!");
            return false;
        } else if (st_number.isEmpty()) {
            editNumber.setError("company number required!");
            return false;
        } else if (st_address.isEmpty()) {
            address.setError("address required!");
            return false;
        } else if (st_pinCode.isEmpty()) {
            pinCode.setError("pincode required!");
            return false;
        } else if (st_conPName.isEmpty()) {
            conpName.setError("contact person name required!");
            return false;
        } else if (st_email_contact.isEmpty()) {
            emailConp.setError("contact person email required!");
            return false;
        } else if (st_numberContact.isEmpty()) {
            numberConp.setError("contact person number required!");
            return false;
        } else if (st_state == null || st_state.isEmpty()) {
            state.setError("please select a state!");
            return false;
        } else if (st_city == null || st_city.isEmpty()) {
            city.setError("please select a city!");
            return false;
        }
        return true;
    }

    @Override
    public void onResponseSucess(String response, int apiId) {
        restClient.dismissLoadingDialog();
        if (response != null) {
            if (apiId == ApiUrls.SIGNUP_ID) {
                SignupModel signupModel = new Gson().fromJson(response, SignupModel.class);
                if (signupModel != null) {
                    if (signupModel.isValid()) {
                        restClient.showApiErrorMessage(signupModel.getDescription());
                    } else {
                        restClient.showApiErrorMessage(signupModel.getDescription());
                    }
                } else {
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            }
        } else {
            restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
        }
    }

    @Override
    public void onFailure(String message) {
        restClient.dismissLoadingDialog();
        restClient.showApiErrorMessage(message);

    }
}
