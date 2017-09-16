package com.knight.fr.fr.job;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.knight.fr.dto.FileDataDTO;


@Component
public class BatchWriter implements ItemWriter<FileDataDTO> {

	@Override
	public void write(List<? extends FileDataDTO> items) throws Exception {

		for (FileDataDTO fileDataDTO : items) {
			System.out.println("here"+fileDataDTO);
		}
	}

}
