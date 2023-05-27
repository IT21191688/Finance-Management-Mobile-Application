package com.example.madproject.Adapter

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DbHelperArticles(context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {
    companion object {
        private val DB_NAME = "FinanceEducation"
        private val DB_VERSION = 4

        //Article table
        private val TABLE_NAME7 = "articles"
        private val ARTICLE_ID = "articleid"
        private val ARTICLE_TITLE= "articletitle"
        private val ARTICLE_DATE = "articlesate"
        private val ARTICLE_DISCRIPTION = "articlediscription"
        private val Artcle_IMAGE = "articleimage"

        //News table
        private val TABLE_NAME8 = "news"
        private val NEWS_ID = "newsid"
        private val NEWS_TITLE= "newstitle"
        private val NEWS_DATE = "newsdate"
        private val NEWS_DISCRIPTION = "newsdiscription"
        private val NEWS_sitelink = "newsitelink"
        private val NEWS_IMAGE = "newsimage"


    }

    override fun onCreate(p0: SQLiteDatabase?) {

        val CREATE_TABLE7 =
            "CREATE TABLE $TABLE_NAME7 ($ARTICLE_ID INTEGER PRIMARY KEY,$ARTICLE_TITLE TEXT,$ARTICLE_DATE TEXT,$ARTICLE_DISCRIPTION TEXT,$Artcle_IMAGE TEXT)"
        p0?.execSQL(CREATE_TABLE7);

        val CREATE_TABLE8 =
            "CREATE TABLE $TABLE_NAME8 ($NEWS_ID INTEGER PRIMARY KEY,$NEWS_TITLE TEXT,$NEWS_DATE TEXT,$NEWS_DISCRIPTION TEXT,$NEWS_sitelink TEXT,$NEWS_IMAGE TEXT)"
        p0?.execSQL(CREATE_TABLE8);

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE7 = "DROP TABLE IF EXISTs $TABLE_NAME7"
        p0?.execSQL(DROP_TABLE7)
        onCreate(p0)

        val DROP_TABLE8 = "DROP TABLE IF EXISTs $TABLE_NAME8"
        p0?.execSQL(DROP_TABLE8)
        onCreate(p0)

    }
//Articles Functions

    fun getAllArtcles(): List<ArticlesModal> {
        val articlesList = ArrayList<ArticlesModal>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME7"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    val artcles = ArticlesModal()

                    artcles.article_Id = cursor.getInt(0)
                    artcles.article_Title = cursor.getString(1)
                    artcles.article_Date = cursor.getString(2)
                    artcles.article_description = cursor.getString(3)
                    artcles.article_Image= cursor.getString(4)

                    articlesList.add(artcles)

                } while (cursor.moveToNext())
            }
        }

        cursor.close()
        return articlesList


    }

    fun addArticles(art: ArticlesModal): Boolean {

        val db = this.writableDatabase
        val values = ContentValues();
        values.put(ARTICLE_TITLE, art.article_Title)
        values.put(ARTICLE_DATE, art.article_Date)
        values.put(ARTICLE_DISCRIPTION, art.article_description)
        values.put(Artcle_IMAGE, art.article_Image)



        val success = db.insert(TABLE_NAME7, null, values)
        db.close()

        return (Integer.parseInt("$success") != -1)
    }


    //fetch
    fun getArticles(_id: Int): ArticlesModal {

        val articles = ArticlesModal()
        val db = writableDatabase
        val selectQueary = "SELECT * FROM $TABLE_NAME7 WHERE $ARTICLE_ID=$_id"
        val cursor: Cursor = db.rawQuery(selectQueary, null)


        cursor?.moveToFirst()

        articles.article_Id = cursor.getInt(0)
        articles.article_Title = cursor.getString(1)
        articles.article_Date = cursor.getString(2)
        articles.article_description = cursor.getString(3)
        articles.article_Image = cursor.getString(4)

        cursor.close()

        return articles;

    }

    //delete
    fun deleteArticle(_id: Int): Boolean {
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME7, ARTICLE_ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1;
    }

    //update
    fun updateArticles(art: ArticlesModal): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ARTICLE_TITLE, art.article_Title)
        values.put(ARTICLE_DATE, art.article_Date)
        values.put(ARTICLE_DISCRIPTION, art.article_description)
        //values.put(Artcle_IMAGE, art.article_Image)

        val success =
            db.update(TABLE_NAME7, values, ARTICLE_ID + "=?", arrayOf(art.article_Id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }


    //News Functions
    fun getAllNews(): List<NewsModal> {
        val newsList = ArrayList<NewsModal>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME8"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val news = NewsModal()
                    news.news_Id = cursor.getInt(0)
                    news.news_Title = cursor.getString(1)
                    news.news_Date = cursor.getString(2)
                    news.news_description = cursor.getString(3)
                    news.news_description = cursor.getString(4)
                    news.news_Image= cursor.getString(5)

                    newsList.add(news)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return newsList
    }
    fun addNews(new: NewsModal): Boolean {

        val db = this.writableDatabase
        val values = ContentValues();
        values.put(NEWS_TITLE, new.news_Title)
        values.put(NEWS_DATE, new.news_Date)
        values.put(NEWS_DISCRIPTION, new.news_description)
        values.put(NEWS_sitelink, new.news_sitelink)
        values.put(NEWS_IMAGE, new.news_Image)

        val success = db.insert(TABLE_NAME8, null, values)
        db.close()
        return (Integer.parseInt("$success") != -1)
    }

    //fetch
    fun getNews(_id: Int): NewsModal {

        val news = NewsModal()
        val db = writableDatabase
        val selectQueary = "SELECT * FROM $TABLE_NAME8 WHERE $NEWS_ID=$_id"
        val cursor: Cursor = db.rawQuery(selectQueary, null)

        cursor?.moveToFirst()
        news.news_Id = cursor.getInt(0)
        news.news_Title = cursor.getString(1)
        news.news_Date = cursor.getString(2)
        news.news_description = cursor.getString(3)
        news.news_sitelink = cursor.getString(4)
        news.news_Image = cursor.getString(5)

        cursor.close()
        return news;
    }

    //delete
    fun deleteNews(_id: Int): Boolean {
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME8, NEWS_ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1;
    }

    //update
    fun updateNews(new: NewsModal): Boolean {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NEWS_TITLE, new.news_Title)
        values.put(NEWS_DATE, new.news_Date)
        values.put(NEWS_DISCRIPTION, new.news_description)
        values.put(NEWS_sitelink, new.news_sitelink)
        //values.put(NEWS_IMAGE, new.news_Image)
        val success =
            db.update(TABLE_NAME8, values, NEWS_ID + "=?", arrayOf(new.news_Id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

}