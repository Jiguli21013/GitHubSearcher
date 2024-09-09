package com.yanchelenko.githubsearcher.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri

object IntentUtils {
    fun openUserRepository(context: Context, htmlUrl: String) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(htmlUrl))

        val packageManager = context.packageManager
        val resolveInfoList: List<ResolveInfo> = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)

        val browsers = resolveInfoList.filter { resolveInfo ->
            val packageName = resolveInfo.activityInfo.packageName
            packageName.contains("browser", ignoreCase = true) || packageName.contains("chrome", ignoreCase = true) || packageName.contains("firefox", ignoreCase = true)
        }

        val targetedIntents = mutableListOf<Intent>()

        for (resolveInfo in browsers) {
            val packageSpecificIntent = Intent(Intent.ACTION_VIEW)
            packageSpecificIntent.data = Uri.parse(htmlUrl)
            packageSpecificIntent.setPackage(resolveInfo.activityInfo.packageName)
            targetedIntents.add(packageSpecificIntent)
        }

        val chooserIntent = Intent.createChooser(targetedIntents.removeAt(0), "Выберите браузер")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedIntents.toTypedArray())

        context.startActivity(chooserIntent)

    }
}
