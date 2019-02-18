package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 11/1/19.
 */
public class CategoryListModel {

    /**
     * Valid : true
     * Description : Category Details
     * Value : [{"Category_Idno":"22","CompanyUser_Idno":"14","Category_Name":"uniform","Is_Deleted":"0","Is_Active":"1","Date_Added":"17-12-2018","AddedBy_UserIdno":"0","Date_Modified":"17/12/2018 11:42:07 AM","ModifiedBy_UserIdno":"","Company_Name":"neeraj","CategoryStatus":"Active"},{"Category_Idno":"23","CompanyUser_Idno":"16","Category_Name":"RESTAURANT","Is_Deleted":"0","Is_Active":"1","Date_Added":"19-12-2018","AddedBy_UserIdno":"0","Date_Modified":"19/12/2018 8:57:07 PM","ModifiedBy_UserIdno":"","Company_Name":"KS SOFTWARES","CategoryStatus":"Active"},{"Category_Idno":"24","CompanyUser_Idno":"16","Category_Name":"HOTEL","Is_Deleted":"0","Is_Active":"1","Date_Added":"19-12-2018","AddedBy_UserIdno":"0","Date_Modified":"19/12/2018 8:57:19 PM","ModifiedBy_UserIdno":"","Company_Name":"KS SOFTWARES","CategoryStatus":"Active"},{"Category_Idno":"25","CompanyUser_Idno":"16","Category_Name":"RETAIL","Is_Deleted":"0","Is_Active":"1","Date_Added":"19-12-2018","AddedBy_UserIdno":"0","Date_Modified":"19/12/2018 8:57:29 PM","ModifiedBy_UserIdno":"","Company_Name":"KS SOFTWARES","CategoryStatus":"Active"}]
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
         * Category_Idno : 22
         * CompanyUser_Idno : 14
         * Category_Name : uniform
         * Is_Deleted : 0
         * Is_Active : 1
         * Date_Added : 17-12-2018
         * AddedBy_UserIdno : 0
         * Date_Modified : 17/12/2018 11:42:07 AM
         * ModifiedBy_UserIdno :
         * Company_Name : neeraj
         * CategoryStatus : Active
         */

        private String Category_Idno;
        private String CompanyUser_Idno;
        private String Category_Name;
        private String Is_Deleted;
        private String Is_Active;
        private String Date_Added;
        private String AddedBy_UserIdno;
        private String Date_Modified;
        private String ModifiedBy_UserIdno;
        private String Company_Name;
        private String CategoryStatus;

        public String getCategory_Idno() {
            return Category_Idno;
        }

        public void setCategory_Idno(String Category_Idno) {
            this.Category_Idno = Category_Idno;
        }

        public String getCompanyUser_Idno() {
            return CompanyUser_Idno;
        }

        public void setCompanyUser_Idno(String CompanyUser_Idno) {
            this.CompanyUser_Idno = CompanyUser_Idno;
        }

        public String getCategory_Name() {
            return Category_Name;
        }

        public void setCategory_Name(String Category_Name) {
            this.Category_Name = Category_Name;
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
    }
}
