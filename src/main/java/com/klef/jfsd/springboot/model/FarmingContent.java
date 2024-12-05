package com.klef.jfsd.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.sql.Blob;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "farming_content")
public class FarmingContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contentid;
    private String title;
    private String description;
    private String author;
    private String contenttype;
    private String category;
    private Date createddate;

    @JsonIgnore
    @Lob
    private Blob media;

    public FarmingContent() {}

    public FarmingContent(Long contentid, String title, String description, String author, String contenttype, String category, Date createddate, Blob media) {
        this.contentid = contentid;
        this.title = title;
        this.description = description;
        this.author = author;
        this.contenttype = contenttype;
        this.category = category;
        this.createddate = createddate;
        this.media = media;
    }

    public Long getContentid() {
        return contentid;
    }

    public void setContentid(Long contentid) {
        this.contentid = contentid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreatedate(Date createddate) {
        this.createddate = createddate;
    }

    

    @Override
    public String toString() {
        return "FarmingContent [contentid=" + contentid + ", title=" + title + ", description=" + description 
               + ", author=" + author + ", contenttype=" + contenttype + ", category=" + category 
               + ", createddate=" + createddate + ", media=" + (media != null ? "present" : "not present") + "]";
    }

	public Blob getMedia() {
		return media;
	}

	public void setMedia(Blob media) {
		this.media = media;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
}
