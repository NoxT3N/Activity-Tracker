import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class DateComparator implements Comparator<Activity> {
    private boolean ascending;

    public DateComparator(boolean ascending){
        this.ascending = ascending;
    }

    @Override
    public int compare(Activity a1, Activity a2) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime date1 = LocalDateTime.parse(a1.getDate(),dateFormat);
        LocalDateTime date2 = LocalDateTime.parse(a2.getDate(),dateFormat);

        int result = date1.compareTo(date2);

        if(!ascending){
            result = -result;
        }

        return result;
    }

}
