package com.knight.fr.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.knight.fr.dto.FileDataDTO;
import com.knight.fr.fr.job.BatchReader;
import com.knight.fr.fr.job.BatchWriter;

@Configuration
public class StepConfig {

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	@Autowired
	BatchReader batchReader;

	@Autowired
	BatchWriter batchWriter;

	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Before Step");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("process").<FileDataDTO, FileDataDTO>chunk(100)
				.reader(batchReader.myFileItemReader()).writer(batchWriter).build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("After Step");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
}
