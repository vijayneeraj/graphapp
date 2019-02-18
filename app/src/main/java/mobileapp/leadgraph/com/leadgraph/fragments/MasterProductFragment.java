package mobileapp.leadgraph.com.leadgraph.fragments;

import android.app.DatePickerDialog;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mobileapp.leadgraph.com.leadgraph.MainActivity;
import mobileapp.leadgraph.com.leadgraph.R;
import mobileapp.leadgraph.com.leadgraph.adapters.ProductListAdapter;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyMasterController;
import mobileapp.leadgraph.com.leadgraph.controller.ProductMasterController;
import mobileapp.leadgraph.com.leadgraph.dialog.ItemDialog;
import mobileapp.leadgraph.com.leadgraph.models.CategoryListModel;
import mobileapp.leadgraph.com.leadgraph.models.CompanyDropDown;
import mobileapp.leadgraph.com.leadgraph.models.ProductListModel;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;

/**
 * @author neeraj on 6/12/18.
 */
public class MasterProductFragment extends Fragment implements View.OnClickListener, CompanyMasterController.CompanyDropDownListner, ItemDialog.SelectionListener, ProductMasterController.CategoryDropDOwnListListener, ProductMasterController.ProductMasterListener, ProductListAdapter.CompanyActionListener {
    View view;
    ImageView serach_btn, add_btn;
    CardView add_card;
    TextView to_date, from_date, effective_date, search_company;
    TextView search_button, save_btn;
    EditText product_cost, product_name, search_product;
    Switch switch_k;
    CompanyMasterController companyMasterController;
    ProductMasterController productMasterController;
    List<CompanyDropDown.ValueBean> valueBeans = new ArrayList<>();
    List<String> companies = new ArrayList<>();
    TextView spin_company, categoriesText;
    String st_parent, st_category, st_productName, st_productCost, st_effectiveDate;
    List<CategoryListModel.ValueBean> categoryLists = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    boolean isActive;
    RecyclerView list_search;
    List<ProductListModel.ValueBean> productsList = new ArrayList<>();
    ProductListAdapter productListAdapter;
    ImageView show_filter;
    LinearLayout lin_filters, search_card;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_master_product, container, false);
        }
        initViews();
        return view;
    }

    private void initViews() {
        companyMasterController = new CompanyMasterController(getActivity());
        productMasterController = new ProductMasterController(getActivity());
        productMasterController.setCategoryDropDOwnListListener(this);
        companyMasterController.setCompanyDropDownListner(this);
        productMasterController.setProductMasterListener(this);
        serach_btn = view.findViewById(R.id.serach_btn);
        from_date = view.findViewById(R.id.from_date);
        to_date = view.findViewById(R.id.to_date);
        from_date.setOnClickListener(this);
        to_date.setOnClickListener(this);
        add_card = view.findViewById(R.id.add_card);
        search_card = view.findViewById(R.id.search_card);
        add_btn = view.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);
        serach_btn.setOnClickListener(this);
        effective_date = view.findViewById(R.id.effective_date);
        effective_date.setOnClickListener(this);
        save_btn = view.findViewById(R.id.save_btn);
        product_name = view.findViewById(R.id.product_name);
        product_cost = view.findViewById(R.id.product_cost);
        search_button = view.findViewById(R.id.search_button);
        save_btn.setOnClickListener(this);
        search_button.setOnClickListener(this);
        spin_company = view.findViewById(R.id.spin_company);
        spin_company.setOnClickListener(this);
        categoriesText = view.findViewById(R.id.categoriesText);
        categoriesText.setOnClickListener(this);
        switch_k = view.findViewById(R.id.switch_k);
        search_company = view.findViewById(R.id.search_company);
        search_company.setOnClickListener(this);
        search_product = view.findViewById(R.id.search_product);
        list_search = view.findViewById(R.id.list_search);
        productListAdapter = new ProductListAdapter(getActivity(), productsList);
        productListAdapter.setCompanyActionListener(this);
        list_search.setAdapter(productListAdapter);
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
        ((MainActivity) getActivity()).setSelected(false, true, false, false, false, false, false, false, false, false);
        ((MainActivity) getActivity()).setBottomNavigation(false, false, false);

        ((MainActivity) getActivity()).setTitle("Product");
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
            case R.id.from_date:
                openDatePicker(0);
                break;
            case R.id.to_date:
                openDatePicker(1);
                break;
            case R.id.effective_date:
                openDatePicker(2);
                break;
            case R.id.spin_company:
                ItemDialog itemDialog = new ItemDialog(getActivity(), "Select", "pcomp", companies);
                itemDialog.setSelectionListener(this);
                itemDialog.show();
                break;
            case R.id.search_company:
                ItemDialog itemDialog2 = new ItemDialog(getActivity(), "Select", "pcomps", companies);
                itemDialog2.setSelectionListener(this);
                itemDialog2.show();
                break;
            case R.id.categoriesText:
                ItemDialog itemDialog1 = new ItemDialog(getActivity(), "Select", "category", categories);
                itemDialog1.setSelectionListener(this);
                itemDialog1.show();
                break;
            case R.id.save_btn:
                if (isValidated() && save_btn.getText().toString().equalsIgnoreCase("save")) {
                    //insert product here
                    productMasterController.insertProduct(st_effectiveDate, st_productName, st_productCost,
                            st_parent, st_category, isActive
                            , SPUtils.getString(getActivity(), SPUtils.USER_ID));

                } else if (isValidated() && save_btn.getText().toString().equalsIgnoreCase("update")) {
                    productMasterController.updateProduct(st_effectiveDate, st_productName, st_productCost,
                            st_parent, st_category, isActive
                            , SPUtils.getString(getActivity(), SPUtils.USER_ID), String.valueOf(save_btn.getTag()));
                }
                break;
            case R.id.search_button:
                //call search api here
                productMasterController.getProducts(st_parent, search_product.getText().toString(), from_date.getText().toString(), to_date.getText().toString());
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

    private void openDatePicker(final int flag) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String text =  datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" +datePicker.getYear();
                if (flag == 0) {
                    from_date.setText(text);

                } else if (flag == 1) {
                    to_date.setText(text);

                } else if (flag == 2) {
                    effective_date.setText(text);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void dropDownList(List<CompanyDropDown.ValueBean> valueBeans) {
        this.valueBeans.clear();
        this.companies.clear();
        this.valueBeans.addAll(valueBeans);
        for (CompanyDropDown.ValueBean valueBean : valueBeans
                ) {
            companies.add(valueBean.getCompany_Name());
        }
        if (this.valueBeans.size() == 1) {
            //set parent company diffault
            onItemSelect(valueBeans.get(0).getCompany_Name(), "pcomp", 0);

        }
    }

    @Override
    public void onItemSelect(String item, String tag, int position) {
        if (tag.equalsIgnoreCase("pcomp")) {
            spin_company.setText(item);
            if (position==-1){
                st_parent = "";
            }else {
                st_parent = valueBeans.get(position).getCompanyUser_Idno();
            }
            productMasterController.callCategoryListAPi("", st_parent);

        } else if (tag.equalsIgnoreCase("category")) {
            categoriesText.setText(item);
            if (position==-1){
                st_category = "";
            }else {
                st_category = categoryLists.get(position).getCategory_Idno();
            }
        } else if (tag.equalsIgnoreCase("pcomps")) {
            search_company.setText(item);
            if (position==-1){
                st_parent = "";
            }else { st_parent = valueBeans.get(position).getCompanyUser_Idno();
            }
        }
    }

    @Override
    public void categoryDropDownList(List<CategoryListModel.ValueBean> valueBeans) {
        this.categoryLists.clear();
        this.categories.clear();
        this.categoryLists.addAll(valueBeans);
        for (CategoryListModel.ValueBean valueBean : valueBeans
                ) {
            categories.add(valueBean.getCategory_Name());
        }
    }

    private boolean isValidated() {
        st_productCost = product_cost.getText().toString();
        st_productName = product_name.getText().toString();
        st_effectiveDate = effective_date.getText().toString();
        if (st_productCost.isEmpty()) {
            product_cost.setError("Product cost required!");
            return false;
        } else if (st_productName.isEmpty()) {
            product_name.setError("Product name required!");
            return false;
        } else if (st_effectiveDate.isEmpty()) {
            effective_date.setError("Effective date required!");
            return false;
        } else if (st_parent == null || st_parent.isEmpty()) {
            spin_company.setError("Please select company!");
            return false;
        } else if (st_category == null || st_category.isEmpty()) {
            categoriesText.setError("Please select a category");
            return false;
        }
        return true;
    }

    private void clearUi() {
        product_cost.setText("");
        product_name.setText("");
        effective_date.setText("yy-mm-dd");
        categoriesText.setText("");
        spin_company.setText("");
        save_btn.setText("save");
    }

    @Override
    public void productAdded() {
        clearUi();
    }

    @Override
    public void productList(List<ProductListModel.ValueBean> valueBeans) {
        this.productsList.clear();
        productsList.addAll(valueBeans);
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void productUpdated() {
        clearUi();
    }

    int position = 0;

    @Override
    public void productDeleted() {
        productsList.remove(position);
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void edit(int position) {
        add_card.setVisibility(View.VISIBLE);
        search_card.setVisibility(View.GONE);
        setUi(productsList.get(position));
    }

    @Override
    public void delete(int position) {
        this.position = position;
        productMasterController.deleteProduct(productsList.get(position).getProduct_Idno());
    }

    private void setUi(ProductListModel.ValueBean ui) {
        product_cost.setText(ui.getProduct_Cost());
        product_name.setText(ui.getProduct_Name());
        effective_date.setText(ui.getEffDT());
        categoriesText.setText(ui.getCategory_Name());
        spin_company.setText(ui.getComp_Name());
        save_btn.setText("update");
        save_btn.setTag(ui.getProduct_Idno());
        st_parent=ui.getCompanyUser_Idno();
        st_category=ui.getCategory_Idno();
        if (ui.getStatus().equalsIgnoreCase("active")) {
            switch_k.setChecked(true);
        } else {
            switch_k.setChecked(false);
        }
    }
}
