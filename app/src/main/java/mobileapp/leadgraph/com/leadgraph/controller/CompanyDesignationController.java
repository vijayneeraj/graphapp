package mobileapp.leadgraph.com.leadgraph.controller;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import mobileapp.leadgraph.com.leadgraph.models.DeleteDesignation;
import mobileapp.leadgraph.com.leadgraph.models.DesignationListModel;
import mobileapp.leadgraph.com.leadgraph.models.InsertDesgination;
import mobileapp.leadgraph.com.leadgraph.models.UpdateDesignation;
import mobileapp.leadgraph.com.leadgraph.rest.ApiErrorMessages;
import mobileapp.leadgraph.com.leadgraph.rest.ApiUrls;
import mobileapp.leadgraph.com.leadgraph.rest.ParamName;
import mobileapp.leadgraph.com.leadgraph.rest.RestAdapter;
import mobileapp.leadgraph.com.leadgraph.rest.RestClient;

/**
 * @author neeraj on 7/1/19.
 */
public class CompanyDesignationController implements RestClient.ApiListeners {
    private Context context;
    RestClient restClient;
    DesignationListener designationListener;


    public void setDesignationListener(DesignationListener designationListener) {
        this.designationListener = designationListener;
    }

    public CompanyDesignationController(Context context) {
        this.context = context;
        restClient = new RestClient(context, this);
    }

    public void insertDesignation(String dsgname, String p_comp_id, String login_user_id, boolean isactive, boolean isadmin) {
        restClient.callApi(ApiUrls.INSERT_DESIGNATION_ID, RestAdapter.getAdapter().insertDesignation("InsertDesignation",
                ParamName.APIVERSION, p_comp_id, dsgname, login_user_id, isactive, isadmin));
    }

    public void getDesignationList(String pcomp_id, String dsg_name) {
        restClient.callApi(ApiUrls.DESIGNATIONLIST_ID, RestAdapter.getAdapter().getDesignationList(dsg_name, pcomp_id, "", ""));
    }
    public void updateDesignation(String dsgname, String p_comp_id, String login_user_id, boolean isactive, boolean isadmin,String dsg_id) {
        restClient.callApi(ApiUrls.UPDATE_DESIGNATION_ID, RestAdapter.getAdapter().updateDesignation("UpDesignation",
                ParamName.APIVERSION, p_comp_id, dsgname, login_user_id, isactive, isadmin,dsg_id));

    }
    public void deleteDesignation(String dsg_id){
        restClient.callApi(ApiUrls.DELETE_DESIGNATION_ID,RestAdapter.getAdapter().deleteDesignation(dsg_id));
    }
    @Override
    public void onResponseSucess(String response, int apiId) {
        restClient.dismissLoadingDialog();
        if (response != null) {
            if (apiId == ApiUrls.INSERT_DESIGNATION_ID) {
                try {
                    InsertDesgination insertDesgination = new Gson().fromJson(response, InsertDesgination.class);
                    if (insertDesgination.isValid()) {
                        if (insertDesgination.getValue().get(0).getStatusCode() != null && insertDesgination.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                            restClient.showApiErrorMessage(insertDesgination.getValue().get(0).getMsg());
                        }
                    } else {
                        restClient.showApiErrorMessage(insertDesgination.getDescription());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            } else if (apiId == ApiUrls.DESIGNATIONLIST_ID) {
                try {
                    DesignationListModel designationListModel = new Gson().fromJson(response, DesignationListModel.class);
                    if (designationListModel.isValid()) {
                        if (designationListModel.getValue().size() > 0) {
                                      if (designationListener!=null){
                                          designationListener.designatioList(designationListModel.getValue());
                                      }
                        }
                    } else {
                        restClient.showApiErrorMessage(designationListModel.getDescription());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            }else if (apiId==ApiUrls.UPDATE_DESIGNATION_ID){
                try {
                    UpdateDesignation updateDesignation=new Gson().fromJson(response,UpdateDesignation.class);
                    if (updateDesignation.isValid()){
                          if (updateDesignation.getValue().get(0).getStatusCode()!=null && updateDesignation.getValue().get(0).getStatusCode().equalsIgnoreCase("200")){
                              restClient.showApiErrorMessage(updateDesignation.getValue().get(0).getMsg());
                          }
                    }else {
                        restClient.showApiErrorMessage(updateDesignation.getDescription());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            }else if (apiId==ApiUrls.DELETE_DESIGNATION_ID){
                try {
                    DeleteDesignation deleteDesignation=new Gson().fromJson(response,DeleteDesignation.class);
                    if (deleteDesignation.isValid()){
                        if (deleteDesignation.getValue().get(0).getStatusCode()!=null && deleteDesignation.getValue().get(0).getStatusCode().equalsIgnoreCase("200")){
                            restClient.showApiErrorMessage(deleteDesignation.getValue().get(0).getMsg());
                        }
                    }else {
                        restClient.showApiErrorMessage(deleteDesignation.getDescription());
                    }
                }catch (Exception e){
                    e.printStackTrace();
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

    public interface DesignationListener {
        void designatioList(List<DesignationListModel.ValueBean> valueBeans);
    }
}
