package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 20/12/18.
 */
public class AddMasterCategoryModel {

    /**
     * Valid : true
     * Description : Category Already Registered With This Company.
     * Value : [{"Msg":"Category Already Registered With This Company.","StatusCode":"498"}]
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
         * Msg : Category Already Registered With This Company.
         * StatusCode : 498
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
