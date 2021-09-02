package com.xuan.mix.bt.creep.core.news;

import java.util.ArrayList;
import java.util.List;

import com.xuan.mix.bt.creep.core.Request;
import com.xuan.mix.bt.creep.core.ResponseParser;
import com.xuan.mix.bt.creep.core.Task;

/**
 * @author xuan
 * @since 2020/11/1
 */
public class NewsResources {

    public static List<Task<List<News>>> getTaskList() {
        List<Task<List<News>>> wrapperList = new ArrayList<>();

        wrapperList.add(buildToutiaoFeed());
        wrapperList.add(buildZjQq());

        return wrapperList;
    }

    public static Task<List<News>> buildToutiaoFeed() {
        Task<List<News>> rrw = new Task<>();
        //
        Request request = new Request();
        request.setUrl("https://www.toutiao.com/api/pc/feed/");
        rrw.setRequest(request);
        //
        ResponseParser<List<News>> responsePaser = (response) -> {
            List<News> newsList = new ArrayList<>();

            //JSONObject result = JSON.parseObject(response.getRawContent());
            //JSONArray data = result.getJSONArray("data");
            //for (int i = 0, n = data.size(); i < n; i++) {
            //    JSONObject d = data.getJSONObject(i);
            //
            //    News news = new News();
            //    news.setTitle(d.getString("title"));
            //    news.setSummary(d.getString("abstract"));
            //    news.setDate(new Date(d.getLong("behot_time") * 1000L));
            //
            //    news.addTag("comments_count", d.getString("comments_count"));
            //    news.addTag("source", d.getString("source"));
            //    news.addTag("tag", d.getString("tag"));
            //    newsList.add(news);
            //}

            return newsList;
        };
        rrw.setResponsePaser(responsePaser);
        return rrw;
    }

    public static Task<List<News>> buildZjQq() {
        Task<List<News>> rrw = new Task<>();
        //
        Request request = new Request();
        request.setUrl(
            "https://i.news.qq.com/trpc.qqnews_web.pc_base_srv"
                + ".base_http_proxy/NinjaPageContentSync?pull_urls=news_top_2018");
        rrw.setRequest(request);
        //
        ResponseParser<List<News>> responsePaser = (response) -> {
            List<News> newsList = new ArrayList<>();

            //JSONObject result = JSON.parseObject(response.getRawContent());
            //JSONArray data = result.getJSONArray("data");
            //for (int i = 0, n = data.size(); i < n; i++) {
            //    JSONObject d = data.getJSONObject(i);
            //
            //    News news = new News();
            //    news.setTitle(d.getString("title"));
            //
            //    news.addTag("url", d.getString("url"));
            //    news.addTag("img_url", d.getString("img_url"));
            //    newsList.add(news);
            //}

            return newsList;
        };
        rrw.setResponsePaser(responsePaser);
        return rrw;
    }

}
