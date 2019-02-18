package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 7/1/19.
 */
public class DesignationListModel {

    /**
     * Valid : true
     * Description : Designation List
     * Value : [{"Designation_Idno":"69","CompanyUser_Idno":"1","Designation_Name":"Developer Aamir","Is_Deleted":"0","Is_Active":"1","Date_Added":"02-01-2019","AddedBy_UserIdno":"1","Date_Modified":"02-01-2019","ModifiedBy_UserIdno":"1","Company_Name":"Cogxim Technologiies","CategoryStatus":"Active","IsAdmin":"General User"}]
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
         * Designation_Idno : 69
         * CompanyUser_Idno : 1
         * Designation_Name : Developer Aamir
         * Is_Deleted : 0
         * Is_Active : 1
         * Date_Added : 02-01-2019
         * AddedBy_UserIdno : 1
         * Date_Modified : 02-01-2019
         * ModifiedBy_UserIdno : 1
         * Company_Name : Cogxim Technologiies
         * CategoryStatus : Active
         * IsAdmin : General User
         */

        private String Designation_Idno;
        private String CompanyUser_Idno;
        private String Designation_Name;
        private String Is_Deleted;
        private String Is_Active;
        private String Date_Added;
        private String AddedBy_UserIdno;
        private String Date_Modified;
        private String ModifiedBy_UserIdno;
        private String Company_Name;
        private String CategoryStatus;
        private String IsAdmin;

        public String getDesignation_Idno() {
            return Designation_Idno;
        }

        public void setDesignation_Idno(String Designation_Idno) {
            this.Designation_Idno = Designation_Idno;
        }

        public String getCompanyUser_Idno() {
            return CompanyUser_Idno;
        }

        public void setCompanyUser_Idno(String CompanyUser_Idno) {
            this.CompanyUser_Idno = CompanyUser_Idno;
        }

        public String getDesignation_Name() {
            return Designation_Name;
        }

        public void setDesignation_Name(String Designation_Name) {
            this.Designation_Name = Designation_Name;
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

        public String getCompany_Name() {
            return Company_Name;
        }

        public void setCompany_Name(String Company_Name) {
            this.Company_Name = Company_Name;
        }

        public String getCategoryStatus() {
            return CategoryStatus;
        }

        public void setCategoryStatus(String CategoryStatus) {
            this.CategoryStatus = CategoryStatus;
        }

        public String getIsAdmin() {
            return IsAdmin;
        }

        public void setIsAdmin(String IsAdmin) {
            this.IsAdmin = IsAdmin;
        }
    }
}
