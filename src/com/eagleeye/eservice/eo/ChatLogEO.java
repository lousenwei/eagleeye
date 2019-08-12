package com.eagleeye.eservice.eo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wilsonlou on 17/03/2017.
 */
@Entity
@Table(name = "chat_log", catalog = "eagleeye")
@IdClass(ChatLogEOId.class)
public class ChatLogEO {
    private Integer id;
    private String serviceStaffId;
    private String toId;
    //表示消息方向 0:from_id->to_id 1:to_id->from_id
    private Long direction;
    /*****
    type & 1 ：自动回复
    type & 2 : 陌生人消息（等于0是好友消息）
    type & 4 : 广播消息
    type & 8 : 最近联系人陌生人消息
    type & 16 : 离线消息
    type & 32 : 子账号转发系统消息
     ****/
    private Long type;
    private Date messageTime;
    private String content;
    private Date createdAt;
    private Long length;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "service_staff_id")
    public String getServiceStaffId() {
        return serviceStaffId;
    }

    public void setServiceStaffId(String serviceStaffId) {
        this.serviceStaffId = serviceStaffId;
    }

    @Id
    @Column(name = "to_id")
    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    @Basic
    @Column(name = "direction")
    public Long getDirection() {
        return direction;
    }

    public void setDirection(Long direction) {
        this.direction = direction;
    }

    @Id
    @Column(name = "message_time")
    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "type")
    public Long getType(){return type;}

    public void setType(Long type){this.type=type;}

    @Basic
    @Column(name = "length")
    public Long getLength(){return length;}

    public void setLength(Long length){this.length=length;}


    @Basic
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatLogEO chatLogEO = (ChatLogEO) o;

        if (id != null ? !id.equals(chatLogEO.id) : chatLogEO.id != null) return false;
        if (serviceStaffId != null ? !serviceStaffId.equals(chatLogEO.serviceStaffId) : chatLogEO.serviceStaffId != null)
            return false;
        if (toId != null ? !toId.equals(chatLogEO.toId) : chatLogEO.toId != null) return false;
        if (direction != null ? !direction.equals(chatLogEO.direction) : chatLogEO.direction != null) return false;
        if (messageTime != null ? !messageTime.equals(chatLogEO.messageTime) : chatLogEO.messageTime != null)
            return false;
        if (content != null ? !content.equals(chatLogEO.content) : chatLogEO.content != null) return false;
        if (createdAt != null ? !createdAt.equals(chatLogEO.createdAt) : chatLogEO.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (serviceStaffId != null ? serviceStaffId.hashCode() : 0);
        result = 31 * result + (toId != null ? toId.hashCode() : 0);
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (messageTime != null ? messageTime.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
