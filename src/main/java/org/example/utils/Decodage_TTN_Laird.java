package org.example.utils;

import com.google.gson.Gson;
import org.example.pojo.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HexFormat;

public class Decodage_TTN_Laird {
    String json = null;
    Response pojoTTN3;

    public Decodage_TTN_Laird(String json) {
        this.json = json;
        Gson gson = new Gson();
        pojoTTN3 = gson.fromJson(json, Response.class);
    }

    public String getUplinkMessage() {
        byte [] decodedBytes = Base64.getDecoder().decode(pojoTTN3.getData().getUplinkMessage().getFrmPayload().toString());
        String batterie = null;
        switch (decodedBytes[5]) {
            case 0:
                batterie = "0-5%";
                break;
            case 1:
                batterie = "5-20%";
                break;
            case 2:
                batterie = "20-40%";
                break;
            case 3:
                batterie = "40-60%";
                break;
            case 4:
                batterie = "60-80%";
                break;
            case 5:
                batterie = "80-100%";
                break;
        }
        String decodedString = "Temperature: " + decodedBytes[14] + "." + decodedBytes[13] + "°C. " + "Batterie: " + batterie;
        return decodedString;
    }

    public String getApplicationId() {
        return pojoTTN3.getIdentifiers().getFirst().getDeviceIds().getApplicationIds().getApplicationId();
    }
    public String getGatewayId() {
        return pojoTTN3.getData().getUplinkMessage().getRxMetadata().getFirst().getGatewayIds().getGatewayId();
    }
    public int getSpreading_factor() {
        return pojoTTN3.getData().getUplinkMessage().getSettings().getDataRate().getLora().getSpreadingFactor();
    }
}
