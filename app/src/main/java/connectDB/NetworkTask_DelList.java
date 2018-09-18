package connectDB;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import listview.CommentItem;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkTask_DelList extends AsyncTask<CommentItem,Void,Integer>{

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    String url = "http://192.168.0.19:8080/comment/delList";
    String json;

    @Override
    protected Integer doInBackground(CommentItem... commentItems) {
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("num",commentItems[0].getNum());
        object.addProperty("password",commentItems[0].getPassword());
        json = gson.toJson(object);
        RequestBody body = RequestBody.create(JSON,json);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
