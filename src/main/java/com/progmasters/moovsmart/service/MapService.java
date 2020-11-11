package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.Property;
import com.progmasters.moovsmart.dto.Location;
import org.cloudinary.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@EnableAsync
public class MapService {
    private static final Logger logger = LoggerFactory.getLogger(MapService.class);

    @Value("${map.api_key}")
    private String apiKey;

    public Location getLocation(Property property) {
        Location location = null;
        String address = property.getZipCode() + " " + property.getCountry() + " " + property.getCity() + " " + property.getStreet();
        String url = "https://maps.googleapis.com/maps/api/geocode/json?key=" + apiKey + "&" +
                "sensor=false&address=" +
                URLEncoder.encode(address, StandardCharsets.UTF_8);
        URL urlObject = null;
        try {
            urlObject = new URL(url);
            URLConnection urlConnection = urlObject.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            String data = readFromInputStream(inputStream);
            JSONObject locationJSON = parseJsonResult(new JSONObject(data));
            if (locationJSON != null) {
                location = new Location((float) locationJSON.getDouble("lng"), (float) locationJSON.getDouble("lat"));
            } else {
                logger.debug("No location found for address: {}", address);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return location;
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private JSONObject parseJsonResult(JSONObject data) {
        if ("OK".equals(data.getString("status"))) {
            return data.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
        } else {
            logger.debug("Message from Google GeoCode API: {}", data.getString("status"));
            return null;
        }
    }
}
