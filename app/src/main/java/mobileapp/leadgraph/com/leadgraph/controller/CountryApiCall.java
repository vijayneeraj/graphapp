package mobileapp.leadgraph.com.leadgraph.controller;

import android.content.Context;

import com.google.gson.Gson;

import mobileapp.leadgraph.com.leadgraph.models.CityModel;
import mobileapp.leadgraph.com.leadgraph.models.StateModel;
import mobileapp.leadgraph.com.leadgraph.rest.ApiErrorMessages;
import mobileapp.leadgraph.com.leadgraph.rest.ApiUrls;
import mobileapp.leadgraph.com.leadgraph.rest.ParamName;
import mobileapp.leadgraph.com.leadgraph.rest.RestAdapter;
import mobileapp.leadgraph.com.leadgraph.rest.RestClient;


/**
 * @author neeraj on 10/12/18.
 */
public class CountryApiCall implements RestClient.ApiListeners {
    Context context;
    RestClient restClient;
    public static final String TOKENTERA = "teramatrix";
    StateListener stateListener;
    CityListener cityListener;

    public CountryApiCall(Context context) {
        this.context = context;
        restClient = new RestClient(context, this);
    }

    public void callStates(StateListener stateListener, String country_id) {
        this.stateListener = stateListener;
        restClient.callApi(ApiUrls.GETSTATE_ID, RestAdapter.getAdapter().getState(ParamName.APIVERSION, "BindState", "1"));
    }

    public void callCities(CityListener cityListener, String state_id) {
        this.cityListener = cityListener;
        restClient.callApi(ApiUrls.GETCITY_ID, RestAdapter.getAdapter().getCity(ParamName.APIVERSION, "BindCity", state_id));
    }

    @Override
    public void onResponseSucess(String response, int apiId) {
        restClient.dismissLoadingDialog();
        if (apiId == ApiUrls.GETSTATE_ID) {
            if (response != null) {
                try {
                    StateModel stateModel = new Gson().fromJson(response, StateModel.class);
                    if (stateModel != null) {
                        if (stateModel.isValid()) {
                            if (stateListener != null) {
                                stateListener.allStates(stateModel);
                            }
                        } else {
                            restClient.showApiErrorMessage(stateModel.getDescription());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            }
        } else if (apiId == ApiUrls.GETCITY_ID) {
            if (response != null) {
                try {
                    CityModel cityModel = new Gson().fromJson(response, CityModel.class);
                    if (cityModel != null) {
                        if (cityListener != null) {
                            cityListener.allCities(cityModel);
                        }
                    } else {
                        restClient.showApiErrorMessage(cityModel.getDescription());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            }
        }
    }

    @Override
    public void onFailure(String message) {
        restClient.dismissLoadingDialog();
        restClient.showApiErrorMessage(message);
    }

    public interface StateListener {
        void allStates(StateModel stateModel);
    }

    public interface CityListener {
        void allCities(CityModel cityModel);
    }
}
