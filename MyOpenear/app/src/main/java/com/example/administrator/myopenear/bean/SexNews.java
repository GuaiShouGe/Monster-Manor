package com.example.administrator.myopenear.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
@Table(name = "sexnew")
public class SexNews extends Model{

    /**
     * feed : http://img.wdjimg.com/image/video/b65ced4f0f559817e35922b6b8413856_0_0.jpeg
     * description : 如果一个朋友连续三年愚人节拿同样一套把戏骗你，你应该会很不屑吧。嘿，不要想得那么简单哦！如果你遇到一个朋友像这部短片主角这么拼的话，你能不能接住招还不一定呢~ From nigahiga
     * category : 剧情
     * title : 愚人节最强骗局
     * blurred : http://img.wdjimg.com/image/video/149a44aba04b4c3c8b84dbead13fc80e_0_0.jpeg
     * playUrl : http://baobab.wdjcdn.com/1459345626907yurenjie_x264.mp4
     */
    @Column(name = "feed")
    private String feed;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "title",unique = true)
    private String title;
    @Column(name = "blurred")
    private String blurred;
    @Column(name = "playUrl")
    private String playUrl;

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getFeed() {
        return feed;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getBlurred() {
        return blurred;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    @Override
    public String toString() {
        return "SexNews{" +
                "feed='" + feed + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", blurred='" + blurred + '\'' +
                ", playUrl='" + playUrl + '\'' +
                '}';
    }
}
