package com.highpeak.Ediscovery.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.highpeak.Ediscovery.bean.FileModel;
import com.highpeak.Ediscovery.exceptions.StorageException;
import com.highpeak.Ediscovery.model.File;
import com.highpeak.Ediscovery.repository.FileRepository;




@Component
public class UploadServiceImpl implements UploadService {

	
	//private final Path rootLocation;

	private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);
	
	
	/*@Autowired
	public UploadServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	 }*/
	 
	/*@Autowired
	private Environment env;*/
	 
	@Autowired
	private FileRepository fileRepository;
	
	
	
	@Override
	public List<File> getFileListById(final List<Integer> id)
	{
		try
		{
			return fileRepository.getFileList();
		}
		catch(final Exception e)
		{
			
			return new ArrayList<>();
		}
	}
	
	
		
		/* String filename = StringUtils.cleanPath(file.getOriginalFilename());
		 
		 
		try {
			
		
			if(file.isEmpty()) {
				throw new StorageException("Failed to store empty file"+ filename);
			}
	
			
			File fileUpload = new File();
			fileUpload.setName(fileModel.getName());
			//fileUpload.setPath(file.get)
			fileUpload.setIsActive(true);
			fileUpload.setIsProcessed(true);
		
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Calendar calobj = Calendar.getInstance();
			fileUpload.setCreatedDate((df.format(calobj.getTime())));
			
			fileRepository.save(fileUpload);
			return "File uploaded successfully";
		}
			catch(Exception e) {
				throw new StorageException("Failed to upload the File" + filename,e);
			}
			
		}*/
	
	
	
	 	
    /*@Override
    public void init() {
    	
    	
        try {
        	
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }*/
}
	
	
	
	

