class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, HashSet<String>> g = new HashMap<>();
        Map<String, String> mailToName = new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        buildGrap(g, accounts, mailToName);

        HashSet<String> visited = new HashSet<>();

        for (String mail : mailToName.keySet()) {
            if (visited.add(mail)) {
                List<String> list = new ArrayList<>();
                list.add(mail);
                dfs(g, list, mail, visited);
                Collections.sort(list);
                list.add(0, mailToName.get(mail));
                res.add(list);
            }
        }

        return res;
    }

    private void buildGrap(Map<String, HashSet<String>> g, List<List<String>> accounts, Map<String, String> mailToName) {
        for (List<String> account : accounts) {
            String name = account.get(0);

            for (int i=1; i<account.size(); i++) {
                String mail = account.get(i);
                mailToName.put(mail, name);
                g.putIfAbsent(mail, new HashSet<>());

                if (1==i) {
                    continue;
                }

                String prev = account.get(i-1);
                g.get(prev).add(mail);
                g.get(mail).add(prev);
            }
        }
    }

    private void dfs(Map<String, HashSet<String>> g, List<String> list, String mail, HashSet<String> visited) {
        if (g.get(mail) == null || g.get(mail).size()==0) return;

        for (String neighbor : g.get(mail)) {
            if (visited.add(neighbor)) {
                list.add(neighbor);
                dfs(g, list, neighbor, visited);
            }
        }
    }
}