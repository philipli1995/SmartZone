package com.philipli.smartzone.bean;

import com.philipli.smartzone.network.ParamNames;

import java.util.List;

/**
 * Created by philipli on 2017/12/18.
 */

public class BookDetailBean {

    @ParamNames("subtitle")
    private BooksBean.BookBean.RatingBean rating;
    @ParamNames("subtitle")
    private String subtitle;
    @ParamNames("pubdate")
    private String pubdate;
    @ParamNames("origin_title")
    private String origin_title;
    @ParamNames("image")
    private String image;
    @ParamNames("binding")
    private String binding;
    @ParamNames("catalog")
    private String catalog;
    @ParamNames("pages")
    private String pages;
    @ParamNames("images")
    private BooksBean.BookBean.ImageBean images;
    @ParamNames("alt")
    private String alt;
    @ParamNames("id")
    private String id;
    @ParamNames("publisher")
    private String publisher;
    @ParamNames("isbn10")
    private String isbn10;
    @ParamNames("isbn13")
    private String isbn13;
    @ParamNames("title")
    private String title;
    @ParamNames("url")
    private String url;
    @ParamNames("alt_title")
    private String alt_title;
    @ParamNames("author_intro")
    private String author_intro;
    @ParamNames("summary")
    private String summary;
    @ParamNames("price")
    private String price;
    @ParamNames("author")
    private List<String> author;
    @ParamNames("tags")
    private List<BooksBean.BookBean.TagBean> tags;
    @ParamNames("translator")
    private List<String> translator;

    public BooksBean.BookBean.RatingBean getRating() {
        return rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public String getImage() {
        return image;
    }

    public String getBinding() {
        return binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getPages() {
        return pages;
    }

    public BooksBean.BookBean.ImageBean getImages() {
        return images;
    }

    public String getAlt() {
        return alt;
    }

    public String getId() {
        return id;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public String getPrice() {
        return price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public List<BooksBean.BookBean.TagBean> getTags() {
        return tags;
    }

    public List<String> getTranslator() {
        return translator;
    }



}
