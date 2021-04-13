package com.example.veterineruygulamasi.RestApi;

import static com.example.veterineruygulamasi.RestApi.BaseUrl.URL;

public class BaseManager {

    protected RestApi getRestApi()
    {
        RestApiClient restApiClient = new RestApiClient(URL);
        return restApiClient.getRestApi();
    }
}
