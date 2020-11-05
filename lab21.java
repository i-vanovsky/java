import java.math.BigDecimal;
class lab21
{
   public static void main(String[] args)
   {
      BigDecimal bd1 = new BigDecimal("1234567890");
      BigDecimal bd2 = new BigDecimal("0.0000001");
      BigDecimal bd3 = new BigDecimal("1234567890");
      for (int i = 0; i < 1_000_000_000; i++) 
         bd3 = bd3.add(bd2);
      System.out.println(bd3.compareTo(bd1)); 
   }
}
