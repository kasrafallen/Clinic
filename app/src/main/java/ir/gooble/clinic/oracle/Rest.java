package ir.gooble.clinic.oracle;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import ir.gooble.clinic.instance.UserInstance;
import ir.gooble.clinic.model.User;

public class Rest implements Response.Listener<String>, Response.ErrorListener {
    public static final String TAG = "REST_";

    private final static String IP = "http://" + Api.ROUTE;

    private static final int RETRIES = 0;
    private static final int TIMEOUT = 5000;

    private CallBack callBack;
    private Context context;
    private Api api;

    private int statusCode;

    public Rest(Context context, Api api) {
        this.context = context;
        this.api = api;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void connect(CallBack callBack) {
        connect(callBack, null);
    }

    public void connect(CallBack callBack, Object data) {
        this.callBack = callBack;
        if (!isConnected(context)) {
            callBack.onInternet();
            return;
        }
        callBack.onBefore();
        Request request = createClient(api, data);
        Volley.newRequestQueue(context).add(request);
    }

    public static String getUrl(Api api, Object data) {
        String link = api.toString();
        if (link != null && data != null && link.contains("{}") && data instanceof String) {
            link = link.replace("{}", (String) data);
        }
        link = IP + link;
        Log.d(TAG + "called", "returned(" + link + "): " + new Gson().toJson(data));
        return link;
    }

    private Request createClient(Api api, final Object data) {
        int method = 0;
        switch (api.getMethod()) {
            case Method.DELETE:
                method = Request.Method.DELETE;
                break;
            case Method.POST:
                method = Request.Method.POST;
                break;
            case Method.PUT:
                method = Request.Method.PUT;
                break;
            case Method.GET:
                method = Request.Method.GET;
                break;
        }
        String url = getUrl(api, data);
        StringRequest request = new StringRequest(method, url, this, this) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                User user = UserInstance.getUser(context);
                if (user != null && user.getToken() != null) {
                    header.put("Authorization", "Bearer " + user.getToken());
                }
                Log.d(TAG, "getHeaders() returned: " + new Gson().toJson(header));
                return header;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    if (data != null && !(data instanceof String)) {
                        return new Gson().toJson(data).getBytes();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT, RETRIES, 0));
        request.setShouldCache(false);
        return request;
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG + "response", "returned(" + statusCode + "): " + response);
        callBack.onResponse(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String response = null;
        if (error != null && error.networkResponse != null && error.networkResponse.data != null && error.networkResponse.data.length > 0) {
            response = new String(error.networkResponse.data);
            statusCode = error.networkResponse.statusCode;
        } else if (error != null && error.getMessage() != null) {
            response = error.getMessage();
        }
        Log.d(TAG + "onFailure", "returned(" + statusCode + "): " + response);
        callBack.onError(response);
    }
}
