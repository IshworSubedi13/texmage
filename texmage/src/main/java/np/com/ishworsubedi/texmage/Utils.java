package np.com.ishworsubedi.texmage;

public class Utils {
    public static String getInitials(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }

        String[] parts = fullName.trim().split("\\s+");
        StringBuilder initials = new StringBuilder();

        for (int i = 0; i < parts.length && i < 2; i++) {
            if (!parts[i].isEmpty()) {
                initials.append(Character.toUpperCase(parts[i].charAt(0)));
            }
        }

        return initials.toString();
    }
}
