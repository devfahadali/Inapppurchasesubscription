package com.example.inapppurchasesubscription

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 *@Author: Fahad Ali
 *@Email:  dev.fahadshabbir@gmail.com
 *@Date: 25/06/2024
 */
fun Context.getStringRes(stringId: Int): String {
    return this.resources.getString(stringId)
}

fun Context.getStringArray(@ArrayRes arrayId: Int): Array<String> {
    return this.resources.getStringArray(arrayId)
}

fun Context.getImageDrawable(@DrawableRes drawableId: Int): Drawable {
    return ContextCompat.getDrawable(this, drawableId)!!
}

fun Context.getInteger(@IntegerRes integerId: Int): Int {
    return this.resources.getInteger(integerId)
}

fun Context.getColour(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}

fun Context.getIntArray(@ArrayRes arrayId: Int): IntArray {
    return resources.getIntArray(arrayId)
}

@SuppressLint("DiscouragedApi")
fun Context.getResourceId(name: String, type: ResourceType): Int {
    return resources.getIdentifier(name, type.value, packageName)
}

fun Context.getColorFromName(name: String): Int {
    val id = getResourceId(name, ResourceType.COLOR)
    return if (id != 0) getColour(id) else -1
}

fun Context.getStringFromName(name: String): String {
    val id = getResourceId(name, ResourceType.STRING)
    return if (id != 0) getString(id) else ""
}

fun Context.getStringFromName(name: String, placeholder: String): String {
    val id = getResourceId(name, ResourceType.STRING)
    return if (id != 0) getString(id, String) else ""
}

fun Context.getDrawable(name: String): Drawable {
    return getImageDrawable(getResourceId(name, ResourceType.DRAWABLE))
}

fun Context.getDimension(name: String): Float {
    val id = getResourceId(name, ResourceType.DIMEN)
    return if (id != 0) resources.getDimension(id) else 0f
}

fun Fragment.getColorFromName(name: String): Int {
    return requireActivity().getColorFromName(name)
}

fun Fragment.getStringFromName(name: String): String {
    return requireActivity().getStringFromName(name)
}

fun Fragment.getStringFromName(name: String, placeHolder: String): String {
    return requireActivity().getStringFromName(name,placeHolder)
}

fun Fragment.getDimension(name: String): Float {
    return requireActivity().getDimension(name)
}
enum class ResourceType(val value: String) {
    STRING("string"), COLOR("color"), DRAWABLE("drawable"), DIMEN("dimen")
}