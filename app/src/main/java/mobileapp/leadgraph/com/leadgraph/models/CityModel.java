package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 28/12/18.
 */
public class CityModel {

    /**
     * Valid : true
     * Description : City List
     * Value : [{"City_Idno":"1","State_Idno":"1","City_Name":"AANDAMAN AND NICOBAR ISLANDS","Status_Flag":"True","STD_Code":"0"},{"City_Idno":"5013","State_Idno":"1","City_Name":"Port Blair","Status_Flag":"True","STD_Code":"0"}]
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
         * City_Idno : 1
         * State_Idno : 1
         * City_Name : AANDAMAN AND NICOBAR ISLANDS
         * Status_Flag : True
         * STD_Code : 0
         */

        private String City_Idno;
        private String State_Idno;
        private String City_Name;
        private String Status_Flag;
        private String STD_Code;

        public String getCity_Idno() {
            return City_Idno;
        }

        public void setCity_Idno(String City_Idno) {
            this.City_Idno = City_Idno;
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

        public String getStatus_Flag() {
            return Status_Flag;
        }

        public void setStatus_Flag(String Status_Flag) {
            this.Status_Flag = Status_Flag;
        }

        public String getSTD_Code() {
            return STD_Code;
        }

        public void setSTD_Code(String STD_Code) {
            this.STD_Code = STD_Code;
        }
    }
}
