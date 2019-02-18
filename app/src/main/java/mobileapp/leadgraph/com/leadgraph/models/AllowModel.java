package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 28/12/18.
 */
public class AllowModel {


    /**
     * Valid : true
     * Description : Company and User Limits
     * Value : [{"Allowed_Company":"999","Allowed_Users":"999","CompanyCreated":"18"}]
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
         * Allowed_Company : 999
         * Allowed_Users : 999
         * CompanyCreated : 18
         */

        private String Allowed_Company;
        private String Allowed_Users;
        private String CompanyCreated;

        public String getAllowed_Company() {
            return Allowed_Company;
        }

        public void setAllowed_Company(String Allowed_Company) {
            this.Allowed_Company = Allowed_Company;
        }

        public String getAllowed_Users() {
            return Allowed_Users;
        }

        public void setAllowed_Users(String Allowed_Users) {
            this.Allowed_Users = Allowed_Users;
        }

        public String getCompanyCreated() {
            return CompanyCreated;
        }

        public void setCompanyCreated(String CompanyCreated) {
            this.CompanyCreated = CompanyCreated;
        }
    }
}
