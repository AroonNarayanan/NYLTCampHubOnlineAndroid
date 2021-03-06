package org.aacnylt.camphubonline.utils

import android.app.ProgressDialog
import android.content.Context
import android.provider.Settings
import org.aacnylt.camphubonline.models.Course
import org.aacnylt.camphubonline.models.Scout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by Aroon on 2/24/2018.
 */
internal object StaticScoutService {
    // TODO: throw an error if hostURL gets like nullified somehow to force a return to loginmenu
    fun createRetrofitService(): ScoutService {
        val retrofit = Retrofit.Builder()
                .baseUrl(hostUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(ScoutService::class.java)
    }

    var hostUrl = ""

    lateinit var CurrentUser: Scout

    lateinit var CourseList: ArrayList<Course>

    fun createProgressDialog(context: Context, message: String): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.isIndeterminate = true
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setCancelable(false)
        progressDialog.setMessage(message)
        return progressDialog
    }

    fun createUniqueID(seed: String): String {
        val uncleanString = UUID.randomUUID().toString() + seed + Calendar.getInstance().time.time.toString()
        return uncleanString.replace("/", "").replace("?", "").replace("\\", "").replace("&", "").replace("=", "").replace("+", "")
    }
}