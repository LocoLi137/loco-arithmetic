package top.loco.hot100;

import top.loco.entity.TreeNode;

public class LC104_BinaryTreeMaxDeep {
    int res = 0, cur = 0;
    public int maxDepth(TreeNode root) {
        process(root);
        
        return res;
    }

    private void process(TreeNode node){
        if (node == null)  return ;
        
        cur ++;
        //前序遍历位置（进入节点） 增加深度
        if (node.left == null && node.right == null) res = Math.max(res, cur);

        process(node.left);
        process(node.right);

        cur --;
    }
}
