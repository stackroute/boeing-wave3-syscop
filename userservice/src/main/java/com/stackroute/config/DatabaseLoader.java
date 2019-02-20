package com.stackroute.config;

import com.stackroute.model.User;
import com.stackroute.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
@Configuration
@PropertySource("classpath:seedData.properties")
@Component
public abstract class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {
//public abstract class DatabaseLoader implements ApplicationListener<ApplicationContextEvent> {



    @Value(("${id2}"))
    private String id1;
    @Value("${username2}")
    private String username1;
    @Value("${password2}")
    private String password1;
    @Value("{phonenumber2}")
    private  String phonenumber1;
    @Value("${emailid2}")
    private String emailId1;
    @Value("{organisationname2}")
    private String company1;
    @Value("{dob2}")
    private  String dob1;
    @Value("${role2}")
    private String role1;


    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event){
        System.out.println("");
        seedData();
    }

        private void seedData() {
        User user2 = new User();
        user2.setId(id1);
        user2.setUsername(username1);
        user2.setPassword(password1);
        user2.setPhoneNumber(phonenumber1);
        user2.setEmail(emailId1);
        user2.setCompany(company1);
        user2.setDob(dob1);
        userRepository.save(user2);
        System.out.println("gogogogogo: ====>"+username1);
        }



//    //Method to be over-ridden for applicationListener
//    @Override
//    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {
//        seedData();
//    }
//
//    private void seedData() {
//        User user2 = new User();
//        user2.setPassword(password1);
//        user2.setDob(dob1);
//        user2.setPhoneNumber(phonenumber1);
//        user2.setName(organisationname1);
//        user2.setEmail(emailId1);
//        user2.setCid(id1);
//        user2.setUsername(username1);
//        userRepository.save(user2);
//
//    }

}


