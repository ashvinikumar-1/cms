package com.highpeak.Ediscovery.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.highpeak.Ediscovery.bean.FileModel;
//import com.highpeak.Ediscovery.model.File;
import com.highpeak.Ediscovery.repository.FileRepository;

@Component
public class UploadServiceImpl implements UploadService {

	@Autowired
	private Environment env;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private UploadService uploadService;

	@Override
	public List<FileModel> getFiles() {

		try {
			List<FileModel> files = fileRepository.getFiles();
			return files;
		} catch (Exception e) {
			throw e;
		}

	}


    @Override
    public String fileUpload(final MultipartFile[] files,String folder) throws Exception {
        AWSCredentials credentials = new BasicAWSCredentials(
                env.getProperty("aws.access.key"),
                env.getProperty("aws.secret.key")
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();



        if(folder==null || folder.length()==0){
            folder = "";
        }
        else{
            folder = folder+"/";
        }

        FileOutputStream fp = null;

        try {

            final List<com.highpeak.Ediscovery.model.File> filesToReturn = new ArrayList<>();

            // final String filePath = env.getProperty("ediscovery.file.upload.path") + "/";




            if (files != null && files.length > 0) {

                for (final MultipartFile file2 : files)

                {
                    File convFile = new File(file2.getOriginalFilename());
                    String fileName;
                    com.highpeak.Ediscovery.model.File file;
                    DateFormat df;
                    Calendar calobj;


                    try (FileOutputStream fos = new FileOutputStream(convFile)) {
                        fos.write(file2.getBytes());
                        fos.close();
                    }

                    fileName = convFile.getName();


//                    filePath = env.getProperty("ediscovery.file.upload.path") + "/";



                    s3client.putObject(
                            env.getProperty("aws.bucket.name"),
                            folder+fileName,
                            convFile
                    );


                    file = new com.highpeak.Ediscovery.model.File();
                    file.setIsActive(true);
                    file.setIsProcessed(false);
                    // file.setCreatedDate(dateUtil.getUTCCalenderInstance(System.currentTimeMillis()));
                    file.setName(fileName);
                    file.setRelativePath(folder+fileName);

                    df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    calobj = Calendar.getInstance();
                    file.setCreatedDate((df.format(calobj.getTime())));

                    file.setAbsolutePath("https://"+env.getProperty("aws.bucket.name")+".s3.us-east-2.amazonaws.com"+"/"+folder+fileName);

                    filesToReturn.add(fileRepository.save(file));

                    // fileRepository.save(file)
                    //delete file
                    convFile.delete();


                }

            }

            return "file uploaded successfully";

        }

        catch (final Exception e) {
            throw e;
        }

        finally {
            if (null != fp) {
                try {
                    fp.close();
                } catch (final Exception e) {
                    throw e;
                }
            }
        }

    }
	/*
	 * @Override public String directoryUpload(String remoteDirPath, String
	 * localParentDir, String remoteParentDir, MultipartFile[] files) throws
	 * Exception {
	 * 
	 * File localDir = new File(localParentDir); File[] subFiles =
	 * localDir.listFiles(); if (subFiles != null && subFiles.length > 0) { for
	 * (File item : subFiles) {
	 * 
	 * String remoteFilePath = remoteDirPath + "/" + remoteParentDir + "/" +
	 * item.getName(); if (remoteParentDir.equals("")) { remoteFilePath =
	 * remoteDirPath + "/" + item.getName(); }
	 * 
	 * if (item.isFile()) { // upload the file String localFilePath =
	 * item.getAbsolutePath(); System.out.println("About to upload the file: " +
	 * localFilePath); String uploaded = uploadService.fileUpload(files);
	 * 
	 * } else { // create directory on the server
	 * 
	 * if (created) { System.out.println("CREATED the directory: " +
	 * remoteFilePath); } else {
	 * System.out.println("COULD NOT create the directory: " + remoteFilePath); }
	 * 
	 * // upload the sub directory String parent = remoteParentDir + "/" +
	 * item.getName(); if (remoteParentDir.equals("")) { parent = item.getName(); }
	 * 
	 * localParentDir = item.getAbsolutePath(); directoryUpload(remoteDirPath,
	 * localParentDir, parent); } } } }
	 */
}
