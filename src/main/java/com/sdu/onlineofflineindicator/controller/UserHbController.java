package com.sdu.onlineofflineindicator.controller;

import com.sdu.onlineofflineindicator.model.Pulse;
import com.sdu.onlineofflineindicator.service.PulseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserHbController {

    private final PulseService pulseService;

    @GetMapping("status/user/{userId}")
    public ResponseEntity<Boolean> getPulse(@PathVariable Integer userId){
        return ResponseEntity.ok().body(pulseService.getPulse(userId));
    }

    @PatchMapping("heartbeat/")
    public ResponseEntity<List<Pulse>> setPulse(@RequestBody List<Integer> userIds) {
        if(userIds.size() == 1){
            return ResponseEntity.ok(List.of(pulseService.setPulseForUser(userIds.get(0))));
        }else{
            return ResponseEntity.ok(pulseService.setPulseForUsers(userIds));
        }
    }

}
