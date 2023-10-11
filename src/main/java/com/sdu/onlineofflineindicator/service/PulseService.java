package com.sdu.onlineofflineindicator.service;

import com.sdu.onlineofflineindicator.model.Pulse;
import com.sdu.onlineofflineindicator.repository.PulseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PulseService {

    private final PulseRepository pulseRepository;

    public boolean getPulse(Integer userId ){
        Optional<Pulse> userPulse = pulseRepository.findById(userId);
        if(userPulse.isPresent()){
            Pulse user = userPulse.get();
            Long userLastHb = user.getLastHb();

            return getPulse(userLastHb);
        }
        return false;
    }


    private static boolean getPulse(Long userHbTsMillis) {
        Instant currentTime = Instant.now();
        Instant timestamp = Instant.ofEpochSecond(userHbTsMillis / 1000); // Convert milliseconds to seconds

        // Calculate the time difference in seconds
        long timeDifference = currentTime.getEpochSecond() - timestamp.getEpochSecond();

        // Check if the time difference is within 30 seconds
        return timeDifference >= 0 && timeDifference <= 30;
    }



    public Pulse setPulseForUser(Integer userId){
        Pulse existingUser = pulseRepository.findById(userId).orElse(null);

        if (existingUser == null) {
            return pulseRepository.save(new Pulse(userId, System.currentTimeMillis()));
        }

        // Update userHb if it is provided in the updates map
        existingUser.setLastHb(System.currentTimeMillis());

        return pulseRepository.save(existingUser);
    }

    public List<Pulse> setPulseForUsers(List<Integer> userIds) {
        List pulses = new ArrayList();
        for (Integer userId : userIds) {
            Pulse existingUser = pulseRepository.findById(userId).orElse(null);

            if (existingUser == null) {
                 pulses.add(pulseRepository.save(new Pulse(userId, System.currentTimeMillis())));
            }else{
                // Update userHb if it is provided in the updates map
                existingUser.setLastHb(System.currentTimeMillis());
                pulses.add(pulseRepository.save(existingUser));
            }
        }
        return pulses;
    }


}
