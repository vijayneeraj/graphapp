package mobileapp.leadgraph.com.leadgraph.rest;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {


//    @FormUrlEncoded
//    @POST(ApiUrls.COMPLETE_TASK)
//    Call<ResponseBody> submitTask(@Field(ParamName.USER_ID) String user_id, @Field(ParamName.TASKS) String tasks, @Field(ParamName.DATE) String date);

    @FormUrlEncoded
    @POST(ApiUrls.LOGIN_API)
    Call<ResponseBody> doLogin(@Field(ParamName.USER) String email, @Field(ParamName.PASS) String password,
                               @Field(ParamName.VER) String ver);

    @GET(ApiUrls.MASTERCATEGORY_ADD)
    Call<ResponseBody> getTaskList(@Query(ParamName.VER) String ver, @Query(ParamName.ACTN) String actn,
                                   @Query(ParamName.COMPID) String compID, @Query(ParamName.CAT_NAME) String cat_name
            , @Query(ParamName.ISACTIVE) String isactive, @Query(ParamName.UID) String uid);

    @GET(ApiUrls.GETSTATE)
    Call<ResponseBody> getState(@Query(ParamName.VER) String ver, @Query(ParamName.ACTN) String actn, @Query(ParamName.IDNO) String id_no);

    @GET(ApiUrls.GETCITY)
    Call<ResponseBody> getCity(@Query(ParamName.VER) String ver, @Query(ParamName.ACTN) String actn, @Query(ParamName.IDNO) String id_no);

    @GET(ApiUrls.ALLOWCOMPANY)
    Call<ResponseBody> getAllowCompany(@Query(ParamName.VER) String ver, @Query(ParamName.IDNO) String comp_id);

    @GET(ApiUrls.INSERTCOMPANY)
    Call<ResponseBody> addCompany(@Query(ParamName.VER) String ver, @Query(ParamName.ACTN) String actn,
                                  @Query(ParamName.PARENT_COMPANY_ID) String p_comp_id, @Query(ParamName.COMPANY_NAME) String comp_name,
                                  @Query(ParamName.GST_NO) String gst_no, @Query(ParamName.OWNER_NAME) String o_name, @Query(ParamName.OWNER_EMAIL) String o_email,
                                  @Query(ParamName.OWNER_PHONE) String o_phone, @Query(ParamName.CITY_ID) String city_id, @Query(ParamName.ADDRESS) String address,
                                  @Query(ParamName.AREA) String area, @Query(ParamName.PINCODE) String pin, @Query(ParamName.CONTACT_PERSON_NAME) String con_p_name,
                                  @Query(ParamName.CONTACT_PERSON_EMAIL) String con_p_email, @Query(ParamName.CONTACT_PERSON_PHONE) String con_p_phone,
                                  @Query(ParamName.ALLOW_COMPANY) String allow_comp, @Query(ParamName.ALLOW_STAFF) String allow_staff, @Query(ParamName.IS_ACTIVE) boolean is_active,
                                  @Query(ParamName.UID) String uid);

    @FormUrlEncoded()
    @POST(ApiUrls.COMPANYLIST)
    Call<ResponseBody> getCompanyList(@Field("Company_Name") String comp_name, @Field("Company_GSTNo") String gst, @Field("State_Idno") String state_id,
                                      @Field("City_Idno") String city_id, @Field("Pincode") String pin_code, @Field("Owner_Name") String o_name,
                                      @Field("Owner_EmailId") String o_email, @Field("ContactPerson_MobileNo") String c_mobile, @Field("CompanyUser_Idno") String comp_id);

    @GET(ApiUrls.DELETE_COMPANY)
    Call<ResponseBody> deleteCompany(@Query(ParamName.COMPANY_USER_ID_NO) String comp_id);

    @GET(ApiUrls.UPDATE_COMPANY)
    Call<ResponseBody> updateCompany(@Query(ParamName.VER) String ver, @Query(ParamName.ACTN) String actn,
                                     @Query(ParamName.PARENT_COMPANY_ID) String p_comp_id, @Query(ParamName.COMPANY_NAME) String comp_name,
                                     @Query(ParamName.GST_NO) String gst_no, @Query(ParamName.OWNER_NAME) String o_name, @Query(ParamName.OWNER_EMAIL) String o_email,
                                     @Query(ParamName.OWNER_PHONE) String o_phone, @Query(ParamName.CITY_ID) String city_id, @Query(ParamName.ADDRESS) String address,
                                     @Query(ParamName.AREA) String area, @Query(ParamName.PINCODE) String pin, @Query(ParamName.CONTACT_PERSON_NAME) String con_p_name,
                                     @Query(ParamName.CONTACT_PERSON_EMAIL) String con_p_email, @Query(ParamName.CONTACT_PERSON_PHONE) String con_p_phone,
                                     @Query(ParamName.ALLOW_COMPANY) String allow_comp, @Query(ParamName.ALLOW_STAFF) String allow_staff, @Query(ParamName.IS_ACTIVE) boolean is_active,
                                     @Query(ParamName.UID) String uid, @Query(ParamName.COMPANY_USER_ID_NO) String comp_id);

    @GET(ApiUrls.COMPANYDROPDOWN)
    Call<ResponseBody> comapniesDropdown(@Query("compidno") String comp_id, @Query(ParamName.VER) String ver);

    @GET(ApiUrls.INSERT_DESIGNATION)
    Call<ResponseBody> insertDesignation(@Query(ParamName.ACTN) String actn, @Query(ParamName.VER) String ver,
                                         @Query(ParamName.COMPANY_USER_ID_NO) String comp_id, @Query(ParamName.DESIGNATION_NAME) String dsg_name, @Query(ParamName.ADDED_BY_USER_ID) String login_user_id,
                                         @Query(ParamName.isactive) boolean isActive, @Query(ParamName.isadmin) boolean isAdmin);

    @FormUrlEncoded()
    @POST(ApiUrls.DESIGNATION_LIST)
    Call<ResponseBody> getDesignationList(@Field(ParamName.DESIGNATION_NAME) String dsg_name, @Field(ParamName.COMPANY_USER_ID_NO) String p_comp_id,
                                          @Field(ParamName.DESIGNATION_ID_NO) String dsg_id,
                                          @Field(ParamName.COMPANY_NAME) String comp_name);

    @GET(ApiUrls.UPDATE_DESIGNATION)
    Call<ResponseBody> updateDesignation(@Query(ParamName.ACTN) String actn, @Query(ParamName.VER) String ver,
                                         @Query(ParamName.COMPANY_USER_ID_NO) String comp_id, @Query(ParamName.DESIGNATION_NAME) String dsg_name, @Query(ParamName.ADDED_BY_USER_ID) String login_user_id,
                                         @Query(ParamName.isactive) boolean isActive,
                                         @Query(ParamName.isadmin) boolean isAdmin, @Query(ParamName.DESIGNATION_ID_NO) String dsg_id);

    @GET(ApiUrls.DELETE_DESIGNATION)
    Call<ResponseBody> deleteDesignation(@Query(ParamName.DESIGNATION_ID_NO) String dsg_id);

    @FormUrlEncoded()
    @POST(ApiUrls.INSERT_STAFF)
    Call<ResponseBody> insertStaff(@Field(ParamName.COMPANY_USER_ID_NO) String p_comp, @Field(ParamName.USER_NAME) String user_name,
                                   @Field(ParamName.DESIGNATION_ID_NO) String dsg_id, @Field(ParamName.JOIN_DATE) String join_date,
                                   @Field(ParamName.CITY_ID_NO) String city_id, @Field(ParamName.USER_CONTACT) String contact,
                                   @Field(ParamName.MARRITAL_STATUS) String ms, @Field(ParamName.USER_DOB) String dob, @Field(ParamName.BLOOD_GRP) String bg,
                                   @Field(ParamName.USER_PIC) String pic, @Field(ParamName.USER_PAN) String pan, @Field(ParamName.USER_AADHAR) String aadhar,
                                   @Field(ParamName.ADDED_BY_USER_ID) String add_by, @Field(ParamName.USER_EMAIL) String email,
                                   @Field(ParamName.ISACTIVE) boolean isactive);

    @FormUrlEncoded()
    @POST(ApiUrls.STAFFLIST)
    Call<ResponseBody> getStaffList(@Field("employee_name") String user_name, @Field(ParamName.STATE_ID_NO) String state_id,
                                    @Field(ParamName.CITY_ID_NO) String city_id,
                                    @Field("User_MobileNo") String mobile_user, @Field("User_Email") String user_email,
                                    @Field(ParamName.COMPANY_USER_ID_NO) String pcomp);

    @FormUrlEncoded()
    @POST(ApiUrls.STAFF_DELETE)
    Call<ResponseBody> deleteStaff(@Field(ParamName.USERIDNO) String user_id);

    @FormUrlEncoded()
    @POST(ApiUrls.UPDATE_STAFF)
    Call<ResponseBody> updateStaff(@Field(ParamName.COMPANY_USER_ID_NO) String p_comp, @Field(ParamName.USER_NAME) String user_name,
                                   @Field(ParamName.DESIGNATION_ID_NO) String dsg_id, @Field(ParamName.JOIN_DATE) String join_date,
                                   @Field(ParamName.CITY_ID_NO) String city_id, @Field(ParamName.USER_CONTACT) String contact,
                                   @Field(ParamName.MARRITAL_STATUS) String ms, @Field(ParamName.USER_DOB) String dob, @Field(ParamName.BLOOD_GRP) String bg,
                                   @Field(ParamName.USER_PIC) String pic, @Field(ParamName.USER_PAN) String pan, @Field(ParamName.USER_AADHAR) String aadhar,
                                   @Field(ParamName.ADDED_BY_USER_ID) String add_by, @Field(ParamName.USER_EMAIL) String email,
                                   @Field(ParamName.ISACTIVE) boolean isactive,@Field("user_idno")String employee_id);

    @FormUrlEncoded()
    @POST(ApiUrls.CATEGORY_LIST)
    Call<ResponseBody> getCategoryList(@Field(ParamName.COMPANY_USER_ID_NO) String pcomp,
                                       @Field("category_name")String cat_name);
    @GET(ApiUrls.CATEGORY_DELETE)
    Call<ResponseBody> deleteCategory(@Query("cateid") String cat_id);

    @GET(ApiUrls.UPDATE_CATEGORY)
    Call<ResponseBody> updateCategory(@Query(ParamName.COMPID) String compID, @Query(ParamName.CAT_NAME) String cat_name
            , @Query(ParamName.ISACTIVE) boolean isactive, @Query(ParamName.UID) String uid,
                                      @Query("cateid")String cat_id);
    @GET(ApiUrls.INSERT_PRODUCT)
    Call<ResponseBody> insertProduct(@Query("compId") String compID, @Query("pro") String pro_name
            , @Query(ParamName.ISACTIVE) boolean isactive, @Query(ParamName.UID) String uid,
                                      @Query("cateid")String cat_id,@Query("cost")String cost,
                                     @Query("effdt")String eff_date);

    @FormUrlEncoded()
    @POST(ApiUrls.PRODUCT_LIST)
    Call<ResponseBody> getProductList(@Field(ParamName.COMPANY_USER_ID_NO) String pcomp,
                                       @Field("product_name")String pro_name,@Field("date_from")String date_from,
                                      @Field("date_to")String date_to);

    @GET(ApiUrls.UPDATE_PRODUCT)
    Call<ResponseBody> updateProduct(@Query("compId") String compID, @Query("pro") String pro_name
            , @Query(ParamName.ISACTIVE) boolean isactive, @Query(ParamName.UID) String uid,
                                     @Query("cateid")String cat_id,@Query("cost")String cost,
                                     @Query("effdt")String eff_date,@Query("prodid")String prod_id);

    @GET(ApiUrls.DELETE_PRODUCT)
    Call<ResponseBody> deleteProduct(@Query("prodid") String prod_id);

    @GET(ApiUrls.INSERTLEAD)
    Call<ResponseBody> insertLead(@Query("stfId") String staff_id,@Query("cno")String case_no,@Query("cdt")String case_date,
                                  @Query("compname")String comp_name,@Query("compId")String pcomp,@Query("prosname")String pros_name,
                                  @Query("conno")String con_no,@Query("altno")String alt_no,@Query("cityid")String city_id,@Query("proid")String prod_id,
                                  @Query("price")String price,@Query("demodt")String demo_date,@Query("demotime")String demo_time,@Query("lang")String lang_id,
                                  @Query("typeid")String lead_type,@Query("remark")String remarks,@Query("statusid")String sttaus_id,@Query("followdt")String next_date,
                                  @Query("assignself")String assignedtoself,@Query("uid")String uid);

    @FormUrlEncoded()
    @POST(ApiUrls.LEAD_LIST)
    Call<ResponseBody> getLeadsList(@Field(ParamName.COMPANY_USER_ID_NO) String pcomp,
                                      @Field("date_from")String date_from,
                                      @Field("date_to")String date_to);
    @GET(ApiUrls.LEAD_UPDATE)
    Call<ResponseBody> updateLead(@Query("stfId") String staff_id,@Query("cno")String case_no,@Query("cdt")String case_date,
                                  @Query("compname")String comp_name,@Query("compId")String pcomp,@Query("prosname")String pros_name,
                                  @Query("conno")String con_no,@Query("altno")String alt_no,@Query("cityid")String city_id,@Query("proid")String prod_id,
                                  @Query("price")String price,@Query("demodt")String demo_date,@Query("demotime")String demo_time,@Query("lang")String lang_id,
                                  @Query("typeid")String lead_type,@Query("remark")String remarks,@Query("statusid")String sttaus_id,@Query("followdt")String next_date,
                                  @Query("assignself")String assignedtoself,@Query("uid")String uid,@Query("cid")String case_id);

    @GET(ApiUrls.DELETELEAD)
    Call<ResponseBody> deleteLead(@Query("cid") String cid);
    @GET(ApiUrls.SIGNUP)
    Call<ResponseBody> createCompany( @Query(ParamName.COMPANY_NAME) String comp_name,
                                  @Query(ParamName.GST_NO) String gst_no, @Query(ParamName.OWNER_NAME) String o_name, @Query(ParamName.OWNER_EMAIL) String o_email,
                                  @Query(ParamName.OWNER_PHONE) String o_phone, @Query(ParamName.CITY_ID) String city_id, @Query(ParamName.ADDRESS) String address,
                                  @Query(ParamName.AREA) String area, @Query(ParamName.PINCODE) String pin, @Query(ParamName.CONTACT_PERSON_NAME) String con_p_name,
                                  @Query(ParamName.CONTACT_PERSON_EMAIL) String con_p_email, @Query(ParamName.CONTACT_PERSON_PHONE) String con_p_phone,
                                  @Query(ParamName.ALLOW_COMPANY) String allow_comp, @Query(ParamName.ALLOW_STAFF) String allow_staff);
}
