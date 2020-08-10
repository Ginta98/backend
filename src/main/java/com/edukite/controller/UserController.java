package com.edukite.controller;

import com.edukite.constant.EdukiteEnum;
import com.edukite.dto.StudentCheckedDto;
import com.edukite.exception.ApiException;
import com.edukite.helper.QRCodeGenerator;
import com.edukite.helper.ResponseBuilder;
import com.edukite.model.Student;
import com.edukite.model.User;
import com.edukite.service.ScheduleService;
import com.edukite.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends BaseConttroller{

	@Value("${qrcode.path}")
	public String QR_STORE_PATH;

	@Autowired
	UserService userService;

	@Autowired
	ScheduleService scheduleService;

	@GetMapping("/info")
	public ResponseEntity<ResponseBuilder<User>> getUsers() {
//		_USER = userService.getUser(_USER_NAME);
		return ResponseEntity.ok(ResponseBuilder.buildResponse(_USER));
	}

	@GetMapping("/info/{userId}")
	public ResponseEntity<ResponseBuilder<User>> getUsers(@PathVariable Integer userId) {
		User user = userService.getUser(userId);
		return ResponseEntity.ok(ResponseBuilder.buildResponse(user));
	}

	@PostMapping("/qrcode")
	public ResponseEntity<ResponseBuilder<Integer>> getUsers2(@RequestBody List<String> studentsCodes, @RequestParam("scheduleId") Integer scheduleId, @RequestParam(value = "checkType", required = false) Integer checkType) throws ApiException, Exception{
//		_USER = userService.getUser(_USER_NAME);
//		QRCodeGenerator.generateQRCodeImage("https://www.baeldung.com/properties-with-spring", 300, 300, QR_STORE_PATH + "\\test"+ (new Date()).getTime() + ".png");
		if (checkType == null)
			checkType = 1;
		if (checkType == 1 || checkType == 2) {

			if (studentsCodes != null && studentsCodes.size() > 0) {
				List<StudentCheckedDto> lst = new ArrayList<>();
				StudentCheckedDto tmp = null;
				Student tmpStudent = null;
				for (String code : studentsCodes) {
					tmp = new StudentCheckedDto();
					tmpStudent = userService.getStudentByCode(code);
					if (tmpStudent != null) {
						tmp.setStudentId(tmpStudent.getId());
						tmp.setCheckStatus(EdukiteEnum.STUDENT_CHECK_STATUS.CHECKED.getValue());
						lst.add(tmp);
					}
				}

				scheduleService.checkinStudent(scheduleId, lst, checkType);

				return ResponseEntity.ok(ResponseBuilder.buildResponse(1));
			}
			return ResponseEntity.ok(ResponseBuilder.buildApplicationException("Invalid size", 400));
		}
		return ResponseEntity.ok(ResponseBuilder.buildApplicationException("Checktype is not correct", 400));

	}

}
