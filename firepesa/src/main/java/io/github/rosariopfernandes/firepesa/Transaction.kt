package io.github.rosariopfernandes.firepesa

import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.HttpsCallableResult

/**
 * Created by rosariopfernandes on 3/26/18.
 */
class Transaction {
    val functions = FirebaseFunctions.getInstance()

    fun payment(msisdn:String, amount:Float, reference:String,
                thirdPartyReference:String): Task<HttpsCallableResult>? {
        val payload : HashMap<String, String> = HashMap()
        payload.put("input_CustomerMSISDN", msisdn)
        payload.put("input_Amount", "$amount")
        payload.put("input_TransactionReference", reference)
        payload.put("input_ThirdPartyReference", thirdPartyReference)
        return functions.getHttpsCallable("payment")
                .call(payload)
    }

    fun refund(transactionId:String, amount:Float): Task<HttpsCallableResult>? {
        val payload : HashMap<String, String> = HashMap()
        payload.put("input_Amount", "$amount")
        payload.put("input_TransactionID", transactionId)
        return functions.getHttpsCallable("refund")
                .call(payload)
    }

    fun query(queryReference:String): Task<HttpsCallableResult>? {
        val payload : HashMap<String, String> = HashMap()
        payload.put("input_QueryReference", queryReference)
        return functions.getHttpsCallable("query")
                .call(payload)
    }
}