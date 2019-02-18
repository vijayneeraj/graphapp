package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 20/12/18.
 */
public class LoginMOdel {

    /**
     * Valid : true
     * Description : User Login Successfully
     * Value : [{"Login_Idno":"216","User_Idno":"0","CompanyUser_Idno":"14","EmployeeName":"","CompanyName":"neeraj","Is_Admin":"0"}]
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
         * Login_Idno : 216
         * User_Idno : 0
         * CompanyUser_Idno : 14
         * EmployeeName :
         * CompanyName : neeraj
         * Is_Admin : 0
         */

        private String Login_Idno;
        private String User_Idno;
        private String CompanyUser_Idno;
        private String EmployeeName;
        private String CompanyName;
        private String Is_Admin;
        private String StatusCode;

        public String getStatusCode() {
            return StatusCode;
        }

        public void setStatusCode(String statusCode) {
            StatusCode = statusCode;
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

        public String getCompanyUser_Idno() {
            return CompanyUser_Idno;
        }

        public void setCompanyUser_Idno(String CompanyUser_Idno) {
            this.CompanyUser_Idno = CompanyUser_Idno;
        }

        public String getEmployeeName() {
            return EmployeeName;
        }

        public void setEmployeeName(String EmployeeName) {
            this.EmployeeName = EmployeeName;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getIs_Admin() {
            return Is_Admin;
        }

        public void setIs_Admin(String Is_Admin) {
            this.Is_Admin = Is_Admin;
        }
    }
}
