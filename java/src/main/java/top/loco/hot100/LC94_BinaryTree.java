package top.loco.hot100;

import java.util.ArrayList;
import java.util.List;

import top.loco.entity.TreeNode;


public class LC94_BinaryTree {
    public static void main(String[] args) {
        List<Integer> inorderTraversal = new LC94_BinaryTree().inorderTraversal(
                    new TreeNode(1, 
                        null, new TreeNode(2, 
                            new TreeNode(3), null)));;
        System.out.println(inorderTraversal.toString());
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

}
