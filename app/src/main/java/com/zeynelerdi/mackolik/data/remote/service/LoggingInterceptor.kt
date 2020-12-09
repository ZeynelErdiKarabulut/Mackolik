package  com.zeynelerdi.mackolik.data.remote.service

import com.zeynelerdi.mackolik.BuildConfig
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.net.ProtocolException

class LoggingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Logger.i(
            String.format(
                "Sending Request %s on %s%n%s",
                request.url(),
                chain.connection(),
                request.headers()
            )
        )

        val requestBuilder = request.newBuilder().method(request.method(), request.body())
        /*if (request.url().toString().contains(BuildConfig.BASE_URL)) {
            requestBuilder.header("X-APP-ID", BuildConfig.APPLICATION_ID)
            requestBuilder.addHeader("X-APP-KEY", BuildConfig.X_APP_KEY)
        } else {
            requestBuilder.header("X-APP-ID", "")
            requestBuilder.addHeader("X-APP-KEY", "")
        }*/

        val newRequest = requestBuilder.build()

        val response: Response
        response = try {
            chain.proceed(newRequest)
        } catch (e: ProtocolException) {
            Response.Builder()
                .request(request)
                .code(204)
                .protocol(Protocol.HTTP_1_1)
                .build()
        }

        var rawJson: String? = ""
        try {
            rawJson = response.body()!!.string()
            if (rawJson != null) {
                Logger.json(rawJson)
            }
        } catch (e: Exception) {
            Logger.e("Null response body")
        }

        return response.newBuilder()
            .body(ResponseBody.create(response.body()!!.contentType(), rawJson!!)).build()
    }

}