package com.highpeak.Ediscovery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.highpeak.Ediscovery.bean.FileModel;
import com.highpeak.Ediscovery.repository.FileRepository;
import com.highpeak.Ediscovery.service.UploadService;
import com.highpeak.Ediscovery.uiresponse.AbstractController;

@Component
@RestController
@EnableAutoConfiguration
@RequestMapping("/rest/file")
public class FileUploadController extends AbstractController {

	@Autowired
	private Environment env;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private FileRepository fileRepository;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String fileUpload(@RequestParam("file") final MultipartFile[] files) throws Exception {

		try {
			return uploadService.fileUpload(files);
		}

		catch (Exception e) {
			throw e;
		}

	}

	/*
	 * @RequestMapping(value = "/uploadDir", method = RequestMethod.POST)
	 * public @ResponseBody String directoryUpload(@RequestParam("files")
	 * MultipartFile[] file, String localParentDir, String remoteParentDir) throws
	 * Exception {
	 * 
	 * try { return uploadService.directoryUpload(localParentDir, remoteParentDir,
	 * file); }
	 * 
	 * catch (Exception e) { throw e; }
	 * 
	 * }
	 */

	@RequestMapping(value = "/getFiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FileModel> getFiles() {

		try {

			return uploadService.getFiles();

		} catch (Exception e) {
			throw e;
		}
	}

}
