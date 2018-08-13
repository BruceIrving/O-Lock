package hotel.cyut.im.o_lock;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Bruce on 2018/6/12.
 */

class AndroidLoginController extends Application {
    public static final String TAG = AndroidLoginController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private static AndroidLoginController mInstance;

    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AndroidLoginController getmInstance(){
        return mInstance;
    }

    public RequestQueue getmRequestQueue() {
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getmRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getmRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
