package com.example.swapnilbasu.chatter;

public class ChatMessage {
    private boolean isImage, isMine;
    private String content;
    private String graphType;
    private String pairs;




    public ChatMessage(String message, boolean mine, boolean image) {
        content = message;
        isMine = mine;
        isImage = image;


    }

    public ChatMessage(String message, boolean mine, boolean image, String thisGraphType) {
        content = message;
        isMine = mine;
        isImage = image;
        graphType = thisGraphType;


    }

    public ChatMessage(String message, boolean mine, boolean image, String thisGraphType, String datapts) {
        content = message;
        isMine = mine;
        isImage = image;
        graphType = thisGraphType;
        pairs = datapts;


    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setIsImage(boolean isImage) {
        this.isImage = isImage;
    }

    public String getGraphType() {
        return graphType;
    }

    public String getPairs() {
        return pairs;
    }


}
