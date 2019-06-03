package com.capitaworld.service.loans.model.api_model;

public class LoantypeSelectionResponse {

    private long id;
    private String type;
    private String description;
    private  int businessTypeId;
    private String imgPath;
    public LoantypeSelectionResponse(){

    }

    public LoantypeSelectionResponse(long id, String type, String description, int businessTypeId, String imgPath, boolean is_active) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.businessTypeId = businessTypeId;
        this.imgPath = imgPath;
        this.is_active = is_active;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(int businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    private boolean is_active;

    @Override
    public String toString() {
        return "LoantypeSelectionResponse{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", businessTypeId=" + businessTypeId +
                ", imgPath='" + imgPath + '\'' +
                ", is_active=" + is_active +
                '}';
    }
}
