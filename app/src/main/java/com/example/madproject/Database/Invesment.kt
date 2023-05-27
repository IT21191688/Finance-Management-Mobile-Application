package com.example.madproject.Database

import android.provider.BaseColumns


object Invesment {
    // Table contents are grouped together in an anonymous object.
    object Users : BaseColumns {
        const val COLUMN_ID = "id"
        const val TABLE_NAME = "InvesmentInfo"
        const val COLUMN1 = "Type"
        const val COLUMN2 = "InvesmentName"
        const val COLUMN3 = "StartDate"
        const val COLUMN4 = "EndDate"
        const val COLUMN5 = "Currency"
        const val COLUMN6 = "InvesmentValue"
        const val COLUMN7 = "IncomeValue"

    }

    object Goal : BaseColumns {
        const val COLUMN_ID = "id"
        const val TABLE_NAME = "Goal"
        const val COLUMN1 = "TotalInvesment"
        const val COLUMN2 = "TotalIncome"
        const val COLUMN3 = "MDate"
    }

}

