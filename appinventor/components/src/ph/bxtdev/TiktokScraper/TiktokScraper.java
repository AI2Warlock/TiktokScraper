package ph.bxtdev.TiktokScraper;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
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
        iconName = "aiwebres/icon.png")

@SimpleObject(external = true)
//Libraries
@UsesLibraries(libraries = "")
//Permissions
@UsesPermissions(permissionNames = "")

public class TiktokScraper extends AndroidNonvisibleComponent {

    private Context context;
    private Activity activity;
    private String key;

    public TiktokScraper(ComponentContainer container){
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }

    @SimpleFunction(description = "Sets API key")
    public void ApiKey(String key){
         this.key = key;
    }

    @SimpleFunction(description = "Gets video info")
    public String GetVideoInfo(String videoUrl, String id) throws IOException {
        AsynchUtil.runAsynchronously(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    // URL encode the video URL
                    String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);

                    // Construct the API URL
                    URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/video/info_v2?video_url=" + encodedVideoUrl + "&video_id=" + id);

                    // Open the connection
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("x-rapidapi-key", key);
                    connection.setRequestProperty("x-rapidapi-host", "tiktok-scraper2.p.rapidapi.com");

                    // Set timeout values
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    // Connect to the API
                    connection.connect();

                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                    reader = new BufferedReader(inputStreamReader);
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    if(form.runOnUiThread){
                            return response.toString();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                        if (connection != null) {
                            connection.disconnect();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return;
        });
    }
}

