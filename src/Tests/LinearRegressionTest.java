import com.halestrom.PriceLines.LinearRegression;
import org.junit.Assert;
import org.junit.Test;


public class LinearRegressionTest
{
    private final double[] testArr = {1, 2, 3, 4, 5};


    @Test
    public void testSigmaX_return_10()
    {
        double expectedResult = 10;
        double actualResult = LinearRegression.sigmaNotationOnIndex(testArr, "x");
        Assert.assertEquals(expectedResult, actualResult, 0.0001);
    }


    @Test
    public void testSigmaY_return_14()
    {
        double expectedResult = 14;
        double actualResult = LinearRegression.sigmaNotationOnIndex(testArr, "y");
        Assert.assertEquals(expectedResult, actualResult, 0.0001);
    }


    @Test
    public void testSigmaXY_return_40()
    {
        double expectedResult = 40;
        double actualResult = LinearRegression.sigmaNotationOnIndex(testArr, "x*y");
        Assert.assertEquals(expectedResult, actualResult, 0.0001);
    }


    @Test
    public void testSigmaXX_return_30()
    {
        double expectedResult = 30;
        double actualResult = LinearRegression.sigmaNotationOnIndex(testArr, "x^2");
        Assert.assertEquals(expectedResult, actualResult, 0.0001);
    }


    @Test
    public void testPricePredictOnIndex_return_6()
    {
        double expectedResult = 6;
        double actualResult = LinearRegression.pricePrediction(testArr, 5);
        Assert.assertEquals(expectedResult, actualResult, 0.0001);
    }


    @Test
    public void testCalculateIntercept_return_1()
    {
        double expectedResult = 1;
        double Sx = 10;
        double Sy = 14;
        double b = 1;
        double n = 4;
        double actualResult = LinearRegression.calculateIntercept(Sx, Sy, n, b);
        Assert.assertEquals(expectedResult, actualResult, 0.0001);
    }


    @Test
    public void testCalculateSlope_return_1()
    {
        double expectedResult = 1;
        double Sxy = 40;
        double Sx = 10;
        double Sy = 14;
        double Sxx = 30;
        double n = 4;
        double actualResult = LinearRegression.calculateSlope(Sxy, Sx, Sy, Sxx, n);
        Assert.assertEquals(expectedResult, actualResult, 0.0001);
    }
}