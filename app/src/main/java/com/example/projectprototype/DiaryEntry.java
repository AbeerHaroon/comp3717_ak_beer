package com.example.projectprototype;

public class DiaryEntry {
    private String entryDate;
    private String entryTitle;
    private String entryStory;

    public DiaryEntry(){};

    public DiaryEntry(String date, String title, String story) {
        entryDate = date;
        entryTitle = title;
        entryStory = story;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getEntryStory() {
        return entryStory;
    }

    public void setEntryStory(String entryStory) {
        this.entryStory = entryStory;
    }
}
