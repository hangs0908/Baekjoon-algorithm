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
	
	static int n, m, answer = Integer.MIN_VALUE;
	static int[][] arr;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	
	public void DFS(int wall) {
		if(wall == 3) {
			spread();
			return;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					DFS(wall+1);
					arr[i][j] = 0;
				}
			}
		}
	}
	
	public void spread() {
		Queue<Viru> q= new LinkedList<>();
		int[][] copy = new int[n][m];
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 2) {
					q.add(new Viru(i,j));
				}
				copy[i][j] = arr[i][j];
			}
		}
		
		
		while(!q.isEmpty()) {
			Viru now = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx >= 0 && nx < n && ny >= 0 && ny < m && copy[nx][ny] == 0) {
					copy[nx][ny] = 2;
					q.add(new Viru(nx,ny));
				}
			}
		}
		
		maxCount(copy);
		
	}
	
	public void maxCount(int[][] copy) {
		int count = 0;
		for(int i = 0; i < n; i++) {
			for(int j= 0; j < m; j++) {
				if(copy[i][j] == 0) {
					count++;
				}
			}
		}
		
		answer = Math.max(count, answer);
	}
	
	public static void main(String[] args) throws Exception{
		Main M = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		for(int i = 0;i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		M.DFS(0);
		System.out.println(answer);
	}
	
}
	