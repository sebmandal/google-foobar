class Node:
    def __init__(self, val=None):
        self.val = val
        self.left = None
        self.right = None
        self.parent = None

    def __str__(self):
        return str(self.val)


def generate_perfect_binary_tree(h, val=None):
    if val == None:
        val = 2 ** h - 1

    if h == 1:
        return Node(val), val

    node = Node(val)

    while val > 1:
        # if right node exists and is empty
        if hasattr(node, "right") and node.right == None:
            node.right, val = generate_perfect_binary_tree(h - 1, val - 1)
            node.right.parent = node.val

            # if on final layer, break after, this is last generation that will have children
            if h == 2:
                node.left, val = generate_perfect_binary_tree(h - 1, val - 1)
                node.left.parent = node.val
                break
        # otherwise add left node
        else:
            node.left, val = generate_perfect_binary_tree(h - 1, val - 1)
            node.left.parent = node.val
            break

    return node, val


def find_node(node, value):
    if node is None:
        return None
    if node.val == value:
        return node
    left = find_node(node.left, value)
    if left:
        return left
    right = find_node(node.right, value)
    if right:
        return right
    return None


def solution(h, q):
    tree = generate_perfect_binary_tree(h)[0]
    result = []
    for value in q:
        if value > 0 and value < 2 ** h:
            node = find_node(tree, value)
            if node.parent != None:
                result.append(node.parent)
            else:
                result.append(-1)
    return result