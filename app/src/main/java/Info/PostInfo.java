package Info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostInfo implements Serializable {
    private String publisher;
    private String subject;
    private String title;
    private Date createdAt;
    private ArrayList<String> contents;
    private ArrayList<String> formats;
    private String reviewcount;
    private String importance;

    public PostInfo(String publisher, String subject, String title, Date createdAt, ArrayList<String> contents, ArrayList<String> formats, String reviewcount, String importance) {
        this.publisher = publisher;
        this.subject = subject;
        this.title = title;
        this.createdAt = createdAt;
        this.contents = contents;
        this.formats = formats;
        this.reviewcount = reviewcount;
        this.importance = importance;
    }

    public Map<String, Object> getPostInfo(){
        Map<String, Object> docData = new HashMap<>();
        docData.put("publisher",publisher);
        docData.put("subject",subject);
        docData.put("title",title);
        docData.put("createdAt",createdAt);
        docData.put("contents",contents);
        docData.put("formats",formats);
        docData.put("reviewcount",reviewcount);
        docData.put("importance",importance);
        return  docData;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<String> getContents() {
        return contents;
    }

    public void setContents(ArrayList<String> contents) {
        this.contents = contents;
    }

    public ArrayList<String> getFormats() {
        return formats;
    }

    public void setFormats(ArrayList<String> formats) {
        this.formats = formats;
    }

    public String getReviewcount() {
        return reviewcount;
    }

    public void setReviewcount(String reviewcount) {
        this.reviewcount = reviewcount;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }
}
