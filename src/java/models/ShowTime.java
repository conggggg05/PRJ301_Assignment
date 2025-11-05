
package models;

import java.util.Date;

public class ShowTime {
    private int  ShowtimeID;
    private int FilmID;
    private Date startTime;
    private float price;

    public ShowTime(int ShowtimeID, int FilmID, Date startTime, float price) {
        this.ShowtimeID = ShowtimeID;
        this.FilmID = FilmID;
        this.startTime = startTime;
        this.price = price;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
