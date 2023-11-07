import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class DateComparator implements Comparator<Activity>  {

    @Override
    public int compare(Activity a1, Activity a2) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.parse(a1.getDate(),dateFormat);
        LocalDate date2 = LocalDate.parse(a2.getDate(),dateFormat);

        return date1.compareTo(date2);
    }


}
