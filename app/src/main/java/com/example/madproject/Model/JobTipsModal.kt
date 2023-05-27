package com.example.madproject.Model

class JobTipsModal {

    var tips_id:Int=0
    var jobTipsTitle:String=""
    var jobTipsDiscription:String=""


    constructor()


    constructor(tips_id: Int, jobTipsTitle: String, jobTipsDiscription: String) {
        this.tips_id = tips_id
        this.jobTipsTitle = jobTipsTitle
        this.jobTipsDiscription = jobTipsDiscription
    }

    constructor(jobTipsTitle: String, jobTipsDiscription: String) {
        this.jobTipsTitle = jobTipsTitle
        this.jobTipsDiscription = jobTipsDiscription
    }




}