package com.example.famchat;

public class Groups {

    private String GroupID;
    private String GroupName;
    private String GroupPassword;
    private String SafeCode;
    private String EmergencyCode;

    public Groups(){
    }

    public String getGroupID() {
        return GroupID;
    }

    public void setGroupID(String groupID) {
        GroupID = groupID;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getGroupPassword() {
        return GroupPassword;
    }

    public void setGroupPassword(String groupPassword) {
        GroupPassword = groupPassword;
    }

    public String getSafeCode() {
        return SafeCode;
    }

    public void setSafeCode(String safeCode) {
        SafeCode = safeCode;
    }

    public String getEmergencyCode() {
        return EmergencyCode;
    }

    public void setEmergencyCode(String emergencyCode) {
        EmergencyCode = emergencyCode;
    }
}
