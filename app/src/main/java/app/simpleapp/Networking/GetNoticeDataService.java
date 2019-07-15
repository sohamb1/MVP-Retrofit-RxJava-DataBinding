package app.simpleapp.Networking;

import java.util.ArrayList;

import app.simpleapp.Models.Notice;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GetNoticeDataService {

    @GET("employees")
    Call<ArrayList<Notice>> getNoticeData();

    @GET("employees/{id}")
    Call<Boolean> getValue(@Path("id") String id);

    @GET("employees")
    Observable<ArrayList<Notice>> getNoticeDataUsingRxJava();
}
