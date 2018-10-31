package com.xx.spring.boot.jdbc.client;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xx.spring.boot.jdbc.MyApplication;
import com.xx.spring.boot.jdbc.dao.BikeData;
import com.xx.spring.boot.jdbc.repository.BikeDataRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BikeDataTest {

	 @Autowired
	 private BikeDataRepository bikeDataRepository;
	  
	 
	 @Test
	 public void whenFindByID_thenReturnBikeData() {
	     	  
	     // when
	     BikeData found = bikeDataRepository.findOne("3");
	     System.out.println("found.getModel() ....................................................................:: "+found.getModel());
	  
/*	     // then
	     assertThat(found.getName())
	       .isEqualTo(alex.getName());
	     
	     
	     GenericXmlApplicationContext ctx =
	    		 new GenericXmlApplicationContext();
	    		 ctx.load("classpath:spring/embedded-h2-cfg.xml");
	    		 ctx.refresh();
	    		 testDao(ctx.getBean(SingerDao.class));
	    		 ctx.close();*/
	 }
}
