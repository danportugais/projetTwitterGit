package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Dan on 10/12/2015.
 */
public class RetrieveTweetsAsyncTask extends AsyncTask<String, Void, List<Tweet>> {

    @Override
    protected List<Tweet> doInBackground(String... login) {

        if (!TextUtils.isEmpty(login[0])) {
            return null;
        } else {
            return TwitterHelper.getTweetsOfUser(login[0]);

        }

    }

    protected void onPostExecute(List<Tweet> tweet) {
        int count = tweet.size(); // iterator fonction
        for (int i = 0; i < count; i++) {
            Log.d("TweetAsyncTask", tweet.get(i).text);
        }
    }


}
