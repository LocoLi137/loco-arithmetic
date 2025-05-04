package top.loco.hot100;

import java.util.HashMap;
import java.util.Map;


public class LC146_LRUCache {
    
}

/**
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
class LRUCache {

    Entry head, tail;
    int capacity, size;
    Map<Integer, Entry> cache = new HashMap<>();

    /**
     *  以 正整数 作为容量 capacity 初始化 LRU 缓存
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        //初始化链表
        initLinkedList();
        size = 0;
        cache = new HashMap<>(capacity + 2);
    }
    
    /**
     * 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * @param key
     * @return
     */
    public int get(int key) {
        Entry node = cache.get(key);;
        if (node == null) return -1;
        modeToHead(node);
        return node.value;
    }
    
    /**
     *   如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        Entry node = cache.get(key);
        if (node != null) {
            node.value = value;
            modeToHead(node);
            return ;
        }

        //不存在，先加进去，判断容量是否满，移除尾结点
        if (size == capacity) { 
            Entry lastNode = tail.prev;
            deleteNode(lastNode);
            cache.remove(lastNode.key);
            size --;
        }

        Entry newNode = new Entry(key, value);
        addNode(newNode);
        cache.put(key, newNode);
        size ++;
    }

    private void modeToHead(Entry node) {
        deleteNode(node);
        addNode(node);
    }

    private void deleteNode(Entry node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNode(Entry node) {
        head.next.prev = node;
        node.next = head.next;

        node.prev = head;
        head.next = node;
    }

    public static class Entry {
        int key, value;
        Entry prev, next;
        Map<Integer, Entry> map = new HashMap<>();

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Entry() {
        
        }
    }

    private void initLinkedList() {
        head = new Entry();
        tail = new Entry();

        head.next = tail;
        tail.prev = head;
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);

        lru.put(1, 1);
        lru.put(2, 2);
        
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
        
        System.out.println("==============");
        lru.put(3, 3);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));

        System.out.println("==============");
        lru.put(2, 4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
    }
}