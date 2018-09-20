package com.jeromemarshall.crypto2k18.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import crypto2k18.R

/**
 * Created by jeromemarshall on 26/2/18.
 */
class Social : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.about_social, container, false)
        val rating = view.findViewById<LinearLayout>(R.id.play_store)
        val facebook = view.findViewById<LinearLayout>(R.id.facebook)
        val gmail = view.findViewById<LinearLayout>(R.id.gmail)
        val youtube = view.findViewById<LinearLayout>(R.id.youtube)
        val youtube_id = "https://www.youtube.com/channel/UCAsIqHyPJPJVZ9JKICnRiZw"
        val facebookUrl = "https://www.facebook.com/Crypto2k18/"
        val ratingUrl = ""
        facebook.setOnClickListener({ openUrl(facebookUrl) })
        rating.setOnClickListener({ openUrl(ratingUrl) })
        gmail.setOnClickListener({ composeEmail(arrayOf("cryptocse2k18@gmail.com")) })
        youtube.setOnClickListener({ openUrl(youtube_id) })
        view.findViewById<LinearLayout>(R.id.web_url)
                .setOnClickListener({ openUrl("http://www.crypto2k18.in") })
        return view
    }

    fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        val chooser = Intent.createChooser(intent, "Connect with us")
        if (intent.resolveActivity((context?.packageManager)) != null) {
            startActivity(chooser)
        }
    }

    fun composeEmail(address: Array<String>) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, address)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tell us your issues or send us a feedback " +
                "we appreciate all your effort to make us better \n #CRYPTO'18")
        if (intent.resolveActivity(context?.packageManager) != null) {
            startActivity(intent)
        }
    }
}

