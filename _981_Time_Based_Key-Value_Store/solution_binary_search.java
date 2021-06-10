class TimeMap {
    
    class Data {
        int timestamp;
        String value;
        public Data(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    Map<String, List<Data>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Data(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return null;
        }
        List<Data> tmp = map.get(key);
        
        if (binarySearch(tmp, timestamp) == null) {
            return "";
        } else {
            return binarySearch(tmp, timestamp).value;
        }
    }
    
    private Data binarySearch(List<Data> list, int timestamp) {
        int l = 0, r = list.size()-1;
        
        while (l+1 < r) {
            int mid = l + (r-l)/2;
            if (list.get(mid).timestamp == timestamp) {
                return list.get(mid);
            } else if (list.get(mid).timestamp < timestamp) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        if (list.get(r).timestamp <= timestamp) {
            return list.get(r);
        } else if (list.get(l).timestamp <= timestamp) {
            return list.get(l);
        } else {
            return null;
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(k~ey,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */