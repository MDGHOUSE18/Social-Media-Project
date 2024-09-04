package com.ghouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ghouse.model.Reels;
import com.ghouse.model.User;
import com.ghouse.service.ReelsService;
import com.ghouse.service.UserService;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    // Create a reel
    @PostMapping("/")
    public ResponseEntity<Reels> createReel(@RequestHeader("Authorization") String token,
                                            @RequestBody Reels reel) throws Exception {
        User user = userService.getUserFromToken(token);
        Reels createdReel = reelsService.createReel(reel, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReel);
    }

    // Get all reels
    @GetMapping("/")
    public ResponseEntity<List<Reels>> findAllReels() {
        List<Reels> reels = reelsService.findAllReels();
        return ResponseEntity.ok(reels);
    }

    // Get reels by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reels>> findUserReels(@PathVariable Integer userId) throws Exception {
        List<Reels> userReels = reelsService.findUserReels(userId);
        return ResponseEntity.ok(userReels);
    }
}
