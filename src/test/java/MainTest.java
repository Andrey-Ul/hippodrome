import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {

    @Test
    @Disabled
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void main() throws Exception {
        Main.main(null);
    }
}