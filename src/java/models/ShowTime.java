
package models;

import java.util.Date;

public class ShowTime {
    private int  ShowtimeID;
    private int FilmID;
    private Date startTime;
    private double Price;

    public ShowTime(int ShowtimeID, int FilmID, Date startTime, double Price) {
        this.ShowtimeID = ShowtimeID;
        this.FilmID = FilmID;
        this.startTime = startTime;
        this.Price = Price;
    }

    public ShowTime() {
    }

    public int getShowtimeID() {
        return ShowtimeID;
    }

    public void setShowtimeID(int ShowtimeID) {
        this.ShowtimeID = ShowtimeID;
    }

    public int getFilmID() {
        return FilmID;
    }

    public void setFilmID(int FilmID) {
        this.FilmID = FilmID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    
}
