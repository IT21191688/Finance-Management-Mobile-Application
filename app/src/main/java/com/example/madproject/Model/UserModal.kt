package com.example.madproject.Model

class UserModal {

    var userId=0
    var fName=""
    var email=""
    var phoneNo=""
    var userName=""
    var password=""
    var status=""

    constructor(
        userId: Int,
        fName: String,
        email: String,
        phoneNo: String,
        userName: String,
        password: String,
        status:String
    ) {
        this.userId = userId
        this.fName = fName
        this.email = email
        this.phoneNo = phoneNo
        this.userName = userName
        this.password = password
        this.status=status
    }
    constructor(fName: String, email: String, phoneNo: String, userName: String, password: String,status:String) {
        this.fName = fName
        this.email = email
        this.phoneNo = phoneNo
        this.userName = userName
        this.password = password
        this.status=status
    }
    constructor()
    constructor(
        userId: Int,
        fName: String,
        email: String,
        phoneNo: String,
        userName: String,
        password: String
    ) {
        this.userId = userId
        this.fName = fName
        this.email = email
        this.phoneNo = phoneNo
        this.userName = userName
        this.password = password
    }
}