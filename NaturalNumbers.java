public class NaturalNumbers
{
    private static boolean isPrime(int value)
    {
        int max = (int)Math.sqrt(value) + 1;
        for(int i = 2; i < max; i++) {
            if(value % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int[] generatePrimes(int count)
    {
        int[] primeList = new int[count];
        int runningValue = 2;
        for(int i = 0; i < count; i++) {
            while(!isPrime(runningValue)) {
                runningValue++;
            }
            primeList[i] = runningValue;
            runningValue++;
        }
        return primeList;
    }

    private static int getDivisorCount(int value, int[] primes)
    {
        if(isPrime(value)) {
            return 2;
        }

        int temp = value;
        int divisorCount = 1;
        for(int i = 0; i < primes.length; i++) {
            int count = 0;
            while(temp % primes[i] == 0) {
                temp /= primes[i];
                count++;
            }
            divisorCount *= (count + 1);
            if(temp == 1) {
                return divisorCount;
            }
            if(isPrime(temp)) {
                return divisorCount * 2;
            }
        }
        if(temp != 1) {
            System.out.println("COULD NOT COUNT, GENERATE MORE PRIMES " + temp);
            System.exit(1);
        }
        return divisorCount;
    }

    public static void main(String[] args) throws Throwable
    {
        int[] primes = generatePrimes(1000000);
        int[] sizes = new int[]{100, 250, 300, 400, 500, 600, 750, 800, 900, 1000};

        int running_total = 0;
        int addend = 1;
        int index = 0;
        while(index < sizes.length) {
            running_total += addend;
            int divisorCount = getDivisorCount(running_total, primes);
            if(divisorCount >= sizes[index]) {
                while(divisorCount >= sizes[index]) {
                    System.out.println(addend);
                    index++;
                    if(index >= sizes.length) {
                        break;
                    }
                }
            }
            addend++;
        }
    }
}
