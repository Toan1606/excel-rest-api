package com.code.export.controller;

import com.code.export.dto.UserRequestDto;
import com.code.export.model.User;
import com.code.export.service.IUserExcelExporter;
import com.code.export.service.IUserServices;
import com.code.export.service.impl.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    public static final String CONTENT_TYPE = "application/octet-stream";
    public static final String HEADER_KEY = "Content-Disposition";
    public static final String HEADER_VALUE = "attachment; filename=users.xlsx";

    private final IUserServices userServices;

    private final IUserExcelExporter userExcelExporter;

    public UserController(@Qualifier("userServices") UserServices userServices,
                          @Qualifier("userExcelExporter") IUserExcelExporter userExcelExporter) {
        this.userServices = userServices;
        this.userExcelExporter = userExcelExporter;
    }

    @GetMapping("/excel/export")
    public ResponseEntity<String> exportToExcel(HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        response.setHeader(HEADER_KEY, HEADER_VALUE);
        List<User> userList = userServices.findAllUsers();
        userExcelExporter.export(response, userList);
        return ResponseEntity.ok("");
    }

    /**
     * This method add new user to database
     * @param userRequestDto
     * @return user after saved in database
     */
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        User user = userServices.saveUser(userRequestDto);
        return ResponseEntity.ok(user);
    }
}
