package com.highpeak.Ediscovery.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.highpeak.Ediscovery.service.UploadService;
import com.highpeak.Ediscovery.utils.DateUtil;
import com.highpeak.Ediscovery.bean.FileModel;
import com.highpeak.Ediscovery.model.File;
import com.highpeak.Ediscovery.repository.FileRepository;



@Component
@RestController
@EnableAutoConfiguration
@RequestMapping("/rest/file")
public class FileUploadController {

	
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private FileRepository fileRepository;
	

	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String fileUpload(@RequestParam("file") final MultipartFile[] files ) throws Exception {
			
		
		FileOutputStream fp = null;
	
		
		try {
			
			final List<File> filesToReturn = new ArrayList<>();
			final String filePath = env.getProperty("ediscovery.file.upload.path") + "/";
			
			if(files!=null && files.length>0) {
				
				for(final MultipartFile file2 : files)
				{
					
					final String fileName = UUID.randomUUID().toString() + file2.getOriginalFilename();
					
					final byte[] bytes = file2.getBytes();
					
					
					fp = new FileOutputStream(filePath + fileName );
					final BufferedOutputStream stream = new BufferedOutputStream(fp);
					stream.write(bytes);
					stream.close();
			
					final File file = new File();
					file.setIsActive(true);
					file.setIsProcessed(false);
					
					DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					Calendar calobj = Calendar.getInstance();
					file.setCreatedDate((df.format(calobj.getTime())));
					
					file.setName(fileName);
					file.setPath(filePath);
					
					filesToReturn.add(fileRepository.save(file));
					
					
				}
				
			}
		
			return "file uploaded successfully";	
		}
	
		catch(final Exception e) {
			throw e;
		}
		
		finally
        {
            if( null != fp )
            {
                try
                {
                    fp.close();
                }
                catch( final Exception e)
                {
                    throw e;
                }
            }		
        }

		
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	/*@Autowired
	private FileUploadService fileuploadservice;
	
	@Autowired
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void uploadFileHandler(@RequestParam("name") String name,
                                  @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                //String rootPath = System.getProperty("catalina.home");
                String rootPath = 
                File dir = new File(0, rootPath + File.separator + "tmpFiles", rootPath, null, null, false, false, null);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.println("Server File Location="
                        + serverFile.getAbsolutePath());

                System.out.println("You successfully uploaded file=" + name);
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + name
                    + " because the file was empty.");
	
	
        }
    }*/


