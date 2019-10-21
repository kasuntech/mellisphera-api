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

import com.mellisphera.entities.AlertUser;
import com.mellisphera.entities.AlertConf;
import com.mellisphera.repositories.AlertUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alertsConf")
public class AlertConfController {

    @Autowired private AlertUserRepository alertUserRepository;

    @GetMapping("/{userId}")
    public AlertUser getConfByUser(@PathVariable String userId) {
        return this.alertUserRepository.findByUserId(userId);
    }

    @PutMapping("/update/{userId}/{alertId}")
    public String updateConf(@PathVariable String userId, @PathVariable String alertId, @RequestBody AlertConf alert) {
        AlertUser alertUser = this.alertUserRepository.findByUserId(userId);
        alertUser.getAlertConf().put(alertId, alert);
        this.alertUserRepository.save(alertUser);
        return alertId + "updated";
    }
}
