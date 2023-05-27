package com.example.madproject.Model

class BankModel {

    var id:Int=0
    var bankName:String=""
    var bankPrice:String=""


    constructor()
    constructor(id: Int, bankName: String, bankPrice: String) {
        this.id = id
        this.bankName = bankName
        this.bankPrice = bankPrice
    }

    constructor(bankName: String, bankPrice: String) {
        this.bankName = bankName
        this.bankPrice = bankPrice
    }


}