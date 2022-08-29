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
	
	static int answer = Integer.MAX_VALUE, count;
	static int n, m;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static int[][] arr;
	static boolean[][] visited;
	static ArrayList<Viru> list = new ArrayList<>();
	static int[] comb;
	
	public void comb(int level, int s) {
		if(level == m) {
			spread();
		} else {
			for(int i = s; i < list.size(); i++) {
				comb[level] = i;
				comb(level+1,i+1);
			}
		}
	}
	
	public void spread() {
		int emptySpace = count;
		Queue<Viru> q= new LinkedList<>();
		visited = new boolean[n][n];
		int[][] copy = new int[n][n];
		
		for(int i = 0; i < m; i++ ) {
			Viru v = list.get(comb[i]);
			q.add(v);
			visited[v.x][v.y] = true;
		}
		for(int i = 0; i < n; i++ ) {
			for(int j = 0; j < n; j++) {
			
				if(arr[i][j] == 2 && !visited[i][j]) {
					copy[i][j] = -2;
				}
				if(arr[i][j] == 1)  {
					copy[i][j] = -1;
				}
			}
		}	
		
		
		while(!q.isEmpty()) {
			Viru now = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = now.x +dx[i];
				int ny = now.y +dy[i];
				if(nx >= 0 && nx < n && ny >= 0 && ny < n && copy[nx][ny] == 0 && !visited[nx][ny]) {
					emptySpace--;
					visited[nx][ny] = true;
					copy[nx][ny] = copy[now.x][now.y] + 1;
					q.add(new Viru(nx,ny));
				}
				
				if(nx >= 0 && nx < n && ny >= 0 && ny < n && copy[nx][ny] == -2 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					copy[nx][ny] = copy[now.x][now.y] + 1;
					q.add(new Viru(nx,ny));
				}
			}
			if(emptySpace == 0) {
				break;
			}
		}
		
		minTime(copy);
		
	}
	
	public void minTime(int[][] copy) {
		int time =0;
		for(int i = 0; i < n; i++) {
			for(int j =0; j< n; j++) {
				if(copy[i][j] == 0 && !visited[i][j]) {
					 time= Integer.MAX_VALUE;
					 break;
				}
				time = Math.max(time, copy[i][j]);
			}
		}
		answer = Math.min(time, answer);
	}
	
	public static void main(String[] args) throws Exception{
		Main M = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		comb = new int[m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) {
					count++;
				}
				
				if(arr[i][j] == 2) {
					list.add(new Viru(i,j));
				}
			}
		}
		if(count == 0) {
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
	