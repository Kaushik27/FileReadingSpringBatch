package com.knight.fr.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfig {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Bean
	public Job myJobFlow(Step step1, Step step2, Step step3) {
		return jobBuilderFactory.get("myJob").start(step1).next(step2).next(step3).build();
	}
}
