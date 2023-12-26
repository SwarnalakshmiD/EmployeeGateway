package com.quinbay.EmployeeAttendaceSystem.services;

import com.quinbay.EmployeeAttendaceSystem.model.vo.*;

import java.util.List;

public interface Services {
    List<UserVo> getAllUserDetails();


    UserVo loginCheck(LoginVo loginVo);
    UserVo getAllUserDetailsByName(String userName);
    List<UserVo> getAllUserDetailsByManagerId(int managerId);

    List<SwipeHistoryVo> getUserSwipehistory(int id);
    String userSwipeDetails(SwipeHistoryVo swipeHistoryVo);

    String applyUserActions(int id, OperationsVo operationsVo);
    String applyActionStatus( OperationsVo operationsVo);

    List<OperationsVo> getUserActionhistory(int id);
    List<OperationsVo> viewEmployeePendingStatus(int id);
    List<ReportVo> reportGeneration(ReportVo reportVo);


    List<NotificationVo> getUserUnreadNotification(int id);

    String updateNotificationReadStatus(String notificationId);

    String nudgeEmployee(NotificationVo notification);

    List<ReportVo> reportGenerationForWeek(ReportVo reportVo);

    List<ReportVo> reportGenerationForMonth(ReportVo reportVo);
}
