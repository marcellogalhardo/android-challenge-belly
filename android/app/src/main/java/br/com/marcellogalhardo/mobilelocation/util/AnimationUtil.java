package br.com.marcellogalhardo.mobilelocation.util;

import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AnimationUtil {

    @Inject
    AnimationUtil() {
    }

    public void startImageViewAnimation(@NonNull ImageView imageView, @DrawableRes int animationDrawable) {
        imageView.setBackgroundResource(animationDrawable);
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.start();
    }

    public void stopImageViewAnimation(@NonNull ImageView imageView, @DrawableRes int animationDrawable) {
        imageView.setBackgroundResource(animationDrawable);
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.stop();
    }

}
