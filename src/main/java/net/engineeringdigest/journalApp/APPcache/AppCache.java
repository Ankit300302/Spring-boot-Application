package net.engineeringdigest.journalApp.APPcache;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp.Repository.ConfigJournalAppRepository;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;


    public Map<String, String> appCache;


    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }

}
