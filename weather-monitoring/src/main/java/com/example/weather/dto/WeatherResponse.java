package com.example.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    private Weather[] weather;

    @JsonProperty("main")
    private Main main;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    @JsonProperty("dt")
    private long dt;

    @JsonProperty("wind")
    private Wind wind;

    public static class Weather {
        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        private String main;
        // Getters and setters
    }

    public static class Main {
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        private double temp;
        private double feels_like;
        private double humidity; // New field

        // Getters and setters
    }

    public static class Wind {
        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        private double speed; // New field


    }


}
