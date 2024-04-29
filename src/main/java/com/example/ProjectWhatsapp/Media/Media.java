package com.example.ProjectWhatsapp.Media;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Media {
    @Id
    @SequenceGenerator(
            name = "media_id_sequence",
            sequenceName = "media_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "media_id_sequence"
    )
    private int mediaId;
    private int messageId;
    private String mediaType;
    private String url;

    public Media(int mediaId, int messageId, String mediaType, String url) {
        this.mediaId = mediaId;
        this.messageId = messageId;
        this.mediaType = mediaType;
        this.url = url;
    }

    public Media(int messageId, String mediaType, String url) {
        this.messageId = messageId;
        this.mediaType = mediaType;
        this.url = url;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return mediaId == media.mediaId && messageId == media.messageId && Objects.equals(mediaType, media.mediaType) && Objects.equals(url, media.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaId, messageId, mediaType, url);
    }

    @Override
    public String toString() {
        return "Media{" +
                "mediaId=" + mediaId +
                ", messageId=" + messageId +
                ", mediaType='" + mediaType + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}