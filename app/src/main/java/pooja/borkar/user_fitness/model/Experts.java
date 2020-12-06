package pooja.borkar.user_fitness.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Experts {


    @SerializedName("response")
    @Expose
    private String response;


    @SerializedName("professional_id")
    @Expose
    private String professional_id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("active")
    @Expose
    private String active;


    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName(" profession")
    @Expose
    private String  profession;
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("rating")
    @Expose
    private String rating;



    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public String getProfessional_id() {
        return professional_id;
    }

    public void setProfessional_id(String professional_id) {
        this.professional_id = professional_id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPath(String path) {
        this.phone = path;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.dob = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setProfession(String profession) {
        this.profession= profession;
    }
    public String getProfession() {
        return profession;
    }

    public void setRating(String  rating) {
        this. rating= rating;
    }
    public String getRating() {
        return  rating;
    }


    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String createdAt) {
        this.created_at = created_at;
    }

    public  Experts(String professional_id,String name,String phone ,String image,String rating) {
        this.name = name;
        this.phone = phone;
        this.image= image;
        this.rating= rating;
        this.professional_id= professional_id;


    }

}