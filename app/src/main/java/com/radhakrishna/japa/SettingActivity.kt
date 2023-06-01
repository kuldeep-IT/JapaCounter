package com.radhakrishna.japa

import android.content.DialogInterface
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ConsumeResponseListener
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.radhakrishna.japa.MainActivity.Companion.IS_RESET
import com.radhakrishna.japa.databinding.ActivitySettingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingActivity : AppCompatActivity(), View.OnClickListener {

    /*
    * https://developer.android.com/google/play/billing/integrate
    * */

    lateinit var binding: ActivitySettingBinding
    lateinit var actionBar: ActionBar

    lateinit var sharedP: SharedPreferences
    lateinit var myEdit: SharedPreferences.Editor

    private lateinit var billingClient: BillingClient

    lateinit var itemInfo10 : ProductDetails
    lateinit var itemInfo20 : ProductDetails
    lateinit var itemInfo30 : ProductDetails
    lateinit var itemInfo40 : ProductDetails
    lateinit var itemInfo50 : ProductDetails
    lateinit var itemInfo60 : ProductDetails
    lateinit var itemInfo70 : ProductDetails
    lateinit var itemInfo80 : ProductDetails
    lateinit var itemInfo90 : ProductDetails
    lateinit var itemInfo100 : ProductDetails
    lateinit var itemInfo1000 : ProductDetails


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SettingTheme)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->
                // To be implemented in a later section.
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                    for (purchase in purchases) {
                        CoroutineScope(Dispatchers.IO).launch {
                            handlePurchase(purchase)
                        }

                    }
                } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
                    // Handle an error caused by a user cancelling the purchase flow.
                } else {
                    // Handle any other error codes.
                }

            }

        billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()

        connectToGooglePlayBilling()


        actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#CCCCFF")))
        actionBar.title = "Settings"

        sharedP = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        myEdit = sharedP.edit()

        binding.reset.setOnClickListener(this)
        binding.btn10.setOnClickListener(this)
        binding.btn20.setOnClickListener(this)
        binding.btn30.setOnClickListener(this)
        binding.btn40.setOnClickListener(this)
        binding.btn50.setOnClickListener(this)
        binding.btn60.setOnClickListener(this)
        binding.btn70.setOnClickListener(this)
        binding.btn80.setOnClickListener(this)
        binding.btn90.setOnClickListener(this)
        binding.btn100.setOnClickListener(this)
        binding.btn1000.setOnClickListener(this)

    }


    private fun connectToGooglePlayBilling() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    getProductDetails()
                }
            }

            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.

                connectToGooglePlayBilling()

            }
        })
    }

    private fun getProductDetails() {

        val product1 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_10")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product2 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_20")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product3 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_30")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product4 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_40")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product5 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_50")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product6 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_60")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product7 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_70")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product8= QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_80")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product9 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_90")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product10 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_100")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        val product11 = QueryProductDetailsParams.Product.newBuilder()
            .setProductId("item_1000")
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        var productList = ArrayList<QueryProductDetailsParams.Product>();
        productList.add( product1 )
        productList.add( product2 )
        productList.add( product3 )
        productList.add( product4 )
        productList.add( product5 )
        productList.add( product6 )
        productList.add( product7 )
        productList.add( product8 )
        productList.add( product9 )
        productList.add( product10 )
        productList.add( product11 )



        val queryProductDetailsParams =
            QueryProductDetailsParams.newBuilder()
                .setProductList(productList)
                .build()


        billingClient.queryProductDetailsAsync(queryProductDetailsParams) { billingResult,
                                                                            productDetailsList ->
            // check billingResult
            // process returned productDetailsList

            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK
                && productDetailsList != null
            ) {
                itemInfo10 = productDetailsList[0];
                itemInfo100 = productDetailsList[1]
                itemInfo1000 = productDetailsList[2]
                itemInfo20 = productDetailsList[3]
                itemInfo30 = productDetailsList[4]
                itemInfo40 = productDetailsList[5]
                itemInfo50 = productDetailsList[6]
                itemInfo60 = productDetailsList[7]
                itemInfo70 = productDetailsList[8]
                itemInfo80 = productDetailsList[9]
                itemInfo90 = productDetailsList[10]
            }

        }
    }


    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun launchPurchaseFlow(itemInfo: ProductDetails) {

        val productDetailsParamsList = listOf(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                // retrieve a value for "productDetails" by calling queryProductDetailsAsync()
                .setProductDetails(itemInfo)
                // to get an offer token, call ProductDetails.subscriptionOfferDetails()
                // for a list of offers that are available to the user
//                .setOfferToken(selectedOfferToken)
                .build()
        )

        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()

// Launch the billing flow
        val billingResult = billingClient.launchBillingFlow(this@SettingActivity, billingFlowParams)
        Log.d(
            "My_KRISHNA",
            "res code: " + billingResult.responseCode + " message: " + billingResult.debugMessage
        )
    }

    private suspend fun handlePurchase(purchase: Purchase?) {

        val consumeParams =
            ConsumeParams.newBuilder()
                .setPurchaseToken(purchase!!.getPurchaseToken())
                .build()

        val listener: ConsumeResponseListener = object : ConsumeResponseListener {
            override fun onConsumeResponse(billingResult: BillingResult, purchaseToken: String) {
                if (purchase.purchaseState === Purchase.PurchaseState.PURCHASED) {
                    if (!purchase.isAcknowledged) {
                        Toast.makeText(
                            this@SettingActivity,
                            "Thank You for your kind donation!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }

        withContext(Dispatchers.IO) {
            billingClient.consumeAsync(consumeParams, listener)
            Log.d("MY_KRISHNA", "handlePurchase: consumeResult CALLED function")

        }

//        Log.d("MY_KRISHNA", "handlePurchase: consumeResult "+consumeResult)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.reset -> {
                MaterialAlertDialogBuilder(
                    this,
                    R.style.RoundShapeAppearance
                ).setTitle("Reset Japa Counter")
                    .setMessage("All the count has been reset to 0.")
                    .setPositiveButton("Reset", DialogInterface.OnClickListener { dialog, which ->
                        myEdit.putString(MainActivity.TODAY_JAPA, "0")
                        myEdit.putString(MainActivity.TOTAL_JAPA, "0")

                        myEdit.commit()

                        IS_RESET = true

//                        showToast("Data is successfully Reset")
                        dialog.dismiss()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    }).show()

            }

            R.id.btn10 -> {
                launchPurchaseFlow(itemInfo10);
            }

            R.id.btn20 -> {
                launchPurchaseFlow(itemInfo20);
            }

            R.id.btn30 -> {
                launchPurchaseFlow(itemInfo30);
            }

            R.id.btn40 -> {
                launchPurchaseFlow(itemInfo40);
            }

            R.id.btn50 -> {
                launchPurchaseFlow(itemInfo50);
            }

            R.id.btn60 -> {
                launchPurchaseFlow(itemInfo60);
            }

            R.id.btn70 -> {
                launchPurchaseFlow(itemInfo70);
            }

            R.id.btn80 -> {
                launchPurchaseFlow(itemInfo80);
            }

            R.id.btn90 -> {
                launchPurchaseFlow(itemInfo90);
            }
            R.id.btn100 -> {
                launchPurchaseFlow(itemInfo100);
            }

            R.id.btn1000 -> {
                launchPurchaseFlow(itemInfo1000);
            }






        }
    }


}