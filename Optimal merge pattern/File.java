public class File 
{
    int min(int[] b)
    {
        int min = 0;
        for(int i = 0 ; i < b.length ; i++)
        {
            if( b[min]>b[i])
            min = i;
        }
        return min;
    }
    public static void main(String[] args) {
        File f = new File();
        int size = 5;
        int[] a = {2 , 5 , 3 , 9 , 8  };
        int[] b = a;
        int[] sum =new int[4];
        while(size > 1)
        {
            
            int i = f.min(b);
            int temp = b[i];
            b[i] = 100000;
            int j = f.min(b);
            int s = temp + b[j];
            b[j] = s ;
            
            size--;
            sum[size-1] = s;
        }
        int s = 0;
        for(int i = 0 ; i < 4 ; i++)
        s += sum[i];
        System.out.println("Optimal merge File shift : "+s);
    }
}
