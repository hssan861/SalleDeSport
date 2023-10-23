/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calander;

/**
 *
 * @author HP
 */
import java.time.ZonedDateTime;
import java.util.Date;

public class CalendarActivity {
    private String title;
    private Date dateStart;
    private Date dateEnd;
    private String description;

    public CalendarActivity() {
        // Default constructor
    }

    public CalendarActivity(String title, Date dateStart, Date dateEnd, String description) {
        this.title = title;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CalendarActivity{" + "title=" + title + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", description=" + description + '}';
    }

}