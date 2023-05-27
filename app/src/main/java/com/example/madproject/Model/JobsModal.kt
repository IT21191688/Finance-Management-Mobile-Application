package com.example.madproject.Model

class JobsModal {

    var id:Int=0
    var jobName:String=""
    var companyName:String=""
    var jobDiscription:String=""
    var siteLink:String=""
    var image:String=""

    constructor()

    constructor(
        id: Int,
        jobName: String,
        companyName: String,
        jobDiscription: String,
        siteLink:String,
        image: String
    )
    {
        this.id = id
        this.jobName = jobName
        this.companyName = companyName
        this.jobDiscription = jobDiscription
        this.siteLink=siteLink
        this.image = image
    }

    constructor(jobName: String, companyName: String, jobDiscription: String,siteLink: String, image: String) {
        this.jobName = jobName
        this.companyName = companyName
        this.jobDiscription = jobDiscription
        this.siteLink=siteLink
        this.image = image
    }

    constructor( id: Int,jobName: String, companyName: String, jobDiscription: String,siteLink: String) {
        this.id = id
        this.jobName = jobName
        this.companyName = companyName
        this.jobDiscription = jobDiscription
        this.siteLink=siteLink
    }


}