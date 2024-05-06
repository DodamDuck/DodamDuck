package org.chosun.dodamduck.utils
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes

fun Context.getDrawableUri(@DrawableRes resId: Int): Uri = Uri.Builder()
    .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
    .authority(packageName)
    .appendPath(resId.toString())
    .build()
