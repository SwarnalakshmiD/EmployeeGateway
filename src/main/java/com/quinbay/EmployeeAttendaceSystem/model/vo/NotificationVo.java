package com.quinbay.EmployeeAttendaceSystem.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationVo {
    public String id;
    private int empId;
    private String notificationMessage;
    private String readStatus;
    private String actionType;
    private String actionStartDate;
 private String updatedDate;
}
