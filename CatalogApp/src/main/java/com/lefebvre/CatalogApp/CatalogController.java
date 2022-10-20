package com.lefebvre.CatalogApp;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CatalogController {

    @Autowired
    private EurekaClient client;

    //get list of courses from api.
    @RequestMapping("/catalog")
    public String getCatalogAll(){
        String courses = "";
        //String courseAppURL = "http://localhost:8080/courses";
        InstanceInfo instanceInfo = client.getNextServerFromEureka("course-app",false);
        RestTemplate restTemplate = new RestTemplate();
        String courseAppURL = instanceInfo.getHomePageUrl();
        courseAppURL = courseAppURL + "/courses";
        courses = restTemplate.getForObject(courseAppURL,String.class);

        return ("Our Courses are" + courses);
    }

    @RequestMapping("/firstcourse")
    public String getCatalog(){
        Course course = new Course();
        User user = new User();
        //String courseAppURL = "http://localhost:8080/courses/id/1";
        InstanceInfo instanceInfo = client.getNextServerFromEureka("course-app",false);
        String courseAppURL = instanceInfo.getHomePageUrl();
        courseAppURL = courseAppURL + "/courses/id/1";

        RestTemplate restTemplate = new RestTemplate();
        course = restTemplate.getForObject(courseAppURL,Course.class);

        instanceInfo = client.getNextServerFromEureka("user-app",false);
        String userAppURL = instanceInfo.getHomePageUrl();
        userAppURL = userAppURL + "/courseid/" + course.getCourseid();

        String userList = restTemplate.getForObject(userAppURL,String.class);

        return ("our first course is "+course.getCoursename() + "******and enrolled users are " + userList);
    }

}
