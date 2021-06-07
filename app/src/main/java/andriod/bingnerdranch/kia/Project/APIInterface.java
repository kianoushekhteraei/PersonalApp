package andriod.bingnerdranch.kia.Project;

import java.util.ArrayList;
import java.util.List;

import andriod.bingnerdranch.kia.Model.Delete_Item_Tabs_Mdl;
import andriod.bingnerdranch.kia.Model.Info_Edit_Profile_Mdl;
import andriod.bingnerdranch.kia.Model.Info_Users_Mdl;
import andriod.bingnerdranch.kia.Model.Post_Data_Orders_Mdl;
import andriod.bingnerdranch.kia.Model.Post_Data_Whish_List_Mdl;
import andriod.bingnerdranch.kia.Model.Rv_Home_Mdl;
import andriod.bingnerdranch.kia.Model.Slider_Img_Mdl;
import andriod.bingnerdranch.kia.Model.Tab_Favourite_Mdl;
import andriod.bingnerdranch.kia.Model.Tab_Orders_Mdl;
import andriod.bingnerdranch.kia.Model.Verify_Number_Mdl;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("get_Data_slider.php")
    Call<List<Slider_Img_Mdl>> get_Data_slider();

    @GET("getData_whish_list.php")
    Observable<ArrayList<Tab_Favourite_Mdl>> getData_favourite();

    @GET("getData_orders.php")
    Observable<ArrayList<Tab_Orders_Mdl>> getData_orders();

    @GET("getData_new_prd.php")
    Observable<List<Rv_Home_Mdl>> getData_new_prd();

    @GET("getData_most_view.php")
    Observable<List<Rv_Home_Mdl>> getData_most_view();

    @GET("getData_most_off.php")
    Observable<List<Rv_Home_Mdl>> getData_most_off();


    @POST("verify_number.php")
    Call<Verify_Number_Mdl> GetPhoneNumber(@Query("phone_number") String phone_number);

    @POST("getData_user.php")
    Call<Info_Users_Mdl> GetInfoAccount(@Query("name_sign_up") String name_sign_up,
//                                        @Query("image_sign_up") String image_sign_up,
                                        @Query("gender_sign_up") String gender_sign_up,
                                        @Query("city_sign_up") String city_sign_up,
                                        @Query("code_sign_up") String code_sign_up,
                                        @Query("email_sign_up") String email_sign_up);

    @POST("data_Profile.php")
    Call<Info_Edit_Profile_Mdl> GetInfoEdit_Profile(@Query("name_profile") String name_profile,
                                                    @Query("gender_profile") String gender_profile,
//                                                    @Query("image_profile") String image_profile,
                                                    @Query("city_profile") String city_profile,
                                                    @Query("email_profile") String email_profile,
                                                    @Query("code_profile") String code_profile,
                                                    @Query("number_profile") String number_profile);


    @POST("postData_whish_list.php")
    Call<Post_Data_Whish_List_Mdl> Post_Data_Wish_List(@Query("before_price") String before_price,
                                                       @Query("after_price") String after_price,
                                                       @Query("link_img") String link_img,
                                                       @Query("off") String off,
                                                       @Query("name_whish_list") String name_whish_list,
                                                       @Query("description") String description);

    @POST("postData_orders.php")
    Call<Post_Data_Orders_Mdl> Post_Data_orders (@Query("before_price") String before_price,
                                                 @Query("after_price") String after_price,
                                                 @Query("link_img") String link_img,
                                                 @Query("off") String off,
                                                 @Query("name_orders") String name_orders,
                                                 @Query("description") String description);


    @DELETE("delete_item_whish_list.php")
    Call<Delete_Item_Tabs_Mdl>Delete_item_whish_list(@Query("id") String getID_delete_item_wish_list);


    @DELETE("delete_item_orders.php")
    Call<Delete_Item_Tabs_Mdl> Delete_item_Orders(@Query("id") Integer ID_delete_item_orders );

}
