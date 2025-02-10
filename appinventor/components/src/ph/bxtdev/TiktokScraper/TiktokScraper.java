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

                    // Run on the UI thread to return the result
                    final String finalResponse = response.toString();
                    form.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            return finalResponse;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
              }
            }
        });
    }

    @SimpleFunction(description = "Fetches videos associated with a user")
public String GetAllVideos(final String secureUserId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/user/videos_v2?sec_uid=" + secureUserId);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Fetches videos associated with a music ID")
public String GetMusicVideo(final String musicId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/music/videos?music_id=" + musicId);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Fetches playlist of a user")
public String GetUserPlaylist(final String secureUserId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/user/playlist?sec_uid=" + secureUserId);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Fetches the list of videos liked by a user")
public String GetUserLikes(final String secureUserId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/user/likes?sec_uid=" + secureUserId);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Fetches replies for a specific comment on a video")
public String GetVideoCommentReply(final String commentId, final String videoId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/video/comment_reply?comment_id=" + commentId + "&video_id=" + videoId);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Fetches the list of users a user is following")
public String GetUserFollowing(final String secureUserId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/user/following?sec_uid=" + secureUserId);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Fetches the list of followers of a user")
public String GetUserFollower(final String secureUserId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/user/follower?sec_uid=" + secureUserId);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Fetches videos uploaded by a user")
public String GetUserVideos(final String secureUserId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/user/videos?sec_uid=" + secureUserId);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Fetches info about a user based on their username")
public String GetUserInfo(final String username) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/user/info?user_name=" + username);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Fetches the comments on a video")
public String GetVideoComments(final String videoUrl) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/video/comments?video_url=" + videoUrl);

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

                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     });
   }

   @SimpleFunction(description = "Gets music info")
public String GetMusicInfo(final String musicId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                // Construct the API URL
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/music/info?music_id=" + musicId);

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

                // Run on the UI thread to return the result
                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Gets videos by hashtag")
public String GetHashtagVideos(final String hashtagId) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                // Construct the API URL
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/hashtag/videos?hashtag_id=" + hashtagId);

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

                // Run on the UI thread to return the result
                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}

@SimpleFunction(description = "Gets info about hashtag")
public String GetHashtagInfo(final String hashtag) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                // Construct the API URL
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/hashtag/info?hashtag=" + hashtag);

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

                // Run on the UI thread to return the result
                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
         }
     });
  }

  @SimpleFunction(description = "Gets video without watermark")
public String GetVideoNoWatermark(final String videoUrl) {
    AsynchUtil.runAsynchronously(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                // URL encode the video URL
                String encodedVideoUrl = URLEncoder.encode(videoUrl, StandardCharsets.UTF_8);

                // Construct the API URL
                URL url = new URL("https://tiktok-scraper2.p.rapidapi.com/video/no_watermark?video_url=" + encodedVideoUrl);

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

                // Run on the UI thread to return the result
                final String finalResponse = response.toString();
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        return finalResponse;
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
  }
}
