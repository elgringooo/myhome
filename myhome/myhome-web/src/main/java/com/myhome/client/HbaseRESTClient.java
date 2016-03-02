package com.myhome.client;

import org.springframework.web.client.RestTemplate;

import com.myhome.domain.hbase.CellSet;

public class HbaseRESTClient {

    public static final String SERVER_URI = "http://root:hadoop@127.0.0.1:9000";

    public static final String ENDPOINT = SERVER_URI + "/employee";

    private RestTemplate restTemplate;

    public HbaseRESTClient() {
        restTemplate = new RestTemplate();
        System.out.println(testGetUserListJSON());

    }

    public Object testGetUserListJSON() {
        CellSet result = restTemplate.getForObject(ENDPOINT + "/*", CellSet.class);
        return result;
    }

    public static void main(String[] args) {
        new HbaseRESTClient();
    }

}
