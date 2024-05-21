class MatrixMultiplication
{
    void display(int[][] a)
    {
        for(int i = 0 ; i<a.length ; i++)
        {
            for( int j = 0 ; j<a.length ; j++)
            System.out.print(a[i][j]+"\t");
            System.out.println();
        }
    }

    int[][] add(int[][] a , int[][] b )
    {
        int c[][] = new int[a.length][a.length];
        for(int i = 0 ; i < a.length ; i++)
        {
            for(int j = 0 ; j < a.length ; j++)
            c[i][j] = a[i][j] + b[i][j];
        }
        return c;
    }
    
    int[][] sub(int[][] a , int[][] b )
    {
        int c[][] = new int[a.length][a.length];
        for(int i = 0 ; i < a.length ; i++)
        {
            for(int j = 0 ; j < a.length ; j++)
            c[i][j] = a[i][j] - b[i][j];
        }
        return c;
    }

    int[][] multiply(int[][] a , int[][] b )
    {
        int n = a.length;
        int res[][] = new int[n][n];
        if(n==1)
        res[0][0] = a[0][0] * b[0][0];
        else
        {
            int[][] a11 = new int[n/2][n/2];
            int[][] a12 = new int[n/2][n/2];
            int[][] a21 = new int[n/2][n/2];
            int[][] a22 = new int[n/2][n/2];
            int[][] b11 = new int[n/2][n/2];
            int[][] b12 = new int[n/2][n/2];
            int[][] b21 = new int[n/2][n/2];
            int[][] b22 = new int[n/2][n/2];

            split(a , a11 , 0 , 0 );
            split(a , a12 , 0 , n/2 );
            split(a , a21 , n/2 , 0 );
            split(a , a22 , n/2 , n/2 );

            split(b , b11 , 0 , 0 );
            split(b , b12 , 0 , n/2 );
            split(b , b21 , n/2 , 0 );
            split(b , b22 , n/2 , n/2 );

            /*
            * p = (a11+a22)(b11+b22)
            * q = (a21+a22)b11
            * r = a11(b12-b22)
            * s = a22(b21-b11)
            * t = (a11+a12)b22
            * u = (a21-a11)(b11+b12)
            * v = (a12-a22)(b21+b22)
            */

            int[][] p = multiply(add(a11 , a22 ),add(b11 , b22));
            int[][] q = multiply(add(a21 ,a22),b11);
            int[][] r = multiply(a11 , sub(b12 , b22));
            int[][] s = multiply(a22, sub(b21,b11));
            int[][] t = multiply(add(a11, a12), b22);
            int[][] u = multiply(sub( a21 , a11) , add(b11 , b12));
            int[][] v = multiply(sub(a12, a22), add(b21, b22));

            /*
            * c11 = p+s-t+v
            * c12 = r+t
            * c21 = q+s
            * c22 = p+r-q+u
            */

            int[][] c11 = add(sub(add(p,s),t),v);
            int[][] c12 = add(t,r);
            int[][] c21 = add(q,s);
            int[][] c22 = add(sub(add(p,r),q),u);

            join(res , c11 , 0 , 0);
            join(res , c12 , 0 , n/2);
            join(res , c21 , n/2 , 0);
            join(res , c22 , n/2 , n/2);

        }
        return res;
    } 

    void split(int[][] a , int[][] r , int i , int j)
    {
        for(int k = 0 , i1=i ; k<r.length ; k++ , i1++)
        {
            for( int l =0 , j1=j; l<r.length ; l++ , j1++)
            r[k][l] =  a[i1][j1];
        }
    }

    void join(int[][] r , int[][] c , int i , int j)
    {
        for(int k = 0 ,i1=i ; k<c.length ; k++ , i1++)
        {
            for(int l = 0 , j1=j ; l<c.length ; l++ , j1++)
            r[i1][j1] = c[k][l];
        }
    }

    public static void main(String[] args) 
    {
        MatrixMultiplication mm = new MatrixMultiplication();

        int[][] a = { { 1, 2, 5, 8 },
                      { 3, 1, 6, 8 },
                      { 6, 5, 0, 0 },
                      { 6, 2, 4, 8 } };
        int[][] b = { { 1, 0, 8, 9 },
                      { 9, 2, 7, 4 },
                      { 3, 5, 9, 1 },
                      { 0, 0, 2, 0 } };

        System.out.println("\nA = ");
        mm.display(a);
        System.out.println("\nB = ");
        mm.display(b);
        
        int[][] c = mm.multiply(a, b);
        System.out.println("\nC =");
        mm.display(c);
    }
}