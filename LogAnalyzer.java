/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    //Q3
    boolean vacant[];
    //Q5
    int[] counts;
    boolean[] occupied = new boolean[5000];
    /**
     * Q12
     */
    public LogAnalyzer(String fileName) 
    {
    this.reader = new LogfileReader(fileName);
    }

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
    System.out.println("Hr: Count");
    int hour = 0; // Initialize the hour variable
    while (hour < hourCounts.length) {
        System.out.println(hour + ": " + hourCounts[hour]);
        hour++; // Increment the hour to move to the next index
    }
    }
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Q13
     * Q14
     */
    public int numberOfAccesses() {
    int total = 0;
    // Use a for loop to iterate over the hourCounts array.
    for (int count : hourCounts) {
        total += count;
    }
    return total;
    }
    
    /**
     * Q15
     */
    public int busiestHour() {
    int busiestHour = 0;
    int maxCount = hourCounts[0];

    for (int hour = 1; hour < hourCounts.length; hour++) {
        if (hourCounts[hour] > maxCount) {
            maxCount = hourCounts[hour];
            busiestHour = hour;
        }
    }

    return busiestHour;
    }
    
    /**
     * Q16
     */
    public int quietestHour() {
    int quietestHour = 0;
    int minCount = hourCounts[0];

    for (int hour = 1; hour < hourCounts.length; hour++) {
        if (hourCounts[hour] < minCount) {
            minCount = hourCounts[hour];
            quietestHour = hour;
        }
    }

    return quietestHour;
}

/**
 * Q18
 */
public int busiestTwoHourPeriod() {
    int busiestStartHour = 0;
    int maxAccessCount = hourCounts[0] + hourCounts[1]; // Initial two-hour sum.

    for (int hour = 1; hour < hourCounts.length - 1; hour++) {
        int currentPeriodSum = hourCounts[hour] + hourCounts[hour + 1];

        if (currentPeriodSum > maxAccessCount) {
            maxAccessCount = currentPeriodSum;
            busiestStartHour = hour;
        }
    }

    return busiestStartHour;
}
}
