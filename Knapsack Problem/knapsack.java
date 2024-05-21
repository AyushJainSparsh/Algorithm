class knapsack
{
    int max(float[] r)
    {
        int max = 0;
        for(int i = 0 ; i<r.length ; i++)
        {
            if(r[max] < r[i])
            max = i;
        }
        return max;
    }

    public static void main(String[] args) 
    {
        knapsack k = new knapsack();
        float capacity = 20;
        float[] profit = {25 , 24 , 15};
        float[] weight = {18 , 15 , 10};
        float[] ratio = new float[3];
        int[] index = new int[3];
        for(int i = 0 ; i<3 ; i++)
        ratio[i] = profit[i]/weight[i];
        float wi = 0 , pi =0  ;
        int i =-1 , max;
        while(true)
        {
            max = k.max(ratio);
            wi += weight[max];
            if(wi > capacity)
            break;
            pi += profit[max];
            ratio[max] = -1;
            index[++i] = max;
        }
        float r = (capacity - wi)/weight[max];
        index[++i] = max;
        pi += profit[i]/r;
        System.out.println("ratio used by weight");
        for(int j = 0 ; j<= i ; j++)
        {
            if(j == i)
            System.out.println(weight[index[j]] + "\t\t" + r);
            else
            System.out.println(weight[index[j]] + "\t\t 1" );
        }
        System.out.println("\n\nMaximum profit = "+pi);

    }
}