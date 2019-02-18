package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 27/12/18.
 */
public class StateModel {


    /**
     * Valid : true
     * Description : State List
     * Value : [{"State_Idno":"1","State_Name":"AANDAMAN AND NICOBAR ISLANDS","Country_Idno":"1","Status_Flag":"True","State_Code":"AN","StateCode_GST":"35"},{"State_Idno":"2","State_Name":"ANDHRA PRADESH","Country_Idno":"1","Status_Flag":"True","State_Code":"AP","StateCode_GST":"37"},{"State_Idno":"3","State_Name":"ARUNACHAL PRADESH","Country_Idno":"1","Status_Flag":"True","State_Code":"AR","StateCode_GST":"12"},{"State_Idno":"4","State_Name":"ASSAM","Country_Idno":"1","Status_Flag":"True","State_Code":"AS","StateCode_GST":"18"},{"State_Idno":"5","State_Name":"BIHAR","Country_Idno":"1","Status_Flag":"True","State_Code":"BR","StateCode_GST":"10"},{"State_Idno":"6","State_Name":"CHANDIGARH","Country_Idno":"1","Status_Flag":"True","State_Code":"CH","StateCode_GST":"04"},{"State_Idno":"7","State_Name":"CHATTISGARH","Country_Idno":"1","Status_Flag":"True","State_Code":"CT","StateCode_GST":"22"},{"State_Idno":"8","State_Name":"DADRA AND NAGAR HAVELI","Country_Idno":"1","Status_Flag":"True","State_Code":"DN","StateCode_GST":"26"},{"State_Idno":"9","State_Name":"DAMAN AND DIU","Country_Idno":"1","Status_Flag":"True","State_Code":"DD","StateCode_GST":"25"},{"State_Idno":"10","State_Name":"GOA","Country_Idno":"1","Status_Flag":"True","State_Code":"GA","StateCode_GST":"30"},{"State_Idno":"11","State_Name":"GUJARAT","Country_Idno":"1","Status_Flag":"True","State_Code":"GJ","StateCode_GST":"24"},{"State_Idno":"12","State_Name":"HARYANA","Country_Idno":"1","Status_Flag":"True","State_Code":"HR","StateCode_GST":"06"},{"State_Idno":"13","State_Name":"HIMACHAL PRADESH","Country_Idno":"1","Status_Flag":"True","State_Code":"HP","StateCode_GST":"02"},{"State_Idno":"14","State_Name":"JAMMU AND KASHMIR","Country_Idno":"1","Status_Flag":"True","State_Code":"JK","StateCode_GST":"01"},{"State_Idno":"15","State_Name":"JHARKHAND","Country_Idno":"1","Status_Flag":"True","State_Code":"JH","StateCode_GST":"20"},{"State_Idno":"16","State_Name":"KARNATAKA","Country_Idno":"1","Status_Flag":"True","State_Code":"KA","StateCode_GST":"29"},{"State_Idno":"17","State_Name":"KERALA","Country_Idno":"1","Status_Flag":"True","State_Code":"KL","StateCode_GST":"32"},{"State_Idno":"18","State_Name":"LAKHSWADEEP","Country_Idno":"1","Status_Flag":"True","State_Code":"LD","StateCode_GST":"31"},{"State_Idno":"19","State_Name":"MADHYA PRADESH","Country_Idno":"1","Status_Flag":"True","State_Code":"MP","StateCode_GST":"23"},{"State_Idno":"20","State_Name":"MAHARASHTRA","Country_Idno":"1","Status_Flag":"True","State_Code":"MH","StateCode_GST":"27"},{"State_Idno":"21","State_Name":"MANIPUR","Country_Idno":"1","Status_Flag":"True","State_Code":"MN","StateCode_GST":"14"},{"State_Idno":"22","State_Name":"MEGHALAYA","Country_Idno":"1","Status_Flag":"True","State_Code":"ML","StateCode_GST":"17"},{"State_Idno":"23","State_Name":"MIZORAM","Country_Idno":"1","Status_Flag":"True","State_Code":"MZ","StateCode_GST":"15"},{"State_Idno":"24","State_Name":"NAGALAND","Country_Idno":"1","Status_Flag":"True","State_Code":"NL","StateCode_GST":"13"},{"State_Idno":"25","State_Name":"ORISSA","Country_Idno":"1","Status_Flag":"True","State_Code":"OR","StateCode_GST":"21"},{"State_Idno":"26","State_Name":"PONDICHERRY","Country_Idno":"1","Status_Flag":"True","State_Code":"PY","StateCode_GST":"34"},{"State_Idno":"27","State_Name":"PUNJAB","Country_Idno":"1","Status_Flag":"True","State_Code":"PB","StateCode_GST":"03"},{"State_Idno":"28","State_Name":"RAJASTHAN","Country_Idno":"1","Status_Flag":"True","State_Code":"RJ","StateCode_GST":"08"},{"State_Idno":"29","State_Name":"SIKKIM","Country_Idno":"1","Status_Flag":"True","State_Code":"SK","StateCode_GST":"11"},{"State_Idno":"30","State_Name":"TAMIL NADU","Country_Idno":"1","Status_Flag":"True","State_Code":"TN","StateCode_GST":"33"},{"State_Idno":"31","State_Name":"TRIPURA","Country_Idno":"1","Status_Flag":"True","State_Code":"TR","StateCode_GST":"16"},{"State_Idno":"32","State_Name":"UTTAR PRADESH","Country_Idno":"1","Status_Flag":"True","State_Code":"UP","StateCode_GST":"09"},{"State_Idno":"33","State_Name":"UTTARANCHAL","Country_Idno":"1","Status_Flag":"True","State_Code":"UT","StateCode_GST":"05"},{"State_Idno":"34","State_Name":"WEST BENGAL","Country_Idno":"1","Status_Flag":"True","State_Code":"WB","StateCode_GST":"19"},{"State_Idno":"35","State_Name":"DELHI","Country_Idno":"1","Status_Flag":"True","State_Code":"DL","StateCode_GST":"07"},{"State_Idno":"36","State_Name":"Telangana","Country_Idno":"1","Status_Flag":"True","State_Code":"TL","StateCode_GST":"36"}]
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
         * State_Idno : 1
         * State_Name : AANDAMAN AND NICOBAR ISLANDS
         * Country_Idno : 1
         * Status_Flag : True
         * State_Code : AN
         * StateCode_GST : 35
         */

        private String State_Idno;
        private String State_Name;
        private String Country_Idno;
        private String Status_Flag;
        private String State_Code;
        private String StateCode_GST;

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

        public String getCountry_Idno() {
            return Country_Idno;
        }

        public void setCountry_Idno(String Country_Idno) {
            this.Country_Idno = Country_Idno;
        }

        public String getStatus_Flag() {
            return Status_Flag;
        }

        public void setStatus_Flag(String Status_Flag) {
            this.Status_Flag = Status_Flag;
        }

        public String getState_Code() {
            return State_Code;
        }

        public void setState_Code(String State_Code) {
            this.State_Code = State_Code;
        }

        public String getStateCode_GST() {
            return StateCode_GST;
        }

        public void setStateCode_GST(String StateCode_GST) {
            this.StateCode_GST = StateCode_GST;
        }
    }
}
