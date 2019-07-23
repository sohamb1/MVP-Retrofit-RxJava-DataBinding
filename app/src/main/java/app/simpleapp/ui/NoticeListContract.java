package app.simpleapp.ui;

import java.util.ArrayList;

import app.simpleapp.Models.Notice;

public interface NoticeListContract {

    interface View {
        void showProgress();
        void hideProgress();
        void resultFail(Throwable t);
        void resultSuccess(ArrayList<Notice> list);
    }

    interface Presenter {
        void fetchResults();
        void fetchResultsUsingRxJava();
    }
}
