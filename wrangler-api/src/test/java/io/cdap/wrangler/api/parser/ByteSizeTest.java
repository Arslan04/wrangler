
import org.junit.Test;

import io.cdap.wrangler.api.parser.ByteSize;

public class ByteSizeTest {

    @Test
    public void testByteSizeParsing() {
        Assertions.assertEquals(10240, new ByteSize("10KB").getBytes());
        Assertions.assertEquals(1572864, new ByteSize("1.5MB").getBytes());
    }
}
