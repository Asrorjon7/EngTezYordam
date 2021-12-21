package info.texnoman.engtezyordamasosiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.gun0912.tedpermission.coroutine.TedPermission
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionLocation()
    }
    private fun permissionLocation() {
        lifecycleScope.launch {
            val permissionResult = TedPermission.create()
                .setRationaleTitle("Geolokatsiyaga ruxsat berig")
                .setRationaleMessage("Ruxsat bering")
                .setDeniedTitle("Permission denied")
                .setDeniedMessage(
                    "If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setGotoSettingButtonText("bla bla")
                .setPermissions(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                )
                .check()

            Log.d("ted", "permissionResult: $permissionResult")
        }
    }
}