package com.zeynelerdi.mackolik.util.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**Examples**/
/**
 * imgView.loadImage(context, "...url...", R.drawable.placeholder)
 * imgView.loadImage(context, "...url...", null)
 *
 * parameters -->
 * context -> not null
 * url -> should be url
 * placeholder -> can be null
 */
fun ImageView.loadImage(
    context: Context,
    url: String,
    placeholder: Drawable?
) {
    if (placeholder != null) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .thumbnail(0.5f)
            .centerCrop()
            .into(this)
    } else {
        Glide.with(context)
            .load(url)
            .thumbnail(0.5f)
            .centerCrop()
            .into(this)
    }
}

/**Examples**/
/**
 * imgView.loadImage(context,R.drawable.image, R.drawable.placeholder)
 * imgView.loadImage(context, "...url...", null)
 *
 * parameters -->
 * context -> not null
 * url -> should be url
 * placeholder -> can be null
 */
fun ImageView.loadImage(
    context: Context,
    url: Drawable,
    placeholder: Drawable?
) {
    if (placeholder != null) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .thumbnail(0.5f)
            .centerCrop().into(this)
    } else {
        Glide.with(context)
            .load(url)
            .thumbnail(0.5f)
            .centerCrop()
            .into(this)
    }
}

/**Examples**/
/**
 * imgView.loadCircleImage(context,R.drawable.image, R.drawable.placeholder)
 * imgView.loadCircleImage(context, "...url...", null)
 *
 * parameters -->
 * context -> not null
 * url -> drawable file
 * placeholder -> can be null
 */
fun ImageView.loadCircleImage(
    context: Context,
    url: String,
    placeholder: Drawable?
) {
    if (placeholder != null) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .thumbnail(0.5f)
            .fitCenter()
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    } else {
        Glide.with(context)
            .load(url)
            .thumbnail(0.5f)
            .fitCenter()
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}

/**Examples**/
/**
 * imgView.loadCircleImage(context,R.drawable.image, R.drawable.placeholder)
 * imgView.loadCircleImage(context, "...url...", null)
 *
 * parameters -->
 * context -> not null
 * url -> drawable file
 * placeholder -> can be null
 */
fun ImageView.loadCircleImage(
    context: Context,
    url: Drawable,
    placeholder: Drawable?
) {
    if (placeholder != null) {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .circleCrop()
            .placeholder(placeholder)
            .thumbnail(0.5f)
            .into(this)
    } else {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .circleCrop()
            .thumbnail(0.5f)
            .into(this)
    }
}