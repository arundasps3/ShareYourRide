package com.arundas;

public class RideModel {

    private String departure;
    private String destination;
    private String date;
    private String time;
    private String message;
    private String fare;

    public RideModel(){

    }

    public RideModel(String departure, String destination, String date, String time, String message, String fare) {
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.message = message;
        this.fare = fare;
    }

    public String getDeparture() { return "DEPARTURE :"+departure;
    }
    public String getDestination() { return "DESTINATION :"+destination;
    }
    public String getDate() { return "DATE :" +date; }
    public String getTime() { return "TIME :"+time; }
    public String getMessage() { return "MESSAGE :"+message; }
    public String getFare() { return "$"+fare; }
}
