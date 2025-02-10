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
    public void GetVideoInfo(final String videoUrl, final String id) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/video/info_v2?video_url=" + encodeUrl(videoUrl) + "&video_id=" + id);
    }

    @SimpleFunction(description = "Fetches videos associated with a user")
    public void GetAllVideos(final String secureUserId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/user/videos_v2?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches videos associated with a music ID")
    public void GetMusicVideo(final String musicId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/music/videos?music_id=" + musicId);
    }

    @SimpleFunction(description = "Fetches playlist of a user")
    public void GetUserPlaylist(final String secureUserId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/user/playlist?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches the list of videos liked by a user")
    public void GetUserLikes(final String secureUserId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/user/likes?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches replies for a specific comment on a video")
    public void GetVideoCommentReply(final String commentId, final String videoId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/video/comment_reply?comment_id=" + commentId + "&video_id=" + videoId);
    }

    @SimpleFunction(description = "Fetches the list of users a user is following")
    public void GetUserFollowing(final String secureUserId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/user/following?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches the list of followers of a user")
    public void GetUserFollower(final String secureUserId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/user/follower?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches videos uploaded by a user")
    public void GetUserVideos(final String secureUserId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/user/videos?sec_uid=" + secureUserId);
    }

    @SimpleFunction(description = "Fetches info about a user based on their username")
    public void GetUserInfo(final String username) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/user/info?user_name=" + username);
    }

    @SimpleFunction(description = "Fetches the comments on a video")
    public void GetVideoComments(final String videoUrl) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/video/comments?video_url=" + encodeUrl(videoUrl));
    }

    @SimpleFunction(description = "Gets music info")
    public void GetMusicInfo(final String musicId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/music/info?music_id=" + musicId);
    }

    @SimpleFunction(description = "Gets videos by hashtag")
    public void GetHashtagVideos(final String hashtagId) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/hashtag/videos?hashtag_id=" + hashtagId);
    }

    @SimpleFunction(description = "Gets info about hashtag")
    public void GetHashtagInfo(final String hashtag) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/hashtag/info?hashtag=" + hashtag);
    }

    @SimpleFunction(description = "Gets video without watermark")
    public void GetVideoNoWatermark(final String videoUrl) {
        fetchData("https://tiktok-scraper2.p.rapidapi.com/video/no_watermark?video_url=" + encodeUrl(videoUrl));
    }

    private void fetchData(final String apiUrl) {
        AsynchUtil.runAsynchronously(new Runnable() {
            @Override
            public void run() {
                // ... (Network request code - Same as before.  Use result[0])

                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Got(result[0]); // Trigger the Got event with the result
                    }
                });
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

        @SimpleEvent(description = "Event triggered when data is fetched")
        public void Got(String result) {
          EventDispatcher.dispatchEvent(this, "Got", result);
        }
}
