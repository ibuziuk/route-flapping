## Investigation of https://github.com/redhat-developer/rh-che/issues/479

After some investigation I think the problem is related to `Connection: keep-alive` header. Here are the demos of running the sample [1] which uses `HttpURLConnection` for pinging route on `api.starter-us-east-2.openshift.com`. By default `Connection: keep-alive` is added to the transport headers and route flapping is not detected:
![connection-keep-alive](https://user-images.githubusercontent.com/1461122/33955163-e31be652-e03a-11e7-8b69-6f3a3a251d1f.png)
Basically, Java does not immediately close the underlaying TCP connection when  the input stream is closed. Instead it keeps it open and tries to reuse it for the next HTTP request to the same server (More details [2]).
In order to disable this behavior `System.setProperty("http.keepAlive","false");` can be used. After disabling keep-alive flapping was successfully detected:
![connection-close](https://user-images.githubusercontent.com/1461122/33955164-e4d22cae-e03a-11e7-9810-b8067e0d8c96.png)

The easiest way to fix it would be adding ` -Dhttp.keepAlive=false` during che-server start-up on osio

[1] https://github.com/ibuziuk/route-flapping
[2] https://stackoverflow.com/questions/4767553/safe-use-of-httpurlconnection
