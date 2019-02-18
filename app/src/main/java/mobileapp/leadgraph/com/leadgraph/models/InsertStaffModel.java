package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 8/1/19.
 */
public class InsertStaffModel {

    /**
     * Valid : true
     * Description : Employee Registered Successfully. Your User Name is your Mobile No.
     * Value : [{"Msg":"Employee Registered Successfully. Your User Name is your Mobile No.","StatusCode":"200"}]
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
         * Msg : Employee Registered Successfully. Your User Name is your Mobile No.
         * StatusCode : 200
         */

        private String Msg;
        private String StatusCode;

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }

        public String getStatusCode() {
            return StatusCode;
        }

        public void setStatusCode(String StatusCode) {
            this.StatusCode = StatusCode;
        }
    }
}
