package com.example.dell.zersey.PHPServer;


import android.content.Context;

public class EventFetch  {
Context context;
public EventFetch(Context context) {
this.context=context;
}

 /*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Void... params) {

        String LINK_USER_ID = "UserEmailId";

        Uri builturi = Uri.parse(new ServerFile().user_cart_fetch).buildUpon().
                appendQueryParameter(LINK_USER_ID,userid)
                .build();
        return new HttpFetch().httpResquest(builturi.toString(), "fetch");

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        spinner.setVisibility(View.GONE);
        Log.v(log_tag,s);
        myJson = s;
        showValues();

    }



private void showValues() {
        try {
        JSONObject jsonObj = new JSONObject(myJson);
        String TAG_SUCCESS = "success";
        success = jsonObj.getInt(TAG_SUCCESS);



        if (success == 1) {


        cart = jsonObj.getJSONArray(TAG_CART);
        int i;

        ArrayList<CartAndroidVersion> android_version = new ArrayList<>();
        for (i = 0; i < cart.length(); i++) {
        JSONObject cart_value = cart.getJSONObject(i);
        CartAndroidVersion androidVersion = new CartAndroidVersion();
        androidVersion.setAndroid_product_name(cart_value.getString(TAG_NAME));
        androidVersion.setAndroid_product_code(cart_value.getString(TAG_CODE));
        androidVersion.setAndroid_o_price(cart_value.getString(TAG_O_PRICE));
        androidVersion.setAndroid_d_price(cart_value.getString(TAG_D_PRICE));
        android_version.add(androidVersion);
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        CartAdapter adapter = new CartAdapter(CartPHPfetch.this,android_version);
        recyclerView.setAdapter(adapter);

        }
        else if (success == 0) {
        tv.setVisibility(View.VISIBLE);
        tv.setText(CartPHPfetch.this.getString(R.string.no_item));
        }
        } catch (JSONException e) {
        e.printStackTrace();
        }

   */     }
