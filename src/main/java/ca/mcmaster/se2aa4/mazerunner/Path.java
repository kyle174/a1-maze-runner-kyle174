package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private final String path;
    public Path(String path) {
        this.path = path;
    }

    public String factorPath() {
        char curr;
        int count;
        String factored = "";
        for (int i=0; i<this.path.length(); i++) {
            curr = this.path.charAt(i);
            count = 1;
            while (i+1 < this.path.length() && this.path.charAt(i+1) == curr) {
                count++;
                i++;
            }
            factored+=add(String.valueOf(curr),count);
        }
        return factored;
    }

    private String add(String direction, int count) {
        if (count == 1) {
            return direction+" ";
        }
        else if(count > 1) {
            return count+direction+" ";
        }
        else {
            return "";
        }
    }

    public String expandPath() {
        String expanded = "";
        char curr;
        char next = ' ';
        for (int i=0; i<this.path.length(); i++) {
            curr = this.path.charAt(i);
            if (i < this.path.length()-1) {
                next = this.path.charAt(i+1);
            }
            if (Character.isDigit(curr)) {
                int num = Character.getNumericValue(curr);
                for (int j=0; j<num; j++) {
                    expanded+=next;
                }
                i++;
            }
            else if (curr == ' ') {
                continue;
            }
            else if (curr == 'L' || curr == 'R' || curr == 'F'){
                expanded+=curr;
            }
            else {
                return "";
            }
        }
        return expanded;
    }
}
