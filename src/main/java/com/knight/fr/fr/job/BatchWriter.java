package com.knight.fr.fr.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knight.fr.domain.Person;
import com.knight.fr.dto.FileDataDTO;
import com.knight.fr.repo.PersonRepo;

@Component
public class BatchWriter implements ItemWriter<FileDataDTO> {

	@Autowired
	PersonRepo personRepo;
	
	@Override
	public void write(List<? extends FileDataDTO> items) throws Exception {

		List<Person> personList=new ArrayList<Person>();
		for (FileDataDTO fileDataDTO : items) {System.out.println("here"+fileDataDTO);
			Person person =new Person();
			person.setName(fileDataDTO.getName());
			person.setAge(fileDataDTO.getAge());
			person.setGender(fileDataDTO.getGender());
			personList.add(person);
		}
		personRepo.save(personList);
		
		
		System.err.println(personRepo.findOne("kaushik"));
//		personRepo.flush();
	}

}
