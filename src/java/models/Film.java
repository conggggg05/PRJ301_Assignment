package models;

public class Film {

    private int filmID;
    private String filmName;
    private String description;
    private int duration; 
    public Film() {
    }
    public Film(int filmID, String filmName, String description, int duration) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.description = description;
        this.duration = duration;
    }
    
    // --- Getters and Setters ---

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Film{" + 
               "filmID=" + filmID + 
               ", filmName='" + filmName + '\'' + 
               ", description='" + description + '\'' + 
               ", duration=" + duration + 
               '}';
    }
}