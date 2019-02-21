package com.grokonez.jwtauthentication.security.services;

import com.grokonez.jwtauthentication.message.request.SignUpForm;
import com.grokonez.jwtauthentication.model.Role;
import com.grokonez.jwtauthentication.model.RoleName;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.RoleRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class KafkaListenerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @KafkaListener(topics = "Kafka_NewUser_Registration", groupId = "group_id")
    public void consume(String message){
        System.out.println("Consumed msg : " + message);
        String[] strMessage = message.split(",");
        User user = new User();
        user.setName(strMessage[5].split(":")[1].replace("\"",""));
        user.setEmail(strMessage[4].split(":")[1].replace("\"",""));
        user.setUsername(strMessage[1].split(":")[1].replace("\"",""));
//        user.setPassword(strMessage[2].split(":")[1].replace("\"",""));
        //encode password before saving in database
        user.setPassword(encoder.encode(  (strMessage[2].split(":")[1].replace("\"","")) ) );
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        userRepository.save(user);
        System.out.println("New Message");
        System.out.println("Consumed msg : " + message);
    }
}