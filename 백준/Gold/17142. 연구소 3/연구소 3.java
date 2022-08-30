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
	
	static int n, m, answer = Integer.MAX_VALUE, emptySpace = 0;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static int[][] map;
	static int[] comb;
	static ArrayList<Viru> list = new ArrayList<>();
	
	public void comb(int level, int s) {
		if(level == m) {
			BFS();
			return;
		} else {
			for(int i = s; i < list.size(); i++) {
				comb[level] = i;
				comb(level+1, i+1);
			}
		}
	}
	
	public void BFS() {
		int count = emptySpace;
		Queue<Viru> q = new LinkedList<>();
		int[][] copy = new int[n][n];
		boolean[][] visited = new boolean[n][n];
		
		for(int i : comb) {
			Viru v = list.get(i);
			q.add(v);
			visited[v.x][v.y] = true;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				copy[i][j] = map[i][j];
				if(copy[i][j] == 1) {
					copy[i][j] = -1;
				}
				if(copy[i][j] == 2 && !visited[i][j]) {
					copy[i][j] = -2;
				} else if(copy[i][j] == 2){
					copy[i][j] = 0;
				}
			}
		}
		
		while(!q.isEmpty()) {
			Viru v = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = v.x +dx[i];
				int ny = v.y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) {
					continue;
				}
				
				if(copy[nx][ny] == 0 && !visited[nx][ny]) {
					count--;
					visited[nx][ny] = true;
					copy[nx][ny] = copy[v.x][v.y] + 1;
					q.add(new Viru(nx,ny));
				}
				if(copy[nx][ny] == -2) {
					copy[nx][ny] = copy[v.x][v.y] + 1;
					q.add(new Viru(nx,ny));
				}
			}
			
			if(count == 0) {
				break;
			}
		}
		
		minTime(copy,visited);	 
		
	}
	
	public void minTime(int[][] copy, boolean[][] visited) {
		int time = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(copy[i][j] == 0 && !visited[i][j]) {
					time = Integer.MAX_VALUE;
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
		
		map = new int[n][n];
		comb = new int[m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					list.add(new Viru(i,j));
				}
				if(map[i][j] == 0) {
					emptySpace++;
				}
			}
		}
		
		if(emptySpace == 0) {
			System.out.println(0);
		} else {
			M.comb(0,0);
			if(answer == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(answer);			
			}
			
		}
	}
	
}
	