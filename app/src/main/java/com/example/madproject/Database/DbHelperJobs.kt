package com.example.madproject.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.madproject.Model.JobTipsModal
import com.example.madproject.Model.JobsModal
import com.example.madproject.Model.UserModal

class DbHelperJobs(context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {

        companion object{

            private val DB_NAME="FinanceManagement"
            private val DB_VERSION=5


            //jobs table
            private val TABLE_NAME="jobs"
            private val ID="id"
            private val JOB_NAME="jobName"
            private val COMPANY_NAME="companyName"
            private val JOB_DISCRIPTION="jobDiscription"
            private val SITE_LINK="siteLink"
            private val IMAGE="image"


            //job tips table
            private val TABLE_NAME2="jobsTips"
            private val TIPSID="tipsId";
            private val TIPS_TITLE="tipsName"
            private val TIPS_DISCRIPTION="tipsDiscription"



            //user Table
            private val TABLE_NAME3="users"
            private val USERID="userId"
            private val USERFNAME="userFirstName"
            private val USEREMAIL="userEmail"
            private val USERPHONENO="userPhoneNo"
            private val USERNAME="userName"
            private val PASSWORD="password"
            private val STATUS="status"


        }

        override fun onCreate(p0: SQLiteDatabase?) {

            val CREATE_TABLE="CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY,$JOB_NAME TEXT,$COMPANY_NAME TEXT,$JOB_DISCRIPTION TEXT,$SITE_LINK TEXT,$IMAGE TEXT)"
            p0?.execSQL(CREATE_TABLE);

            val CREATE_TABLE2="CREATE TABLE $TABLE_NAME2 ($TIPSID INTEGER PRIMARY KEY,$TIPS_TITLE TEXT,$TIPS_DISCRIPTION TEXT)"
            p0?.execSQL(CREATE_TABLE2);

            val CREATE_TABLE3="CREATE TABLE $TABLE_NAME3 ($USERID INTEGER PRIMARY KEY,$USERFNAME TEXT,$USEREMAIL TEXT,$USERPHONENO TEXT,$USERNAME TEXT,$PASSWORD TEXT,$STATUS TEXT)"
            p0?.execSQL(CREATE_TABLE3);

        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            val DROP_TABLE="DROP TABLE IF EXISTs $TABLE_NAME"
            p0?.execSQL(DROP_TABLE)
            onCreate(p0)

            val DROP_TABLE2="DROP TABLE IF EXISTs $TABLE_NAME2"
            p0?.execSQL(DROP_TABLE2)
            onCreate(p0)

            val DROP_TABLE3="DROP TABLE IF EXISTs $TABLE_NAME3"
            p0?.execSQL(DROP_TABLE3)
            onCreate(p0)
        }
//job Tips Functions

    fun getAllJobTips():List<JobTipsModal>{
        val jobTipsList=ArrayList<JobTipsModal>()
        val db=writableDatabase
        val selectQuery="SELECT * FROM $TABLE_NAME2"
        val cursor=db.rawQuery(selectQuery,null)

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{

                    val jobtips=JobTipsModal()

                    jobtips.tips_id=cursor.getInt(0)
                    jobtips.jobTipsTitle=cursor.getString(1)
                    jobtips.jobTipsDiscription=cursor.getString(2)

                    jobTipsList.add(jobtips)

                }while(cursor.moveToNext())
            }
        }

        cursor.close()
        return jobTipsList


    }
    fun addJobTips(tip:JobTipsModal):Boolean{

        val db=this.writableDatabase
        val values= ContentValues();
        values.put(TIPS_TITLE,tip.jobTipsTitle)
        values.put(TIPS_DISCRIPTION,tip.jobTipsDiscription)

        val success=db.insert(TABLE_NAME2,null,values)
        db.close()

        return (Integer.parseInt("$success")!=-1)
    }


    fun getJobTip(_id:Int):JobTipsModal{

        val jobTip=JobTipsModal()
        val db=writableDatabase
        val selectQueary="SELECT * FROM $TABLE_NAME2 WHERE $TIPSID=$_id"
        val cursor: Cursor =db.rawQuery(selectQueary,null)


        cursor?.moveToFirst()

        jobTip.tips_id=cursor.getInt(0)
        jobTip.jobTipsTitle=cursor.getString(1)
        jobTip.jobTipsDiscription=cursor.getString(2)


        cursor.close()

        return jobTip;

    }

    fun deleteJob(_id:Int):Boolean{

        val db=this.writableDatabase

        val success=db.delete(TABLE_NAME2, TIPSID+"=?", arrayOf(_id.toString())).toLong()

        db.close()
        return Integer.parseInt("$success")!=-1;

    }

    fun updateJobTip(jobTip: JobTipsModal):Boolean{

        val db=this.writableDatabase
        val values= ContentValues()
        values.put(TIPS_TITLE,jobTip.jobTipsTitle)
        values.put(TIPS_DISCRIPTION,jobTip.jobTipsDiscription)

        val success=db.update(TABLE_NAME2,values, TIPSID+"=?",arrayOf(jobTip.tips_id.toString())).toLong()

        db.close()
        return Integer.parseInt("$success")!=-1

    }




    //Jobs Functions
        fun getAllJobs():List<JobsModal>{
            val userlist=ArrayList<JobsModal>()
            val db=writableDatabase
            val selectQuery="SELECT * FROM $TABLE_NAME"
            val cursor=db.rawQuery(selectQuery,null)

            if(cursor != null){
                if(cursor.moveToFirst()){
                    do{

                        val jobs=JobsModal()

                        jobs.id=cursor.getInt(0)
                        jobs.jobName=cursor.getString(1)
                        jobs.companyName=cursor.getString(2)
                        jobs.jobDiscription=cursor.getString(3)
                        jobs.siteLink=cursor.getString(4)
                        jobs.image=cursor.getString(5)

                        userlist.add(jobs)

                    }while(cursor.moveToNext())
                }
            }

            cursor.close()
            return userlist


        }

        fun addJob(job:JobsModal):Boolean{

            val db=this.writableDatabase
            val values= ContentValues();
            values.put(JOB_NAME,job.jobName)
            values.put(COMPANY_NAME,job.companyName)
            values.put(JOB_DISCRIPTION,job.jobDiscription)
            values.put(SITE_LINK,job.siteLink)
            values.put(IMAGE,job.image)

            val success=db.insert(TABLE_NAME,null,values)
            db.close()

            return (Integer.parseInt("$success")!=-1)
        }



        fun getJob(_id:Int):JobsModal{

            val jobs=JobsModal()
            val db=writableDatabase
            val selectQueary="SELECT * FROM $TABLE_NAME WHERE $ID=$_id"
            val cursor: Cursor =db.rawQuery(selectQueary,null)


            cursor?.moveToFirst()

            jobs.id=cursor.getInt(0)
            jobs.jobName=cursor.getString(1)
            jobs.companyName=cursor.getString(2)
            jobs.jobDiscription=cursor.getString(3)
            jobs.siteLink=cursor.getString(4)
            jobs.image=cursor.getString(5)


            cursor.close()

            return jobs;

        }

        fun delete(_id:Int):Boolean{

            val db=this.writableDatabase

            val success=db.delete(TABLE_NAME,ID+"=?", arrayOf(_id.toString())).toLong()

            db.close()
            return Integer.parseInt("$success")!=-1;

        }

        fun updateJob(job: JobsModal):Boolean{

            val db=this.writableDatabase
            val values= ContentValues()
            values.put(JOB_NAME,job.jobName)
            values.put(COMPANY_NAME,job.companyName)
            values.put(JOB_DISCRIPTION,job.jobDiscription)
            values.put(SITE_LINK,job.siteLink)
            //values.put(IMAGE,job.image)

            val success=db.update(TABLE_NAME,values, ID+"=?",arrayOf(job.id.toString())).toLong()

            db.close()
            return Integer.parseInt("$success")!=-1

        }
    //User table functions



    fun addUser(user:UserModal):Boolean{

        val db=this.writableDatabase
        val values= ContentValues();


        values.put(USERFNAME,user.fName)
        values.put(USEREMAIL,user.email)
        values.put(USERPHONENO,user.phoneNo)
        values.put(USERNAME,user.userName)
        values.put(PASSWORD,user.password)
        values.put(STATUS,user.status)

        val success=db.insert(TABLE_NAME3,null,values)
        db.close()

        return (Integer.parseInt("$success")!=-1)
    }


    fun getOneUser(_id:Int):UserModal{

        val userM=UserModal()
        val db=writableDatabase
        val selectQueary="SELECT * FROM $TABLE_NAME3 WHERE $USERID=$_id"
        val cursor: Cursor =db.rawQuery(selectQueary,null)


        cursor?.moveToFirst()

        userM.userId=cursor.getInt(0)
        userM.fName=cursor.getString(1)
        userM.email=cursor.getString(2)
        userM.phoneNo=cursor.getString(3)
        userM.userName=cursor.getString(4)
        userM.password=cursor.getString(5)
        //userM.status=cursor.getString(6)

        cursor.close()

        return userM;

    }

    fun deleteUser(_id:Int):Boolean{

        val db=this.writableDatabase

        val success=db.delete(TABLE_NAME3, USERID+"=?", arrayOf(_id.toString())).toLong()

        db.close()
        return Integer.parseInt("$success")!=-1;

    }

    fun updateUser(user:UserModal):Boolean{

        val db=this.writableDatabase
        val values= ContentValues()


        values.put(USERFNAME,user.fName)
        values.put(USEREMAIL,user.email)
        values.put(USERPHONENO,user.phoneNo)
        values.put(USERNAME,user.userName)
        values.put(PASSWORD,user.password)
        //values.put(STATUS,user.status)

        val success=db.update(TABLE_NAME3,values, USERID+"=?",arrayOf(user.userId.toString())).toLong()

        db.close()
        return Integer.parseInt("$success")!=-1

    }


    fun validateUser(userName:String,password:String):Boolean{

        //val userM=UserModal()
        val db=writableDatabase
        val selectQueary="SELECT * FROM $TABLE_NAME3 WHERE $USERNAME='$userName' AND $PASSWORD='$password'"
        val cursor: Cursor =db.rawQuery(selectQueary,null)


        cursor?.moveToFirst()

        //userM.userId=cursor.getInt(0)
        var userN=cursor.getString(4)
        var pass=cursor.getString(5)
        var status=cursor.getString(6)


        db.close()

        if(userName.equals(userN)&&password.equals(pass)){
            return true;
        }
        else{
            return false
        }



    }

    fun getUserId(userName:String,password:String):UserModal{

        val userM=UserModal()
        val db=writableDatabase
        val selectQueary="SELECT * FROM $TABLE_NAME3 WHERE $USERNAME='$userName' AND $PASSWORD='$password'"
        val cursor: Cursor =db.rawQuery(selectQueary,null)


        cursor?.moveToFirst()

        userM.userId=cursor.getInt(0)
        userM.fName=cursor.getString(1)
        userM.email=cursor.getString(2)
        userM.phoneNo=cursor.getString(3)
        userM.userName=cursor.getString(4)
        userM.password=cursor.getString(5)
        userM.status=cursor.getString(6)


        db.close()


        return userM;



    }





}

