class StringUtils {
    public static boolean isPalindrome(String str) {
        if (str == null || str.length() < 1) {
            return false;
        }
        str = str.toLowerCase().replaceAll("['\\s]", "");
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    System.out.println(isPalindrome("Don't nod"));
    }
}