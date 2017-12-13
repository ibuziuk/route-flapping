package ibuziuk.route.flapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RouteFlappingDemo {
    
    // Some doc can be found here:
    // https://stackoverflow.com/questions/2793150/using-java-net-urlconnection-to-fire-and-handle-http-requests

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    private static final String GET_URL = "https://hello-openshift-route-ibuziuk-che.8a09.starter-us-east-2.openshiftapps.com/";
    private static final String KL_TOKEN = "Bearer <token>";

    public static void main(String[] args)
        throws IOException, KeyManagementException, NoSuchAlgorithmException, InterruptedException {
        while (true) {
            sendGET();
            Thread.sleep(1000);
        }
//      sendOKGeT();
    }

    private static void sendGET()
        throws IOException, NoSuchAlgorithmException, KeyManagementException {

      URL obj = new URL(GET_URL);
      HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//      con.setRequestProperty("Authorization", KL_TOKEN);
      con.setRequestProperty("User-Agent", USER_AGENT);
      con.setRequestMethod("GET");
      con.setRequestProperty("Accept", "application/json");
      con.setUseCaches(false);
      con.setDefaultUseCaches(false);
      con.setDoOutput(true);
      con.setDoInput(true);
      int responseCode = con.getResponseCode();
      System.out.println("GET Response Code :: " + responseCode);
      if (responseCode == HttpURLConnection.HTTP_OK) { // success
        BufferedReader in = new BufferedReader(new InputStreamReader(
            con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();
        // print result
        System.out.println(response.toString());
      } else {
        System.out.println("GET request not worked");
      }
    }

    private static void sendOKGeT() throws IOException, InterruptedException {
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder()
          .url(GET_URL)
          .addHeader("Authorization", KL_TOKEN)
          .addHeader("User-Agent", USER_AGENT)
          .build();

      while (true) {
        try (Response response = client.newCall(request).execute()) {
          if (!response.isSuccessful()) {
            System.out.println("Unexpected code " + response);
          }
          System.out.println(response.code());
          Thread.sleep(1000);
        }
      }
    }

}
