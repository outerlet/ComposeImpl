package jp.craftman1take.composeimpl.ui.composebyfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.craftman1take.composeimpl.R

class ComposeByFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_by_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ComposeByFragmentFragment(), null)
                .commitNow()
        }
    }
}