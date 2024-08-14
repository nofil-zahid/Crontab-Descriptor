import java.util.Objects;

public class CronJob {
    private final String[] cronIn;
    private final String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };
    private final String[] week = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    CronJob () {
        cronIn = new String[5];
    }

    CronJob (String[] arr) {
        cronIn = new String[5];
        setValue(arr);
    }

    CronJob (String str) {
        cronIn = new String[5];
        setValue(str);
    }

    public boolean setValue(String[] arr) {
        if (arr.length == 5) {
            putAllValuesOfArray(cronIn, arr);
            return true;
        }
        return false;
    }

    public boolean setValue(String str) {
        String [] arr = str.split(" ");
        if (arr.length == 5) {
            putAllValuesOfArray(cronIn, arr);
            return true;
        }
        return false;
    }

    public String evaluateCronString () {
        // 0. minute
        // 1. hour
        // 2. day of month
        // 3. month
        // 4. day of week
        
        boolean isAllStar = true;
        String minute="", hour="", day_of_month="", month="", day_of_week="", resultant="";

        for (String item : cronIn) {
            if (!Objects.equals(item, "*")) {
                isAllStar = false;
                break;
            }
        }
        
        if (isAllStar) return "At every minute.";

        if ((cronIn[0].contains("-") || cronIn[0].contains(",")) || (cronIn[1].contains("-") || cronIn[1].contains(",")) || (cronIn[0].contains("*") || cronIn[1].contains("*"))) {
            minute = evaluateMinute(cronIn[0]);
            hour = evaluateHour(cronIn[1]);
        }
        else {
            minute = Integer.parseInt(cronIn[0]) > 10 ? cronIn[0] : "0"+cronIn[0] ;
            hour = Integer.parseInt(cronIn[1]) > 10 ? cronIn[1] : "0"+cronIn[1] ;
        }
        day_of_month = evaluateDayOfMonth(cronIn[2]);
        month = evaluateMonth(cronIn[3]);
        day_of_week = evaluateDayOfWeek(cronIn[4]);

        if (minute.length()==2 && hour.length()==2) {
            resultant = "At "+hour+":"+minute+day_of_month+day_of_week+month;
        }
        else {
            resultant = "At"+minute+hour+day_of_month+day_of_week+month;
        }

        return resultant;
    }

    public boolean printDetails () {
        if (cronIn[0] == null) return false;

        System.out.print("Input: ");
        for (String item : cronIn) {
            System.out.print(item + " ");
        }
        System.out.println("\nLength: "+cronIn.length);

        return true;
    }

    private void putAllValuesOfArray (String[] output, String[] input) {
        if (output.length != input.length) {
            return;
        }

        for (int i=0; i<input.length; ++i) {
            output[i] = input[i];
        }
    }

    private String evaluateMinute (String min) {
        String result = " minute ";
        String[] daysInMonth;

        if (min.isEmpty() || min.equals(" ")) {
            return "";
        }

        if (min.equals("*")) return "on every minute";

        if (min.contains("-")) {
            daysInMonth = min.split("-");
            if (daysInMonth.length != 2) return "";
            int start = Integer.parseInt(daysInMonth[0]);
            int end = Integer.parseInt(daysInMonth[1]);
            if ((start >= end) || (start>59) || (end>59)) return "";
            result = " every " + result + "from "+start+" through "+end;
            return result;
        }

        daysInMonth = min.split(",");

        if (daysInMonth.length == 1) {
            if (Integer.parseInt(min) > 0 && Integer.parseInt(min)<32)
                result = result + Integer.parseInt(min);
            else
                result = "";
        }
        else {
            int val = 0;
            for (int i=0; i<daysInMonth.length; ++i) {
                val = Integer.parseInt(daysInMonth[i]);
                if (val<0 || val>59) return "";
                if (i == daysInMonth.length-1) {
                    result = result + "\b\b and " + val;
                }
                else {
                    result = result + val+ ", ";
                }
            }
        }

        return result;
    }

    private String evaluateHour (String hour) {
        String result = " past hour ";
        String[] daysInMonth;

        if (hour.isEmpty() || hour.equals("*") || hour.equals(" ")) {
            return "";
        }

        if (hour.contains("-")) {
            daysInMonth = hour.split("-");
            if (daysInMonth.length != 2) return "";
            int start = Integer.parseInt(daysInMonth[0]);
            int end = Integer.parseInt(daysInMonth[1]);
            if ((start >= end) || (start>23) || (end>23)) return "";
            result = " past every hour from "+start+" through "+end;
            return result;
        }

        daysInMonth = hour.split(",");

        if (daysInMonth.length == 1) {
            if (Integer.parseInt(hour) > 0 && Integer.parseInt(hour)<32)
                result = result + Integer.parseInt(hour);
            else
                result = "";
        }
        else {
            int val = 0;
            for (int i=0; i<daysInMonth.length; ++i) {
                val = Integer.parseInt(daysInMonth[i]);
                if (val<0 || val>23) return "";
                if (i == daysInMonth.length-1) {
                    result = result + "\b\b and " + val;
                }
                else {
                    result = result + val+ ", ";
                }
            }
        }

        return result;
    }

    private String evaluateDayOfMonth (String dom) {
        String result = " on ";
        String[] daysInMonth;

        if (dom.isEmpty() || dom.equals("*") || dom.equals(" ")) {
            return "";
        }

        if (dom.contains("-")) {
            daysInMonth = dom.split("-");
            if (daysInMonth.length != 2) return "";
            int start = Integer.parseInt(daysInMonth[0]);
            int end = Integer.parseInt(daysInMonth[1]);
            if ((start >= end) || (start>31) || (end>31)) return "";
            result = result + "every day-of-month from "+start+" through "+end;
            return result;
        }

        daysInMonth = dom.split(",");

        result = result + "day-of-month ";

        if (daysInMonth.length == 1) {
            if (Integer.parseInt(dom) > 0 && Integer.parseInt(dom)<32)
                result = result + Integer.parseInt(dom);
            else
                result = "";
        }
        else {
            int val = 0;
            for (int i=0; i<daysInMonth.length; ++i) {
                val = Integer.parseInt(daysInMonth[i]);
                if (val<0 || val>31) return "";
                if (i == daysInMonth.length-1) {
                    result = result + "\b\b and " + val;
                }
                else {
                    result = result + val+ ", ";
                }
            }
        }

        return result;
    }

    private String evaluateMonth (String month) {
        String result = " in ";
        String[] monthsArray;

        if (month.isEmpty() || month.equals("*") || month.equals(" ")) {
            return "";
        }

        if (month.contains("-")) {
            monthsArray = month.split("-");
            if (monthsArray.length != 2) return "";
            int start = Integer.parseInt(monthsArray[0]);
            int end = Integer.parseInt(monthsArray[1]);
            if ((start >= end) || (start>12) || (end>12)) return "";
            result = result + "every month from "+months[start-1]+" through "+months[end-1];
            return result;
        }

        monthsArray = month.split(",");

        if (monthsArray.length == 1) {
            if (Integer.parseInt(month) > 0 && Integer.parseInt(month)<12)
                result = result + months[Integer.parseInt(month)-1];
            else
                result = "";
        }
        else {
            int val = 0;
            for (int i=0; i<monthsArray.length; ++i) {
                val = Integer.parseInt(monthsArray[i]);
                if (val<0 || val>12) return "";
                if (i == monthsArray.length-1) {
                    result = result + "\b\b and " + months[val-1];
                }
                else {
                    result = result + months[val-1] + ", ";
                }
            }
        }

        return result;
    }

    private String evaluateDayOfWeek (String dow) {
        String result = " on ";
        String[] daysInWeek;

        if (dow.isEmpty() || dow.equals("*") || dow.equals(" ")) {
            return "";
        }

        if (dow.contains("-")) {
            daysInWeek = dow.split("-");
            if (daysInWeek.length != 2) return "";
            int start = Integer.parseInt(daysInWeek[0]);
            int end = Integer.parseInt(daysInWeek[1]);
            if ((start >= end) || (start>6) || (end>6)) return "";
            result = result + "every day-of-week from "+week[start]+" through "+week[end];
            return result;
        }

        daysInWeek = dow.split(",");

        if (daysInWeek.length == 1) {
            if (Integer.parseInt(dow) > 0 && Integer.parseInt(dow)<8)
                result = result + week[Integer.parseInt(dow)];
            else
                result = "";
        }
        else {
            int val = 0;
            for (int i=0; i<daysInWeek.length; ++i) {
                val = Integer.parseInt(daysInWeek[i]);
                if (val<0 || val>6) return "";
                if (i == daysInWeek.length-1) {
                    result = result + "\b\b and " + week[val];
                }
                else {
                    result = result + week[val] + ", ";
                }
            }
        }

        return result;
    }
}
