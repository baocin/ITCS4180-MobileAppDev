package com.itis4180.gbl.homework08.Utils;

/**
 * Created by gbl on 4/15/2016.
 */
public class Message {

    String timeStamp;
    boolean message_read;
    String message_text;
    String receiver;
    String sender;

    public String getTimeStamp() {
        return timeStamp;
    }

    public boolean isMessage_read() {
        return message_read;
    }

    public String getMessage_text() {
        return message_text;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }
}
