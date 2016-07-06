package xyz.aungpyaephyo.joketeller.data.vos;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aung on 6/25/16.
 */
public class EventVO {

    private static final SimpleDateFormat sdfEventTimeReceive = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
    private static final SimpleDateFormat sdfEventStartTime = new SimpleDateFormat("EEEE, MMM dd, hh:mm aa");
    private static final SimpleDateFormat sdfEventEndTime = new SimpleDateFormat("hh:mm aa");

    @SerializedName("event_title")
    private String eventTitle;

    @SerializedName("stock_photo")
    private String stockPhotoPath;

    @SerializedName("event_desc")
    private String eventDesc;

    @SerializedName("start_time")
    private String startTimeText;

    @SerializedName("end_time")
    private String endTimeText;

    public EventVO(String eventTitle, String stockPhotoPath, String eventDesc, String startTimeText, String endTimeText) {
        this.eventTitle = eventTitle;
        this.stockPhotoPath = stockPhotoPath;
        this.eventDesc = eventDesc;
        this.startTimeText = startTimeText;
        this.endTimeText = endTimeText;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getStockPhotoPath() {
        return stockPhotoPath;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public String getStartTimeText() {
        return startTimeText;
    }

    public String getEndTimeText() {
        return endTimeText;
    }

    public String getEventTime() {
        try {
            Date startDateTime = sdfEventTimeReceive.parse(startTimeText);
            Date endDateTime = sdfEventTimeReceive.parse(endTimeText);

            return sdfEventStartTime.format(startDateTime) + " - " + sdfEventEndTime.format(endDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
