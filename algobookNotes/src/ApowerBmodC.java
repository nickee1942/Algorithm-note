public class ApowerBmodC {
    public long solution(long a, int b, int mod){
        long res = 1 % mod;
        while(b > 0){
            if((b & 1) == 1){
                res = res * a % mod;
            }
            a = a * a % mod;
        }
        return (int)res;
    }
}
