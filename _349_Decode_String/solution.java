class Solution {
    public String decodeString(String ss) {
        char[] s = ss.toCharArray();
        Deque<Object> dq = new ArrayDeque<>();
        int num = 0;
        
        for (int i=0; i<s.length; i++) {
            if (s[i]>='0' && s[i]<='9') {
                num = num * 10 + s[i] - '0';
            } else if (s[i] == '[') {
                dq.addLast(num);
                num = 0;
            } else if (s[i] == ']') {
                String newStr = popStack(dq);
                Integer count = (Integer)dq.pollLast();
                for (int j=0; j<count; j++) {
                    dq.addLast(newStr);
                }
            } else {
                dq.addLast(String.valueOf(s[i]));  // char to String (Object)
            }
        }
        
        
        return popStack(dq);
    }
    
    private String popStack(Deque<Object> dq) {
        StringBuilder sb = new StringBuilder();
        
        while (!dq.isEmpty() && dq.peekLast() instanceof String) {
            sb.insert(0, dq.pollLast());
        }
        
        return sb.toString();
    }
}