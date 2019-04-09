package com.mariabeyrak.scatter.models.requests.AppInfo;

import com.google.gson.annotations.SerializedName;

public class AppInfoResponseData {
    @SerializedName("app")
    private String appName;
    @SerializedName("app_version")
    private String appVersion;
    @SerializedName("protocol_name")
    private String protocolName;
    @SerializedName("protocol_version")
    private String protocolVersion;

    public AppInfoResponseData(String appName, String appVersion, String protocolName, String protocolVersion) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.protocolName = protocolName;
        this.protocolVersion = protocolVersion;
    }
}
