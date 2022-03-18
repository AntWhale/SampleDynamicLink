package com.boo.sample.sampledynamiclink

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.boo.sample.sampledynamiclink.databinding.ActivityMainBinding
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val binding by lazy{ ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link = Uri.parse("https://www.example.com/")
            domainUriPrefix = "https://example.page.link"
            // Open links with this app on Android
            androidParameters {  }
            // Open links with com.example.ios on iOS
            iosParameters("com.example.ios"){}
        }

        val dynamicLinkUri = dynamicLink.uri
    }

    private fun makeDynamicLink2(){
        val dynamicLink = Firebase.dynamicLinks.dynamicLink {
            link = Uri.parse("https://www.example.com/")
            domainUriPrefix = "https://example.page.link"
            androidParameters("com.example.android") {
                minimumVersion = 125
            }
            iosParameters("com.example.ios"){
                appStoreId = "123456789"
                minimumVersion = "1.0.1"
            }
            googleAnalyticsParameters {
                source = "orkut"
                medium = "social"
                campaign = "example-promo"
            }
            itunesConnectAnalyticsParameters {
                providerToken = "123456"
                campaignToken = "example-promo"
            }
            socialMetaTagParameters {
                title = "Example of a Dynamic Link"
                description = "This link works whether the app is installed or not!"
            }
        }


    }
}