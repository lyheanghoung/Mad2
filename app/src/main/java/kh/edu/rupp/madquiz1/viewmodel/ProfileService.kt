import android.provider.ContactsContract
import retrofit2.Response
import retrofit2.http.GET

interface ProfileService {
    @GET("api/fb-profile.json")
    suspend fun getProfile(): Response<ContactsContract.Profile>
}
