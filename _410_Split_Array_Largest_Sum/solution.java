class Solution {
    public int splitArray(int[] nums, int m) {
        if (nums==null || nums.length==0) {
            return 0;
        }
        
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            max = max >= num ? max : num;
            sum += num;
        }
        
        int le = max, ri = sum;
        
        while (le+1 < ri) {
            int mid = le + (ri-le)/2;
            int piece = getCnt(nums, mid);
            
            if (piece == m) {
                ri = mid;
            } else if (piece < m) {
                ri = mid;
            } else {
                le = mid;                
            }
        }
        
        if (getCnt(nums, le) <= m) {
            return le;
        } else {
            return ri;
        }
    }
    
    private int getCnt(int[] nums, int target) {
        int res = 0;
        int sum = 0;
        for (int num : nums) {
            if (sum + num <= target) {
                sum += num;
            } else {
                sum = num;
                res++;
            }
        }
        if (sum != 0) {
            res++;
        }
        return res;
    }
}