package com.yesferal.hornsapp.app.presentation.ui.profile

import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.pm.PackageInfoCompat
import androidx.fragment.app.Fragment
import com.yesferal.hornsapp.app.BuildConfig
import com.yesferal.hornsapp.app.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment
    : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpVersion()

        setUpShare()
    }

    private fun setUpShare() {
        shareTextView.setImageView(R.drawable.ic_share)
        shareTextView.setText(getString(R.string.share_with_friends), getString(R.string.use_your_favorite_apps))
        shareTextView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_hornsapp_message))
            intent.type = "text/plain"
            startActivity(intent)
        }
    }

    private fun setUpVersion() {
        activity?.let { activityNonNull ->
            val packageInfo: PackageInfo = activityNonNull
                .packageManager
                .getPackageInfo(activityNonNull.packageName, 0)

            val versionName: String = packageInfo.versionName
            val versionCode: Long = PackageInfoCompat.getLongVersionCode(packageInfo)

            versionTextView.setImageView(R.drawable.ic_information)
            val version = StringBuilder()
                .append(versionName)
                .append(".")
                .append(versionCode)

            if (BuildConfig.DEBUG) {
                version.append("-DEV")
            }

            versionTextView.setText(getString(R.string.version), version.toString())
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}