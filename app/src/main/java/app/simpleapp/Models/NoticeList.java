package app.simpleapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import app.simpleapp.Models.Notice;

public class NoticeList {

    @SerializedName("notice_list")
    private ArrayList<Notice> noticeList;

    public ArrayList<Notice> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(ArrayList<Notice> noticeList) {
        this.noticeList = noticeList;
    }
}
