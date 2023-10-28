public class Time {
    //defining the hour, minute and second integers used in the Time class
    private int hour;
    private int minute;
    private int second;

    //sets default constructor for "t1" and is set to midnight
    public Time() {
        //everything is set to 0 so 00:00:00 can be altered via increments, does not take inputs.
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    //for "t2," instead sets the time defaults to the integer inputs
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /*All increments are provided in seconds, the method distributes the input value
     into hours, minutes and seconds to be applied to ANY method of Time*/
    public void increment(int increment) {
        //hour or more increment
        if (increment >= 3600) {
            //adds lowest whole integer derived from the increment and its remainders into hours, minutes and seconds
            hour = hour + ((increment - (increment % 3600)) / 3600);
            minute = minute + (((increment % 3600) - (increment % 60)) / 60);
            second = second + (increment % 60);

            //increment < hour (3600)
        } else if (increment >= 60) {
            minute = minute + ((increment - (increment % 60)) / 60);
            second = second + (increment % 60);

            //increment < minute
        } else {
            second = second + increment;
        }


        //does math to make values exceeding their cap distribute to the next higher place
        while (second >= 60) {
            //seconds -> minute + seconds
            minute = minute + 1;
            second = second - 60;
        }
        while (minute >= 60) {
            //minutes -> hour + minutes
            hour = hour + 1;
            minute = minute - 60;
        }
        while (hour >= 24) {
            //hours -> nextday + hours
            hour = hour - 24;
        }
    }

    //print method that outputs the time in 12 hour or 24 hour system in the 0:00:00 format
    public void print(boolean military) {
        String printHour;
        String printMinute;
        String printSecond;
        String am_pm;

        /*reformats minute and second output to have a leading zero if
         the integer is a single digit, applies to both military and 12 hour format*/
        if (second < 10) {
            //for seconds
            printSecond = "0" + second;
        } else {
            printSecond = "" + second;
        }
        if (minute < 10) {
            //for minutes
            printMinute = "0" + minute;
        } else {
            printMinute = "" + minute;
        }

        //formats to 12 hour if military is false
        if (!military) {
            if (hour == 0) {
                //overrides 0 and replaces with a 12 for midnight
                printHour = "12";
            } else if (hour <= 12) {
                printHour = "" + hour;
            } else {
                //reduces the hour by 12 for PM format
                printHour = "" + (hour - 12);
            }
        } else {
            //if military was true, will leave the hour untouched
            printHour = "" + hour;
        }

        if (!military) {
            if (hour < 12) {
                am_pm = " AM";
            } else {
                am_pm = " PM";
            }
        } else {
            //if military was true, will replace am_pm with a blank
            am_pm = "";
        }
        //prints all variables to screen in the standard 0:00:00 AM/PM format used on digital devices
        System.out.println(printHour + ":" + printMinute + ":" + printSecond + am_pm);
    }

    //deciphers the string input for t3
    public static Time fromString(String time) {
        //does array splitting at the ':' character
        String[] timeArray = time.split(":");
        //passes the hour characters into the first position of the array
        int hours = Integer.parseInt(timeArray[0]);
        //passes the minute characters into the second position of the array
        int minutes = Integer.parseInt(timeArray[1]);
        //passes the second characters into the third position of the array
        int seconds = Integer.parseInt(timeArray[2]);

        //returns an object 'Time' containing hours, minutes and seconds
        return new Time(hours, minutes, seconds);
    }
}