
package anuja.divekar.userappfitness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpertPlan {
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("expert_plans_id")
    @Expose
    private String expert_plans_id;
    @SerializedName("professional_id")
    @Expose
    private String professional_id;

    @SerializedName("expert_name")
    @Expose
    private String expert_name ;

    @SerializedName("plan_sups_id")
    @Expose
    private String plan_sups_id ;

    @SerializedName("plans_status")
    @Expose
    private String  plans_status;

    @SerializedName("expert_plans_sups_price")
    @Expose
    private String  expert_plans_sups_price;

    @SerializedName("expert_plans_name")
    @Expose
    private String expertPlansName;
    @SerializedName("expert_plans_duration")
    @Expose
    private String expertPlansDuration;
    @SerializedName("expert_plans_details")
    @Expose
    private String expertPlansDetails;
    @SerializedName("expert_plans_price")
    @Expose
    private String expertPlansPrice;
    public String getResponse() {
        return response;
    }
    public String getExpert_plans_id() {
        return expert_plans_id;
    }
    public String getProfessional_id() {
        return professional_id;
    }
    public String getExpert_name() {
        return expert_name;
    }



    public String getExpertPlansName() {
        return expertPlansName;
    }

    public void setExpertPlansName(String expertPlansName) {
        this.expertPlansName = expertPlansName;
    }

    public void setExpert_name(String expert_name) {
        this.expert_name= expert_name;
    }


    public String getExpertPlansDuration() {
        return expertPlansDuration;
    }

    public void setExpertPlansDuration(String expertPlansDuration) {
        this.expertPlansDuration = expertPlansDuration;
    }

    public String getExpertPlansDetails() {
        return expertPlansDetails;
    }

    public void setExpertPlansDetails(String expertPlansDetails) {
        this.expertPlansDetails = expertPlansDetails;
    }

    public String getExpertPlansPrice() {
        return expertPlansPrice;
    }

    public void setExpertPlansPrice(String expertPlansPrice) {
        this.expertPlansPrice = expertPlansPrice;
    }

    public String getPlans_status() {
        return plans_status;
    }

    public void setPlans_status(String plans_status) {
        this.plans_status = plans_status;
    }
    public String getPlan_sups_id() {
        return plan_sups_id;
    }
    public void setPlan_sups_id(String plan_sups_id) {
        this.plan_sups_id = plan_sups_id;
    }

    public String getExpert_plans_sups_price() {
        return expert_plans_sups_price;
    }
    public void setExpert_plans_sups_price(String expert_plans_sups_price) {
        this.expert_plans_sups_price = expert_plans_sups_price;
    }
}
