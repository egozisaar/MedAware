import java.lang.reflect.Parameter;
import java.util.*;

class SimilaritiesGroup {
    private List<TargetData> targets;
    private List<String> changes;

    SimilaritiesGroup() {
        this.targets = new ArrayList<>();
        this.changes = new ArrayList<>();
    }

    SimilaritiesGroup(List<TargetData> targets, List<String> changes) {
        this.targets = new ArrayList<>(targets);
        this.changes = new ArrayList<>(changes);
    }

    void add(TargetData target, String change) {
        this.targets.add(target);
        this.changes.add(change);
    }

    @Override
    public String toString() {
        if (this.targets.size() <= 1)
            return "";
        else {
            StringBuilder builder = new StringBuilder();
            for (TargetData target : this.targets)
                builder.append(target.toString()).append('\n');

            builder.append("The changing words were: ");
            for (String change : this.changes)
                builder.append(change).append(", ");


            String str = builder.toString();
            return str.substring(0, str.length() - 2);
        }
    }
}

class Comparator {

    private class Pair {
        private TargetData target;
        private String change;

        Pair(TargetData target, String change) {
            this.target = target;
            this.change = change;
        }
    }

    List<SimilaritiesGroup> getSimilaritiesGroups(List<TargetData> data) {
        Map<String, List<Pair>> sentences = new HashMap<>();

        for (TargetData target : data) {
            String[] words = target.getAction().split(" ");
            for (int i = 0; i < words.length; i++) {
                String key = getSubArrayStringRep(words, i);

                sentences.putIfAbsent(key, new ArrayList<>());
                List<Pair> pairsList = sentences.get(key);
                Pair pair = new Pair(target, words[i]);

                if (!isPairExists(pairsList, pair))
                    pairsList.add(pair);
            }
        }

        List<SimilaritiesGroup> groups = new ArrayList<>();
        for (Map.Entry<String, List<Pair>> entry : sentences.entrySet()) {
            SimilaritiesGroup group = new SimilaritiesGroup();
            for (Pair pair : entry.getValue()) {
                group.add(pair.target, pair.change);
            }

            groups.add(group);
        }

        return groups;
    }

    private boolean isPairExists(List<Pair> pairsList, Pair pair) {
        boolean exists = false;
        for (Pair oldPair : pairsList) {
            if (oldPair.change.equals(pair.change) &&
                    oldPair.target.equals(pair.target)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    private String getSubArrayStringRep(String[] words, int i) {
        String[] subArr = new String[words.length - 1];
        System.arraycopy(words, 0, subArr, 0, i);
        System.arraycopy(words, i + 1, subArr, i, words.length - 1 - i);
        return String.join(" ", subArr);
    }
}
