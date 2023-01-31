/*
Prepare the Bunnies' Escape
===========================

You're awfully close to destroying the LAMBCHOP doomsday device and freeing Commander Lambda's bunny workers, but once they're free of the work duties the bunnies are going to need to escape Lambda's space station via the escape pods as quickly as possible. Unfortunately, the halls of the space station are a maze of corridors and dead ends that will be a deathtrap for the escaping bunnies. Fortunately, Commander Lambda has put you in charge of a remodeling project that will give you the opportunity to make things a little easier for the bunnies. Unfortunately (again), you can't just remove all obstacles between the bunnies and the escape pods - at most you can remove one wall per escape pod path, both to maintain structural integrity of the station and to avoid arousing Commander Lambda's suspicions. 

You have maps of parts of the space station, each starting at a work area exit and ending at the door to an escape pod. The map is represented as a matrix of 0s and 1s, where 0s are passable space and 1s are impassable walls. The door out of the station is at the top left (0,0) and the door into an escape pod is at the bottom right (w-1,h-1). 

Write a function solution(map) that generates the length of the shortest path from the station door to the escape pod, where you are allowed to remove one wall as part of your remodeling plans. The path length is the total number of nodes you pass through, counting both the entrance and exit nodes. The starting and ending positions are always passable (0). The map will always be solvable, though you may or may not need to remove a wall. The height and width of the map can be from 2 to 20. Moves can only be made in cardinal directions; no diagonal moves are allowed.

Languages
=========

To provide a Python solution, edit solution.py
To provide a Java solution, edit Solution.java

Test cases
==========
Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

-- Python cases --
Input:
solution.solution([[0, 1, 1, 0], [0, 0, 0, 1], [1, 1, 0, 0], [1, 1, 1, 0]])
Output:
    7

Input:
solution.solution([[0, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 0], [0, 0, 0, 0, 0, 0], [0, 1, 1, 1, 1, 1], [0, 1, 1, 1, 1, 1], [0, 0, 0, 0, 0, 0]])
Output:
    11
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        int[][] map = { { 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0 } };
        System.out.println(Solution.solution(map));
        return;
    }

    public static int solution(int[][] map) {
        int bestShortestLength = Integer.MAX_VALUE;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = 0;
                    int shortestLength = bfs(map);
                    bestShortestLength = Math.min(bestShortestLength, shortestLength);
                    map[i][j] = 1;
                }
            }
        }

        return bestShortestLength != Integer.MAX_VALUE ? bestShortestLength : -1;
    }

    private static int bfs(int[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        int[][] dist = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        List<int[]> queue = new ArrayList<>();
        queue.add(new int[] { 0, 0, 1 });
        visited[0][0] = true;
        dist[0][0] = 1;

        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };

        while (!queue.isEmpty()) {
            int[] curr = queue.remove(0);
            int x = curr[0];
            int y = curr[1];
            int distCurr = curr[2];

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX < 0 || newX >= rows || newY < 0 || newY >= cols) {
                    continue;
                }

                if (visited[newX][newY]) {
                    continue;
                }

                if (map[newX][newY] == 1) {
                    continue;
                }

                dist[newX][newY] = distCurr + 1;
                visited[newX][newY] = true;
                queue.add(new int[] { newX, newY, dist[newX][newY] });

                if (newX == rows - 1 && newY == cols - 1) {
                    return dist[newX][newY];
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
