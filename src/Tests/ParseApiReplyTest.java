import com.halestrom.API.ParseApiReply;
import org.junit.Assert;
import org.junit.Test;

public class ParseApiReplyTest
{

    final String exampleApiReply = "{\"Response\":\"Success\",\"Message\":\"\",\"HasWarning\":false,\"Type\":100,\"RateLimit\":{},\"Data\":{\"Aggregated\":false,\"TimeFrom\":1613692800,\"TimeTo\":1614297600,\"Data\":[{\"time\":1,\"high\":56331.09,\"low\":50766.83,\"open\":51591.61,\"volumefrom\":72433.44,\"volumeto\":3893034056.64,\"close\":1,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":2,\"high\":57528.16,\"low\":54140.61,\"open\":55933.47,\"volumefrom\":52924.57,\"volumeto\":2974872569.57,\"close\":2,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":3,\"high\":58348.75,\"low\":55516.07,\"open\":55900.84,\"volumefrom\":33823.01,\"volumeto\":1934005633.01,\"close\":3,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":4,\"high\":57540.74,\"low\":48194.31,\"open\":57469.95,\"volumefrom\":130806.33,\"volumeto\":6995162441.1,\"close\":4,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":5,\"high\":54164.35,\"low\":44983.39,\"open\":54121.17,\"volumefrom\":165758.08,\"volumeto\":8016450648.12,\"close\":5,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":6,\"high\":51399.55,\"low\":47015.55,\"open\":48898.7,\"volumefrom\":74972.28,\"volumeto\":3729774704.65,\"close\":6,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":7,\"high\":52086.34,\"low\":46728.88,\"open\":49738.17,\"volumefrom\":73102.42,\"volumeto\":3643008748.86,\"close\":7,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":8,\"high\":47767.06,\"low\":44153.46,\"open\":47082.87,\"volumefrom\":47784.9,\"volumeto\":2207244356.28,\"close\":8,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}]}}\n";
    final String exampleJSON = "[{\"time\":1,\"high\":56331.09,\"low\":50766.83,\"open\":51591.61,\"volumefrom\":72433.44,\"volumeto\":3893034056.64,\"close\":1,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":2,\"high\":57528.16,\"low\":54140.61,\"open\":55933.47,\"volumefrom\":52924.57,\"volumeto\":2974872569.57,\"close\":2,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":3,\"high\":58348.75,\"low\":55516.07,\"open\":55900.84,\"volumefrom\":33823.01,\"volumeto\":1934005633.01,\"close\":3,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":4,\"high\":57540.74,\"low\":48194.31,\"open\":57469.95,\"volumefrom\":130806.33,\"volumeto\":6995162441.1,\"close\":4,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":5,\"high\":54164.35,\"low\":44983.39,\"open\":54121.17,\"volumefrom\":165758.08,\"volumeto\":8016450648.12,\"close\":5,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":6,\"high\":51399.55,\"low\":47015.55,\"open\":48898.7,\"volumefrom\":74972.28,\"volumeto\":3729774704.65,\"close\":6,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":7,\"high\":52086.34,\"low\":46728.88,\"open\":49738.17,\"volumefrom\":73102.42,\"volumeto\":3643008748.86,\"close\":7,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"},{\"time\":8,\"high\":47767.06,\"low\":44153.46,\"open\":47082.87,\"volumefrom\":47784.9,\"volumeto\":2207244356.28,\"close\":8,\"conversionType\":\"direct\",\"conversionSymbol\":\"\"}]";


    @Test
    public void stringToJsonTest()
    {
        String actualResult = ParseApiReply.stringToJson(exampleApiReply);
        Assert.assertEquals(exampleJSON, actualResult);
    }


    @Test
    public void priceJsonToDoubleArr()
    {
        double[] expectedArr = {1, 2, 3, 4, 5, 6, 7, 8};
        double[] actualArr = ParseApiReply.priceJsonToDoubleArr(exampleJSON);
        Assert.assertArrayEquals(expectedArr, actualArr, 0.001);
    }


    @Test
    public void dateJsonToUnixArr()
    {
        long[] expectedArr = {1, 2, 3, 4, 5, 6, 7, 8};
        long[] actualArr = ParseApiReply.dateJsonToUnixArr(exampleJSON);
        Assert.assertArrayEquals(expectedArr, actualArr);

    }


    @Test
    public void unixToDateStr()
    {
        long unixDate = 1613692800;
        String expectedDate = "02/19/2021";
        String actualDate = ParseApiReply.unixToDateStr(unixDate);
        Assert.assertEquals(expectedDate, actualDate);
    }
}