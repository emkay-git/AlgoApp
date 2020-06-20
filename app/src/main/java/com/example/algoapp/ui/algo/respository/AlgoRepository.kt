package com.example.algoapp.ui.algo.respository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.algoapp.ui.algo.AlgoConstants
import com.example.algoapp.ui.algo.service.AlgoService
import com.example.algoapp.utils.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.algoapp.models.Algo
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AlgoRepository(val app: Application) {

    val algoList  = MutableLiveData<Array<Algo>>();
    init {
        val data = getDataFromCache()
        if(data.isEmpty()) {
            getDataFromAPI();
            println("called api");
        } else {
            algoList.value = data;
        }
    }

    private fun getDataFromAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

    // this makes it a background thread
    @WorkerThread
    suspend fun callWebService() {
        if(networkAvailable()) {
            println("ONLINE **************************")
            val retrofit = Retrofit.Builder()
                .baseUrl(AlgoConstants.ALGO_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
            val service  = retrofit.create(AlgoService::class.java)
            val serviceData = service.getAlgoList().body()?: emptyArray();
            algoList.postValue(serviceData);
            saveToCache(serviceData);
        }
    }

    private fun saveToCache(algoList: Array<Algo>) {
        val moshi = Moshi.Builder().build();
        val arrayType = Types.newParameterizedType(List::class.java,
            Algo::class.java)
        val adapterType: JsonAdapter<List<Algo>>  = moshi.adapter(arrayType);
        val json = adapterType.toJson(algoList.toList());
        FileHelper.saveTextToFile(app,json);
    }

    private fun getDataFromCache(): Array<Algo> {
        val data  = FileHelper.readTextFromFile(app)
        if(data==null) {
            return emptyArray();
        }

        val moshi = Moshi.Builder().build();
        val arrayType = Types.newParameterizedType(List::class.java,
            Algo::class.java)
        val adapterType: JsonAdapter<List<Algo>>  = moshi.adapter(arrayType);
        val list :List<Algo> =  adapterType.fromJson(data)?: emptyList();
        return list.toTypedArray();
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
        val networkInfo = connectivityManager.activeNetworkInfo;
        return networkInfo?.isConnectedOrConnecting?: false;
    }
}