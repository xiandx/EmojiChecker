package com.ustc.domain;

public class Message {
    private int id;
    private String ts;
    private String emoji_id;
    private int user_id;
    public Message(){

    }
    public Message(String ts, String emoji_id, int user_id) {
        this.ts = ts;
        this.emoji_id = emoji_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getEmoji_id() {
        return emoji_id;
    }

    public void setEmoji_id(String emoji_id) {
        this.emoji_id = emoji_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", ts='" + ts + '\'' +
                ", emoji_id='" + emoji_id + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
