import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int n, k;
	static int[] arr = new int[100001];
	static boolean[] check = new boolean[100001];
	
	public int BFS() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		check[n] = true;
		boolean flag = false;
		
		int L = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			
			for(int i = 0; i < len; i++) {
				int position = q.poll();
				check[position] = true;
				int[] mv = move(position);
				
				for(int j = 0; j < 3; j++) {
					int nx = mv[j];
					
					if(nx >= 0 && nx <= 100000 && !check[nx]) {
						if(nx == k) {
							arr[nx]++;
							flag = true;
						}
						q.offer(nx);
					}
				}
			}
			if(flag) {
				return L+1;
			}
			
			L++;
		}
		return L;
		
	}
	
	private int[] move(int pos) {
		int[] mv = {pos-1, pos+1, 2*pos};
		return mv;
	}
	
	
	public static void main(String[] args) throws Exception{
		Main M = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if(n == k) {
			System.out.println(0);
			System.out.println(1);
		} else {
			System.out.println(M.BFS());
			System.out.println(arr[k]);
		}
		
		
	}	
	
}
	


 