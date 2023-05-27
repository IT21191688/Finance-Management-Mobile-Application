package com.example.madproject.Adapter

class NewsModal {
    var news_Id : Int=0
    var news_Title : String=""
    var news_Date : String=""
    var news_description: String=""
    var news_sitelink: String=""
    var news_Image :String=""

    constructor(
        news_Id: Int,
        news_Title: String,
        news_Date: String,
        news_description: String,
        news_sitelink: String,
        news_Image: String
    ) {
        this.news_Id = news_Id
        this.news_Title = news_Title
        this.news_Date = news_Date
        this.news_description = news_description
        this.news_sitelink = news_sitelink
        this.news_Image = news_Image
    }

    constructor(news_Title: String, news_Date: String, news_description: String, news_sitelink: String, news_Image: String
    ) {
        this.news_Title = news_Title
        this.news_Date = news_Date
        this.news_description = news_description
        this.news_sitelink = news_sitelink
        this.news_Image = news_Image
    }

    constructor(news_Id: Int, news_Title: String, news_Date: String, news_description: String, news_sitelink: String
    ) {
        this.news_Id = news_Id
        this.news_Title = news_Title
        this.news_Date = news_Date
        this.news_description = news_description
        this.news_sitelink = news_sitelink
    }
    constructor()
}