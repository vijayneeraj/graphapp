package mobileapp.leadgraph.com.leadgraph.controller;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import mobileapp.leadgraph.com.leadgraph.models.CategoryListModel;
import mobileapp.leadgraph.com.leadgraph.models.DeleteProductModel;
import mobileapp.leadgraph.com.leadgraph.models.InsertProductModel;
import mobileapp.leadgraph.com.leadgraph.models.ProductListModel;
import mobileapp.leadgraph.com.leadgraph.models.UpdateProductModel;
import mobileapp.leadgraph.com.leadgraph.rest.ApiErrorMessages;
import mobileapp.leadgraph.com.leadgraph.rest.ApiUrls;
import mobileapp.leadgraph.com.leadgraph.rest.RestAdapter;
import mobileapp.leadgraph.com.leadgraph.rest.RestClient;

/**
 * @author neeraj on 15/1/19.
 */
public class ProductMasterController implements RestClient.ApiListeners {
    private Context context;
    private RestClient restClient;
    private CategoryDropDOwnListListener categoryDropDOwnListListener;
    private ProductMasterListener productMasterListener;

    public void setProductMasterListener(ProductMasterListener productMasterListener) {
        this.productMasterListener = productMasterListener;
    }

    public void setCategoryDropDOwnListListener(CategoryDropDOwnListListener categoryDropDOwnListListener) {
        this.categoryDropDOwnListListener = categoryDropDOwnListListener;
    }

    public ProductMasterController(Context context) {
        this.context = context;
        restClient = new RestClient(context, this);
    }

    public void callCategoryListAPi(String catName, String pComp) {
        restClient.callApi(ApiUrls.CATEGORY_LIST_ID, RestAdapter.getAdapter().getCategoryList(pComp, catName));
    }

    public void insertProduct(String date, String name, String cost, String pcomp, String category, boolean isActive, String uid) {
        restClient.callApi(ApiUrls.INSERT_PRODUCTID, RestAdapter.getAdapter().insertProduct(pcomp, name, isActive, uid
                , category, cost, date));
    }

    public void getProducts(String pcomp, String pro_name, String date_from, String date_to) {
        restClient.callApi(ApiUrls.PRODUCT_LIST_ID, RestAdapter.getAdapter().getProductList(pcomp,
                pro_name, date_from, date_to));
    }
    public void updateProduct(String date, String name, String cost, String pcomp,
                              String category, boolean isActive, String uid,String prod_id){
        restClient.callApi(ApiUrls.UPDATE_PRODUCT_ID, RestAdapter.getAdapter().updateProduct(pcomp, name, isActive, uid
                , category, cost, date,prod_id));
    }
    public void deleteProduct(String prod_id){
        restClient.callApi(ApiUrls.DELETE_PRODUCT_ID,RestAdapter.getAdapter().deleteProduct(prod_id));

    }

    @Override
    public void onResponseSucess(String response, int apiId) {
        restClient.dismissLoadingDialog();
        if (response != null) {
            if (apiId == ApiUrls.CATEGORY_LIST_ID) {
                try {
                    CategoryListModel categoryListModel = new Gson().fromJson(response, CategoryListModel.class);
                    if (categoryListModel != null) {
                        if (categoryListModel.isValid()) {
                            if (categoryListModel.getValue().size() > 0) {
                                //added done clear box here
                                if (categoryDropDOwnListListener != null) {
                                    categoryDropDOwnListListener.categoryDropDownList(categoryListModel.getValue());
                                }
                            } else {
                                restClient.showApiErrorMessage(categoryListModel.getDescription());

                            }
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

            } else if (apiId == ApiUrls.INSERT_PRODUCTID) {
                try {
                    InsertProductModel insertProductModel = new Gson().fromJson(response, InsertProductModel.class);
                    if (insertProductModel != null) {
                        if (insertProductModel.isValid()) {
                            if (insertProductModel.getValue().size() > 0) {
                                //added done clear box here
                                if (insertProductModel.getValue().get(0).getStatusCode() != null &&
                                        insertProductModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                                    restClient.showApiErrorMessage(insertProductModel.getDescription());
                                    if (productMasterListener != null) {
                                        productMasterListener.productAdded();
                                    }
                                } else {
                                    restClient.showApiErrorMessage(insertProductModel.getDescription());

                                }
                            } else {
                                restClient.showApiErrorMessage(insertProductModel.getDescription());

                            }
                        } else {
                            restClient.showApiErrorMessage(insertProductModel.getDescription());

                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                }

            }else if (apiId==ApiUrls.PRODUCT_LIST_ID){
                try {
                    ProductListModel productListModel = new Gson().fromJson(response, ProductListModel.class);
                    if (productListModel != null) {
                        if (productListModel.isValid()) {
                            //added done clear box here
                            if (productMasterListener != null) {
                                productMasterListener.productList(productListModel.getValue());
                            }
                        } else {
                            restClient.showApiErrorMessage(productListModel.getDescription());

                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                }

            }else if (apiId==ApiUrls.UPDATE_PRODUCT_ID){
                try {
                    UpdateProductModel updateProductModel = new Gson().fromJson(response, UpdateProductModel.class);
                    if (updateProductModel != null) {
                        if (updateProductModel.isValid()) {
                            if (updateProductModel.getValue().size() > 0) {
                                //added done clear box here
                                if (updateProductModel.getValue().get(0).getStatusCode() != null &&
                                        updateProductModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                                    restClient.showApiErrorMessage(updateProductModel.getDescription());
                                    if (productMasterListener != null) {
                                        productMasterListener.productUpdated();
                                    }
                                } else {
                                    restClient.showApiErrorMessage(updateProductModel.getDescription());
                                }
                            } else {
                                restClient.showApiErrorMessage(updateProductModel.getDescription());
                            }
                        } else {
                            restClient.showApiErrorMessage(updateProductModel.getDescription());
                        }
                    } else {
                        restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    restClient.showApiErrorMessage(ApiErrorMessages.SYNTEXERROR);

                }

            }else if (apiId==ApiUrls.DELETE_PRODUCT_ID){
                try {
                    DeleteProductModel deleteProductModel = new Gson().fromJson(response, DeleteProductModel.class);
                    if (deleteProductModel != null) {
                        if (deleteProductModel.isValid()) {
                            if (deleteProductModel.getValue().size() > 0) {
                                //added done clear box here
                                if (deleteProductModel.getValue().get(0).getStatusCode() != null &&
                                        deleteProductModel.getValue().get(0).getStatusCode().equalsIgnoreCase("200")) {
                                    restClient.showApiErrorMessage(deleteProductModel.getDescription());
                                    if (productMasterListener != null) {
                                        productMasterListener.productDeleted();
                                    }
                                } else {
                                    restClient.showApiErrorMessage(deleteProductModel.getDescription());
                                }
                            } else {
                                restClient.showApiErrorMessage(deleteProductModel.getDescription());

                            }
                        } else {
                            restClient.showApiErrorMessage(deleteProductModel.getDescription());

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

    public interface CategoryDropDOwnListListener {
        void categoryDropDownList(List<CategoryListModel.ValueBean> valueBeans);
    }

    public interface ProductMasterListener {
        void productAdded();
        void productList(List<ProductListModel.ValueBean>  valueBeans);
        void productUpdated();
        void productDeleted();
    }

}
