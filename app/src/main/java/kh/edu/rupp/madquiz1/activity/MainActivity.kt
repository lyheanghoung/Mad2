import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kh.edu.rupp.madquiz1.R
import kh.edu.rupp.madquiz1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userProfileViewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userProfileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        userProfileViewModel.fetchUserProfile()

        userProfileViewModel.userProfileLiveData.observe(this, { userProfile ->
            binding.apply {
                userName.text = "${userProfile?.firstName} ${userProfile?.lastName}"
                friendCount.text = "${userProfile?.friendCount} friends"
                bio.text = userProfile?.bio

                Glide.with(this@MainActivity)
                    .load(userProfile?.profileImage)
                    .placeholder(R.drawable.placeholder_profile)
                    .error(R.drawable.error_image)
                    .into(profileImage)

                Glide.with(this@MainActivity)
                    .load(userProfile?.coverImage)
                    .placeholder(R.drawable.placeholder_cover)
                    .error(R.drawable.error_image)
                    .into(coverImage)
            }
        })
    }
}
