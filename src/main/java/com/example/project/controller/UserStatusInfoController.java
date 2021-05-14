package com.example.project.controller;

import com.example.project.service.UserStatusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author: Marcel
 * @date: 2021/5/14 20:29
 */

@Controller
public class UserStatusInfoController {

    @Autowired
    private UserStatusInfoService userStatusInfoService;



}
