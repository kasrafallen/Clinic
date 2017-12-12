package ir.gooble.clinic.instance;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyInstance {

    private static final VolleyInstance ourInstance = new VolleyInstance();
    private RequestQueue requestQueue;

    public static VolleyInstance getInstance() {
        return ourInstance;
    }

    private VolleyInstance() {

    }

    public RequestQueue getRequestQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }
}
