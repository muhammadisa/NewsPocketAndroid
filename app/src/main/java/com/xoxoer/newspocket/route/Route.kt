package com.xoxoer.newspocket.route

import android.content.Context
import android.net.Uri
import com.xoxo.mnp.utils.orion.Orion
import com.xoxo.mnp.utils.orion.OrionContract
import com.xoxoer.newspocket.route.InitialRouteName.DASHBOARD
import com.xoxoer.newspocket.route.InitialRouteName.HEADLINE

object InitialRouteName {
    // replace this with your initial route name
    const val HEADLINE = "headline"
    const val DASHBOARD = "dashboard"
}

class Route internal constructor(
    context: Context,
    private val initial: String
) : Orion(), OrionContract {

    init {
        super.init(context)
    }

    // get string from string resource for app domain
    override val appDomain: String = context.getString(com.xoxoer.newspocket.R.string.app_domain)

    override val routes: Map<String, Uri> = mapOf(
        // deep link format : appdomain://your.desired.activity
        HEADLINE to Uri.parse("$appDomain://headline.news"),
        DASHBOARD to Uri.parse("$appDomain://dashboard.news")
    )

    fun navigate() {
        super.navigate(routes[initial] ?: error(""))
    }

    fun navigate(extraKey: String, extra: Any) {
        val uri = routes[initial] ?: error("")
        super.navigate(uri, extraKey, extra)
    }

    fun navigateNoHistory() {
        super.navigateNoHistory(routes[initial] ?: error(""))
    }

}