class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int[] nums = new int[10101010];
        int max = 0;
        
        for (int i=0; i<intervals.length; i++) {
            nums[intervals[i][0]]++;
            nums[intervals[i][1]]--;
            max = Math.max(max, intervals[i][1]);
        }
        
        int res = 0;
        for (int i=0; i<=max; i++) {
            if (i!=0) {
                nums[i] += nums[i-1];
            }
            
            res = res > nums[i] ? res : nums[i];
        }
        
        return res;
    }
}