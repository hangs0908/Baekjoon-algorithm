import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Viru {
	int x, y;
	
	public Viru(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int n, m, answer = Integer.MAX_VALUE;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static boolean[][] visited;
	static int[][] arr;
	static int[] comb;
	static ArrayList<Viru> list = new ArrayList<>();
	
	public void comb(int level, int s) {
		if(level == m) {
			BFS();
		} else {
			for(int i = s; i < list.size(); i++) {
				comb[level] = i;
				comb(level+1,i+1);
			}
		}
	}
	
	public void BFS() {
		Queue<Viru> q = new LinkedList<>();
		visited= new boolean[n][n];
		int[][] copy = new int[n][n];
		for(int i = 0; i < m ;i++) {
			int idx = comb[i];
			Viru v= list.get(idx);
			q.add(v);
			visited[v.x][v.y] = true;
		}
		for(int i =0; i< n; i++) {
			for(int j = 0; j < n;j++) {
				if(arr[i][j] == 1) {
					copy[i][j] = -1;
				}
			}
		}
		
		
		
		while(!q.isEmpty()) {
			Viru now = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if(nx >= 0 && nx < n && ny >= 0 && ny < n && copy[nx][ny] == 0 && !visited[nx][ny]) {
					copy[nx][ny] = copy[now.x][now.y] + 1;
					q.add(new Viru(nx,ny));
				}
			}
		}
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j <n ; j++) {
//				System.out.print(copy[i][j] +" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		minTime(copy,visited);
	}
	
	public void minTime(int[][] copy, boolean[][] visited) {
		int time = 0;
		for(int i =0; i< n; i++) {
			for(int j = 0; j < n;j++) {
				if(copy[i][j] == 0 && !visited[i][j]) {
					time = Integer.MAX_VALUE;
					break;
				}
				time = Math.max(time, copy[i][j]);
			}
		}
		answer = Math.min(answer, time);
	}
	
	
	public static void main(String[] args) throws Exception{
		Main M = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		visited = new boolean[n][n];
		comb = new int[m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 2) {
					list.add(new Viru(i,j));
				}
			}
		}


		M.comb(0,0);
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
		
		
	}
	
}
	