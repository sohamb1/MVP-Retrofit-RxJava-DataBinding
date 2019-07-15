package app.simpleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.simpleapp.Models.Notice;
import app.simpleapp.Models.NoticeList;
import app.simpleapp.Networking.GetNoticeDataService;
import app.simpleapp.Networking.RetrofitInstance;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NoticeAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GetNoticeDataService getNoticeDataService = RetrofitInstance.getRetrofitInstance()
                .create(GetNoticeDataService.class);

        /*Call<ArrayList<Notice>> noticeListCall = getNoticeDataService.getNoticeData();

        noticeListCall.enqueue(new Callback<ArrayList<Notice>>() {
            @Override
            public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {
                generateNoticeList(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("TAG","soham "+t.getMessage());
            }
        });*/

        Observable<ArrayList<Notice>> noticeListCall = getNoticeDataService.getNoticeDataUsingRxJava();

        //Observable<ArrayList<Notice>> noticeListCall2 = getNoticeDataService.getNoticeDataUsingRxJava();

        /*Observable.merge(noticeListCall, noticeListCall2)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::generateNoticeList, this::handleError);*/

        noticeListCall.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::generateNoticeList, this::handleError);
    }

    private void handleError(Throwable t) {
        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }

    private void working() {
        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }


    /** Method to generate List of notice using RecyclerView with custom adapter*/
    private void generateNoticeList(ArrayList<Notice> noticeArrayList) {
        if (noticeArrayList != null && noticeArrayList.size() != 0) {
            Toast.makeText(this, noticeArrayList.size()+"", Toast.LENGTH_LONG).show();
            recyclerView = findViewById(R.id.recycler_view_notice_list);
            adapter = new NoticeAdapter(noticeArrayList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }
}
