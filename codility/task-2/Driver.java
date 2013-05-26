class Solution {

  private int N, M;
  private boolean[][] visited;
  private int[][] map;

  private void floodFill(int x, int y, int color) {
    if (x < 0 || x >= N || y < 0 || y >= M || visited[x][y] || map[x][y] != color) {
      return;
    }

    visited[x][y] = true;
    floodFill(x + 1, y, color);
    floodFill(x - 1, y, color);
    floodFill(x, y + 1, color);
    floodFill(x, y - 1, color);
  }

  public int solution(int[][] A) {
    N = A.length;
    M = A[0].length;
    visited = new boolean[N][M];
    map = A;
    int count = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (!visited[i][j]) {
          count++;
          floodFill(i, j, A[i][j]);
        }
      }
    }

    return count;
  }
}
