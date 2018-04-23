package com.highpeak.Ediscovery.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String fileUpload(final MultipartFile[] files) throws Exception {

		FileOutputStream fp = null;

		try {

			final List<com.highpeak.Ediscovery.model.File> filesToReturn = new ArrayList<>();

			// final String filePath = env.getProperty("ediscovery.file.upload.path") + "/";

			if (files != null && files.length > 0) {

				for (final MultipartFile file2 : files)

				{

					// final String fileName = UUID.randomUUID().toString() +
					// file2.getOriginalFilename();

					final String fileName = file2.getOriginalFilename();
					final String filePath = env.getProperty("ediscovery.file.upload.path") + "/";

					String path = "/home/karthikjoshi/Documents/ediscovery";
					String base = "/home/karthikjoshi/Documents";
					String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

					final byte[] bytes = file2.getBytes();

					fp = new FileOutputStream(filePath + fileName);
					final BufferedOutputStream stream = new BufferedOutputStream(fp);
					stream.write(bytes);
					stream.close();

					final com.highpeak.Ediscovery.model.File file = new com.highpeak.Ediscovery.model.File();
					file.setIsActive(true);
					file.setIsProcessed(false);
					// file.setCreatedDate(dateUtil.getUTCCalenderInstance(System.currentTimeMillis()));
					file.setName(fileName);
					file.setRelativePath(relative);

					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar calobj = Calendar.getInstance();
					file.setCreatedDate((df.format(calobj.getTime())));

					file.setAbsolutePath(filePath);

					filesToReturn.add(fileRepository.save(file));

					// fileRepository.save(file)

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
