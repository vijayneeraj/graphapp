package mobileapp.leadgraph.com.leadgraph.controller;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import mobileapp.leadgraph.com.leadgraph.models.DeleteLeadModel;
import mobileapp.leadgraph.com.leadgraph.models.InsertLeadModel;
import mobileapp.leadgraph.com.leadgraph.models.LeadListModel;
import mobileapp.leadgraph.com.leadgraph.models.UpdateLeadModel;
import mobileapp.leadgraph.com.leadgraph.rest.ApiErrorMessages;
import mobileapp.leadgraph.com.leadgraph.rest.ApiUrls;
import mobileapp.leadgraph.com.leadgraph.rest.RestAdapter;
import mobileapp.leadgraph.com.leadgraph.rest.RestClient;

/**
 * @author neeraj on 18/1/19.
 */
public class LeadLockController implements RestClient.ApiListeners {
    private Context context;
    private RestClient restClient;
    private LeadLockListners leadLockListners;

    public void setLeadLockListners(LeadLockListners leadLockListners) {
        this.leadLockListners = leadLockListners;
    }

    public LeadLockController(Context context) {
        this.context = context;
        restClient = new RestClient(context, this);

    }

    public void insertLead(String pcomp, String comp_name, String stff_id, String pros_name, String con_number, String alt_con_number,
                           String prod_id, String price, String lang_id, String lead_type, String lead_status, String next_date,
                           String case_no, String date, String state, String city, String demo_date, String demo_time,
                           String remarks, String assignedToSelf, String uid) {
        restClient.callApi(ApiUrls.INSERT_LEAD_ID, RestAdapter.getAdapter().insertLead(stff_id, case_no, date, comp_name, pcomp, pros_name, con_number, alt_con_number
                , city, prod_id, price, demo_date, demo_time, lang_id, lead_type, remarks, lead_status, next_date, assignedToSelf, uid));
    }

    public void leadList(String pcomp, String date_from, String to_date) {
        restClient.callApi(ApiUrls.LEAD_LIST_ID, RestAdapter.getAdapter().getLeadsList(pcomp, date_from, to_date));
    }

    public void updateLead(String pcomp, String comp_name, String stff_id, String pros_name, String con_number, String alt_con_number,
                           String prod_id, String price, String lang_id, String lead_type, String lead_status, String next_date,
                           String case_no, String date, String state, String city, String demo_date, String demo_time,
                           String remarks, String assignedToSelf, String uid, String cid) {
        restClient.callApi(ApiUrls.LEAD_UPDATE_ID, RestAdapter.getAdapter().updateLead(stff_id, case_no, date, comp_name, pcomp, pros_name, con_number, alt_con_number
                , city, prod_id, price, demo_date, demo_time, lang_id, lead_type, remarks, lead_status, next_date, assignedToSelf, uid, cid));
    }

    public void deleteLead(String cid) {
        restClient.callApi(ApiUrls.DELETELEAD_ID, RestAdapter.getAdapter().deleteLead(cid));
    }

    @Override
    public void onResponseSucess(String response, int apiId) {
        restClient.dismissLoadingDialog();
        if (response != null) {
            if (apiId == ApiUrls.INSERT_LEAD_ID) {
                try {
                    InsertLeadModel insertLeadModel = new Gson().fromJson(response, InsertLeadModel.class);
                    if (insertLeadModel != null) {
                        if (insertLeadModel.isValid()) {
                            if (insertLeadModel.getValue().get(0).getStatusCode() != null &&
                                    insertLeadModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                                restClient.showApiErrorMessage(insertLeadModel.getDescription());
                            } else {
                                restClient.showApiErrorMessage(insertLeadModel.getDescription());
                            }
                        } else {
                            restClient.showApiErrorMessage(insertLeadModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                }

            } else if (apiId == ApiUrls.LEAD_LIST_ID) {
                try {
                    LeadListModel leadListModel = new Gson().fromJson(response, LeadListModel.class);
                    if (leadListModel != null) {
                        if (leadListModel.isValid()) {
                            if (leadLockListners != null) {
                                leadLockListners.leadList(leadListModel.getValue());
                            }
                        } else {
                            restClient.showApiErrorMessage(leadListModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                }

            } else if (apiId == ApiUrls.LEAD_UPDATE_ID) {
                 try {
                     UpdateLeadModel updateLeadModel = new Gson().fromJson(response, UpdateLeadModel.class);
                     if (updateLeadModel != null) {
                         if (updateLeadModel.isValid()) {
                             if (updateLeadModel.getValue().get(0).getStatusCode() != null &&
                                     updateLeadModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                                 restClient.showApiErrorMessage(updateLeadModel.getDescription());
                             } else {
                                 restClient.showApiErrorMessage(updateLeadModel.getDescription());
                             }
                         } else {
                             restClient.showApiErrorMessage(updateLeadModel.getDescription());
                         }
                     } else {
                         restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                     }
                 }catch (Exception e){
                     e.printStackTrace();
                     restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                 }

            }if (apiId==ApiUrls.DELETELEAD_ID){
                try {
                    DeleteLeadModel deleteLeadModel = new Gson().fromJson(response, DeleteLeadModel.class);
                    if (deleteLeadModel != null) {
                        if (deleteLeadModel.isValid()) {
                            if (deleteLeadModel.getValue().get(0).getStatusCode() != null &&
                                    deleteLeadModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                                restClient.showApiErrorMessage(deleteLeadModel.getDescription());
                                if (leadLockListners!=null){
                                    leadLockListners.deleteLead();
                                }
                            } else {
                                restClient.showApiErrorMessage(deleteLeadModel.getDescription());
                            }
                        } else {
                            restClient.showApiErrorMessage(deleteLeadModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
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

    public interface LeadLockListners {
        void leadList(List<LeadListModel.ValueBean> leadList);
        void deleteLead();
    }
}
