
package pooja.borkar.user_fitness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpertPlan {
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("expert_plans_id")
    @Expose
    private String expert_plans_id;
    @SerializedName("professional_id")
    @Expose
    private String professional_id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("expert_name")
    @Expose
    private String expert_name ;

    @SerializedName("plan_sups_id")
    @Expose
    private String plan_sups_id ;

    @SerializedName("plans_status")
    @Expose
    private String  plans_status;
    @SerializedName("rate")
    @Expose
    private int  rate;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("comment")
    @Expose
    private String  comment;
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

    @SerializedName("expert_tips_info")
    @Expose
    private String expert_tips_info;
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
    public void setProfessional_id(String professional_id) {
        this.professional_id= professional_id;
    }

    public void setProfession(String profession) {
        this.profession= profession;
    }
    public String getProfession() {
        return profession;
    }

    public String getExpertPlansName() {
        return expertPlansName;
    }

    public void setExpertPlansName(String expertPlansName) {
        this.expertPlansName = expertPlansName;
    }

    public void setRate(int rate) {
        this.rate= rate;
    }
    public int getRate() {
        return rate;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setComment(String comment) {
        this.comment= comment;
    }
    public String getComment() {
        return comment;
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getExpert_tips_info() {
        return  expert_tips_info;
    }
    public void setExpert_tips_info(String  expert_tips_info) {
        this. expert_tips_info =  expert_tips_info;
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
