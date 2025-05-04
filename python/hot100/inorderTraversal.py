from typing import List
import unittest
import sys
import os

# 添加当前脚本的父目录到 sys.path
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from entity.TreeNode import TreeNode  # 确保导入的是 TreeNode 类


def inorderTraversal(root: TreeNode) -> List[int]:
    WHITE, GRAY = 0, 1
    res = []
    stack = [(WHITE, root)]
    while stack:
        color, node = stack.pop()
        if node is None: continue
        if color == WHITE:
            stack.append((WHITE, node.right))
            stack.append((GRAY, node))
            stack.append((WHITE, node.left))
        else:
            res.append(node.val)
    return res


class TestInorderTraversal(unittest.TestCase):
    def test_empty_tree(self):
        self.assertEqual(inorderTraversal(self, None), [])

    def test_single_node(self):
        root = TreeNode(1)
        self.assertEqual(inorderTraversal(self, root), [1])

    def test_multiple_nodes(self):
        root = TreeNode(1)
        root.right = TreeNode(2)
        root.right.left = TreeNode(3)
        self.assertEqual(inorderTraversal(self, root), [1, 3, 2])


if __name__ == "__main__":
    # unittest.main()
    res = inorderTraversal(TreeNode(1));
    print(res)  # [1]
