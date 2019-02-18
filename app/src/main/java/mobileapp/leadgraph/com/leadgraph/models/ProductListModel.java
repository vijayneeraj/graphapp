package mobileapp.leadgraph.com.leadgraph.models;

import java.util.List;

/**
 * @author neeraj on 17/1/19.
 */
public class ProductListModel {
    /**
     * Valid : true
     * Description : Product List
     * Value : [{"Comp_Name":"KS SOFTWARES","Product_Name":"RESTAURANT","Category_Name":"Develope","Product_Cost":"15000.00","EffDT":"19-12-2018","Status":"Active","Product_Idno":"46"},{"Comp_Name":"KS SOFTWARES","Product_Name":"HOTEL MANAGEMENT","Category_Name":"HOTEL","Product_Cost":"25000.00","EffDT":"19-12-2018","Status":"Active","Product_Idno":"47"}]
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
         * Comp_Name : KS SOFTWARES
         * Product_Name : RESTAURANT
         * Category_Name : Develope
         * Product_Cost : 15000.00
         * EffDT : 19-12-2018
         * Status : Active
         * Product_Idno : 46
         */

        private String Comp_Name;
        private String Product_Name;
        private String Category_Name;
        private String Product_Cost;
        private String EffDT;
        private String Status;
        private String Product_Idno;
        private String Category_Idno;
        private String CompanyUser_Idno;

        public String getCategory_Idno() {
            return Category_Idno;
        }

        public void setCategory_Idno(String category_Idno) {
            Category_Idno = category_Idno;
        }

        public String getCompanyUser_Idno() {
            return CompanyUser_Idno;
        }

        public void setCompanyUser_Idno(String companyUser_Idno) {
            CompanyUser_Idno = companyUser_Idno;
        }

        public String getComp_Name() {
            return Comp_Name;
        }

        public void setComp_Name(String Comp_Name) {
            this.Comp_Name = Comp_Name;
        }

        public String getProduct_Name() {
            return Product_Name;
        }

        public void setProduct_Name(String Product_Name) {
            this.Product_Name = Product_Name;
        }

        public String getCategory_Name() {
            return Category_Name;
        }

        public void setCategory_Name(String Category_Name) {
            this.Category_Name = Category_Name;
        }

        public String getProduct_Cost() {
            return Product_Cost;
        }

        public void setProduct_Cost(String Product_Cost) {
            this.Product_Cost = Product_Cost;
        }

        public String getEffDT() {
            return EffDT;
        }

        public void setEffDT(String EffDT) {
            this.EffDT = EffDT;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getProduct_Idno() {
            return Product_Idno;
        }

        public void setProduct_Idno(String Product_Idno) {
            this.Product_Idno = Product_Idno;
        }
    }
}
