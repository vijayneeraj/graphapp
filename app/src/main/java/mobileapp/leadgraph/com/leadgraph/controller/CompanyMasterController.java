package mobileapp.leadgraph.com.leadgraph.controller;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import mobileapp.leadgraph.com.leadgraph.models.AllowModel;
import mobileapp.leadgraph.com.leadgraph.models.CompanyDeleteModel;
import mobileapp.leadgraph.com.leadgraph.models.CompanyDropDown;
import mobileapp.leadgraph.com.leadgraph.models.CompanyInsertModel;
import mobileapp.leadgraph.com.leadgraph.models.CompanyListModel;
import mobileapp.leadgraph.com.leadgraph.models.UpdateCompanyModel;
import mobileapp.leadgraph.com.leadgraph.rest.ApiErrorMessages;
import mobileapp.leadgraph.com.leadgraph.rest.ApiUrls;
import mobileapp.leadgraph.com.leadgraph.rest.ParamName;
import mobileapp.leadgraph.com.leadgraph.rest.RestAdapter;
import mobileapp.leadgraph.com.leadgraph.rest.RestClient;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;

/**
 * @author neeraj on 28/12/18.
 */
public class CompanyMasterController implements RestClient.ApiListeners {
    private Context context;
    private RestClient restClient;
    private CompanyMasterListeners companyMasterListeners = null;
    CompanyDropDownListner companyDropDownListner;

    public void setCompanyDropDownListner(CompanyDropDownListner companyDropDownListner) {
        this.companyDropDownListner = companyDropDownListner;
    }

    public RestClient getRestClient() {
        return restClient;
    }

    /**
     * @param context                used for rest client
     * @param companyMasterListeners listener for api response call back
     */

    public CompanyMasterController(Context context, CompanyMasterListeners companyMasterListeners) {
        this.context = context;
        restClient = new RestClient(context, this);
        this.companyMasterListeners = companyMasterListeners;
    }

    public CompanyMasterController(Context context) {
        this.context = context;
        restClient = new RestClient(context, this);
    }

    public void checkCompanyAllow() {
        restClient.callApi(ApiUrls.ALLOWCOMPANY_ID, RestAdapter.getAdapter().getAllowCompany(ParamName.APIVERSION, SPUtils.getString(context, SPUtils.COMPANY_USER_ID)));

    }

    public void deleteCompany(String comp_id) {
        restClient.callApi(ApiUrls.DELETE_COMPANY_ID, RestAdapter.getAdapter().deleteCompany(comp_id));
    }

    public void addCompany(String ver, String actn, String comp_name, String parent_id, String o_name, String address, String area,
                           String pin, String o_email, String o_phone, String cp_name, String cp_email, String cp_phone, String allowComp, String allowStraff, String uid, boolean isactive, String gst,
                           String cityID) {
        restClient.callApi(ApiUrls.INSERTCOMPANY_ID, RestAdapter.getAdapter().addCompany(ver, actn, parent_id, comp_name, gst, o_name, o_email,
                o_phone, cityID, address, area, pin, cp_name, cp_email, cp_phone, allowComp, allowStraff, isactive, uid));

    }

    public void getCompanyList(String comp_name, String gst, String state_id,
                               String city_id, String pin_code, String o_name, String o_email, String c_mobile, String comp_id) {
        restClient.callApi(ApiUrls.COMPANYLIST_ID, RestAdapter.getAdapter().getCompanyList(comp_name, gst, state_id, city_id,
                pin_code, o_name, o_email, c_mobile, comp_id));
    }

    public void getComapnyDropDown(String comp_id) {
        restClient.callApi(ApiUrls.COMPANYDROPDOWN_ID, RestAdapter.getAdapter().comapniesDropdown(comp_id, ParamName.APIVERSION));
    }

    /**
     * actn=UpCompany
     */
    public void updateCompany(String ver, String actn, String comp_name, String parent_id, String o_name, String address, String area,
                              String pin, String o_email, String o_phone, String cp_name, String cp_email, String cp_phone, String allowComp, String allowStraff, String uid, boolean isactive, String gst,
                              String cityID, String comp_id) {
        restClient.callApi(ApiUrls.UPDATE_COMPANY_ID, RestAdapter.getAdapter().updateCompany(ver, actn, parent_id, comp_name, gst, o_name, o_email,
                o_phone, cityID, address, area, pin, cp_name, cp_email, cp_phone, allowComp, allowStraff, isactive, uid, comp_id));

    }

    @Override
    public void onResponseSucess(String response, int apiId) {
        restClient.dismissLoadingDialog();
        if (response == null) {
            restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            return;
        }
        if (apiId == ApiUrls.ALLOWCOMPANY_ID) {
           try {
               AllowModel allowModel = new Gson().fromJson(response, AllowModel.class);
               if (allowModel != null) {
                   if (allowModel.isValid()) {
                       int allow = Integer.parseInt(allowModel.getValue().get(0).getAllowed_Company());
                       int created = Integer.parseInt(allowModel.getValue().get(0).getCompanyCreated());
                       if (created < allow) {
                           if (companyMasterListeners != null) {
                               companyMasterListeners.companyAllowed(true);
                           }
                       } else {
                           if (companyMasterListeners != null) {
                               companyMasterListeners.companyAllowed(false);
                           }
                       }
                   } else {
                       restClient.showApiErrorMessage(allowModel.getDescription());
                   }
               } else {
                   restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
               }
           }catch (Exception e){
               e.printStackTrace();
               restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
           }
        } else if (apiId == ApiUrls.INSERTCOMPANY_ID) {
            if (response != null) {
                try {
                    CompanyInsertModel companyInsertModel = new Gson().fromJson(response, CompanyInsertModel.class);
                    if (companyInsertModel != null) {
                        if (companyInsertModel.isValid()) {
                            if (companyInsertModel.getValue().get(0).getStatusCode() != null
                                    && companyInsertModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                                restClient.showApiErrorMessage(companyInsertModel.getDescription());
                                //company added sucessfully
                                if (companyMasterListeners != null) {
                                    companyMasterListeners.companyAddedSucessFully();
                                }
                            } else {
                                restClient.showApiErrorMessage(companyInsertModel.getDescription());
                            }
                        } else {
                            restClient.showApiErrorMessage(companyInsertModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                    }
                } catch (Exception e) {
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }

            } else {
                restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            }
        } else if (apiId == ApiUrls.COMPANYLIST_ID) {
            if (response != null) {
                try {
                    CompanyListModel companyListModel = new Gson().fromJson(response, CompanyListModel.class);
                    if (companyListModel != null) {
                        if (companyListModel.isValid()) {
                            if (companyListModel.getValue() != null) {
                                companyMasterListeners.companyList(companyListModel.getValue());
                            }
                        } else {
                            restClient.showApiErrorMessage(companyListModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                    }
                } catch (Exception e) {
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }

            } else {
                restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            }
        } else if (apiId == ApiUrls.DELETE_COMPANY_ID) {
            if (response != null) {
                try {
                    CompanyDeleteModel companyDeleteModel = new Gson().fromJson(response, CompanyDeleteModel.class);
                    if (companyDeleteModel != null) {
                        if (companyDeleteModel.isValid()) {
                            if (companyDeleteModel.getValue() != null) {
                                restClient.showApiErrorMessage(companyDeleteModel.getValue().get(0).getMsg());
                                if (companyMasterListeners != null) {
                                    companyMasterListeners.onDeleted();
                                }
                            }
                        } else {
                            restClient.showApiErrorMessage(companyDeleteModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                    }
                } catch (Exception e) {
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }

            } else {
                restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            }
        } else if (apiId == ApiUrls.UPDATE_COMPANY_ID) {
            if (response != null) {
                try {
                    UpdateCompanyModel updateCompanyModel = new Gson().fromJson(response, UpdateCompanyModel.class);
                    if (updateCompanyModel != null) {
                        if (updateCompanyModel.isValid()) {
                            if (updateCompanyModel.getValue() != null) {
                                restClient.showApiErrorMessage(updateCompanyModel.getValue().get(0).getMsg());
                            }
                        } else {
                            restClient.showApiErrorMessage(updateCompanyModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                    }
                } catch (Exception e) {
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }

            } else {
                restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            }
        } else if (apiId == ApiUrls.COMPANYDROPDOWN_ID) {
            if (response != null) {
                try {
                    CompanyDropDown companyDropDown = new Gson().fromJson(response, CompanyDropDown.class);
                    if (companyDropDown != null) {
                        if (companyDropDown.isValid()) {
                            if (companyDropDown.getValue() != null) {
                                if (companyDropDownListner != null) {
                                    companyDropDownListner.dropDownList(companyDropDown.getValue());
                                }
                            }
                        } else {
                            restClient.showApiErrorMessage(companyDropDown.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                    }
                } catch (Exception e) {
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }

            } else {
                restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            }
        }
    }

    @Override
    public void onFailure(String message) {
        restClient.dismissLoadingDialog();
        restClient.showApiErrorMessage(message);
    }

    public interface CompanyMasterListeners {
        void companyAllowed(boolean v);
        void companyAddedSucessFully();
        void companyList(List<CompanyListModel.ValueBean> compList);
        void onDeleted();
    }

    public interface CompanyDropDownListner {
        void dropDownList(List<CompanyDropDown.ValueBean> valueBeans);
    }
}
