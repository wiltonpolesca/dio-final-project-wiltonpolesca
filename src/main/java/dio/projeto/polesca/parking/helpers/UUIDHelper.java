package dio.projeto.polesca.parking.helpers;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UUIDHelper {
    public static String getUUID()  {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
