package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 31/12/18.
 */
public class CompanyListModel {

    /**
     * Valid : true
     * Description : List of Company
     * Value : [{"CompanyUser_Idno":"14","Company_Name":"neeraj","Company_GSTNo":"123456789","Company_AddressLine1":"72/215 vinayak path mansarove, mansarover, mansarover","Company_Area":"mansarover","City_Idno":"2931","Pincode":"302020","Owner_Name":"neeraj","Owner_MobileNo":"9782952778","ContactPerson_MobileNo":"vijayneeraj93@gmail.com","ContactPerson_EmailId":"vijayneeraj93@gmail.com","Company_GSTNo1":"123456789","Date_Added":"12/17/2018 11:40:07 AM","Date_Modified":"","Is_Deleted":"0","Is_Active":"1","InsertCompanyUser_Idno":"1","Allowed_Company":"50","Allowed_Staff":"999","City_Name":"Ratangarh (Churu)","State_Name":"RAJASTHAN","Country_Idno":"1","Login_Idno":"216","User_Idno":"0","User_Name":"9782952778","LoginHits":"0","LoginExp_Date":"31-12-2019","CompanyAddress":"72/215 vinayak path mansarove, mansarover, mansarover, mansarover, Ratangarh (Churu), RAJASTHAN","CompanyStatus":"Active"},{"CompanyUser_Idno":"25","Company_Name":"neerjcreated","Company_GSTNo":"6789il","Company_AddressLine1":"udhfufjvu","Company_Area":"ydyg","City_Idno":"5013","Pincode":"302020","Owner_Name":"testname","Owner_MobileNo":"3698521470","ContactPerson_MobileNo":"testname@gmail.co","ContactPerson_EmailId":"contact@test.com","Company_GSTNo1":"6789il","Date_Added":"12/31/2018 10:28:40 AM","Date_Modified":"","Is_Deleted":"0","Is_Active":"1","InsertCompanyUser_Idno":"14","Allowed_Company":"4","Allowed_Staff":"2","City_Name":"Port Blair","State_Name":"AANDAMAN AND NICOBAR ISLANDS","Country_Idno":"1","Login_Idno":"236","User_Idno":"0","User_Name":"3698521470","LoginHits":"0","LoginExp_Date":"07-01-2019","CompanyAddress":"udhfufjvu, ydyg, Port Blair, AANDAMAN AND NICOBAR ISLANDS","CompanyStatus":"Active"}]
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
         * CompanyUser_Idno : 14
         * Company_Name : neeraj
         * Company_GSTNo : 123456789
         * Company_AddressLine1 : 72/215 vinayak path mansarove, mansarover, mansarover
         * Company_Area : mansarover
         * City_Idno : 2931
         * Pincode : 302020
         * Owner_Name : neeraj
         * Owner_MobileNo : 9782952778
         * ContactPerson_MobileNo : vijayneeraj93@gmail.com
         * ContactPerson_EmailId : vijayneeraj93@gmail.com
         * Company_GSTNo1 : 123456789
         * Date_Added : 12/17/2018 11:40:07 AM
         * Date_Modified :
         * Is_Deleted : 0
         * Is_Active : 1
         * InsertCompanyUser_Idno : 1
         * Allowed_Company : 50
         * Allowed_Staff : 999
         * City_Name : Ratangarh (Churu)
         * State_Name : RAJASTHAN
         * Country_Idno : 1
         * Login_Idno : 216
         * User_Idno : 0
         * User_Name : 9782952778
         * LoginHits : 0
         * LoginExp_Date : 31-12-2019
         * CompanyAddress : 72/215 vinayak path mansarove, mansarover, mansarover, mansarover, Ratangarh (Churu), RAJASTHAN
         * CompanyStatus : Active
         */

        private String CompanyUser_Idno;
        private String Company_Name;
        private String Company_GSTNo;
        private String Company_AddressLine1;
        private String Company_Area;
        private String City_Idno;
        private String Pincode;
        private String Owner_Name;
        private String Owner_MobileNo;
        private String ContactPerson_MobileNo;
        private String ContactPerson_EmailId;
        private String Company_GSTNo1;
        private String Date_Added;
        private String Date_Modified;
        private String Is_Deleted;
        private String Is_Active;
        private String InsertCompanyUser_Idno;
        private String Allowed_Company;
        private String Allowed_Staff;
        private String City_Name;
        private String State_Name;
        private String Country_Idno;
        private String Login_Idno;
        private String User_Idno;
        private String User_Name;
        private String LoginHits;
        private String LoginExp_Date;
        private String CompanyAddress;
        private String CompanyStatus;
        private String ContactPerson_Name;
        private String Owner_EmailId;

        public String getOwner_EmailId() {
            return Owner_EmailId;
        }

        public void setOwner_EmailId(String owner_EmailId) {
            Owner_EmailId = owner_EmailId;
        }

        public String getContactPerson_Name() {
            return ContactPerson_Name;
        }

        public void setContactPerson_Name(String contactPerson_Name) {
            ContactPerson_Name = contactPerson_Name;
        }

        public String getCompanyUser_Idno() {
            return CompanyUser_Idno;
        }

        public void setCompanyUser_Idno(String CompanyUser_Idno) {
            this.CompanyUser_Idno = CompanyUser_Idno;
        }

        public String getCompany_Name() {
            return Company_Name;
        }

        public void setCompany_Name(String Company_Name) {
            this.Company_Name = Company_Name;
        }

        public String getCompany_GSTNo() {
            return Company_GSTNo;
        }

        public void setCompany_GSTNo(String Company_GSTNo) {
            this.Company_GSTNo = Company_GSTNo;
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

        public String getCity_Idno() {
            return City_Idno;
        }

        public void setCity_Idno(String City_Idno) {
            this.City_Idno = City_Idno;
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

        public String getCompany_GSTNo1() {
            return Company_GSTNo1;
        }

        public void setCompany_GSTNo1(String Company_GSTNo1) {
            this.Company_GSTNo1 = Company_GSTNo1;
        }

        public String getDate_Added() {
            return Date_Added;
        }

        public void setDate_Added(String Date_Added) {
            this.Date_Added = Date_Added;
        }

        public String getDate_Modified() {
            return Date_Modified;
        }

        public void setDate_Modified(String Date_Modified) {
            this.Date_Modified = Date_Modified;
        }

        public String getIs_Deleted() {
            return Is_Deleted;
        }

        public void setIs_Deleted(String Is_Deleted) {
            this.Is_Deleted = Is_Deleted;
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

        public String getCity_Name() {
            return City_Name;
        }

        public void setCity_Name(String City_Name) {
            this.City_Name = City_Name;
        }

        public String getState_Name() {
            return State_Name;
        }

        public void setState_Name(String State_Name) {
            this.State_Name = State_Name;
        }

        public String getCountry_Idno() {
            return Country_Idno;
        }

        public void setCountry_Idno(String Country_Idno) {
            this.Country_Idno = Country_Idno;
        }

        public String getLogin_Idno() {
            return Login_Idno;
        }

        public void setLogin_Idno(String Login_Idno) {
            this.Login_Idno = Login_Idno;
        }

        public String getUser_Idno() {
            return User_Idno;
        }

        public void setUser_Idno(String User_Idno) {
            this.User_Idno = User_Idno;
        }

        public String getUser_Name() {
            return User_Name;
        }

        public void setUser_Name(String User_Name) {
            this.User_Name = User_Name;
        }

        public String getLoginHits() {
            return LoginHits;
        }

        public void setLoginHits(String LoginHits) {
            this.LoginHits = LoginHits;
        }

        public String getLoginExp_Date() {
            return LoginExp_Date;
        }

        public void setLoginExp_Date(String LoginExp_Date) {
            this.LoginExp_Date = LoginExp_Date;
        }

        public String getCompanyAddress() {
            return CompanyAddress;
        }

        public void setCompanyAddress(String CompanyAddress) {
            this.CompanyAddress = CompanyAddress;
        }

        public String getCompanyStatus() {
            return CompanyStatus;
        }

        public void setCompanyStatus(String CompanyStatus) {
            this.CompanyStatus = CompanyStatus;
        }
    }
}
