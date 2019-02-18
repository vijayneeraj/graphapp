package mobileapp.leadgraph.com.leadgraph;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import mobileapp.leadgraph.com.leadgraph.dialog.LoginAsDialog;
import mobileapp.leadgraph.com.leadgraph.models.LoginMOdel;
import mobileapp.leadgraph.com.leadgraph.rest.ApiErrorMessages;
import mobileapp.leadgraph.com.leadgraph.rest.ApiUrls;
import mobileapp.leadgraph.com.leadgraph.rest.AuthUtils;
import mobileapp.leadgraph.com.leadgraph.rest.RestAdapter;
import mobileapp.leadgraph.com.leadgraph.rest.RestClient;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;

/**
 * @author neeraj on 5/12/18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RestClient.ApiListeners, LoginAsDialog.LoginAsListener {
    Button btn_signin;
    EditText edit_password, edit_username;
    String username;
    String password;
    TextView signup_btn;
    RestClient restClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        restClient = new RestClient(this, this);
        signup_btn = findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(this);
        btn_signin = findViewById(R.id.btn_signin);
        edit_password = findViewById(R.id.edit_password);
        edit_username = findViewById(R.id.edit_username);
        btn_signin.setOnClickListener(this);
    }

    private void callLoginAPi() {
        restClient.callApi(ApiUrls.LOGINAPI_ID, RestAdapter.getAdapter().doLogin(username, AuthUtils.basic(password), "1.0"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signin:
                //call main activity here
                if (validateParam()) {
                    callLoginAPi();
                }
                break;
            case R.id.signup_btn:
                Intent intent1 = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private boolean validateParam() {
        username = edit_username.getText().toString();
        password = edit_password.getText().toString();
        if (username.isEmpty()) {
            edit_username.setError("username required!");
            return false;
        } else if (password.isEmpty()) {
            edit_password.setError("password required!");
            return false;
        }
        return true;
    }

    /**
     * api response handler methods
     *
     * @param response
     * @param apiId
     */

    @Override
    public void onResponseSucess(String response, int apiId) {
        restClient.dismissLoadingDialog();
        if (apiId == ApiUrls.LOGINAPI_ID) {
            if (response != null) {
                LoginMOdel loginMOdel = new Gson().fromJson(response, LoginMOdel.class);
                if (loginMOdel != null) {
                    if (loginMOdel.isValid()) {
                        //yes logged in success
                        if (loginMOdel.getValue().get(0).getStatusCode()==null) {
                            if (loginMOdel.getValue().size() <= 1) {
                                //direct login
                                makeLogin(loginMOdel.getValue().get(0));
                            } else {
                                //show login dialog
                                LoginAsDialog loginAsDialog = new LoginAsDialog(this, loginMOdel.getValue());
                                loginAsDialog.setLoginAsListener(this);
                                loginAsDialog.show();
                            }
                        }else {
                            restClient.showApiErrorMessage(loginMOdel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(loginMOdel.getDescription());
                    }
                } else {
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            } else {
                restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            }
        }
    }

    private void makeLogin(LoginMOdel.ValueBean valueBean) {
        SPUtils.setLogin(true, this);
        SPUtils.setString(this, valueBean.getLogin_Idno(), SPUtils.LOGIN_ID);
        SPUtils.setString(this, valueBean.getUser_Idno(), SPUtils.USER_ID);
        SPUtils.setString(this, valueBean.getCompanyUser_Idno(), SPUtils.COMPANY_USER_ID);
        SPUtils.setString(this, valueBean.getCompanyName(), SPUtils.COMPANY_NAME);
        SPUtils.setString(this, valueBean.getEmployeeName(), SPUtils.EMPLOY_NAME);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure(String message) {
        restClient.dismissLoadingDialog();
        restClient.showApiErrorMessage(message);
    }

    @Override
    public void onLoginAs(LoginMOdel.ValueBean valueBean) {
        makeLogin(valueBean);
    }
}
