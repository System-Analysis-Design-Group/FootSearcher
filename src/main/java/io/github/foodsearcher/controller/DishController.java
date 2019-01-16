package io.github.foodsearcher.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
	public StatusMsg updatePicture(@PathVariable("dishId") Long id, @RequestParam(value = "file", required = false) MultipartFile multipartFile) {
		DishInfo res = dishInfoService.findById(id);
		try {
			String path = "./picture/dishes/" ;
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
			UUID uuid = UUID.randomUUID();
			String result = uuid.toString().replace("-", "");
			String fileName = result + ".png";
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + fileName));
			byte[] bs = new byte[1024];
			int len;
			while ((len = fileInputStream.read(bs)) != -1) {
				bos.write(bs, 0, len);
			}
			bos.flush();
			bos.close();
			if(path != null){
				res.setImagePath(path + fileName);
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
