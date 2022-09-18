package verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    private Executor executor;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<Integer> captorInteger;

    private Service service;

    @BeforeEach
    void setUp() {
        service = new Service(executor);
    }

    @Test
    void someBusinessLogic_whenObjectIsInt() {
        Object valueAsInt = 111;

        service.someBusinessLogic(valueAsInt);

        Mockito.verify(executor, Mockito.times(3)).execute(captorInteger.capture());
        Mockito.verify(executor, Mockito.never()).extract(valueAsInt);


        List<Integer> allValues = captorInteger.getAllValues();

        System.out.println();
    }

    @Test
    void someBusinessLogic_whenObjectIsString() {
        Object valueAsString = "111";

        service.someBusinessLogic(valueAsString);

        Mockito.verify(executor).extract(captorString.capture());
        Mockito.verify(executor, Mockito.never()).execute(valueAsString);
        String actualValue = captorString.getValue();

        Assertions.assertEquals(valueAsString, actualValue);
    }
}