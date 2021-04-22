package com.excilys.cdb.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.service.CompanyService;

@RestController
@RequestMapping("/CompanyAPI")
public class CompanyAPI {
	@Autowired
	CompanyService companyService;

}
