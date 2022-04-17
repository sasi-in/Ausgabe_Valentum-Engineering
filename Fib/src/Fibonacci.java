import java.math.BigInteger;
import java.util.Scanner;
public class Fibonacci {
    public static BigInteger Fib(int n) {

        int num = Math.abs(n);
        if (num == 0) {
            return BigInteger.ZERO;
        }
        else if (num <= 2) {
            return BigInteger.ONE;
        }

        BigInteger[][] number = { { BigInteger.ONE, BigInteger.ONE }, { BigInteger.ONE, BigInteger.ZERO } };
        BigInteger[][] result = { { BigInteger.ONE, BigInteger.ONE }, { BigInteger.ONE, BigInteger.ZERO } };

        while (num > 0) {
            if (num%2 == 1) result = MultiplyMatrix(result, number);
            number = MultiplyMatrix(number, number);
            num/= 2;
        }

        return result[1][1].multiply(BigInteger.valueOf(((n < 0) ? -1:1)));
    }

    public static BigInteger[][] MultiplyMatrix(BigInteger[][] mat1, BigInteger[][] mat2) {
        return new BigInteger[][] {
                {
                        mat1[0][0].multiply(mat2[0][0]).add(mat1[0][1].multiply(mat2[1][0])),
                        mat1[0][0].multiply(mat2[0][1]).add(mat1[0][1].multiply(mat2[1][1]))
                },
                {
                        mat1[1][0].multiply(mat2[0][0]).add(mat1[1][1].multiply(mat2[1][0])),
                        mat1[1][0].multiply(mat2[0][1]).add(mat1[1][1].multiply(mat2[1][1]))
                }
        };
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.println("Enter the number elements you want to test: ") ;
        int n;

        n = sc.nextInt();
        while(n == 0 || n > 523){
            System.out.println("Enter number between 1 to 523") ;
            System.out.println("Enter the number of elements you want to test again:  ") ;
            n = sc.nextInt();
        }
        int[] a = new int[n];
        int i;
        System.out.println("Enter the elements: ") ;
        for(i=0;i<n;i++)
        {
            a[i] = sc.nextInt();
        }
        for(i=0;i<n;i++)
        {
            System.out.println("Fibonacci Series " + a[i] + " is " + Fib(a[i]));
        }

    }
}


