public class segmentTree {
    //build segment tree by calling build, then use modify to do upsert and querySum to query sum at [start,end]
    class Node{
        int sum;
        int start, end;
        Node left, right;
        Node(int start, int end){
            this.start = start;
            this.end = end;
            this.sum = 0;
            this.left = null;
            this.right = null;
        }
    }

    Node build(int[] nums, int start, int end){
        if(start > end)
            return null;
        Node curr = new Node(start, end);
        if(start == end){
            curr.sum = nums[start];
            return curr;
        }

        int mid = start + (end - start) / 2;
        curr.left = build(nums, start, mid);
        curr.right = build(nums, mid + 1, end);
        curr.sum = curr.left.sum + curr.right.sum;
        return curr;
    }

    int querySum(Node root, int start, int end){
        if(root.start == start && root.end == end)
            return root.sum;

        int mid = root.start + (root.end - root.start) / 2;
        int lsum = 0, rsum = 0;
        if(start <= mid)
            lsum = querySum(root.left, start, Math.min(mid, end));
        if(mid + 1 <= end)
            rsum = querySum(root.right, Math.max(start, mid + 1), end);
        return lsum + rsum;
    }

    void modify(Node root, int idx, int val){
        if(root.start == root.end && root.start == idx){
            root.sum = val;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if(idx <= mid)
            modify(root.left, idx, val);
        else
            modify(root.right, idx, val);

        root.sum = root.left.sum + root.right.sum;
    }
}
