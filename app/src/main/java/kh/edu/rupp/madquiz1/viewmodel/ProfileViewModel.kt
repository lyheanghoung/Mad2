// UserProfileViewModel.kt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserProfileViewModel : ViewModel() {

    // Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/api/fb-profile.json")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // MutableLiveData for user profile data
    private val _userProfileLiveData = MutableLiveData<UserProfile>()
    val userProfileLiveData: LiveData<UserProfile> get() = _userProfileLiveData

    // Function to fetch user profile data from API
    fun fetchUserProfile() {
        viewModelScope.launch {
            try {
                val response = retrofit.create(ProfileService::class.java).getProfile()
                if (response.isSuccessful) {
                    val userProfile = response.body()
                    _userProfileLiveData.value = userProfile
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}
