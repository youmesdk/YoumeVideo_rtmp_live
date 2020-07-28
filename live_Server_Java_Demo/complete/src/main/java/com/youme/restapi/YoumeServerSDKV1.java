package com.youme.restapi;

import com.github.kevinsawicki.http.HttpRequest;

public class YoumeServerSDKV1 {
	private final String baseURL    = "https://api.youme.im:443/v1/";
	private final String appKey     = "YOUME6CBCA84A86A95B5648C45915195F1DB57484D530";
	private final String secretKey  = "2jtyIK+lBS2pjgEmLhr32bp5UxsXPrRNRtcbD80vYSi6OSVUeC7B1EBwKeF2JM3zHmYpR+8QIF60SGeHU1GHlWOQhHUH3KTVz1T4I0cosCJVCKL3b5tPQ977iF0i5rQGAmDD6DAG5MO6k8cnNpplFERgHvTaHpJDo3w4HTcTaIUBAAE=";
	private final String restApikey = "32c628d6938be7dd79bfbd12b4a3ad68";
	private static final YoumeServerSDKV1 instance = new YoumeServerSDKV1();
	private YoumeServerSDKV1 (){}

	public static YoumeServerSDKV1 getInstance(){
        return instance;
    }

	public String getUserToken(String userid){
        String sha1Src = appKey + restApikey + userid;
        String authToken = YMASUtil.getSHA1(sha1Src);
        return authToken;
    }
    
    // HTTP POST
    public String sendPost(String serviceName,String command, String body) throws Exception {
		String url = genUrl(serviceName, command);

        HttpRequest request = HttpRequest.post(url).contentType("application/json").send(body);
        return request.body();

    }
    
    private String genCheckSum(long curtime,String secretKey) {
    	String encodeSrc = secretKey + curtime;
    	
    	// System.out.println("curtime:"+ curtime);
    	// System.out.println("encodeSrc:"+encodeSrc);
    	String checkSum = YMASUtil.getSHA1(encodeSrc);
    	// System.out.println("clientCheckSum:"+checkSum);
    	return checkSum;
    }
    
    private String genUrl(String serviceName,String command) {
    	String url = baseURL + serviceName;
    	url += "/"+command;
    	url += "?appkey=";
    	url += appKey;
    	url += "&identifier=admin";
    	
    	long now = System.currentTimeMillis()/1000;
    	url += "&curtime=";
    	url += now;
    	url += "&checksum=";
    	url += genCheckSum(now,secretKey);
    	
    	return url;
    }
    
}
