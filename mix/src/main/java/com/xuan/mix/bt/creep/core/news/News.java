package com.xuan.mix.bt.creep.core.news;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuan
 * @since 2020/11/1
 */
public class News {

    private String title;
    private String summary;
    private String bodyText;
    private Date date;
    private Map<String, String> tags;

    public void addTag(String key, String value) {
        if (null == tags) {
            tags = new HashMap<>();
        }
        tags.put(key, value);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

}
