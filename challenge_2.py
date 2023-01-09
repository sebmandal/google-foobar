def solution(src, dest):
    if src == dest:
        return 0

    board = [
        [0, 1, 2, 3, 4, 5, 6, 7],
        [8, 9, 10, 11, 12, 13, 14, 15],
        [16, 17, 18, 19, 20, 21, 22, 23],
        [24, 25, 26, 27, 28, 29, 30, 31],
        [32, 33, 34, 35, 36, 37, 38, 39],
        [40, 41, 42, 43, 44, 45, 46, 47],
        [48, 49, 50, 51, 52, 53, 54, 55],
        [56, 57, 58, 59, 60, 61, 62, 63]
    ]

    knight_moves = [
        (+1, +2),
        (+1, -2),
        (-1, +2),
        (-1, -2),
        (+2, +1),
        (+2, -1),
        (-2, +1),
        (-2, -1),
    ]

    src = (
        src // 8,  # row
        src % 8,  # col
    )

    dest = (
        dest // 8,  # row
        dest % 8,  # col
    )

    moves = 0
    positions = [
        src
    ]

    while dest not in positions:
        new_positions = []
        for position in positions:
            for move in knight_moves:
                #checking if the row and column are okay to move to (checking if they're within 0 -> 7)
                if position[0] + move[0] in board[0] and position[1] + move[1] in board[0]:
                    new_positions.append((position[0] + move[0], position[1] + move[1]))

        moves += 1
        positions = new_positions

    return moves


print(solution(63, 61))
