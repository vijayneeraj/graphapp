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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mobileapp.leadgraph.com.leadgraph.MainActivity;
import mobileapp.leadgraph.com.leadgraph.R;
import mobileapp.leadgraph.com.leadgraph.adapters.CategoryListAdapter;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyMasterController;
import mobileapp.leadgraph.com.leadgraph.dialog.ItemDialog;
import mobileapp.leadgraph.com.leadgraph.models.AddMasterCategoryModel;
import mobileapp.leadgraph.com.leadgraph.models.CategoryDeleteModel;
import mobileapp.leadgraph.com.leadgraph.models.CategoryListModel;
import mobileapp.leadgraph.com.leadgraph.models.CompanyDropDown;
import mobileapp.leadgraph.com.leadgraph.models.CompanyListModel;
import mobileapp.leadgraph.com.leadgraph.models.UpdateCategoryModel;
import mobileapp.leadgraph.com.leadgraph.rest.ApiErrorMessages;
import mobileapp.leadgraph.com.leadgraph.rest.ApiUrls;
import mobileapp.leadgraph.com.leadgraph.rest.ParamName;
import mobileapp.leadgraph.com.leadgraph.rest.RestAdapter;
import mobileapp.leadgraph.com.leadgraph.rest.RestClient;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;
import okhttp3.Call;

/**
 * @author neeraj on 6/12/18.
 */
public class MasterProductCategoryFragment extends Fragment implements View.OnClickListener, RestClient.ApiListeners, CompanyMasterController.CompanyDropDownListner, ItemDialog.SelectionListener, CategoryListAdapter.CompanyActionListener {
    View view;
    ImageView serach_btn, add_btn;
    CardView add_card;
    TextView save_btn, search_button, company_spin, cat_list_companyname;
    Switch switch_k;
    EditText category_name, cat_list_catname;
    RestClient restClient;
    String cat_name;
    boolean isActive;
    RecyclerView recyclerView;
    CompanyMasterController companyMasterController;
    List<CompanyDropDown.ValueBean> companiesList = new ArrayList<>();
    List<String> companiesListString = new ArrayList<>();
    String st_parent;
    List<CategoryListModel.ValueBean> companyCategories = new ArrayList<>();
    CategoryListAdapter categoryListAdapter;
    int position = -1;
    ImageView show_filter;
    LinearLayout lin_filters, search_card;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_product_category, container, false);
        }
        initViews();
        return view;
    }

    private void initViews() {
        restClient = new RestClient(getActivity(), this);
        companyMasterController = new CompanyMasterController(getActivity());
        companyMasterController.setCompanyDropDownListner(this);
        serach_btn = view.findViewById(R.id.serach_btn);
        add_card = view.findViewById(R.id.add_card);
        search_card = view.findViewById(R.id.search_card);
        add_btn = view.findViewById(R.id.add_btn);
        switch_k = view.findViewById(R.id.switch_k);
        company_spin = view.findViewById(R.id.company_spin);
        category_name = view.findViewById(R.id.category_name);
        add_btn.setOnClickListener(this);
        serach_btn.setOnClickListener(this);
        save_btn = view.findViewById(R.id.save_btn);
        search_button = view.findViewById(R.id.search_button);
        save_btn.setOnClickListener(this);
        search_button.setOnClickListener(this);
        cat_list_companyname = view.findViewById(R.id.cat_list_companyname);
        cat_list_companyname.setOnClickListener(this);
        cat_list_catname = view.findViewById(R.id.cat_list_catname);
        cat_list_companyname.setText(SPUtils.getString(getActivity(), SPUtils.COMPANY_NAME));
        company_spin.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.list_search);
        categoryListAdapter = new CategoryListAdapter(getActivity(), companyCategories);
        categoryListAdapter.setCompanyActionListener(this);
        recyclerView.setAdapter(categoryListAdapter);
        lin_filters = view.findViewById(R.id.lin_filters);
        show_filter = view.findViewById(R.id.show_filter);
        show_filter.setOnClickListener(this);
        switch_k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isActive = b;
            }
        });
        companyMasterController.getComapnyDropDown(SPUtils.getString(getActivity(), SPUtils.COMPANY_USER_ID));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setSelected(true, false, false, false, false, false, false, false, false, false);
        ((MainActivity) getActivity()).setBottomNavigation(false, false, false);

        ((MainActivity) getActivity()).setTitle("Product");
    }

    private boolean isvalidate() {
        cat_name = category_name.getText().toString();
        if (cat_name.isEmpty()) {
            category_name.setError("Category Name Required!");
            return false;
        } else if (st_parent == null || st_parent.isEmpty()) {
            company_spin.setError("select company!");
            return false;
        }
        return true;
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
            case R.id.save_btn:
                if (isvalidate() && save_btn.getText().toString().equalsIgnoreCase("save")) {
                    addCategory();
                } else if (isvalidate() && save_btn.getText().toString().equalsIgnoreCase("update")) {
                    //update category here
                    updateCategory((String) save_btn.getTag());
                }
                break;
            case R.id.company_spin:
                ItemDialog itemDialog = new ItemDialog(getActivity(), "Select", "pcomp", companiesListString);
                itemDialog.setSelectionListener(this);
                itemDialog.show();
                break;
            case R.id.cat_list_companyname:
                ItemDialog itemDialog1 = new ItemDialog(getActivity(), "Select", "pcomp", companiesListString);
                itemDialog1.setSelectionListener(this);
                itemDialog1.show();
                break;
            case R.id.search_button:
                //todo call list api for category here
                callCategoryListAPi(cat_list_catname.getText().toString(), st_parent);
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
        ((MainActivity) getActivity()).didTapButton(view);
    }

    private void addCategory() {
        restClient.callApi(ApiUrls.CATEGORYADD_ID, RestAdapter.getAdapter().getTaskList(ParamName.APIVERSION, "InsertCategory",
                st_parent, cat_name, String.valueOf(isActive), SPUtils.getString(getActivity(), SPUtils.USER_ID)));
    }

    private void callCategoryListAPi(String catName, String pComp) {
        restClient.callApi(ApiUrls.CATEGORY_LIST_ID, RestAdapter.getAdapter().getCategoryList(pComp, catName));
    }

    private void deleteCategory(String cat_id) {
        restClient.callApi(ApiUrls.CATEGORY_DELETE_ID, RestAdapter.getAdapter().deleteCategory(cat_id));
    }

    private void updateCategory(String cat_id) {
        restClient.callApi(ApiUrls.UPDATE_CATEGORY_ID, RestAdapter.getAdapter().updateCategory(st_parent, cat_name, isActive,
                SPUtils.getString(getActivity(), SPUtils.USER_ID), cat_id));
    }

    @Override
    public void onResponseSucess(String response, int apiId) {
        restClient.dismissLoadingDialog();
        if (apiId == ApiUrls.CATEGORYADD_ID) {
            if (response != null) {
                try {
                    AddMasterCategoryModel addMasterCategoryModel = new Gson().fromJson(response, AddMasterCategoryModel.class);
                    if (addMasterCategoryModel != null) {
                        if (addMasterCategoryModel.isValid()) {
                            if (addMasterCategoryModel.getValue().get(0).getStatusCode() != null) {
                                //added done clear box here
                                category_name.setText("");
                                restClient.showApiErrorMessage(addMasterCategoryModel.getDescription());
                            } else {
                                restClient.showApiErrorMessage(addMasterCategoryModel.getDescription());

                            }
                        } else {
                            restClient.showApiErrorMessage(addMasterCategoryModel.getDescription());

                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }

            } else {
                restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            }
        } else if (apiId == ApiUrls.CATEGORY_LIST_ID) {
            if (response != null) {
                try {
                    CategoryListModel categoryListModel = new Gson().fromJson(response, CategoryListModel.class);
                    if (categoryListModel != null) {
                        if (categoryListModel.isValid()) {
                            //added done clear box here
                            companyCategories.clear();
                            companyCategories.addAll(categoryListModel.getValue());
                            categoryListAdapter.notifyDataSetChanged();
                        } else {
                            restClient.showApiErrorMessage(categoryListModel.getDescription());

                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }

            } else {
                restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            }
        } else if (apiId == ApiUrls.CATEGORY_DELETE_ID) {
            if (response != null) {
                try {
                    CategoryDeleteModel categoryDeleteModel = new Gson().fromJson(response, CategoryDeleteModel.class);
                    if (categoryDeleteModel != null) {
                        if (categoryDeleteModel.isValid()) {
                            if (categoryDeleteModel.getValue().size() > 0) {
                                //added done clear box here
                                if (categoryDeleteModel.getValue().get(0).getStatusCode() != null &&
                                        categoryDeleteModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                                    companyCategories.remove(position);
                                    categoryListAdapter.notifyDataSetChanged();
                                    restClient.showApiErrorMessage(categoryDeleteModel.getDescription());
                                } else {
                                    restClient.showApiErrorMessage(categoryDeleteModel.getDescription());
                                }
                            } else {
                                restClient.showApiErrorMessage(categoryDeleteModel.getDescription());

                            }
                        } else {
                            restClient.showApiErrorMessage(categoryDeleteModel.getDescription());

                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                }

            } else {
                restClient.showApiErrorMessage(ApiErrorMessages.NOTVALID);
            }
        } else if (apiId == ApiUrls.UPDATE_CATEGORY_ID) {
            if (response != null) {
                try {
                    UpdateCategoryModel updateCategoryModel = new Gson().fromJson(response, UpdateCategoryModel.class);
                    if (updateCategoryModel != null) {
                        if (updateCategoryModel.isValid()) {
                            if (updateCategoryModel.getValue().size() > 0) {
                                //added done clear box here
                                if (updateCategoryModel.getValue().get(0).getStatusCode() != null &&
                                        updateCategoryModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                                    restClient.showApiErrorMessage(updateCategoryModel.getDescription());
                                    clearUi();
                                } else {
                                    restClient.showApiErrorMessage(updateCategoryModel.getDescription());
                                }
                            } else {
                                restClient.showApiErrorMessage(updateCategoryModel.getDescription());
                            }
                        } else {
                            restClient.showApiErrorMessage(updateCategoryModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                    }
                }catch (Exception e){
                    e.printStackTrace();
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

    @Override
    public void dropDownList(List<CompanyDropDown.ValueBean> valueBeans) {
        companiesList.clear();
        companiesList.addAll(valueBeans);
        companiesListString.clear();
        for (CompanyDropDown.ValueBean valueBean : valueBeans
                ) {
            companiesListString.add(valueBean.getCompany_Name());
        }
        if (this.companiesList.size() == 1) {
            //set parent company diffault
            onItemSelect(companiesList.get(0).getCompany_Name(), "pcomp", 0);
        }
    }

    @Override
    public void onItemSelect(String item, String tag, int position) {
        if (tag.equalsIgnoreCase("pcomp")) {
            company_spin.setText(item);
            cat_list_companyname.setText(item);
            if (position==-1){
                st_parent = "";
            }else {
                st_parent = companiesList.get(position).getCompanyUser_Idno();
            }
        }
    }

    private void clearUi() {
        category_name.setText("");
        switch_k.setChecked(false);
        save_btn.setText("Save");
    }

    private void setUi(CategoryListModel.ValueBean ui) {
        category_name.setText(ui.getCategory_Name());
        company_spin.setText(ui.getCompany_Name());
        st_parent = ui.getCompanyUser_Idno();
        if (ui.getIs_Active().equalsIgnoreCase("1")) {
            switch_k.setChecked(true);
        } else {
            switch_k.setChecked(false);
        }
        save_btn.setText("UPDATE");
        save_btn.setTag(ui.getCategory_Idno());
    }

    @Override
    public void edit(int position) {
        add_card.setVisibility(View.VISIBLE);
        search_card.setVisibility(View.GONE);
        setUi(companyCategories.get(position));
    }

    @Override
    public void delete(int position) {
        //call delete api here
        this.position = position;
        deleteCategory(companyCategories.get(position).getCategory_Idno());
    }
}
