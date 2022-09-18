package service.advice;

import service.preference.Preference;

import java.util.Set;

public interface AdviceService {

    Set<Preference> getAdvice(String userId);
}
