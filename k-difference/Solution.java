import java.io.*;
import java.util.*;

public class Solution {

    private void run(Reader r) throws Exception {
        BufferedReader rd = new BufferedReader(r);
        StringTokenizer st = new StringTokenizer(rd.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<Integer>(N);
        int count = 0;
        
        st = new StringTokenizer(rd.readLine());
        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(st.nextToken());
            if(set.contains(x - K)) count++;
            if(set.contains(x + K)) count++;
            
            set.add(x);
        }
        
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        Reader rd = args.length == 0 ? new InputStreamReader(System.in) : new FileReader(args[0]);
        new Solution().run(rd);
    }
}