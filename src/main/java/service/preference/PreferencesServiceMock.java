package service.preference;

import java.util.Set;

public class PreferencesServiceMock implements PreferencesService {

    private Set<Preference> value;

    @Override
    public Set<Preference> get(String userId) {
        return value;
    }

    public void setValue(Set<Preference> value) {
        this.value = value;
    }
}

