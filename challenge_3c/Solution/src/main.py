def solution(map):
    best_shortest_length = float('inf')

    for i in range(len(map)):
        for j in range(len(map[0])):
            if map[i][j] == 1:
                map[i][j] = 0
                shortest_length = bfs(map)
                best_shortest_length = min(
                    best_shortest_length, shortest_length)
                map[i][j] = 1

    return best_shortest_length if best_shortest_length != float('inf') else -1


def bfs(map):
    rows = len(map)
    cols = len(map[0])
    dist = [[0 for _ in range(cols)] for _ in range(rows)]
    visited = [[False for _ in range(cols)] for _ in range(rows)]
    queue = []
    queue.append((0, 0, 1))
    visited[0][0] = True
    dist[0][0] = 1

    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    while queue:
        x, y, dist_curr = queue.pop(0)

        for i in range(4):
            new_x, new_y = x + dx[i], y + dy[i]

            if new_x < 0 or new_x >= rows or new_y < 0 or new_y >= cols:
                continue

            if visited[new_x][new_y]:
                continue

            if map[new_x][new_y] == 1:
                continue

            dist[new_x][new_y] = dist_curr + 1
            visited[new_x][new_y] = True
            queue.append((new_x, new_y, dist[new_x][new_y]))

            if new_x == rows-1 and new_y == cols-1:
                return dist[new_x][new_y]

    return -1
