package com.mellisphera.security.service;

import com.mellisphera.entities.*;
import com.mellisphera.entities.bm.BmNote;
import com.mellisphera.repositories.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.mellisphera.entities.bm.BmApiary;
import com.mellisphera.entities.bm.BmHive;
import com.mellisphera.entities.bm.BmSensor;
import com.mellisphera.security.entities.BmAuth;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class BmAuthServiceImpl implements BmAuthService {
	
    @Value("${apiwatch.app.geoip.timeout}")
    private int timeout;
    @Value("${mellisphera.app.bmAuth.url}")
    private String bmUrl;
    @Value("${mellisphera.app.bmAuth.licenceKey}")
    private String licenceKey;
    
    private HttpEntity<MultiValueMap<String, String>> requestEntity;
    private HttpHeaders header;
    
    @Autowired private ApiaryRepository apiaryRepository;
    @Autowired private HivesRepository hiveRepository;
    @Autowired private SensorRepository sensorRepository;
    @Autowired private NoteRepository noteRepository;
    @Autowired private UserRepository userRepository;
    
	@Override
	public BmAuth getBmAuth(String username, String password) {
		this.header = new HttpHeaders();
		this.header.add("Content-Type", "application/x-www-form-urlencoded");
		this.header.add("license_key", this.licenceKey);
		MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
		bodyMap.add("username", username);
		bodyMap.add("password", password);
		this.requestEntity = new HttpEntity<>(bodyMap, this.header);
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		BmAuth bmAuth = restTemplate.postForObject(bmUrl, requestEntity, BmAuth.class);
		return bmAuth;
	}

	@Override
	public void saveBmData(BmAuth bmData, User user) {
		System.out.println(bmData);
		for(BmApiary bmApiary: bmData.getPayload().getApiaries()) {
			Apiary newApiary = new Apiary();
			newApiary.set_id(bmApiary.getApiaryId());
			newApiary.setZipCode(bmApiary.getZipCode());
			newApiary.setName(bmApiary.getName());
			newApiary.setUserId(user.getId());
			newApiary.setCreateDate(bmApiary.getCreateDate());
			newApiary.setPrivateApiary(bmApiary.getPrivateApiary());
			newApiary.setCountryCode(bmApiary.getCountryCode());
			newApiary.setUsername(user.getUsername());
			newApiary.setPhoto("./assets/imageClient/testAccount.png");
			System.out.println(bmApiary	);
			this.apiaryRepository.insert(newApiary).get_id();
			for(BmHive bmHive: bmApiary.getHives()) {
				Hive newHive = new Hive();
				newHive.set_id(bmHive.getHiveId());
				newHive.setHivePosY(0);
				newHive.setHivePosX(0);
				newHive.setApiaryId(bmApiary.getApiaryId());
				newHive.setUserId(user.getId());
				newHive.setCreateDate(this.convertTimestampToDate(bmHive.getCreateDate()));
				newHive.setHidden(bmHive.getHidden());
				newHive.setDataLastReceived(this.convertTimestampToDate(bmHive.getDataLastReceived()));
				newHive.setName(bmHive.getName());
				newHive.setUsername(user.getUsername());
				newHive.setName(bmHive.getName());
				this.hiveRepository.insert(newHive).get_id();
				if (bmHive.getDevices() != null) {
					for(BmSensor bmSensor : bmHive.getDevices()) {
						Sensor sensor = new Sensor();
						sensor.set_id(bmSensor.getDevice().getDeviceId());
						sensor.setHiveId(bmHive.getHiveId());
						sensor.setCreateDate(this.convertTimestampToDate(bmSensor.getDevice().getCreateDate()));
						sensor.setDataLastReceived(this.convertTimestampToDate(bmSensor.getDevice().getDataLastReceived()));
						sensor.setSensorRef(bmSensor.getDevice().getDeviceAddress());
						sensor.setModel(bmSensor.getDevice().getModel());
						sensor.setHiveName(bmHive.getName());
						sensor.setApiaryId(bmApiary.getApiaryId());
						sensor.setHivePositionId(bmSensor.getHivePositionId());
						sensor.setStart(this.convertTimestampToDate(bmSensor.getStart()));
						sensor.setType(this.getTypeByRef(bmSensor.getDevice().getDeviceAddress()));
						this.sensorRepository.insert(sensor);
					}
				}
			if (bmHive.getNotes() != null) {
				for (BmNote bmNote: bmHive.getNotes()) {
					Note hiveNote = new Note(bmNote.getNoteId(),
							this.convertTimestampToDate(bmNote.getCreateDate()),
							bmNote.getType(),
							bmNote.getTags(),
							bmNote.getDescription(),
							bmNote.getHiveId(),
							bmNote.getApiaryId(),
							this.convertTimestampToDate(bmNote.getOpsDate()),
							bmApiary.getUserId());
					this.noteRepository.insert(hiveNote);
				}
			}
			}
			if (bmApiary.getNotes() != null) {
				for (BmNote bmNote: bmApiary.getNotes()) {
					Note hiveNote = new Note(bmNote.getNoteId(),
							this.convertTimestampToDate(bmNote.getCreateDate()),
							bmNote.getType(),
							bmNote.getTags(),
							bmNote.getDescription(),
							bmNote.getHiveId(),
							bmNote.getApiaryId(),
							this.convertTimestampToDate(bmNote.getOpsDate()),
							bmApiary.getUserId());
					this.noteRepository.insert(hiveNote);
				}
			}
		}
	}

	@Override
	public Date convertTimestampToDate(int time){
		Timestamp timestamp = new Timestamp(time);
		return new Date(timestamp.getTime());
	}


	private String getTypeByRef(String ref) {
		String prefix = ref.split(":")[0];
		if (prefix.equals("41")) {
			return "T2";
		} else if (prefix.equals("42")) {
			return "T_HR";
		} else if (prefix.equals("43")) {
			return "WEIGHT";
		} else {
			return "ALIEN";
		}
	}
	
    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
