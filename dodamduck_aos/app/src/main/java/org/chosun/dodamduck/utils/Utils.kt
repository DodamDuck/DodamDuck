package org.chosun.dodamduck.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.chosun.dodamduck.utils.Utils.formatDateDiff
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

object Utils {
    fun String.formatDateDiff(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val inputDate = LocalDateTime.parse(this, formatter)
        val currentDate = LocalDateTime.now()

        val daysDiff = ChronoUnit.DAYS.between(inputDate, currentDate)
        val hoursDiff = ChronoUnit.HOURS.between(inputDate, currentDate)
        val minutesDiff = ChronoUnit.MINUTES.between(inputDate, currentDate)

        return when {
            daysDiff > 0 -> "${daysDiff}일 전"
            hoursDiff > 0 -> "${hoursDiff}시간 전"
            minutesDiff > 0 -> "${minutesDiff}분 전"
            else -> "방금 전"
        }
    }

    fun String.convertDateTime(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("a hh시 mm분", Locale.KOREAN)
        val date: Date = inputFormat.parse(this) ?: return "Invalid Date"

        return outputFormat.format(date)
    }

    fun getCurrentDateTimeFormatted(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일시")
        return current.format(formatter)
    }

    fun Uri.uriToBitmap(context: Context): Bitmap? {
        return try {
            context.contentResolver.openInputStream(this)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun Uri.uriToMultipartBody(context: Context): MultipartBody.Part {
        val inputStream = context.contentResolver.openInputStream(this)
        val requestBody = inputStream?.readBytes()?.toRequestBody("image/jpg".toMediaTypeOrNull())
        val filename = "upload.jpg"
        val filePart = MultipartBody.Part.createFormData("image", filename, requestBody!!)
        return filePart
    }

    fun String.getUserProfileUrl(): String
        = "http://sy2978.dothome.co.kr/userProfile/user_id_${this}.jpg"

    fun String.ellipsis(maxLength: Int): String {
        return if (this.length > maxLength) this.substring(0, maxLength) + "..."
        else this
    }

}