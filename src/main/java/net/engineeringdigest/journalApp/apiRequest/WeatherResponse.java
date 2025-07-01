package net.engineeringdigest.journalApp.apiRequest;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
public class WeatherResponse {

    private Location location;
    private Current current;

    @Getter
    @Setter
    public static class Current {  // ✅ static
        private String observation_time;
        private int temperature;
        private int weather_code;
        private ArrayList<String> weather_icons;
        private ArrayList<String> weather_descriptions;
        private int wind_speed;
        private int wind_degree;
        private String wind_dir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        private int uv_index;
        private int visibility;
    }

    @Getter
    @Setter
    public static class Location {  // ✅ static
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;
        private String timezone_id;
        private String localtime;
        private int localtime_epoch;
        private String utc_offset;
    }
}
