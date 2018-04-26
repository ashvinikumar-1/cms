package com.highpeak.Ediscovery.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.highpeak.Ediscovery.bean.FileModel;

@Component
public interface UploadService {

	/* public List<File> getFiles(); */

	public List<FileModel> getFiles();

	public String fileUpload(final MultipartFile[] files,String folder) throws Exception;

	/*
	 * public String directoryUpload(String remoteDirPath, String localParentDir,
	 * String remoteParentDir, MultipartFile[] files) throws Exception;
	 */

}