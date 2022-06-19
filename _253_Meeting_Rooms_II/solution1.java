
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {

                return o1[0] - o2[0];    
            }
        });
                
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        minHeap.add(intervals[0]);
        for (int i=1; i<intervals.length; i++) {
            int[] current = intervals[i];
            int[] earliest = minHeap.poll();
            
            if (earliest[1] <= current[0]) {
                earliest[1] = current[1];
            } else {
                minHeap.add(current);
            }
            
            minHeap.add(earliest);
        }
        
        return minHeap.size();
    }
}