import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	public static int N;
	public static Point[] points;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(sc.readLine());
		points = new Point[N];
		
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(sc.readLine());
			points[i] = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		//points를 x값이 오름차순이 되도록 정렬
		Arrays.parallelSort(points, (a,b) -> a.x - b.x);
		
		//TreeSet에 들어가는 Point의 y값이 같으면, x값이 오름차순이 되도록, 다르다면, y값이 오름차순이 되도록(y값을 기준으로 정렬)
		TreeSet<Point> set = new TreeSet<>((a, b) -> ((a.y == b.y) ? a.x - b.x : a.y - b.y));
		//후보 포인트 초기화 
		set.add(points[0]);
		set.add(points[1]);
		
		//임시 정답 
		long answer = distSquare(points[0], points[1]);
		//마지막으로 비교했던 포인트 저장
		int start = 0;
		
		for(int i=2;i<N;i++) {
			Point current = points[i];
			while(start<i) {
				Point candidatePoint = points[start];
				long diff = current.getX() - candidatePoint.getX();
				if (diff*diff > answer) {
					set.remove(candidatePoint);
					start++;
				} else {
					break;
				}
			}
			
			//y값의 비교를 위해 범위를 정해줌 
			int d = (int) Math.sqrt((double) answer) + 1;
			
			Point from = new Point(-100001, current.getY()-d);
			Point to = new Point(100001, current.getY()+d);
			
			for (Point point : set.subSet(from, to)) {
				long distance = distSquare(current, point);
				answer = Math.min(answer, distance);
			}
			set.add(current);
		}
		
		System.out.println(answer);
		
		sc.close();
	}
	
	static long distSquare(Point A, Point B) {
		return (long)((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
	}
	
	static class Point {
		private int x,y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		
	}
}
