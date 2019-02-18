package mobileapp.leadgraph.com.leadgraph.controller;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import mobileapp.leadgraph.com.leadgraph.models.DeleteStaffModel;
import mobileapp.leadgraph.com.leadgraph.models.InsertStaffModel;
import mobileapp.leadgraph.com.leadgraph.models.StaffListModel;
import mobileapp.leadgraph.com.leadgraph.models.UpdateStaffModel;
import mobileapp.leadgraph.com.leadgraph.rest.ApiErrorMessages;
import mobileapp.leadgraph.com.leadgraph.rest.ApiUrls;
import mobileapp.leadgraph.com.leadgraph.rest.RestAdapter;
import mobileapp.leadgraph.com.leadgraph.rest.RestClient;

/**
 * @author neeraj on 8/1/19.
 */
public class ChannelStaffController implements RestClient.ApiListeners {
    private Context context;
    private RestClient restClient;
    StaffControllerListner staffControllerListner;

    public void setStaffControllerListner(StaffControllerListner staffControllerListner) {
        this.staffControllerListner = staffControllerListner;
    }

    public ChannelStaffController(Context context) {
        this.context = context;
        restClient = new RestClient(context, this);
    }

    public void insertStaff(String pcomp, String user_name, String dsg_id, String join_date, String city_id, String contact,
                            String ms, String dob, String bg, String pan, String pic, String aadhar, String add_by, String email, boolean isactive) {
        restClient.callApi(ApiUrls.INSERT_STAFF_ID, RestAdapter.getAdapter().insertStaff(pcomp, user_name, dsg_id,
                join_date, city_id, contact
                , ms, dob, bg, pic, pan, aadhar, add_by, email, isactive));
    }

    public void deleteStaff(String user_id) {
        restClient.callApi(ApiUrls.STAFF_DELETE_ID, RestAdapter.getAdapter().deleteStaff(user_id));
    }

    public void getStaffList(String user_name, String pcomp, String user_mobile, String user_email) {
        restClient.callApi(ApiUrls.STAFFLIST_ID, RestAdapter.getAdapter().getStaffList(user_name, "",
                "",
                user_mobile, user_email, pcomp));
    }
    public void updateStaff(String pcomp, String user_name, String dsg_id, String join_date, String city_id, String contact,
                            String ms, String dob, String bg, String pan, String pic, String aadhar, String add_by, String email, boolean isactive,String user_id){
        restClient.callApi(ApiUrls.UPDATE_STAFF_ID, RestAdapter.getAdapter().updateStaff(pcomp, user_name, dsg_id,
                join_date, city_id, contact
                , ms, dob, bg, pic, pan, aadhar, add_by, email, isactive,user_id));
    }

    @Override
    public void onResponseSucess(String response, int apiId) {
        restClient.dismissLoadingDialog();
        if (response != null) {
            if (apiId == ApiUrls.INSERT_STAFF_ID) {
                try {
                    InsertStaffModel insertStaffModel = new Gson().fromJson(response, InsertStaffModel.class);
                    if (insertStaffModel.isValid()) {
                        if (insertStaffModel.getValue().get(0).getStatusCode() != null && insertStaffModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                            restClient.showApiErrorMessage(insertStaffModel.getValue().get(0).getMsg());
                        } else {
                            restClient.showApiErrorMessage(insertStaffModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(insertStaffModel.getDescription());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            } else if (apiId == ApiUrls.STAFFLIST_ID) {
                try {
                    StaffListModel staffListModel = new Gson().fromJson(response, StaffListModel.class);
                    if (staffListModel.isValid()) {
                        if (staffListModel.getValue().size()>0){
                            if (staffControllerListner != null) {
                                staffControllerListner.staffList(staffListModel.getValue());
                            }
                        }else {
                            restClient.showApiErrorMessage(staffListModel.getDescription());

                        }

                    } else {
                        restClient.showApiErrorMessage(staffListModel.getDescription());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            } else if (apiId == ApiUrls.STAFF_DELETE_ID) {
                try {
                    DeleteStaffModel deleteStaffModel = new Gson().fromJson(response, DeleteStaffModel.class);
                    if (deleteStaffModel.isValid()) {
                        if (deleteStaffModel.getValue().get(0).getStatusCode() != null &&
                                deleteStaffModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                            restClient.showApiErrorMessage(deleteStaffModel.getValue().get(0).getMsg());
                        }
                    } else {
                        restClient.showApiErrorMessage(deleteStaffModel.getDescription());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }
            }else if (apiId==ApiUrls.UPDATE_STAFF_ID){
                try {
                    UpdateStaffModel updateStaffModel=new Gson().fromJson(response,UpdateStaffModel.class);
                    if (updateStaffModel.isValid()){
                        if (updateStaffModel.getValue().get(0).getStatusCode()!=null &&
                                updateStaffModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")){
                            restClient.showApiErrorMessage(updateStaffModel.getValue().get(0).getMsg());
                        }else {
                            restClient.showApiErrorMessage(updateStaffModel.getDescription());
                        }
                    }else {
                        restClient.showApiErrorMessage(updateStaffModel.getDescription());
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

    public interface StaffControllerListner {
        void staffList(List<StaffListModel.ValueBean> valueBeans);
    }
}
