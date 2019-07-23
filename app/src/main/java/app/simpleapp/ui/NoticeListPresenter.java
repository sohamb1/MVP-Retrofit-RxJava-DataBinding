package app.simpleapp.ui;

import java.util.ArrayList;

import app.simpleapp.Models.Notice;
import app.simpleapp.Networking.GetNoticeDataService;
import app.simpleapp.Networking.RetrofitInstance;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeListPresenter implements NoticeListContract.Presenter{

    private NoticeListContract.View mainActivityView;
    private GetNoticeDataService getNoticeDataService;

    public NoticeListPresenter(NoticeListContract.View mainActivityView) {
        this.mainActivityView = mainActivityView;
        getNoticeDataService = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);
    }

    @Override
    public void fetchResultsUsingRxJava() {
        Observable<ArrayList<Notice>> noticeListCall = getNoticeDataService.getNoticeDataUsingRxJava();
        noticeListCall.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mainActivityView::resultSuccess, mainActivityView::resultFail);
    }

    @Override
    public void fetchResults() {

        Call<ArrayList<Notice>> noticeListCall = getNoticeDataService.getNoticeData();
        noticeListCall.enqueue(new Callback<ArrayList<Notice>>() {
            @Override
            public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {
                mainActivityView.resultSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {
                mainActivityView.resultFail(t);
            }
        });
    }
}
