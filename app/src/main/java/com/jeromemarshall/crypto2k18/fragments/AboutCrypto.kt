package com.jeromemarshall.crypto2k18.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import crypto2k18.R

/**
 * Created by jeromemarshall on 26/2/18.
 */
class AboutCrypto : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.about_impulse, container, false)
        val textContentAbout = view.findViewById<TextView>(R.id.content_about)
        val textTitleAbout = view.findViewById<TextView>(R.id.title_about)
        val imageAbout = view.findViewById<ImageView>(R.id.about_image)
        val imageAboutDesc = view.findViewById<TextView>(R.id.about_image_desc)
        textTitleAbout.text = "Crypto 2K18"
        textContentAbout.text = "A rhythmic Inter- Collegiate State-Level technical fest CRYPTO 2k18 a very special occasion on 25 and 26TH SEPTEMBER 2018, an annual state level technical fest from Adhiyamaan college of Engineering, Hosur.\n" +
                "\n" +
                "CRYPTO 2k18 intends to be a platform for students from all colleges across Tamil Nadu to exhibit and explore their skills and creativity. " +
                "\n" +
                "Almost 10 events have been included in the Technical fest." +
                "\n" +
                "CRYPTO 2k18 hosts a large number of events to cover a number of dimensions of creative technology such as Paper Presentation, Code Error Hunting, Poster Presentation, Ex-Quiz Me, Graphical Design, Mock CID, Marketing, Photography, WordsWorth, PUBG. In the previous edition of CRYPTO, there were 10 colleges with around 100 students who took part in the fest and showcased their talent.\n" +
                "\n" +
                "CRYPTO 2k18 will have a host of events and competitions exploring the various dimensions of technology, which brings out creativity and innovations among the students.\n"
        imageAboutDesc.visibility = View.GONE
        return view
    }
}