package com.quinbay.EmployeeAttendaceSystem.servicesImplementation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.EmployeeAttendaceSystem.model.vo.*;
import com.quinbay.EmployeeAttendaceSystem.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class ServicesImpl implements Services{
    @Autowired
    RestTemplate restTemplate;
    String baseUrl = "http://localhost:8080/userService";
    String baseUrlAction = "http://localhost:8094/actions";
   String notificationUrl="http://localhost:8099/notification";
    public List<UserVo> getAllUserDetails(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl+"/user").build();
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, List.class).getBody();

    }

    public UserVo loginCheck(LoginVo loginVo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginVo> entity = new HttpEntity<>(loginVo, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl+"/user/login").build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, UserVo.class).getBody();

    }

    public UserVo getAllUserDetailsByName(String userName){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl+"/user/name").queryParam("userName",userName).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,UserVo.class).getBody();


    }
    public List<UserVo> getAllUserDetailsByManagerId(int managerId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl+"/user/manager").queryParam("managerId",managerId).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,List.class).getBody();

    }

    public List<SwipeHistoryVo> getUserSwipehistory(int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/viewswipehistory").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,List.class).getBody();

    }

    public String userSwipeDetails(SwipeHistoryVo swipeHistoryVo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SwipeHistoryVo> entity = new HttpEntity<>(swipeHistoryVo,headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/swipecard").build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class).getBody();

    }

    public String applyUserActions(int id, OperationsVo operationsVo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OperationsVo> entity = new HttpEntity<>(operationsVo, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/applyactions").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class).getBody();

    }

    public String applyActionStatus( OperationsVo operationsVo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OperationsVo> entity = new HttpEntity<>(operationsVo, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/approval").build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity, String.class).getBody();

    }

    public List<OperationsVo> getUserActionhistory(int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/viewactionhistory").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,List.class).getBody();


    }
    public List<OperationsVo> viewEmployeePendingStatus(int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/viewpendingstatus").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,List.class).getBody();


    }
    public List<ReportVo> reportGeneration(ReportVo reportVo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ReportVo> entity = new HttpEntity<>(reportVo, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/report").build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, List.class).getBody();

    }

    public List<NotificationVo> getUserUnreadNotification(int id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(notificationUrl+"/getNotification/"+id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,List.class).getBody();

    }

    public String updateNotificationReadStatus(String notificationId)
    {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(notificationUrl+"/updateNotification/"+notificationId).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity, String.class).getBody();

    }

    public String nudgeEmployee(NotificationVo notification)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<NotificationVo> entity = new HttpEntity<>(notification, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl+"/nudgeEmployee/").build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class).getBody();

    }

    public List<ReportVo> reportGenerationForWeek(ReportVo reportVo)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ReportVo> entity = new HttpEntity<>(reportVo, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/report/week").build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,List.class).getBody();
    }

    public  List<ReportVo> reportGenerationForMonth(ReportVo reportVo)
    {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ReportVo> entity = new HttpEntity<>(reportVo, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/report/month").build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,List.class).getBody();
    }

}
