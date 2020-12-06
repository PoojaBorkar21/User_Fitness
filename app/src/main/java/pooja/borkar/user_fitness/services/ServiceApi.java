package pooja.borkar.user_fitness.services;

import java.util.List;

import  pooja.borkar.user_fitness.model.Expart_name;
import  pooja.borkar.user_fitness.model.ExpertPlan;
import  pooja.borkar.user_fitness.model.Experts;
import  pooja.borkar.user_fitness.model.MySliderList;
import  pooja.borkar.user_fitness.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {
    @GET("register.php")
    Call<User> doRegistration(
            @Query("name") String name,
            @Query("email") String email,
            @Query("gender") String gender,
            @Query("dob") String dob,
            @Query("password") String password,
            @Query("phone") String phone,
            @Query("image") String image
    );

    @GET("login.php")
    Call<User> doLogin(
            @Query("email") String email,
            @Query("password") String password
    );
    @GET("emailv.php")
    Call<User> doverify(
            @Query("otp") String otp

    );

    @GET("admin_banner.php")
    Call<List<MySliderList>> getonbordingdata();

    @POST("profile_update.php")
    @FormUrlEncoded
    Call<User> prof_update(
            @Field("name") String name,
            @Field("email") String email,
            @Field("gender") String gender,
            @Field("dob") String dob,
            @Field("phone") String phone,
            @Field("image") String image
            // @Field("height") String height,
            // @Field("weight") String weight

    );

    @POST("admininfo.php")
    @FormUrlEncoded
    Call<User> getAdmin(
            @Field("email") String email
    );

    @POST("forgotpass.php")
    @FormUrlEncoded
    Call<User> forgotPassword(
            @Field("email") String email
    );



    @GET("prof_types.php")
    Call<List<Expart_name>> getExpertList();


    @POST("prof_plan.php")
    @FormUrlEncoded
    Call<List<ExpertPlan>> getExpertPlan(
            @Field("professional_id") String professional_id
    );

    @POST("profflist.php")
    @FormUrlEncoded
    Call<List<Experts>> expertlist(
            @Field("experties_type_name") String experties_type_name
    );


    @POST("subscribe_plan.php")
    @FormUrlEncoded
    Call<ExpertPlan> subscribe_plan(@Field("email") String email,
                                    @Field("professional_id") String professional_id,
                                    @Field("expert_plans_id") String expert_plans_id,
                                    @Field("plans_price") String expert_plans_sups_price,
                                    @Field("expert_plans_duration") String expert_plans_duration);

    @POST("getMyPlan.php")
    @FormUrlEncoded
    Call<List<ExpertPlan>> getMyPlan(@Field("email") String email);


    @POST("getSubMyPlan.php")
    @FormUrlEncoded
    Call<List<ExpertPlan>> getSubMyPlan(@Field("email") String email);


    @POST("Del_subscribe_plan.php")
    @FormUrlEncoded
    Call<ExpertPlan> Del_subscribe_plan(@Field("plan_sups_id") String plan_sups_id);

    @POST("doRating.php")
    @FormUrlEncoded
    Call<ExpertPlan>doRating(@Field("email")String email,@Field("professional_id") String professional_id,@Field("rate")int rate,@Field("comment") String comment);


    @POST("getExpertTips.php")
    @FormUrlEncoded
    Call<List<ExpertPlan>>getExpertTips(@Field("plan_sups_id")String plan_sups_id);
}

