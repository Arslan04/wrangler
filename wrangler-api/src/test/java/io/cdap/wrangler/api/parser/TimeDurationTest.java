
import org.junit.Assert;
import org.junit.Test;

import io.cdap.wrangler.api.parser.TimeDuration;

public class TimeDurationTest{

    @Test
    public void testTimeDurationParsing() {
        Assert.assertEquals(5000, new TimeDuration("5s"). getMillis());
        Assert.assertEquals(2100, new TimeDuration("2.1s"). getMillis());
    }

}