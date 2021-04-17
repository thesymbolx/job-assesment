package evans.dale.job_assessment.dependency_injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import evans.dale.job_assessment.room.RoomModel
import evans.dale.job_assessment.room.RoomModelImpl
import evans.dale.job_assessment.service.RoomRepo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(ViewModelComponent::class)
@Module
class ServiceModule {
    @Provides
    fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://ssgmobile.github.io/api/")
            .callFactory(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @ViewModelScoped
    @Provides
    fun getRoomService(client: Retrofit): RoomRepo =
        client.create(RoomRepo::class.java)

    @Provides
    @ViewModelScoped
    fun getRoomRepo(roomService: RoomRepo): RoomModel =
        RoomModelImpl(
            roomService
        )
}