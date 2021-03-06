/* Copyright 2018-present Mellisphera
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. */ 



package com.mellisphera.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mellisphera.entities.DailyRecordsTH;
import com.mellisphera.entities.DailyRecordsW;
import com.mellisphera.entities.Hive;
import com.mellisphera.entities.SimpleSeries;
import com.mellisphera.repositories.DailyRecordsWRepository;

@Service
@RestController
@RequestMapping("/dailyRecordsW")
public class DailyRecordsWController {

	@Autowired
	private DailyRecordsWRepository dailyRecordsWRepository;
	@Autowired HiveController hiveController;
	
	public DailyRecordsWController(DailyRecordsWRepository dailyRecordsWRepository) {
		this.dailyRecordsWRepository = dailyRecordsWRepository;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces={"application/json"})
	public List<DailyRecordsW> getAll(){
		return this.dailyRecordsWRepository.findAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces={"application/json"})
	public Optional<DailyRecordsW> getById(@PathVariable("id") String id){
		return this.dailyRecordsWRepository.findById(id);
	}
	
	@GetMapping(value="/hive/{hiveId}")
	public List<DailyRecordsW>getByhiveId(@PathVariable("hiveId") String hiveId){
		return this.dailyRecordsWRepository.findDailyRecordsWByHiveId(hiveId);
	}
	
	@PostMapping("/apiary/{idApiary}")
	public List<List<DailyRecordsW>> getDailyRecordsWByApiary(@PathVariable String idApiary, @RequestBody Date[] range) {
        Sort sort = new Sort(Direction.DESC, "timestamp");
		return this.hiveController.getAllUserHives(idApiary).stream().map(hive -> this.dailyRecordsWRepository.findByHiveIdAndRecordDateBetween(hive.get_id(), range[0], range[1], sort)).collect(Collectors.toList());
	}
	
	@PostMapping("/hive/between/{hiveId}")
	public List<SimpleSeries> getWeightIcomeWByHive(@PathVariable String hiveId, @RequestBody Date[] range) {
        Sort sort = new Sort(Direction.DESC, "timestamp");
		return this.dailyRecordsWRepository.findByHiveIdAndRecordDateBetween(hiveId, range[0], range[1], sort).stream().map(_daily -> new SimpleSeries(_daily
				.getRecordDate(), _daily.getWeight_income_gain(), _daily.getSensorRef())).collect(Collectors.toList());
	}
	
	
	@PostMapping("tMin/{hiveId}")
	public List<SimpleSeries> getTminByHive(@RequestBody Date[] range, @PathVariable String hiveId){
        Sort sort = new Sort(Direction.DESC, "timestamp");
		return this.dailyRecordsWRepository.findByHiveIdAndRecordDateBetween(hiveId, range[0], range[1], sort).stream().map(_daily -> new SimpleSeries(_daily
				.getRecordDate(), _daily.getTemp_ext_min(), _daily.getSensorRef())).collect(Collectors.toList());
	}
	
	@PostMapping("tMax/{hiveId}")
	public List<SimpleSeries> getTmaxByHive(@RequestBody Date[] range, @PathVariable String hiveId){
        Sort sort = new Sort(Direction.DESC, "timestamp");
		return this.dailyRecordsWRepository.findByHiveIdAndRecordDateBetween(hiveId, range[0], range[1], sort).stream().map(_daily -> new SimpleSeries(_daily
				.getRecordDate(), _daily.getTemp_ext_max(), _daily.getSensorRef())).collect(Collectors.toList());
	}
	

	@PostMapping("weightMax/{hiveId}")
	public List<SimpleSeries> getWeightByHive(@RequestBody Date[] range, @PathVariable String hiveId){
        Sort sort = new Sort(Direction.DESC, "timestamp");
		return this.dailyRecordsWRepository.findByHiveIdAndRecordDateBetween(hiveId, range[0], range[1], sort).stream().map(_daily -> new SimpleSeries(_daily
				.getRecordDate(), _daily.getWeight_max(), _daily.getSensorRef())).collect(Collectors.toList());
	}
	
}
