public class Main {
    public static void main(String[] args) {

        String[] arr = {"3", "*", "1,5,11", "2-3", "2-5"};

        // (Min) (Hour) (Day_of_Month) (Month) (Day_of_Week)

        CronJob con = new CronJob("3 2 * 1,4,5 1-5");
//        con.setValue(arr);
        System.out.println("----------------");
        System.out.println("Details:_");
        System.out.println("----------------");
        if (!con.printDetails()) {
            System.out.println("Enter Value First");
        }

        System.out.println("----------------");
        System.out.println("Output:_");
        System.out.println("----------------");
        System.out.println(con.evaluateCronString());
    }
}
