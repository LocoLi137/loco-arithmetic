package top.loco.hot100;

import top.loco.entity.ListNode;

public class LC148_SortedLinkedList {
    public static void main(String[] args) {
        //[4,2,1,3]
        ListNode cur = new ListNode(4, 
                new ListNode(2,
                        new ListNode(1,
                                new ListNode(3))));
    
        new LC148_SortedLinkedList().sortList(cur);
    }

    // 自底向上归并排序
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head; 
        }
        // 获取链表长度
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        ListNode dummyHead = new ListNode(0, head);
        // 每次将链表拆分成多个长度为subLength的子链表，并两两合并
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                // 第一个子链表的头节点
                ListNode head1 = curr;
                // 找到第一个子链表的尾节点
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                // 第二个子链表的头节点
                ListNode head2 = curr.next;
                curr.next = null; // 断开第一个子链表
                curr = head2; // 移动到第二个子链表
                // 找到第二个子链表的尾节点
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next; // 保存下一段链表的起始节点
                    curr.next = null; // 断开第二个子链表
                }
                // 合并两个子链表
                ListNode merged = merge(head1, head2);
                prev.next = merged; // 将合并后的链表连接到前一个部分
                // 移动prev到合并后链表的尾部
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next; // 继续处理下一段链表
            }
        }
        return dummyHead.next; // 返回排序后的链表
    }   

    // 合并两个有序链表
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0); // 哑节点
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1; // 选择较小的节点
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next; // 移动到下一个位置
        }
        // 将剩余的节点连接到结果链表
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next; // 返回合并后的链表
    }
}