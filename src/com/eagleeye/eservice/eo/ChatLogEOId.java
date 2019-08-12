package com.eagleeye.eservice.eo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by wilsonlou on 17/03/2017.
 */
public class ChatLogEOId implements Serializable {
    private Integer id;
    private String serviceStaffId;
    private String toId;
    private Timestamp messageTime;

    @Column(name = "id")
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "service_staff_id")
    @Id
    public String getServiceStaffId() {
        return serviceStaffId;
    }

    public void setServiceStaffId(String serviceStaffId) {
        this.serviceStaffId = serviceStaffId;
    }

    @Column(name = "to_id")
    @Id
    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    @Column(name = "message_time")
    @Id
    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatLogEOId that = (ChatLogEOId) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (serviceStaffId != null ? !serviceStaffId.equals(that.serviceStaffId) : that.serviceStaffId != null)
            return false;
        if (toId != null ? !toId.equals(that.toId) : that.toId != null) return false;
        if (messageTime != null ? !messageTime.equals(that.messageTime) : that.messageTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (serviceStaffId != null ? serviceStaffId.hashCode() : 0);
        result = 31 * result + (toId != null ? toId.hashCode() : 0);
        result = 31 * result + (messageTime != null ? messageTime.hashCode() : 0);
        return result;
    }
}
