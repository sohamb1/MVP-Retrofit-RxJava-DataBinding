package app.simpleapp.Networking;

import java.util.ArrayList;

import app.simpleapp.Models.Notice;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;


public interface GetNoticeDataService {

    @GET("employees")
    Call<ArrayList<Notice>> getNoticeData();

    @GET("employees")
    Observable<ArrayList<Notice>> getNoticeDataUsingRxJava();
}
