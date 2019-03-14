package com.stackroute.jwtauthentication.security.services;

import com.stackroute.jwtauthentication.model.User;
import com.stackroute.jwtauthentication.repository.RoleRepository;
import com.stackroute.jwtauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @KafkaListener(topics = "Kafka_NewUser_Registration", groupId = "group_id_login")
    public void consume(String message){
        String[] strMessage = message.split(",");
        User user = new User();
        user.setName(strMessage[5].split(":")[1].replace("\"",""));
        user.setEmail(strMessage[4].split(":")[1].replace("\"",""));
        user.setUsername(strMessage[1].split(":")[1].replace("\"",""));
        //encode password before saving in database
        user.setPassword(encoder.encode(  (strMessage[2].split(":")[1].replace("\"","")) ) );
        userRepository.save(user);
    }
}
