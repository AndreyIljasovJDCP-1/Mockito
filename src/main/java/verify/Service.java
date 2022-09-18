package verify;

public class Service {

    private final Executor executor;

    public Service(Executor executor) {
        this.executor = executor;
    }

    public void someBusinessLogic(Object o) {
        switch (o) {
            case Integer i ->  {
                executor.execute(i);
                executor.execute(123);
                executor.execute(1234);

            }
            case String s -> executor.extract(s);
            default -> throw new RuntimeException("ERROR");
        }
    }


}
