package com.pops.restWebService.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

// field 1,2 only
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean somebean = new SomeBean("bean1", "bean2", "bean3");

//		tell it what to ignore
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		// create filters provider to ignore
//		You must define your bean you are filtering so go to SomeBean object and add @JsonFilter("SomeBeanFilter")
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

//		bean value i want to add in
		MappingJacksonValue mapping = new MappingJacksonValue(somebean);

		mapping.setFilters(filters);

		return mapping;

	}

//	field 2,3 only
	@GetMapping("/filtering-all")
	public MappingJacksonValue retrieveListOfSomeBeans() {

		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6"));

//		tell it what to ignore
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

		// create filters provider to ignore
//		You must define your bean you are filtering so go to SomeBean object and add @JsonFilter("SomeBeanFilter")
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

//		bean value i want to add in
		MappingJacksonValue mapping = new MappingJacksonValue(list);

		mapping.setFilters(filters);

		return mapping;
	}
}
