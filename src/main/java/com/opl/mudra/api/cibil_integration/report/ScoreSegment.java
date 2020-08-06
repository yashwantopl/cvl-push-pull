
package com.opl.mudra.api.cibil_integration.report;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ScoreDate",
    "Score",
    "Length",
    "ReasonCode2FieldLength",
    "ReasonCode1FieldLength",
    "ScoreCardName",
    "ScoreName",
    "ReasonCode1",
    "ReasonCode2",
    "ExclusionCode4",
    "ScoreCardVersion",
    "ExclusionCode4FieldLength"
})
public class ScoreSegment implements Serializable
{

    @JsonProperty("ScoreDate")
    private String scoreDate;
    @JsonProperty("Score")
    private String score;
    @JsonProperty("Length")
    private Integer length;
    @JsonProperty("ReasonCode2FieldLength")
    private String reasonCode2FieldLength;
    @JsonProperty("ReasonCode1FieldLength")
    private String reasonCode1FieldLength;
    @JsonProperty("ScoreCardName")
    private String scoreCardName;
    @JsonProperty("ScoreName")
    private String scoreName;
    @JsonProperty("ReasonCode1")
    private String reasonCode1;
    @JsonProperty("ReasonCode2")
    private String reasonCode2;
    @JsonProperty("ExclusionCode4")
    private String exclusionCode4;
    @JsonProperty("ScoreCardVersion")
    private Integer scoreCardVersion;
    @JsonProperty("ExclusionCode4FieldLength")
    private String exclusionCode4FieldLength;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8014504648115279999L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ScoreSegment() {
    }

    /**
     * 
     * @param reasonCode1FieldLength
     * @param exclusionCode4FieldLength
     * @param exclusionCode4
     * @param reasonCode2
     * @param length
     * @param score
     * @param reasonCode1
     * @param scoreCardVersion
     * @param reasonCode2FieldLength
     * @param scoreDate
     * @param scoreName
     * @param scoreCardName
     */
    public ScoreSegment(String scoreDate, String score, Integer length, String reasonCode2FieldLength, String reasonCode1FieldLength, String scoreCardName, String scoreName, String reasonCode1, String reasonCode2, String exclusionCode4, Integer scoreCardVersion, String exclusionCode4FieldLength) {
        super();
        this.scoreDate = scoreDate;
        this.score = score;
        this.length = length;
        this.reasonCode2FieldLength = reasonCode2FieldLength;
        this.reasonCode1FieldLength = reasonCode1FieldLength;
        this.scoreCardName = scoreCardName;
        this.scoreName = scoreName;
        this.reasonCode1 = reasonCode1;
        this.reasonCode2 = reasonCode2;
        this.exclusionCode4 = exclusionCode4;
        this.scoreCardVersion = scoreCardVersion;
        this.exclusionCode4FieldLength = exclusionCode4FieldLength;
    }

    @JsonProperty("ScoreDate")
    public String getScoreDate() {
        return scoreDate;
    }

    @JsonProperty("ScoreDate")
    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    @JsonProperty("Score")
    public String getScore() {
        return score;
    }

    @JsonProperty("Score")
    public void setScore(String score) {
        this.score = score;
    }

    @JsonProperty("Length")
    public Integer getLength() {
        return length;
    }

    @JsonProperty("Length")
    public void setLength(Integer length) {
        this.length = length;
    }

    @JsonProperty("ReasonCode2FieldLength")
    public String getReasonCode2FieldLength() {
        return reasonCode2FieldLength;
    }

    @JsonProperty("ReasonCode2FieldLength")
    public void setReasonCode2FieldLength(String reasonCode2FieldLength) {
        this.reasonCode2FieldLength = reasonCode2FieldLength;
    }

    @JsonProperty("ReasonCode1FieldLength")
    public String getReasonCode1FieldLength() {
        return reasonCode1FieldLength;
    }

    @JsonProperty("ReasonCode1FieldLength")
    public void setReasonCode1FieldLength(String reasonCode1FieldLength) {
        this.reasonCode1FieldLength = reasonCode1FieldLength;
    }

    @JsonProperty("ScoreCardName")
    public String getScoreCardName() {
        return scoreCardName;
    }

    @JsonProperty("ScoreCardName")
    public void setScoreCardName(String scoreCardName) {
        this.scoreCardName = scoreCardName;
    }

    @JsonProperty("ScoreName")
    public String getScoreName() {
        return scoreName;
    }

    @JsonProperty("ScoreName")
    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    @JsonProperty("ReasonCode1")
    public String getReasonCode1() {
        return reasonCode1;
    }

    @JsonProperty("ReasonCode1")
    public void setReasonCode1(String reasonCode1) {
        this.reasonCode1 = reasonCode1;
    }

    @JsonProperty("ReasonCode2")
    public String getReasonCode2() {
        return reasonCode2;
    }

    @JsonProperty("ReasonCode2")
    public void setReasonCode2(String reasonCode2) {
        this.reasonCode2 = reasonCode2;
    }

    @JsonProperty("ExclusionCode4")
    public String getExclusionCode4() {
        return exclusionCode4;
    }

    @JsonProperty("ExclusionCode4")
    public void setExclusionCode4(String exclusionCode4) {
        this.exclusionCode4 = exclusionCode4;
    }

    @JsonProperty("ScoreCardVersion")
    public Integer getScoreCardVersion() {
        return scoreCardVersion;
    }

    @JsonProperty("ScoreCardVersion")
    public void setScoreCardVersion(Integer scoreCardVersion) {
        this.scoreCardVersion = scoreCardVersion;
    }

    @JsonProperty("ExclusionCode4FieldLength")
    public String getExclusionCode4FieldLength() {
        return exclusionCode4FieldLength;
    }

    @JsonProperty("ExclusionCode4FieldLength")
    public void setExclusionCode4FieldLength(String exclusionCode4FieldLength) {
        this.exclusionCode4FieldLength = exclusionCode4FieldLength;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
