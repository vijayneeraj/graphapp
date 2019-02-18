package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 7/1/19.
 */
public class CompanyDropDown {

    /**
     * Valid : true
     * Description : Company List
     * Value : [{"CompanyUser_Idno":"14","Company_Name":"neeraj","InsertCompanyUser_Idno":"1"},{"CompanyUser_Idno":"25","Company_Name":"neerjcreated","InsertCompanyUser_Idno":"14"}]
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
         * InsertCompanyUser_Idno : 1
         */

        private String CompanyUser_Idno;
        private String Company_Name;
        private String InsertCompanyUser_Idno;

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

        public String getInsertCompanyUser_Idno() {
            return InsertCompanyUser_Idno;
        }

        public void setInsertCompanyUser_Idno(String InsertCompanyUser_Idno) {
            this.InsertCompanyUser_Idno = InsertCompanyUser_Idno;
        }
    }
}
