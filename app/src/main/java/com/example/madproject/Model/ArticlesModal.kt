package com.example.madproject.Adapter

class ArticlesModal {
    var article_Id : Int=0
    var article_Title : String=""
    var article_Date : String=""
    var article_description: String=""
    var article_Image :String=""
    constructor()
    constructor(
        article_Id: Int,
        article_Title: String,
        article_Date: String,
        article_description: String,
        article_Image: String
    ) {
        this.article_Id = article_Id
        this.article_Title = article_Title
        this.article_Date = article_Date
        this.article_description = article_description
        this.article_Image = article_Image
    }

    constructor(
        article_Title: String,
        article_Date: String,
        article_description: String,
        article_Image: String
    ) {
        this.article_Title = article_Title
        this.article_Date = article_Date
        this.article_description = article_description
        this.article_Image = article_Image
    }

    constructor(article_Id: Int,article_Title: String, article_Date: String, article_description: String) {
        this.article_Id = article_Id
        this.article_Title = article_Title
        this.article_Date = article_Date
        this.article_description = article_description
    }

}