package io.github.foodsearcher.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.foodsearcher.model.DishInfo;
import io.github.foodsearcher.model.StatusMsg;
import io.github.foodsearcher.service.DishInfoService;

@RestController
@RequestMapping("/dishes")																																		
public class DishController {
	
	@Autowired
	private DishInfoService dishInfoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public StatusMsg createDish(@RequestBody DishInfo dishInfo) {
		DishInfo res;
		try {
			res = dishInfoService.createDishInfo(dishInfo);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) res);
	}
	
	@RequestMapping(value = "/{dishId}",method = RequestMethod.PUT)
	public StatusMsg updatePicture(@PathVariable("dishId") Long id, @RequestParam(value = "file", required = false) MultipartFile file) {
		DishInfo res = dishInfoService.findById(id);
		try {
			Path path = null;
			File directory = new File(".");
			String UPLOADED_FOLDER = "/picture/dishes/";
			File dir = new File(directory.getCanonicalPath() + UPLOADED_FOLDER);
			if (dir.exists()) {
	            if (dir.isDirectory()) {
	                System.out.println("dir exists");
	            } else {
	                System.out.println("the same name file exists, can not create dir");
	            }
	        } else {
	            System.out.println("dir not exists, create it ...");
	            dir.mkdir();
	        }
			if (file != null && StringUtils.hasText(file.getOriginalFilename())) {
				byte[] bytes = file.getBytes();
		        path = Paths.get(directory.getCanonicalPath() + UPLOADED_FOLDER + file.getOriginalFilename());
		        Files.write(path, bytes);
			}
			if(path != null){
				res.setImagePath(path.toString());
			}
			res = dishInfoService.updataDishInfo(res);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) res.getImagePath());
	}
	
	@RequestMapping(value = "/{dishId}",method = RequestMethod.GET)
	public StatusMsg getDish(@PathVariable("dishId") Long id) {
		DishInfo result;
		try {
			result = dishInfoService.findById(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) result);
	}
	
	@RequestMapping(value = "/{dishId}",method = RequestMethod.DELETE)
	public StatusMsg deleteDish(@PathVariable("dishId") Long id) {
		try {
			dishInfoService.delete(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public StatusMsg updateDish(@RequestBody DishInfo dishInfo) {
		DishInfo result;
		try {
			result = dishInfoService.updataDishInfo(dishInfo);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) result);
	}
	
	@RequestMapping(value="/store/{storeId}",method = RequestMethod.GET)
	public StatusMsg getStoreDish(@PathVariable("storeId") Long id) {
		List<DishInfo> result;
		try {
			result = dishInfoService.findByStoreId(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) result);
	}
}
