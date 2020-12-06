package anuja.divekar.userappfitness.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MySliderList {
    @SerializedName("admin_baner_id")
    @Expose
    private String admin_baner_id;

    @SerializedName("admin_baner_url")
    @Expose
    private String admin_baner_url;

    public MySliderList(String admin_baner_id, String admin_baner_url) {
        this.admin_baner_id = admin_baner_id;
        this.admin_baner_url = admin_baner_url;
    }

    public String getAdminBanerId() {
        return admin_baner_id;
    }

    public void setAdminBanerId(String adminBanerId) {
        this.admin_baner_id = admin_baner_id;
    }

    public String getAdminBanerUrl() {
        return admin_baner_url;
    }

    public void setAdminBanerUrl(String adminBanerUrl) {
        this.admin_baner_url = admin_baner_url;
    }
}
