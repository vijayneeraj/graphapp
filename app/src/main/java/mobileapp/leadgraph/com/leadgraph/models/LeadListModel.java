package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 22/1/19.
 */
public class LeadListModel {

    /**
     * Valid : true
     * Description : Lead List
     * Value : [{"Lead_Idno":"11509","Prospect_Name":"testpros","CaseLock_No":"2","Staff_Name":"neer","Product_Name":"testinghfbyg","Lead_Date":"18-01-2019","Assinged_To":"","User_Idno":"","LeadFinalStatus":"Demo Done","CompanyUser_Idno":"25","City_Idno":"597","Product_Idno":"57","Demo_Date":"20-01-2019","Lead_ContactName":"testpros","Lead_ContactNumber":"3216547890","City_Idno2":"597","Lead_Cost":"2563.00","Lead_Type":"1","AddedBy_UserIdno":"0","ModifiedBy_UserIdno":"","AssignTo_Self":"0","Comp_Name":"neerjcreated","Prospect_Name1":"testpros","Contact_No":"3216547890","Alter_No":"9632587410","Demo_Time":"16:25","Pref_Lang":"1","Remarks":"tests","LeadFollowup_Idno":"13062","Lead_Status":"1","Lead_NextFollwUpDate":"19-01-2019","FollowupBy_UserIdno":"213","LeadFollowup_Remark":"Lead Created","FollowupBy_CompanyUserIdno":"25","LeadAssignment_Idno":"","Assignment_Date":"","AssignedBy_UserIdno":"","AssignedBy_CompanyUserIdno":"","Company_Name":"neerjcreated","Company_AddressLine1":"udhfufjvu","Company_Area":"ydyg","Pincode":"302020","Owner_Name":"testname","Owner_MobileNo":"3698521470","Owner_EmailId":"testname@gmail.co","ContactPerson_Name":"contact test","ContactPerson_MobileNo":"3214569870","ContactPerson_EmailId":"contact@test.com","Company_GSTNo":"6789il","Is_Active":"1","InsertCompanyUser_Idno":"14","Allowed_Company":"4","Allowed_Staff":"2","State_Idno":"11","City_Name":"Aadityana","IsExch_Detl":"0","LeadSource_Idno":"0","Ocupation_Idno":"0","Area_Idno":"0","Brand_Idno":"","ExchVehModel_Idno":"","Color_Idno":"","Mfg_Year":"","Veh_Price":""}]
     */

    private boolean Valid;
    private String Description;
    private List<ValueBean> Value;

    public boolean isValid() {
        return Valid;
    }

    public void setValid(boolean Valid) {
        this.Valid = Valid;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public List<ValueBean> getValue() {
        return Value;
    }

    public void setValue(List<ValueBean> Value) {
        this.Value = Value;
    }

    public static class ValueBean {
        /**
         * Lead_Idno : 11509
         * Prospect_Name : testpros
         * CaseLock_No : 2
         * Staff_Name : neer
         * Product_Name : testinghfbyg
         * Lead_Date : 18-01-2019
         * Assinged_To :
         * User_Idno :
         * LeadFinalStatus : Demo Done
         * CompanyUser_Idno : 25
         * City_Idno : 597
         * Product_Idno : 57
         * Demo_Date : 20-01-2019
         * Lead_ContactName : testpros
         * Lead_ContactNumber : 3216547890
         * City_Idno2 : 597
         * Lead_Cost : 2563.00
         * Lead_Type : 1
         * AddedBy_UserIdno : 0
         * ModifiedBy_UserIdno :
         * AssignTo_Self : 0
         * Comp_Name : neerjcreated
         * Prospect_Name1 : testpros
         * Contact_No : 3216547890
         * Alter_No : 9632587410
         * Demo_Time : 16:25
         * Pref_Lang : 1
         * Remarks : tests
         * LeadFollowup_Idno : 13062
         * Lead_Status : 1
         * Lead_NextFollwUpDate : 19-01-2019
         * FollowupBy_UserIdno : 213
         * LeadFollowup_Remark : Lead Created
         * FollowupBy_CompanyUserIdno : 25
         * LeadAssignment_Idno :
         * Assignment_Date :
         * AssignedBy_UserIdno :
         * AssignedBy_CompanyUserIdno :
         * Company_Name : neerjcreated
         * Company_AddressLine1 : udhfufjvu
         * Company_Area : ydyg
         * Pincode : 302020
         * Owner_Name : testname
         * Owner_MobileNo : 3698521470
         * Owner_EmailId : testname@gmail.co
         * ContactPerson_Name : contact test
         * ContactPerson_MobileNo : 3214569870
         * ContactPerson_EmailId : contact@test.com
         * Company_GSTNo : 6789il
         * Is_Active : 1
         * InsertCompanyUser_Idno : 14
         * Allowed_Company : 4
         * Allowed_Staff : 2
         * State_Idno : 11
         * City_Name : Aadityana
         * IsExch_Detl : 0
         * LeadSource_Idno : 0
         * Ocupation_Idno : 0
         * Area_Idno : 0
         * Brand_Idno :
         * ExchVehModel_Idno :
         * Color_Idno :
         * Mfg_Year :
         * Veh_Price :
         */

        private String Lead_Idno;
        private String Prospect_Name;
        private String CaseLock_No;
        private String Staff_Name;
        private String Product_Name;
        private String Lead_Date;
        private String Assinged_To;
        private String User_Idno;
        private String LeadFinalStatus;
        private String CompanyUser_Idno;
        private String City_Idno;
        private String Product_Idno;
        private String Demo_Date;
        private String Lead_ContactName;
        private String Lead_ContactNumber;
        private String City_Idno2;
        private String Lead_Cost;
        private String Lead_Type;
        private String AddedBy_UserIdno;
        private String ModifiedBy_UserIdno;
        private String AssignTo_Self;
        private String Comp_Name;
        private String Prospect_Name1;
        private String Contact_No;
        private String Alter_No;
        private String Demo_Time;
        private String Pref_Lang;
        private String Remarks;
        private String LeadFollowup_Idno;
        private String Lead_Status;
        private String Lead_NextFollwUpDate;
        private String FollowupBy_UserIdno;
        private String LeadFollowup_Remark;
        private String FollowupBy_CompanyUserIdno;
        private String LeadAssignment_Idno;
        private String Assignment_Date;
        private String AssignedBy_UserIdno;
        private String AssignedBy_CompanyUserIdno;
        private String Company_Name;
        private String Company_AddressLine1;
        private String Company_Area;
        private String Pincode;
        private String Owner_Name;
        private String Owner_MobileNo;
        private String Owner_EmailId;
        private String ContactPerson_Name;
        private String ContactPerson_MobileNo;
        private String ContactPerson_EmailId;
        private String Company_GSTNo;
        private String Is_Active;
        private String InsertCompanyUser_Idno;
        private String Allowed_Company;
        private String Allowed_Staff;
        private String State_Idno;
        private String City_Name;
        private String IsExch_Detl;
        private String LeadSource_Idno;
        private String Ocupation_Idno;
        private String Area_Idno;
        private String Brand_Idno;
        private String ExchVehModel_Idno;
        private String Color_Idno;
        private String Mfg_Year;
        private String Veh_Price;
        private String State_Name;

        public String getState_Name() {
            return State_Name;
        }

        public void setState_Name(String state_Name) {
            State_Name = state_Name;
        }

        public String getLead_Idno() {
            return Lead_Idno;
        }

        public void setLead_Idno(String Lead_Idno) {
            this.Lead_Idno = Lead_Idno;
        }

        public String getProspect_Name() {
            return Prospect_Name;
        }

        public void setProspect_Name(String Prospect_Name) {
            this.Prospect_Name = Prospect_Name;
        }

        public String getCaseLock_No() {
            return CaseLock_No;
        }

        public void setCaseLock_No(String CaseLock_No) {
            this.CaseLock_No = CaseLock_No;
        }

        public String getStaff_Name() {
            return Staff_Name;
        }

        public void setStaff_Name(String Staff_Name) {
            this.Staff_Name = Staff_Name;
        }

        public String getProduct_Name() {
            return Product_Name;
        }

        public void setProduct_Name(String Product_Name) {
            this.Product_Name = Product_Name;
        }

        public String getLead_Date() {
            return Lead_Date;
        }

        public void setLead_Date(String Lead_Date) {
            this.Lead_Date = Lead_Date;
        }

        public String getAssinged_To() {
            return Assinged_To;
        }

        public void setAssinged_To(String Assinged_To) {
            this.Assinged_To = Assinged_To;
        }

        public String getUser_Idno() {
            return User_Idno;
        }

        public void setUser_Idno(String User_Idno) {
            this.User_Idno = User_Idno;
        }

        public String getLeadFinalStatus() {
            return LeadFinalStatus;
        }

        public void setLeadFinalStatus(String LeadFinalStatus) {
            this.LeadFinalStatus = LeadFinalStatus;
        }

        public String getCompanyUser_Idno() {
            return CompanyUser_Idno;
        }

        public void setCompanyUser_Idno(String CompanyUser_Idno) {
            this.CompanyUser_Idno = CompanyUser_Idno;
        }

        public String getCity_Idno() {
            return City_Idno;
        }

        public void setCity_Idno(String City_Idno) {
            this.City_Idno = City_Idno;
        }

        public String getProduct_Idno() {
            return Product_Idno;
        }

        public void setProduct_Idno(String Product_Idno) {
            this.Product_Idno = Product_Idno;
        }

        public String getDemo_Date() {
            return Demo_Date;
        }

        public void setDemo_Date(String Demo_Date) {
            this.Demo_Date = Demo_Date;
        }

        public String getLead_ContactName() {
            return Lead_ContactName;
        }

        public void setLead_ContactName(String Lead_ContactName) {
            this.Lead_ContactName = Lead_ContactName;
        }

        public String getLead_ContactNumber() {
            return Lead_ContactNumber;
        }

        public void setLead_ContactNumber(String Lead_ContactNumber) {
            this.Lead_ContactNumber = Lead_ContactNumber;
        }

        public String getCity_Idno2() {
            return City_Idno2;
        }

        public void setCity_Idno2(String City_Idno2) {
            this.City_Idno2 = City_Idno2;
        }

        public String getLead_Cost() {
            return Lead_Cost;
        }

        public void setLead_Cost(String Lead_Cost) {
            this.Lead_Cost = Lead_Cost;
        }

        public String getLead_Type() {
            return Lead_Type;
        }

        public void setLead_Type(String Lead_Type) {
            this.Lead_Type = Lead_Type;
        }

        public String getAddedBy_UserIdno() {
            return AddedBy_UserIdno;
        }

        public void setAddedBy_UserIdno(String AddedBy_UserIdno) {
            this.AddedBy_UserIdno = AddedBy_UserIdno;
        }

        public String getModifiedBy_UserIdno() {
            return ModifiedBy_UserIdno;
        }

        public void setModifiedBy_UserIdno(String ModifiedBy_UserIdno) {
            this.ModifiedBy_UserIdno = ModifiedBy_UserIdno;
        }

        public String getAssignTo_Self() {
            return AssignTo_Self;
        }

        public void setAssignTo_Self(String AssignTo_Self) {
            this.AssignTo_Self = AssignTo_Self;
        }

        public String getComp_Name() {
            return Comp_Name;
        }

        public void setComp_Name(String Comp_Name) {
            this.Comp_Name = Comp_Name;
        }

        public String getProspect_Name1() {
            return Prospect_Name1;
        }

        public void setProspect_Name1(String Prospect_Name1) {
            this.Prospect_Name1 = Prospect_Name1;
        }

        public String getContact_No() {
            return Contact_No;
        }

        public void setContact_No(String Contact_No) {
            this.Contact_No = Contact_No;
        }

        public String getAlter_No() {
            return Alter_No;
        }

        public void setAlter_No(String Alter_No) {
            this.Alter_No = Alter_No;
        }

        public String getDemo_Time() {
            return Demo_Time;
        }

        public void setDemo_Time(String Demo_Time) {
            this.Demo_Time = Demo_Time;
        }

        public String getPref_Lang() {
            return Pref_Lang;
        }

        public void setPref_Lang(String Pref_Lang) {
            this.Pref_Lang = Pref_Lang;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
        }

        public String getLeadFollowup_Idno() {
            return LeadFollowup_Idno;
        }

        public void setLeadFollowup_Idno(String LeadFollowup_Idno) {
            this.LeadFollowup_Idno = LeadFollowup_Idno;
        }

        public String getLead_Status() {
            return Lead_Status;
        }

        public void setLead_Status(String Lead_Status) {
            this.Lead_Status = Lead_Status;
        }

        public String getLead_NextFollwUpDate() {
            return Lead_NextFollwUpDate;
        }

        public void setLead_NextFollwUpDate(String Lead_NextFollwUpDate) {
            this.Lead_NextFollwUpDate = Lead_NextFollwUpDate;
        }

        public String getFollowupBy_UserIdno() {
            return FollowupBy_UserIdno;
        }

        public void setFollowupBy_UserIdno(String FollowupBy_UserIdno) {
            this.FollowupBy_UserIdno = FollowupBy_UserIdno;
        }

        public String getLeadFollowup_Remark() {
            return LeadFollowup_Remark;
        }

        public void setLeadFollowup_Remark(String LeadFollowup_Remark) {
            this.LeadFollowup_Remark = LeadFollowup_Remark;
        }

        public String getFollowupBy_CompanyUserIdno() {
            return FollowupBy_CompanyUserIdno;
        }

        public void setFollowupBy_CompanyUserIdno(String FollowupBy_CompanyUserIdno) {
            this.FollowupBy_CompanyUserIdno = FollowupBy_CompanyUserIdno;
        }

        public String getLeadAssignment_Idno() {
            return LeadAssignment_Idno;
        }

        public void setLeadAssignment_Idno(String LeadAssignment_Idno) {
            this.LeadAssignment_Idno = LeadAssignment_Idno;
        }

        public String getAssignment_Date() {
            return Assignment_Date;
        }

        public void setAssignment_Date(String Assignment_Date) {
            this.Assignment_Date = Assignment_Date;
        }

        public String getAssignedBy_UserIdno() {
            return AssignedBy_UserIdno;
        }

        public void setAssignedBy_UserIdno(String AssignedBy_UserIdno) {
            this.AssignedBy_UserIdno = AssignedBy_UserIdno;
        }

        public String getAssignedBy_CompanyUserIdno() {
            return AssignedBy_CompanyUserIdno;
        }

        public void setAssignedBy_CompanyUserIdno(String AssignedBy_CompanyUserIdno) {
            this.AssignedBy_CompanyUserIdno = AssignedBy_CompanyUserIdno;
        }

        public String getCompany_Name() {
            return Company_Name;
        }

        public void setCompany_Name(String Company_Name) {
            this.Company_Name = Company_Name;
        }

        public String getCompany_AddressLine1() {
            return Company_AddressLine1;
        }

        public void setCompany_AddressLine1(String Company_AddressLine1) {
            this.Company_AddressLine1 = Company_AddressLine1;
        }

        public String getCompany_Area() {
            return Company_Area;
        }

        public void setCompany_Area(String Company_Area) {
            this.Company_Area = Company_Area;
        }

        public String getPincode() {
            return Pincode;
        }

        public void setPincode(String Pincode) {
            this.Pincode = Pincode;
        }

        public String getOwner_Name() {
            return Owner_Name;
        }

        public void setOwner_Name(String Owner_Name) {
            this.Owner_Name = Owner_Name;
        }

        public String getOwner_MobileNo() {
            return Owner_MobileNo;
        }

        public void setOwner_MobileNo(String Owner_MobileNo) {
            this.Owner_MobileNo = Owner_MobileNo;
        }

        public String getOwner_EmailId() {
            return Owner_EmailId;
        }

        public void setOwner_EmailId(String Owner_EmailId) {
            this.Owner_EmailId = Owner_EmailId;
        }

        public String getContactPerson_Name() {
            return ContactPerson_Name;
        }

        public void setContactPerson_Name(String ContactPerson_Name) {
            this.ContactPerson_Name = ContactPerson_Name;
        }

        public String getContactPerson_MobileNo() {
            return ContactPerson_MobileNo;
        }

        public void setContactPerson_MobileNo(String ContactPerson_MobileNo) {
            this.ContactPerson_MobileNo = ContactPerson_MobileNo;
        }

        public String getContactPerson_EmailId() {
            return ContactPerson_EmailId;
        }

        public void setContactPerson_EmailId(String ContactPerson_EmailId) {
            this.ContactPerson_EmailId = ContactPerson_EmailId;
        }

        public String getCompany_GSTNo() {
            return Company_GSTNo;
        }

        public void setCompany_GSTNo(String Company_GSTNo) {
            this.Company_GSTNo = Company_GSTNo;
        }

        public String getIs_Active() {
            return Is_Active;
        }

        public void setIs_Active(String Is_Active) {
            this.Is_Active = Is_Active;
        }

        public String getInsertCompanyUser_Idno() {
            return InsertCompanyUser_Idno;
        }

        public void setInsertCompanyUser_Idno(String InsertCompanyUser_Idno) {
            this.InsertCompanyUser_Idno = InsertCompanyUser_Idno;
        }

        public String getAllowed_Company() {
            return Allowed_Company;
        }

        public void setAllowed_Company(String Allowed_Company) {
            this.Allowed_Company = Allowed_Company;
        }

        public String getAllowed_Staff() {
            return Allowed_Staff;
        }

        public void setAllowed_Staff(String Allowed_Staff) {
            this.Allowed_Staff = Allowed_Staff;
        }

        public String getState_Idno() {
            return State_Idno;
        }

        public void setState_Idno(String State_Idno) {
            this.State_Idno = State_Idno;
        }

        public String getCity_Name() {
            return City_Name;
        }

        public void setCity_Name(String City_Name) {
            this.City_Name = City_Name;
        }

        public String getIsExch_Detl() {
            return IsExch_Detl;
        }

        public void setIsExch_Detl(String IsExch_Detl) {
            this.IsExch_Detl = IsExch_Detl;
        }

        public String getLeadSource_Idno() {
            return LeadSource_Idno;
        }

        public void setLeadSource_Idno(String LeadSource_Idno) {
            this.LeadSource_Idno = LeadSource_Idno;
        }

        public String getOcupation_Idno() {
            return Ocupation_Idno;
        }

        public void setOcupation_Idno(String Ocupation_Idno) {
            this.Ocupation_Idno = Ocupation_Idno;
        }

        public String getArea_Idno() {
            return Area_Idno;
        }

        public void setArea_Idno(String Area_Idno) {
            this.Area_Idno = Area_Idno;
        }

        public String getBrand_Idno() {
            return Brand_Idno;
        }

        public void setBrand_Idno(String Brand_Idno) {
            this.Brand_Idno = Brand_Idno;
        }

        public String getExchVehModel_Idno() {
            return ExchVehModel_Idno;
        }

        public void setExchVehModel_Idno(String ExchVehModel_Idno) {
            this.ExchVehModel_Idno = ExchVehModel_Idno;
        }

        public String getColor_Idno() {
            return Color_Idno;
        }

        public void setColor_Idno(String Color_Idno) {
            this.Color_Idno = Color_Idno;
        }

        public String getMfg_Year() {
            return Mfg_Year;
        }

        public void setMfg_Year(String Mfg_Year) {
            this.Mfg_Year = Mfg_Year;
        }

        public String getVeh_Price() {
            return Veh_Price;
        }

        public void setVeh_Price(String Veh_Price) {
            this.Veh_Price = Veh_Price;
        }
    }
}
