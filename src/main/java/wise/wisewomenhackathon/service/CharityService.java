package wise.wisewomenhackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wise.wisewomenhackathon.config.CharityConfiguration;

@Service
public class CharityService {

    @Autowired
    private CharityConfiguration charityConfiguration;

    public String getCharityDescription(String charityName) {
        return charityConfiguration.getDescriptions().get(charityName);
    }
}
