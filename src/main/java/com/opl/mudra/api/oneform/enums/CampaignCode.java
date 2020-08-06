package com.opl.mudra.api.oneform.enums;

/**
 * Created by dhaval on 18-Dec-17.
 */
public enum CampaignCode {

    EMAIL(231, "Email", "Email"),
    SMS(232, "SMS", "SMS"),
    FACEBOOK(233, "Facebook", "Facebook"),
    GOOGLE(234, "Google", "Google"),
    TWITTER(235, "Twitter", "Twitter"),
    LINKEDIN(236, "LinkedIn", "LinkedIn"),
    INSTAGRAM(237, "Instagram", "Instagram"),
    WHATSAPP(238, "Whatsapp", "Whatsapp");

    private final Integer id;
    private final String value;
    private final String description;

    CampaignCode(Integer id, String value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static CampaignCode getById(Integer id) {
        switch (id) {
            case 231:
                return EMAIL;
            case 232:
                return SMS;
            case 233:
                return FACEBOOK;
            case 234:
                return GOOGLE;
            case 235:
                return TWITTER;
            case 236:
                return LINKEDIN;
            case 237:
                return INSTAGRAM;
            case 238:
                return WHATSAPP;
            default:
                return null;
        }
    }

    public static CampaignCode[] getAll(){
        return CampaignCode.values();

    }
}
