package ph.bxtdev.TiktokScraper;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.util.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@DesignerComponent(
        version = 1,
        description = "TikTok Scraper Extension",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "")
@SimpleObject(external = true)
// Libraries
@UsesLibraries(libraries = "")
// Permissions
@UsesPermissions(permissionNames = "")

public class TiktokScraper extends AndroidNonvisibleComponent {

    private Context context;
    private Activity activity;
    private String key;

    public TiktokScraper(ComponentContainer container) {
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }

    @SimpleFunction(description = "Sets API key")
    public void ApiKey(String key) {
        this.key = key;
    }

    @SimpleFunction(description = "Gets video info")
    public String GetVideoInfo(final String videoUrl, final String id) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/video/info_v2?video_url=" + encodeUrl(videoUrl) + "&video_id=" + id);
    }

    @SimpleFunction(description = "Fetches videos associated with a user")
    public String GetAllVideos(final String secureUserId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/user/videos_v2?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches videos associated with a music ID")
    public String GetMusicVideo(final String musicId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/music/videos?music_id=" + musicId);
    }

    @SimpleFunction(description = "Fetches playlist of a user")
    public String GetUserPlaylist(final String secureUserId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/user/playlist?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches the list of videos liked by a user")
    public String GetUserLikes(final String secureUserId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/user/likes?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches replies for a specific comment on a video")
    public String GetVideoCommentReply(final String commentId, final String videoId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/video/comment_reply?comment_id=" + commentId + "&video_id=" + videoId);
    }

    @SimpleFunction(description = "Fetches the list of users a user is following")
    public String GetUserFollowing(final String secureUserId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/user/following?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches the list of followers of a user")
    public String GetUserFollower(final String secureUserId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/user/follower?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches videos uploaded by a user")
    public String GetUserVideos(final String secureUserId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/user/videos?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches info about a user based on their username")
    public String GetUserInfo(final String username) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/user/info?user_name=" + username);
    }

    @SimpleFunction(description = "Fetches the comments on a video")
    public String GetVideoComments(final String videoUrl) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/video/comments?video_url=" + encodeUrl(videoUrl));
    }

    @SimpleFunction(description = "Gets music info")
    public String GetMusicInfo(final String musicId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/music/info?music_id=" + musicId);
    }

    @SimpleFunction(description = "Gets videos by hashtag")
    public String GetHashtagVideos(final String hashtagId) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/hashtag/videos?hashtag_id=" + hashtagId);
    }

    @SimpleFunction(description = "Gets info about hashtag")
    public String GetHashtagInfo(final String hashtag) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/hashtag/info?hashtag=" + hashtag);
    }

    @SimpleFunction(description = "Gets video without watermark")
    public String GetVideoNoWatermark(final String videoUrl) {
        return fetchData("https://tiktok-scraper2.p.rapidapi.com/video/no_watermark?video_url=" + encodeUrl(videoUrl));
    }


    private String fetchData(final String apiUrl) {
        AsynchUtil.runAsynchronously(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(apiUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("x-rapidapi-key", key);
                    connection.setRequestProperty("x-rapidapi-host", "tiktok-scraper2.p.rapidapi.com");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.connect();

                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                    reader = new BufferedReader(inputStreamReader);
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    final String result = response.toString();

                    form.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            return result;
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        });
    }


    private String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
