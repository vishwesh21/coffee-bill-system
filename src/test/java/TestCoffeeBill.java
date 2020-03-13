import coffee.utility.BillUtility;
import org.junit.Assert;
import org.junit.Test;


public class TestCoffeeBill {
    @Test
    public void testAllExclusionScanerio(){

        String[] orderList = {"-Coffee"};
        String bill = new BillUtility().getBillingAmount(orderList);
        Assert.assertEquals("Not Valid Order",bill);
    }

    @Test
    public void testNonExclusionScanerio(){
        String[] orderList = {"Coffee"};
        String bill = new BillUtility().getBillingAmount(orderList);
        Assert.assertEquals("5.0",bill);
    }

    @Test
    public void testValidScanerio(){
        String[] orderList = {"Chai,-sugar"};
        String bill = new BillUtility().getBillingAmount(orderList);
        Assert.assertEquals("3.5",bill);
        String[] orderList1 = {"Chai, -sugar, -milk"};
        Assert.assertEquals("2.5",new BillUtility().getBillingAmount(orderList1));
        String[] orderList2 = {"Chai"};
        Assert.assertEquals("4.0",new BillUtility().getBillingAmount(orderList2));
        String[] orderList3 = {"Chai, -sugar", "Chai", "Coffee, -milk"};
        Assert.assertEquals("11.5",new BillUtility().getBillingAmount(orderList3));

    }

    @Test
    public void testInValidOrder(){
        String[] orderList = {"-sugar,Coffee"};
        String bill = new BillUtility().getBillingAmount(orderList);
        Assert.assertEquals("Not Valid Order",bill);
    }

    @Test
    public void testMenuItemWithWrongIngredient(){
        String[] orderList = {"Coffee,-soda"};
        String bill = new BillUtility().getBillingAmount(orderList);
        Assert.assertEquals("Not Valid Order",bill);
    }
}
