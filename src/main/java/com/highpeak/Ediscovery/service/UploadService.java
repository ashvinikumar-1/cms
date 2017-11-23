package com.highpeak.Ediscovery.service;

import java.util.List;

import org.hibernate.exception.DataException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.highpeak.Ediscovery.bean.FileModel;
import com.highpeak.Ediscovery.model.File;



public interface UploadService {

	//public String fileUpload(MultipartFile file,FileModel fileModel);
	
	public List<File> getFileListById( List<Integer> id );
	
	//public String store(MultipartFile file);
	
	//File findByid( Integer fileId ) throws DataException;
	
	//File saveFile( File file ) throws DataException;

}
