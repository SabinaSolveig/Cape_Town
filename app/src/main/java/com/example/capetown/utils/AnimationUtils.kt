package com.example.capetown.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.RecyclerView

object AnimationUtils {

    fun animateViewAppear(view: View, delay: Long = 0) {
        view.apply {
            alpha = 0f
            translationY = 50f
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(400)
                .setStartDelay(delay)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }

    fun animateRecyclerViewItem(itemView: View, position: Int) {
        itemView.apply {
            alpha = 0f
            translationX = -100f
            animate()
                .alpha(1f)
                .translationX(0f)
                .setDuration(300)
                .setStartDelay((position * 50).coerceAtMost(500).toLong())
                .setInterpolator(OvershootInterpolator(0.5f))
                .start()
        }
    }

    fun animateClick(view: View, onComplete: () -> Unit) {
        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.95f).apply { duration = 100 }
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.95f).apply { duration = 100 }
        val scaleUpX = ObjectAnimator.ofFloat(view, "scaleX", 0.95f, 1f).apply { duration = 150 }
        val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 0.95f, 1f).apply { duration = 150 }

        val animatorSet = AnimatorSet()
        animatorSet.play(scaleDownX).with(scaleDownY)
        animatorSet.play(scaleUpX).with(scaleUpY).after(scaleDownX)
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.start()

        view.postDelayed(onComplete, 250)
    }

    fun animateContentSlideIn(view: View) {
        view.apply {
            alpha = 0f
            translationY = 100f
            animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }
}