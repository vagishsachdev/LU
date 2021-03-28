import java.util.*;
public class dp1{

    // public static int mazepath(int sr, int sc, int dr, int dc, int arr[][]){

    //     int ans = 0;

    //     if(sr == dr && sc == dc){
    //         return 1;
    //     }

    //     if(sr + 1 < dr){
    //         ans += mazepath(sr + 1, sc, dr, dc, arr);
    //     }

    //     if(sc + 1 < dc){
    //         ans += mazepath(sr, sc + 1, dr, dc, arr);
    //     }

    //     return ans;

    // }

    public static int mazePath_rec(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            return 1;
        }

        int count = 0;
        if (sc + 1 <= ec)
            count += mazePath_rec(sr, sc + 1, er, ec);
        if (sr + 1 <= er)
            count += mazePath_rec(sr + 1, sc, er, ec);
       

        return count;
    }

    public static int mazePath_memo(int sr, int sc, int er, int ec, int dp[][]){
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if(dp[sr][sc] != 0){
            return dp[sr][sc];
        }

        int count = 0;
        if (sc + 1 <= ec)
            count += mazePath_memo(sr, sc + 1, er, ec, dp);
        if (sr + 1 <= er)
            count += mazePath_memo(sr + 1, sc, er, ec, dp);
       

        return dp[sr][sc] = count;
    }

    //To convert memoisation to dp, find out direction of dependency, here recursion
    //goes from START to END, which means that to find the answer of the START,
    //WE have to go to END first
    //So, start the tabulation in opposite way, from end to start, becuase start 
    //answer depends upon end answer, so we need end answer first

    public static int mazePath_dp(int SR, int SC, int er, int ec, int dp[][]){
        for(int sr = er; sr >= 0; sr--){
            for(int sc = ec; sc >= 0; sc--){
                
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }
        
                int count = 0;
                if (sc + 1 <= ec)
                    count += dp[sr][sc + 1];
                if (sr + 1 <= er)
                    count += dp[sr+1][sc];
         
                dp[sr][sc] = count;

            }
        }
        return dp[SR][SC];
    }

    public static void mazePath() {
        int n = 2;
        int m = 2;

        int dp[][] = new int [n][m];

        System.out.println(mazePath_dp(0, 0, n-1,m-1, dp));
        //System.out.println(mazePath_memo(0, 0, n - 1, m-1, dp));
        //System.out.println(mazePathInfi_DP(0, 0, n - 1, m - 1, dp));

    }

    public static int boardPath_memo(int src, int dest, int dp[]){


        if(src == dest){
            return dp[src] = 1;
        }

        if(dp[src] != 0){
            return dp[src];
        }

        int ans = 0;
        for(int i = 1; i <= 6 && src + i <= dest; i++ ){
            ans += boardPath_memo(src + i, dest, dp);
        }

        return dp[src] = ans;

    }

    public static int boardPath_dp(int SRC, int dest, int dp[]){


        for(int src = dest; src>=0; src--){

            if(src == dest){
                dp[src] = 1;
                continue;
            }
    
            int ans = 0;
            for(int i = 1; i <= 6 && src + i <= dest; i++ ){
                ans += dp[src+i];//(src + i, dest, dp);
            }
    
            dp[src] = ans;
        }

        return dp[SRC];

    }
    

    public static void boardPath(){
        int n = 10;
        int dp[] = new int[n +1];
        //System.out.println(boardPath_memo(0, n, dp));
        System.out.println(boardPath_dp(0, n, dp));
    }

    //jumps of 1 and 2 only
    public static int climbStairs_memo(int src, int dp[]){

        if(src <= 1){           //src can also be negative so 
            return dp[src] = 1;
        }

        if(dp[src]!= 0){
            return dp[src];
        }

        return dp[src] = climbStairs_memo(src - 1, dp) + climbStairs_memo(src - 2, dp);

    }

    public static void climbStairs(){
        int n = 1;
        int dp[] = new int[n+1];
        System.out.println(climbStairs_memo(n, dp));
    }



    public static void main(String[] args) {
    //    mazePath();
    //    boardPath();
          climbStairs();
    }






}