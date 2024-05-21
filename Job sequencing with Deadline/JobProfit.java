public class JobProfit 
{
    int max(int[] d)
    {
        int max = 0;
        for(int i = 0 ; i<d.length ; i++)
        {
            if(d[max] < d[i])
            max = i;
        }
        return max;
    }
    public static void main(String[] args) 
    {
        JobProfit jp = new JobProfit();
        int n = 4 ; 
        int[] profit = {100 , 10 , 15 , 27};
        int[] deadline = {2 , 1 , 2 , 1};
        int di = 0;
        int pi = 0;
        int i = -1;
        int[] index = new int[4];
        while(true)
        {
            int max = jp.max(profit);
            di += deadline[max];
            if(di > n)
            break;
            pi += profit[max];
            profit[max] = -1;
            index[++i] = max;
        }
        System.out.println("Maximum profit : "+pi);
        System.out.println("\nSequence index wise : ");
        for(int j = 0 ; j<=i ; j++)
        System.out.print("job"+(index[j]+1)+"\t");
    }
}
