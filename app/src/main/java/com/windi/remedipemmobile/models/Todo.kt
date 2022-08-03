package com.windi.remedipemmobile.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Todo (
    var date : Date = Date(0,0,0),
    var title : String = "",
    var description : String = ""
    ) : Parcelable