package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 9/1/19.
 */
public class StaffListModel {

    /**
     * Valid : true
     * Description : Empolyee List
     * Value : [{"User_Idno":"88","CompanyUser_Idno":"2","User_Name":"NARESH KUMAWAT","Designation_Idno":"3","Designation_Name":"Support Executive","User_DOJ":"","City_Name":"Amguri","User_MobileNo":"9782746611","User_Email":"naresh.kumawat@cogxim.com","User_MaritalStatus":"Divorced","User_DOB":"","User_BloodGroup":"","User_Pic":"","User_Pan":"","User_AadhaarNo":"","Date_Added":"27/11/2018 11:56:09 AM","AddedBy_UserIdno":"","Date_Modified":"","ModifiedBy_UserIdno":"","Is_Deleted":"0","Is_Active":"0","State_Idno":"4","State_Name":"ASSAM","Comp_Name":"cogxim","EmployeeStatus":"InActive"}]
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
         * User_Idno : 88
         * CompanyUser_Idno : 2
         * User_Name : NARESH KUMAWAT
         * Designation_Idno : 3
         * Designation_Name : Support Executive
         * User_DOJ :
         * City_Name : Amguri
         * User_MobileNo : 9782746611
         * User_Email : naresh.kumawat@cogxim.com
         * User_MaritalStatus : Divorced
         * User_DOB :
         * User_BloodGroup :
         * User_Pic :
         * User_Pan :
         * User_AadhaarNo :
         * Date_Added : 27/11/2018 11:56:09 AM
         * AddedBy_UserIdno :
         * Date_Modified :
         * ModifiedBy_UserIdno :
         * Is_Deleted : 0
         * Is_Active : 0
         * State_Idno : 4
         * State_Name : ASSAM
         * Comp_Name : cogxim
         * EmployeeStatus : InActive
         */

        private String User_Idno;
        private String CompanyUser_Idno;
        private String User_Name;
        private String Designation_Idno;
        private String Designation_Name;
        private String User_DOJ;
        private String City_Name;
        private String User_MobileNo;
        private String User_Email;
        private String User_MaritalStatus;
        private String User_DOB;
        private String User_BloodGroup;
        private String User_Pic;
        private String User_Pan;
        private String User_AadhaarNo;
        private String Date_Added;
        private String AddedBy_UserIdno;
        private String Date_Modified;
        private String ModifiedBy_UserIdno;
        private String Is_Deleted;
        private String Is_Active;
        private String State_Idno;
        private String State_Name;
        private String Comp_Name;
        private String EmployeeStatus;

        public String getUser_Idno() {
            return User_Idno;
        }

        public void setUser_Idno(String User_Idno) {
            this.User_Idno = User_Idno;
        }

        public String getCompanyUser_Idno() {
            return CompanyUser_Idno;
        }

        public void setCompanyUser_Idno(String CompanyUser_Idno) {
            this.CompanyUser_Idno = CompanyUser_Idno;
        }

        public String getUser_Name() {
            return User_Name;
        }

        public void setUser_Name(String User_Name) {
            this.User_Name = User_Name;
        }

        public String getDesignation_Idno() {
            return Designation_Idno;
        }

        public void setDesignation_Idno(String Designation_Idno) {
            this.Designation_Idno = Designation_Idno;
        }

        public String getDesignation_Name() {
            return Designation_Name;
        }

        public void setDesignation_Name(String Designation_Name) {
            this.Designation_Name = Designation_Name;
        }

        public String getUser_DOJ() {
            return User_DOJ;
        }

        public void setUser_DOJ(String User_DOJ) {
            this.User_DOJ = User_DOJ;
        }

        public String getCity_Name() {
            return City_Name;
        }

        public void setCity_Name(String City_Name) {
            this.City_Name = City_Name;
        }

        public String getUser_MobileNo() {
            return User_MobileNo;
        }

        public void setUser_MobileNo(String User_MobileNo) {
            this.User_MobileNo = User_MobileNo;
        }

        public String getUser_Email() {
            return User_Email;
        }

        public void setUser_Email(String User_Email) {
            this.User_Email = User_Email;
        }

        public String getUser_MaritalStatus() {
            return User_MaritalStatus;
        }

        public void setUser_MaritalStatus(String User_MaritalStatus) {
            this.User_MaritalStatus = User_MaritalStatus;
        }

        public String getUser_DOB() {
            return User_DOB;
        }

        public void setUser_DOB(String User_DOB) {
            this.User_DOB = User_DOB;
        }

        public String getUser_BloodGroup() {
            return User_BloodGroup;
        }

        public void setUser_BloodGroup(String User_BloodGroup) {
            this.User_BloodGroup = User_BloodGroup;
        }

        public String getUser_Pic() {
            return User_Pic;
        }

        public void setUser_Pic(String User_Pic) {
            this.User_Pic = User_Pic;
        }

        public String getUser_Pan() {
            return User_Pan;
        }

        public void setUser_Pan(String User_Pan) {
            this.User_Pan = User_Pan;
        }

        public String getUser_AadhaarNo() {
            return User_AadhaarNo;
        }

        public void setUser_AadhaarNo(String User_AadhaarNo) {
            this.User_AadhaarNo = User_AadhaarNo;
        }

        public String getDate_Added() {
            return Date_Added;
        }

        public void setDate_Added(String Date_Added) {
            this.Date_Added = Date_Added;
        }

        public String getAddedBy_UserIdno() {
            return AddedBy_UserIdno;
        }

        public void setAddedBy_UserIdno(String AddedBy_UserIdno) {
            this.AddedBy_UserIdno = AddedBy_UserIdno;
        }

        public String getDate_Modified() {
            return Date_Modified;
        }

        public void setDate_Modified(String Date_Modified) {
            this.Date_Modified = Date_Modified;
        }

        public String getModifiedBy_UserIdno() {
            return ModifiedBy_UserIdno;
        }

        public void setModifiedBy_UserIdno(String ModifiedBy_UserIdno) {
            this.ModifiedBy_UserIdno = ModifiedBy_UserIdno;
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

        public String getState_Idno() {
            return State_Idno;
        }

        public void setState_Idno(String State_Idno) {
            this.State_Idno = State_Idno;
        }

        public String getState_Name() {
            return State_Name;
        }

        public void setState_Name(String State_Name) {
            this.State_Name = State_Name;
        }

        public String getComp_Name() {
            return Comp_Name;
        }

        public void setComp_Name(String Comp_Name) {
            this.Comp_Name = Comp_Name;
        }

        public String getEmployeeStatus() {
            return EmployeeStatus;
        }

        public void setEmployeeStatus(String EmployeeStatus) {
            this.EmployeeStatus = EmployeeStatus;
        }
    }
}
