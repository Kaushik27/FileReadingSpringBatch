package com.knight.fr.fr.job;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.knight.fr.dto.FileDataDTO;

@Configuration
public class BatchReader {

	@Value("${app.filePath}")
	private String filePath;

	@Bean
	public ItemReader<FileDataDTO> myFileItemReader() {
		System.err.println(filePath);
		FlatFileItemReader<FileDataDTO> fileReader = new FlatFileItemReader<>();
		fileReader.setResource(new FileSystemResource(filePath));
		fileReader.setLinesToSkip(1);
		LineMapper<FileDataDTO> lineMapper = createLineMapper();
		fileReader.setLineMapper(lineMapper);
		return fileReader;
	}

	public LineMapper<FileDataDTO> createLineMapper() {
		DefaultLineMapper<FileDataDTO> lineMapper = new DefaultLineMapper<>();
		LineTokenizer lineTokenizer = createLineTokenizer();
		lineMapper.setLineTokenizer(lineTokenizer);
		FieldSetMapper<FileDataDTO> informationMapper = createInformationMapper();
		lineMapper.setFieldSetMapper(informationMapper);

		return lineMapper;
	}

	public LineTokenizer createLineTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setNames(new String[] { "name", "age", "gender" });
		return lineTokenizer;
	}

	public FieldSetMapper<FileDataDTO> createInformationMapper() {
		BeanWrapperFieldSetMapper<FileDataDTO> informationMapper = new BeanWrapperFieldSetMapper<>();
		informationMapper.setTargetType(FileDataDTO.class);
		return informationMapper;
	}
}
