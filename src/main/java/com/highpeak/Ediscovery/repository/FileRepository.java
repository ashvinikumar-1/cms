package com.highpeak.Ediscovery.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.highpeak.Ediscovery.model.File;


@Transactional
public interface FileRepository extends PagingAndSortingRepository<File,Integer> {

	
	@Query( " from File WHERE f_id in :id" )
     List getFileList();
	
	
}
