class lab19
{
   public static void main(String[] args)
   {
      int i = 0;
      int sec = 0;
      while(true)
      {
         //if(i<0 & i+1000>=0) //если считать прошлым возврат к 0+
         if(i>i+1000) //если считать прошлым уход в -
            break;
         i+=1000;
         sec+=1;
      }
      System.out.println(sec/3600/24+" days");
   }
}
