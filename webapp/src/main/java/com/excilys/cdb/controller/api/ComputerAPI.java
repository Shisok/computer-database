package com.excilys.cdb.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.dto.rest.ComputerDTORest;
import com.excilys.cdb.exception.InputException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.MapperComputer;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.PageService;

@RestController
@RequestMapping("/ComputerAPI")
public class ComputerAPI {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private PageService pageService;
	@Autowired
	private MapperComputer mapperComputer;

	@GetMapping(value = "/page/{numPage}/{nbObject}", produces = "application/json")
	public ResponseEntity<?> getComputer(@PathVariable int numPage, @PathVariable int nbObject) {
		Page<Computer> page = new Page<Computer>();
		setOrderBy(page, null, null);
		setPageInt(page, numPage);
		setObjectPerPage(page, nbObject);
		List<Computer> listComputer = null;
		try {
			listComputer = this.pageService.searchAllComputerPagination(page);
			if (listComputer.size() == 0) {
				throw new InputException("No computer found. Please, verify the page number.");
			}
		} catch (InputException e) {
			LoggerCdb.logError(getClass(), e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		List<ComputerDTORest> listDTO = mapperComputer.mapFromListDTORestToListModel(listComputer);

		return new ResponseEntity<>(listDTO, HttpStatus.OK);
	}

//	private Page<Computer> pageHandler(Integer pageno, String search) {
//		Page<Computer> page = new Page<Computer>();
//		setOrderBy(page, null, null);
//		setPageInt(page, pageno);
//		setObjectPerPage(page, null);
//		if (search != null) {
//			page.setNbComputer(this.computerService.searchNameCount(search));
//			page.setContentPage(this.pageService.searchNamePagination(page, search));
//
//		} else {
//			page.setNbComputer(this.computerService.countComputer());
//			page.setContentPage(this.pageService.searchAllComputerPagination(page));
//		}
//		page.setPageMax();
//		page.setIndex();
//		return page;
//	}

	private void setOrderBy(Page<Computer> page, String orderBy, String sort) {
		if (orderBy != null) {
			page.setOrderAttribute(orderBy);
		} else {
			page.setOrderAttribute("computer.id");
		}
		if (sort != null) {
			page.setOrderSort(sort);
		} else {
			page.setOrderSort("asc");
		}

	}

	private void setPageInt(Page<Computer> page, Integer pageno) {
		if (pageno == null) {
			pageno = 1;
		}
		page.setPageInt(pageno - 1);
	}

	private void setObjectPerPage(Page<Computer> page, Integer nbObject) {
		if (nbObject == null) {
			nbObject = 10;
		}
		page.setObjetPerPage(nbObject);
	}

}
